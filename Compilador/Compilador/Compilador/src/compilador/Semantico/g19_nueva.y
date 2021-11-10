
%token ID CADENA IF THEN ELSE ENDIF PRINT AND OR FUNC RETURN BEGIN END BREAK CTE_ULONG CTE_DOUBLE DOUBLE ULONG REPEAT TRY CATCH ASIGNACION PRE MENOR_IGUAL MAYOR_IGUAL IGUAL_IGUAL DISTINTO
%start programa

%%

programa : ID';' BEGIN conjunto_sentencias_declarativas conjunto_sentencias_ejecutables END ';'
		;

conjunto_sentencias_declarativas : sentencia_declarativa
        ;

sentencia_declarativa : tipo lista_variables ';'
        ;

lista_variables : ID
		| lista_variables ',' ID
		;	

conjunto_sentencias_ejecutables : sentencia_ejecutable
        ;

sentencia_ejecutable : BEGIN ejecutable END ';' 
        ;

ejecutable : asignacion 
        ;

asignacion : ID ASIGNACION  expresion
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
        ;

tipo : ULONG  
		| DOUBLE
		| CADENA
   	 	;


%%

private  AnalizadorLex aLexico;
private TablaSimbolos ts;

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
