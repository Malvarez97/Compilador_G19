package compilador;

import compilador.maquina_estado.MaquinaEstados;
import compilador.simbolo.TablaSimbolos;
import compilador.util.CodigoFuente;
import compilador.util.TablaPalabrasReserv;


public class AnalizadorLex {
	private CodigoFuente cod_Fuente;
	private final MaquinaEstados maquinaEstados;
	public static final short T_EOF = 0;
	public short ultimoTokenGenerado = -1;
	public boolean finalizo=false;
	public String ultimoLexemaGenerado;
	public TablaSimbolos ts;
	
	
	 public AnalizadorLex(CodigoFuente fuente, TablaSimbolos tablaS){
	        this.cod_Fuente = fuente;
			this.ts= tablaS;
	        this.maquinaEstados = new MaquinaEstados(this, fuente,tablaS, iniciarTpalabrasR());
	    }

	public TablaSimbolos getTs() {
		return ts;
	}

	public CodigoFuente getCod_Fuente() {
		return cod_Fuente;
	}

	public MaquinaEstados getMaquinaEstados() {
		return maquinaEstados;
	}

	public void setVariablesSintactico(short token, String lexema){
	        this.ultimoTokenGenerado = token;
	        this.ultimoLexemaGenerado = lexema;
	    }

	    public int getLineaActual(){
	        return maquinaEstados.getLineaActual();
	    }

	    private TablaPalabrasReserv iniciarTpalabrasR(){

	        return null;
	    }

	    public int tokengenerado(){
			maquinaEstados.reiniciar();
			while (!maquinaEstados.esEstadoFinal()){
	            if (cod_Fuente.eof()) {
					maquinaEstados.cambiarEOF();
					finalizo = true;
				}
	            else {
	                maquinaEstados.cambiar(cod_Fuente.simActual());
	                cod_Fuente.adelanta();
	            }
	        }
	        return ultimoTokenGenerado;
	    }


}
