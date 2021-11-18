%{
import compilador.AnalizadorLex;
import compilador.Compilador;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;
%}

%token ID CADENA UNTIL IF THEN ELSE ENDIF PRINT AND OR FUNC RETURN BEGIN END BREAK CTE_ULONG CTE_DOUBLE DOUBLE ULONG REPEAT TRY CATCH ASIGNACION PRE MENOR_IGUAL MAYOR_IGUAL IGUAL_IGUAL DISTINTO
%start programa

%%

<<<<<<< HEAD
programa : ID {ambitoActual=$1.sval;}
 BEGIN conjunto_sentencias_declarativas sentencia_ejecutable END ';'{t.agregarUsoVariablesTS($1.sval,"nombre_programa");}
=======
programa : ID BEGIN conjunto_sentencias_declarativas sentencia_ejecutable END ';'
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
		;

conjunto_sentencias_declarativas : sentencia_declarativa
		| conjunto_sentencias_declarativas sentencia_declarativa
		;

sentencia_ejecutable :  ejecutable
		| BEGIN conjunto_sentencias_ejecutables END ';'
		| TRY ejecutable CATCH BEGIN ejecutable END ';'
		| error_sentencias_ejecutables
		;

ejecutable : asignacion ';'
		| sentencia_control_repeat ';'
		| sentencia_salida
		| sentencia_if
		;

error_sentencias_ejecutables : TRY ejecutable BEGIN ejecutable END ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
		| TRY ejecutable CATCH ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
		;

sentencia_declarativa : tipo lista_variables ';'
<<<<<<< HEAD
		| tipo FUNC ID {ambitoActual=ambitoActual+"."+$3.sval;}
		'(' parametro ')' conjunto_sentencias_declarativas BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'{
                                                                                                                                                                    //Cambiara el ambito de las variables en caso de ser necesario
                                                                                                                                                                    StringBuilder reverse = new StringBuilder( ambitoActual);
                                                                                                                                                                    StringBuilder aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                                    reverse = new StringBuilder(aux.substring(aux.lastIndexOf(".")+1, aux.length()));
                                                                                                                                                                    aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                                    ambitoActual=aux.toString();
                                                                                                                                                                    t.agregarUsoVariablesTS($3.sval,"nombre_funcion");
		                                                                                                                                                         }
		| FUNC tipo ID {ambitoActual=ambitoActual+"."+$3.sval;}
		 '(' parametro ')' conjunto_sentencias_declarativas BEGIN PRE':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'{            StringBuilder reverse = new StringBuilder( ambitoActual);
                                                                                                                                                        StringBuilder aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                        reverse = new StringBuilder(aux.substring(aux.lastIndexOf(".")+1, aux.length()));
                                                                                                                                                        aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                        ambitoActual=aux.toString();
                                                                                                                                                        t.agregarUsoVariablesTS($3.sval,"nombre_funcion");
		                                                                                                                                                   }
		| FUNC ID ',' lista_variables';'{t.agregarUsoVariablesTS($2.sval,"variable");}
		;

=======
		| tipo FUNC ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'
		| FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN PRE':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'
		| FUNC ID ',' lista_variables';'
		| error_sentencias_declarativas
		;

error_sentencias_declarativas : tipo ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
		| FUNC ',' lista_variables ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
		| FUNC tipo ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN PRE':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
		| tipo FUNC ID '('  ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
		| FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
		;
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a



conjunto_sentencias_ejecutables_funcion : ejecutable
		| conjunto_sentencias_ejecutables_funcion ejecutable
		;

<<<<<<< HEAD
lista_variables : ID {t.agregarUsoVariablesTS($1.sval,"variable");
                       ts.cambiarNombre($1.sval);}
		| lista_variables ',' ID { t.agregarUsoVariablesTS($3.sval,"variable");
		                           ts.cambiarNombre($3.sval);}
=======
lista_variables : ID
		| lista_variables ',' ID
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
		;

conjunto_sentencias_ejecutables : ejecutable
		| conjunto_sentencias_ejecutables ejecutable
		;

sentencia_if : IF '(' condicion ')' rama_then_sola ENDIF
   		| IF '(' condicion ')' rama_then_sola rama_else ENDIF
   		| error_sentencia_if
		;

error_sentencia_if : IF '(' condicion rama_then_sola ENDIF {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
		| IF '(' condicion ')' rama_else ENDIF {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
		;

rama_then_sola : THEN sentencia_ejecutable
		;

rama_else : ELSE sentencia_ejecutable
		;


sentencia_control_repeat : REPEAT '(' asignacion ';' condicion_repeat ';' factor ')' sentencia_ejecutable
		| REPEAT '(' asignacion ';' condicion_repeat ';' factor ')' sentencia_ejecutable BREAK
		| error_repeat
		;

error_repeat : REPEAT '('  condicion_repeat ';' factor ')' sentencia_ejecutable BREAK {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
		;

operador : AND
		| OR
		;		

comparador :  '<' 
		|'>' 
		| MENOR_IGUAL 
		| MAYOR_IGUAL 
		| DISTINTO 
		| IGUAL_IGUAL 
		;

sentencia_salida : PRINT '(' CADENA ')'
		| error_sentencia_salida
		;

error_sentencia_salida : PRINT CADENA ')' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
		| PRINT '(' ')' {Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
		;

tipo : ULONG  
		| DOUBLE
		| CADENA
   	 	;

condicion_repeat : ID comparador expresion
		;	

asignacion : ID ASIGNACION expresion
		;

condicion : condicion comparador expresion 
		| condicion operador expresion
		| expresion comparador expresion 		 
		;

expresion : expresion '+' termino 
	 	| expresion '-' termino 
		| termino 
	 	;

termino : termino '*' factor 
        | termino '/' factor
        | factor 
        ;

factor : ID  
        | CTE_DOUBLE 
        | CTE_ULONG
        | '-' CTE_DOUBLE{ ts.cambiarNegativo($2.sval,aLexico); }
        ;

parametro :tipo ID
		;

%%
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";
<<<<<<< HEAD
Terceto t;
public  static String ambitoActual;
=======

>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a

public Parser (AnalizadorLex aLexico, TablaSimbolos ts) {
this.ts= ts;
this.aLexico=aLexico;
}

public int yylex() {
        int token = aLexico.tokengenerado();
        System.out.println( token+"->"+ Compilador.traductor(token));
        yylval = new ParserVal(aLexico.ultimoLexemaGenerado);
        return token;
    }

private void yyerror (String mensajeerror) {
   Notificacion.addError(aLexico.getLineaActual(), mensajeerror);
    }