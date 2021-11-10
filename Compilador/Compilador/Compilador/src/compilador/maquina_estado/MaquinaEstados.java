package compilador.maquina_estado;


import compilador.*;
import compilador.Semantico.Parser;
import compilador.asemanticas.*;
import compilador.simbolo.TablaSimbolos;
import compilador.util.CodigoFuente;
import compilador.util.TablaPalabrasReserv;

public class MaquinaEstados {
		public static final String ANSI_RESET = "\u001B[0m";
		public static final String ANSI_GREEN = "\u001B[32m";

		private final AristaEstado[][] maquinaEstados = new AristaEstado[Contenedor_Estados.TOTAL_ESTADOS][Contenedor_Inputs.TOTAL_INPUTS]; //[filas][columnas].
	    private final AnalizadorLex aLexico; //Permite agregar tokens a medida que se generan.
	    private final CuentaSaltoLinea cuentaSaltoLinea = new CuentaSaltoLinea(); //Permite saber la linea actual
	    private int estadoActual = Contenedor_Estados.INICIAL;


	    public MaquinaEstados(AnalizadorLex aLexico, CodigoFuente cFuente, TablaSimbolos tablaS,
							  TablaPalabrasReserv tablaPR) {
	        this.aLexico = aLexico;

	        inicMaquinaEstados(cFuente, tablaS, tablaPR);
	    }
		public AnalizadorLex getaLexico(){
			return aLexico;
		}
	    private void inicMaquinaEstados(CodigoFuente cFuente, TablaSimbolos tablaS, TablaPalabrasReserv tablaPR) { /// Inicializo Acciones semanticas

			/** AS0  Inic String vacio*/
			InicStringVacio inicStringVacio = new InicStringVacio();
			/** AS1 ConcChar*/
			ConcChar concatenaChar = new ConcChar(cFuente);
			/** As2 TruncId */
			TruncId truncaId = new TruncId(aLexico);
			/** As3 Retrocede Fuente */
			Retrocede_Fuente retrocedeFuente = new Retrocede_Fuente(cFuente);
			/** As4 GeneraTs */
			GeneraTokenTSimbolos generaTokenId = new GeneraTokenTSimbolos(this, tablaS, Parser.ID);
			/**  As5 GeneraPr */
			GeneraTPr generaTokenPR = new GeneraTPr(this);
			/**  As6 GeneraTp -> inicializada en cada estado */
			GeneraTokenLiteral tokenLiteral = new GeneraTokenLiteral(this, cFuente);

			/**  As7 IgnoraC*/
			IgnoraC consumeChar = new IgnoraC();

			/**  As8 GeneraUL*/
			GeneraULONG generatokenUl = new GeneraULONG(this, tablaS,Parser.ULONG);
			/**  As9 ParseDouble :-> */

			/**  As10 GeneraToukenDouble */
			GeneraDouble generaDouble = new GeneraDouble(this, tablaS, Parser.DOUBLE);
			/**  As11 incrementa salto*/

			/** As12 Chequeo tipo */
			ChequeoTipo chequeo= new ChequeoTipo(this,tablaS,cFuente);
			/** Inicializacion de errores generica y errores part */
			NotError notificaELexico = new NotError("Simbolo no reconocido", aLexico, cFuente, true);
			GeneraTokenParticular generarEOF = new GeneraTokenParticular(this, AnalizadorLex.T_EOF);
	        GeneraTokenTSimbolos generaTokenCadena = new GeneraTokenTSimbolos(this, tablaS,Parser.CADENA);
			GeneraTokenParticular generaAsignacion= new GeneraTokenParticular(this,Parser.ASIGNACION);
			GeneraTokenParticular generaAnd =new GeneraTokenParticular(this,Parser.AND);
			GeneraTokenParticular generaOR =new GeneraTokenParticular(this,Parser.OR);

			//  Inicializacion de estados y transiciones

			inicTransiciones(Contenedor_Estados.INICIAL, Contenedor_Estados.INICIAL ,notificaELexico); // Error por defecto del lexico
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.DESCARTABLE] = new AristaEstado(Contenedor_Estados.INICIAL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.SALTO_LINEA] = new AristaEstado(Contenedor_Estados.INICIAL,
	            cuentaSaltoLinea); //Salto de linea.
	        inicCaminoLiterales(cFuente);
			inicCaminoIdsyPrs(inicStringVacio, concatenaChar,chequeo);
	        inicCaminoComentario(cFuente, retrocedeFuente, notificaELexico);
	        inicCaminoComparadores(cFuente, retrocedeFuente, consumeChar);
	        inicCaminoCtesNum(inicStringVacio, concatenaChar, retrocedeFuente, generatokenUl, consumeChar,
	            generaDouble);
	        inicCaminoCadenas(cFuente, inicStringVacio, concatenaChar, generaTokenCadena);
			inicCaminoOr(cFuente,consumeChar,retrocedeFuente,generaOR);
			inicCaminoop(cFuente,consumeChar,retrocedeFuente,generaAsignacion);
			inicCaminoAnd(cFuente,consumeChar,retrocedeFuente,generaAnd);

	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generarEOF);

	    }


	    public boolean esEstadoFinal() {
	        return estadoActual == Contenedor_Estados.FINAL;
	    } // retorna true cuando la maquina termine de leer

	    public void reiniciar() {
	        estadoActual = 0;
	    }// reinicia la maquina

	    public void setVariablesSintactico(short tokenes, String lexema) {
	        aLexico.setVariablesSintactico(tokenes, lexema);
			} // Retorna eñ lexema  la linea de codigo actual


	    public int getLineaActual() {
	        return cuentaSaltoLinea.getCantLineas();
	    } // retorna la linea de codigo en la cual estoy parado


	    public void cambiar(char input) { // simula las transiciones de estados
	        int codigoInput = Contenedor_Inputs.charToInt(input);
			/*System.out.println(ANSI_GREEN+"codigo leido "+input+ANSI_RESET );*/

			AristaEstado AristaEstado = maquinaEstados[estadoActual][codigoInput];
			/*System.out.print(estadoActual);*/
	        estadoActual = AristaEstado.getSigEstado();
			/*System.out.println("-->"+estadoActual);*/
	        AristaEstado.ejecutar();
	    }


	    public void cambiarEOF() { //
			AristaEstado TransicionEstados = maquinaEstados[estadoActual][Contenedor_Inputs.EOF];
	        TransicionEstados.ejecutar();
	        aLexico.setVariablesSintactico(AnalizadorLex.T_EOF, ""); //Genera el token asociado al EOF ('0').
	        estadoActual = Contenedor_Estados.FINAL; //Finalizo ejecucion
	    }

	    // Inicializa las trancisiones de todos los estados por defecto que tiene esa fila de la matriz
	    private void inicTransiciones(int estadoOrigen, int estadoDestino, AccionSemantica... accionesSemanticas) {
	        for (int input = 0; input < Contenedor_Inputs.TOTAL_INPUTS; input++)
	            maquinaEstados[estadoOrigen][input] = new AristaEstado(estadoDestino, accionesSemanticas);
	    }

	// inicializo las transiciones asociadas de los identificadores
	    private void inicCaminoLiterales(CodigoFuente cFuente) {
			// inicializa el resto ..
	        GeneraTokenLiteral generaTokenL= new GeneraTokenLiteral(this, cFuente);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.SUMA] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.GUION] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.MULTIPL] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.PARENT_A] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.PARENT_C] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.COMA] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.PUNTO_COMA] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.DOSP] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenL);
	    }

		private void inicCaminoIdsyPrs (AccionSemantica inicStringVacio,AccionSemantica concatenaChar, AccionSemantica chequeoTipo){


			//Estado 0
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.LETRA_MAYUS] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR, inicStringVacio,concatenaChar);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.LETRA_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR, inicStringVacio,concatenaChar);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.U_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR, inicStringVacio,concatenaChar);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.LETRA_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR, inicStringVacio,concatenaChar);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.E_EXP] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR, inicStringVacio,concatenaChar);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.GUION_B] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,inicStringVacio,concatenaChar);

			// Estado 1
			inicTransiciones(Contenedor_Estados.DETECCION_IDyPR,Contenedor_Estados.FINAL,chequeoTipo);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.LETRA_MAYUS] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.LETRA_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.GUION_B] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.E_EXP] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.L_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.U_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
			maquinaEstados[Contenedor_Estados.DETECCION_IDyPR][Contenedor_Inputs.LETRA_MINUSC] = new AristaEstado(Contenedor_Estados.DETECCION_IDyPR,concatenaChar);
		}


	    private void inicCaminoComentario(CodigoFuente cFuente, AccionSemantica retrocedeFuente,
	                                      AccionSemantica notificaErrorLexico) {

			/// Estado 0
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.DIV] = new AristaEstado(Contenedor_Estados.COMENTARIO);//inicio transicion de comentario o division
			
	        // Estado 3 , posible division o comentario
			CodigoFuente fuente = new CodigoFuente("/");
			GeneraTokenLiteral token = new GeneraTokenLiteral(this,fuente);
			inicTransiciones(Contenedor_Estados.COMENTARIO, Contenedor_Estados.FINAL, retrocedeFuente,token);// si viene otra cosa genero el /

			maquinaEstados[Contenedor_Estados.COMENTARIO][Contenedor_Inputs.DIV] = new AristaEstado(Contenedor_Estados.COMENTARIO2);


			//Estado 4

			inicTransiciones(Contenedor_Estados.COMENTARIO2, Contenedor_Estados.COMENTARIO2); // cuerpo del comentario, no realizo ninguna accion

			maquinaEstados[Contenedor_Estados.COMENTARIO2][Contenedor_Inputs.DIV]= new AristaEstado(Contenedor_Estados.COMENTARIO3);// posible salida del comentario

	        NotError errorEOF = new NotError("Simbolo no reconocido por el compilador", aLexico, cFuente, true);
	        maquinaEstados[Contenedor_Estados.COMENTARIO][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, errorEOF);
			// si tengo un comentario y EOF retorno termino y retorno el error

			// Estado 5 
			inicTransiciones(Contenedor_Estados.COMENTARIO3, Contenedor_Estados.COMENTARIO2); // sigue siendo cuerpo del comentario
	        maquinaEstados[Contenedor_Estados.COMENTARIO3][Contenedor_Inputs.DIV]= new AristaEstado(Contenedor_Estados.INICIAL); // fin del comentario

			NotWarning wareof =new NotWarning("se termino la ejecucion dentro de un comentario",aLexico);
			maquinaEstados[Contenedor_Estados.COMENTARIO3][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL);
			
	    }


	    private void inicCaminoComparadores(CodigoFuente cFuente, AccionSemantica retrocedeFuente,
	                                        AccionSemantica consumeChar) {
			GeneraTokenParticular tokenparticular;
			GeneraTokenParticular generaDistinto =new GeneraTokenParticular(this,Parser.DISTINTO);
			GeneraTokenParticular generaIgual= new GeneraTokenParticular(this,Parser.IGUAL_IGUAL);
			GeneraTokenParticular generaMenorIgual = new GeneraTokenParticular(this, Parser.MENOR_IGUAL);
			GeneraTokenParticular generaMayorIgual = new GeneraTokenParticular(this,Parser.MAYOR_IGUAL);
			// Estado 0

			// Token '<':
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.MENOR] = new AristaEstado(Contenedor_Estados.DISTYMENOR);// para mi esta mal

			// Token '>' o '='
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.MAYOR] = new AristaEstado(Contenedor_Estados.COMPYMAYOR);
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.IGUAL] = new AristaEstado(Contenedor_Estados.COMPYMAYOR);


			// Estado 6
			GeneraTokenParticular generaTokenPR = new GeneraTokenParticular(this,(short) '>');
			inicTransiciones(Contenedor_Estados.COMPYMAYOR, Contenedor_Estados.FINAL,retrocedeFuente,generaMayorIgual);

			maquinaEstados[Contenedor_Estados.COMPYMAYOR][Contenedor_Inputs.IGUAL] = new AristaEstado(Contenedor_Estados.FINAL,consumeChar,generaIgual);
			NotError errorEOF = new NotError("Simbolo no reconocido por el compilador", aLexico, cFuente, true);
			maquinaEstados[Contenedor_Estados.COMPYMAYOR][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, errorEOF);


			//Estado 7
			GeneraTokenParticular token = new GeneraTokenParticular(this,(short)'<'); /** aca hay que poner la variable que se quiera de ese simbolo*/
			inicTransiciones(Contenedor_Estados.DISTYMENOR, Contenedor_Estados.FINAL, retrocedeFuente,token);

			maquinaEstados[Contenedor_Estados.DISTYMENOR][Contenedor_Inputs.IGUAL] = new AristaEstado(Contenedor_Estados.FINAL, consumeChar,generaMenorIgual);
			maquinaEstados[Contenedor_Estados.DISTYMENOR][Contenedor_Inputs.MAYOR] = new AristaEstado(Contenedor_Estados.FINAL, consumeChar,generaDistinto);

			maquinaEstados[Contenedor_Estados.DISTYMENOR][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.COMPYMAYOR, errorEOF);

		}


	    private void inicCaminoCtesNum(AccionSemantica inicStringVacio, AccionSemantica concatenaChar,
	                                   AccionSemantica retrocedeFuente, AccionSemantica generaTokenULONG,
	                                   AccionSemantica consumeChar, AccionSemantica generaTokenDouble) {



			// Inicializo los errores
	        NotWarning faltaSufijo = new NotWarning("Falto el sufijo 'ul' al final del numero, el numero fue tomado como ULONG ", aLexico);
	        NotWarning sufijoInvalido = new NotWarning("se encontro un sufijo que no es valido como ULONG, este se tomara como ULONG", aLexico);
	        NotWarning sinExponente = new NotWarning("no hay exponente, este sera tomado como 0 por defecto", aLexico);

	        //Estado 0
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_1, inicStringVacio,
	            concatenaChar);
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.PUNTO] = new AristaEstado(Contenedor_Estados.D_U_4, inicStringVacio,
	            concatenaChar);

	        // Estado 12
	        //Con las salidas no reconocidas el compilador lo toma como ULONG y genera un warning
	        inicTransiciones(Contenedor_Estados.D_U_1, Contenedor_Estados.FINAL, generaTokenDouble,retrocedeFuente);
	        //si hay un salto de linea, genero Warning por falta
	        maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.SALTO_LINEA] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG,
	            cuentaSaltoLinea, faltaSufijo);

	        //Digitos.
	        maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_1,
	            concatenaChar);

	        //Punto ('.'). Salto a deteccion de parte decimal de doubles.
	        maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.PUNTO] = new AristaEstado(Contenedor_Estados.D_U_3,
	            concatenaChar);

			// U : empieza camino correcto ULONG
			maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.U_MINUSC] = new AristaEstado(Contenedor_Estados.D_U_2,consumeChar);

			// OTRO
			maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL,
					concatenaChar);


	        //EOF. Crea un UINT pero genera un warning por falta de sufijo.
	        maquinaEstados[Contenedor_Estados.D_U_1][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG,
	            faltaSufijo);

	        // Estado 13

	        // Crea un ULONG  por defecto pero genera un warning por falta de sufijo.
	        inicTransiciones(Contenedor_Estados.D_U_2, Contenedor_Estados.FINAL, retrocedeFuente, generaTokenULONG, sufijoInvalido);
	        //Salto de linea. Crea un UINT pero genera un warning por falta de sufijo.
	        maquinaEstados[Contenedor_Estados.D_U_2][Contenedor_Inputs.SALTO_LINEA] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG,
	            cuentaSaltoLinea, sufijoInvalido);
	        //Letra 'l' minuscula.
	        maquinaEstados[Contenedor_Estados.D_U_2][Contenedor_Inputs.L_MINUSC] = new AristaEstado(Contenedor_Estados.D_U_6,consumeChar); // no hace nada ya que la parte ul del sufijo solo me importa para clasificar

			//Cualquier otra cosa, retorno numero como ULONG y pongo aviso el warning
	        maquinaEstados[Contenedor_Estados.D_U_2][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL,generaTokenULONG,sufijoInvalido);

	        //EOF. Crea un UINT pero genera un warning por falta de sufijo.
	        maquinaEstados[Contenedor_Estados.D_U_2][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG, sufijoInvalido);

	        // Estado 17
			inicTransiciones(Contenedor_Estados.D_U_6, Contenedor_Estados.FINAL, retrocedeFuente,generaTokenULONG);

			maquinaEstados[Contenedor_Estados.D_U_6][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG, sufijoInvalido);
			maquinaEstados[Contenedor_Estados.D_U_6][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenULONG, sufijoInvalido);


			// Estado 14
			inicTransiciones(Contenedor_Estados.D_U_3, Contenedor_Estados.FINAL, retrocedeFuente, generaTokenDouble);
			maquinaEstados[Contenedor_Estados.D_U_3][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_3,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_3][Contenedor_Inputs.E_EXP] = new AristaEstado(Contenedor_Estados.D_U_4,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_3][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_3,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_3][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenDouble,sinExponente);
			maquinaEstados[Contenedor_Estados.D_U_3][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL,retrocedeFuente,generaTokenDouble,sinExponente);



			// Estado 15
			inicTransiciones(Contenedor_Estados.D_U_4, Contenedor_Estados.FINAL, retrocedeFuente, generaTokenDouble);
			maquinaEstados[Contenedor_Estados.D_U_4][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_4,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_4][Contenedor_Inputs.SUMA] = new AristaEstado(Contenedor_Estados.D_U_5,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_4][Contenedor_Inputs.GUION] = new AristaEstado(Contenedor_Estados.D_U_5,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_4][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL,retrocedeFuente,generaTokenDouble);
			maquinaEstados[Contenedor_Estados.D_U_4][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenDouble,sinExponente);

			// Estado 16
			inicTransiciones(Contenedor_Estados.D_U_5, Contenedor_Estados.FINAL, retrocedeFuente, generaTokenDouble);
			maquinaEstados[Contenedor_Estados.D_U_5][Contenedor_Inputs.DIGITO] = new AristaEstado(Contenedor_Estados.D_U_5,concatenaChar);
			maquinaEstados[Contenedor_Estados.D_U_5][Contenedor_Inputs.OTRO] = new AristaEstado(Contenedor_Estados.FINAL, generaTokenDouble,retrocedeFuente, sinExponente);

	    }


	    private void inicCaminoCadenas(CodigoFuente cFuente, AccionSemantica inicStringVacio, AccionSemantica concatenaChar,
	                                   AccionSemantica generaTokenCadena) {
	        // Estado 0
	        maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.PORCENTAJE] = new AristaEstado(Contenedor_Estados.UNALINEA, inicStringVacio,
	            concatenaChar);

	        // Estado 11
			inicTransiciones(Contenedor_Estados.UNALINEA,Contenedor_Estados.UNALINEA, concatenaChar);
			NotError saltoInvalido = new NotError("En esta seccion del codigo no se puede realizar un salto",aLexico,cFuente,false);
			maquinaEstados[Contenedor_Estados.UNALINEA][Contenedor_Inputs.SALTO_LINEA] = new AristaEstado(Contenedor_Estados.UNALINEA,saltoInvalido,cuentaSaltoLinea);
			maquinaEstados[Contenedor_Estados.UNALINEA][Contenedor_Inputs.PORCENTAJE] = new AristaEstado(Contenedor_Estados.FINAL);


	        //finaliza la cadena con porcentaje
	        maquinaEstados[Contenedor_Estados.UNALINEA][Contenedor_Inputs.PORCENTAJE] = new AristaEstado(Contenedor_Estados.FINAL, concatenaChar,generaTokenCadena);
	        //Hay un salto de linea.
	        //EOF. Queda la cadena abierta, por lo que hay que notificar un error.
	        NotError errorCadenaAbierta = new NotError("Se llego al EOF y la cadena quedo abierta", aLexico,
	            cFuente, false);
	        maquinaEstados[Contenedor_Estados.UNALINEA][Contenedor_Inputs.EOF] = new AristaEstado(Contenedor_Estados.FINAL, errorCadenaAbierta);
	    }

		private void inicCaminoOr(CodigoFuente cfuente,AccionSemantica consumeChar,Retrocede_Fuente retrocedeFuente,GeneraTokenParticular generatokenliteral){

			//Estado 0
			maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.OR] = new AristaEstado(Contenedor_Estados.OR);


			// Estado 9
			// si viene una sola aviso con un Warning pero lo tomo como un or
			NotError error = new NotError("error en la palabra Or, tiene un solo |",aLexico,cfuente,true);
			inicTransiciones(Contenedor_Estados.OR, Contenedor_Estados.FINAL,retrocedeFuente,error);

			maquinaEstados[Contenedor_Estados.OR][Contenedor_Inputs.OR] = new AristaEstado(Contenedor_Estados.FINAL,generatokenliteral,consumeChar);




		}
	private void inicCaminoAnd(CodigoFuente cfuente,AccionSemantica consumeChar,Retrocede_Fuente retrocedeFuente,GeneraTokenParticular generatokenp){


		//Estado 0
		maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.AND] = new AristaEstado(Contenedor_Estados.AND);


		// Estado 8

		NotError error = new NotError("error en la palabra And, tiene un solo &",aLexico,cfuente,true);
		inicTransiciones(Contenedor_Estados.AND, Contenedor_Estados.FINAL,retrocedeFuente,error);
		maquinaEstados[Contenedor_Estados.AND][Contenedor_Inputs.AND] = new AristaEstado(Contenedor_Estados.FINAL,generatokenp,consumeChar);
	}

	private void inicCaminoop(CodigoFuente cfuente, AccionSemantica consumeChar,Retrocede_Fuente retrocedeFuente,GeneraTokenParticular generatoken){

		//Estado 0
		   maquinaEstados[Contenedor_Estados.INICIAL][Contenedor_Inputs.DOSP] = new AristaEstado(Contenedor_Estados.ASIGNACION);


		   //Estado 10
			NotError error = new NotError("error en la asginacion, ",aLexico,cfuente,true);
			GeneraTokenLiteral generaTokenL= new GeneraTokenLiteral(this,cfuente);
			inicTransiciones(Contenedor_Estados.ASIGNACION, Contenedor_Estados.FINAL,retrocedeFuente,generaTokenL);
			maquinaEstados[Contenedor_Estados.ASIGNACION][Contenedor_Inputs.IGUAL] = new AristaEstado(Contenedor_Estados.FINAL,consumeChar,generatoken);


	}

}
