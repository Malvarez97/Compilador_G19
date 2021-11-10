#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "g19_gramatica.y"
import compilador.AnalizadorLex;
import compilador.Compilador;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;
#line 11 "y.tab.c"
#define ID 257
#define CADENA 258
#define UNTIL 259
#define IF 260
#define THEN 261
#define ELSE 262
#define ENDIF 263
#define PRINT 264
#define AND 265
#define OR 266
#define FUNC 267
#define RETURN 268
#define BEGIN 269
#define END 270
#define BREAK 271
#define CTE_ULONG 272
#define CTE_DOUBLE 273
#define DOUBLE 274
#define ULONG 275
#define REPEAT 276
#define TRY 277
#define CATCH 278
#define ASIGNACION 279
#define PRE 280
#define MENOR_IGUAL 281
#define MAYOR_IGUAL 282
#define IGUAL_IGUAL 283
#define DISTINTO 284
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    1,    2,    2,    3,    3,    3,    3,
    3,   12,   12,   12,   12,   12,    8,    9,    9,    6,
    6,    4,    4,    4,    4,   15,   15,   15,   15,   14,
   14,   14,   18,   18,   16,   17,   13,   13,   13,   20,
   20,   20,   24,   24,   25,   25,   26,   26,   26,   26,
   26,   26,   21,   21,   27,   27,    5,    5,    5,   22,
   19,   11,   11,   11,   10,   10,   10,   28,   28,   28,
   23,   23,   23,   23,    7,    7,   29,
};
short yylen[] = {                                         2,
    6,    1,    2,    2,    1,    2,    3,   15,   21,    5,
    1,    2,    4,   20,   13,   20,    3,    1,    2,    1,
    3,    4,    7,    1,    1,    3,    6,    4,    3,    6,
    7,    1,    5,    6,    2,    2,    1,    1,    1,    7,
   10,    1,    7,    9,    1,    1,    1,    1,    1,    1,
    1,    1,    5,    1,    3,    3,    1,    1,    1,    4,
    4,    3,    3,    3,    3,    3,    1,    3,    3,    1,
    1,    1,    1,    2,    2,    1,    1,
};
short yydefred[] = {                                      0,
    0,    0,    0,   59,    0,    0,   58,   57,    0,    2,
    0,   11,    0,    0,    0,    4,    0,    0,    0,    0,
    0,    0,    0,    3,    5,    0,   24,   25,   32,   37,
   38,   39,   42,   54,   20,    0,   12,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    6,
    0,    0,    7,    0,    0,   13,    0,   71,   73,   72,
    0,    0,   70,    0,    0,    0,   55,    0,   56,    0,
    0,    0,    0,    1,   26,    0,   21,   10,    0,    0,
    0,   76,   74,   61,    0,    0,    0,    0,   49,   50,
   52,   51,   47,   48,    0,    0,   45,   46,    0,    0,
    0,    0,    0,   22,    0,    0,    0,    0,   28,    0,
    0,    0,    0,   75,    0,    0,    0,   68,   69,    0,
   35,    0,    0,    0,   33,    0,    0,   53,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   36,   30,
    0,   34,    0,    0,    0,   27,    0,    0,    0,   17,
    0,    0,   31,   60,    0,   40,   23,    0,   18,    0,
    0,    0,    0,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   15,    0,    0,
    0,    0,    0,    0,    0,    0,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   14,
    0,   16,    9,
};
short yydgoto[] = {                                       2,
    9,   23,   10,   25,  112,   38,   81,  113,  158,   65,
   66,   12,   26,   27,   28,  100,  124,   29,   30,   31,
   32,  106,   63,   33,  101,  102,   34,   64,   82,
};
short yysindex[] = {                                   -229,
 -237,    0,   72,    0,    6,  -20,    0,    0,  -72,    0,
  -44,    0,   20, -214, -204,    0, -202,   58,  -37, -126,
   75, -126, -112,    0,    0, -158,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -134,    0,   12, -214,   50,
  100,  -28,  -28,  113,   -7, -108,  -87, -249,  115,    0,
  117,  149,    0,  -64,   80,    0,  -40,    0,    0,    0,
  -62,    9,    0,  119,   45,   -5,    0,  160,    0,  150,
  -38, -126,  -52,    0,    0,  -36,    0,    0, -200,  -30,
  175,    0,    0,    0,  -28,  -28,  -28,  -28,    0,    0,
    0,    0,    0,    0,  -28,  -70,    0,    0, -115,  -13,
  -28,  -28,  169,    0,   27,   63,  -12, -126,    0, -200,
  216, -214,  -10,    0, -200,  119,  119,    0,    0,   61,
    0,  -70,  -95,    2,    0,   61,   61,    0,  -28,  -28,
  228,  211,    4,   25, -200,   85,   -9,   26,    0,    0,
   30,    0,   57,  234,  -70,    0,  239, -126,   52,    0,
  241,  -47,    0,    0,  -70,    0,    0, -198,    0, -126,
  291,  274,  293,   66,  294,    0, -184,  -28,  301,  -28,
  285,  -28,  305,   21,  -28,   35,    0,  130,  -28,  289,
   41,  290,   81,  136,   82,  295,   84,    0,   83,  315,
   88,  317,  299,  -28,  319,  -28,    0,  155,  -28,  167,
  302,  297,  303,   90,  304,   94,  306,   96,  308,    0,
  309,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -41,    0,    0,    0,    0,    0, -143,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  328,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -35,  -29,    0,    0,    1,
    0,    0,    0,    0,    0,    7,   31,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -127,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,    0,  361,   14,   42,   -1,  296,  -75,  213,  -16,
  -15,    0,   24,    0,    0,  272,  251,    0,  329,    0,
    0,    0,  -65,    0,    0,  -56,    0,  129,    0,
};
#define YYTABLESIZE 376
short yytable[] = {                                      67,
   79,   67,   45,   67,  110,   65,  109,   65,   95,   65,
  163,   66,   40,   66,   37,   66,   61,   67,   67,   72,
   67,  118,  119,   65,   65,   62,   65,    1,   73,   66,
   66,    3,   66,   69,  134,   99,   50,   55,   16,  138,
  131,   64,   35,   46,   11,   48,   15,   63,  129,   14,
   11,   85,   41,   86,   93,   54,   94,    4,   17,  149,
   64,  180,   64,   39,  144,   19,   63,   84,   63,  165,
   53,   62,   17,    7,    8,  182,   42,   21,  120,   19,
   93,  186,   94,  173,  126,  127,   93,   85,   94,   86,
   62,   21,   62,   54,   93,  107,   94,   43,   80,   85,
   93,   86,   94,   85,   93,   86,   94,   61,   56,  121,
  136,   51,  143,   29,   47,  154,   29,   80,   29,   29,
   29,  130,   52,   54,   29,   29,   29,   29,   54,   44,
   17,  133,   29,   29,   29,  139,   44,   19,   78,   57,
   44,   44,   44,  150,   17,   96,  122,   18,   44,   21,
   44,   19,  174,   67,  176,  178,   20,   49,  156,  181,
   87,   70,  184,   21,   22,   88,  122,  140,  164,   17,
  183,  159,   85,   74,   86,   75,  189,  198,   85,  200,
   86,  166,  202,  159,   17,    4,   17,   18,   76,   18,
  166,   19,   77,   19,    5,  201,   20,   85,   20,   86,
  103,    7,    8,   21,   22,   21,   22,  203,  104,   85,
   83,   86,   35,  116,  117,  115,  108,    4,  105,   67,
   44,    4,   36,   67,   67,   65,  114,  128,   58,   65,
   65,   66,  162,    7,    8,   66,   66,    7,    8,   67,
   67,   67,   67,   59,   60,   65,   65,   65,   65,  125,
   68,   66,   66,   66,   66,   96,  135,  132,  137,   97,
   98,   64,   13,    4,  142,   64,   64,   63,  145,  146,
  151,   63,   63,  147,  155,   89,   90,   91,   92,    7,
    8,   64,   64,   64,   64,   97,   98,   63,   63,   63,
   63,   62,  153,  148,  152,   62,   62,  157,  161,   97,
   98,   89,   90,   91,   92,   97,   98,   89,   90,   91,
   92,   62,   62,   62,   62,   89,   90,   91,   92,   58,
  160,   89,   90,   91,   92,   89,   90,   91,   92,    4,
  168,  169,  170,  172,   59,   60,  171,  205,    5,   85,
  175,   86,    6,  177,  179,    7,    8,  185,  187,  190,
  188,  192,  193,  191,  194,  195,  196,  197,  199,  207,
  204,  206,  208,  209,  210,  211,  212,  213,   77,   24,
  123,  111,  167,  141,    0,   71,
};
short yycheck[] = {                                      41,
   41,   43,   40,   45,   41,   41,   59,   43,   65,   45,
   58,   41,   14,   43,   59,   45,   45,   59,   60,  269,
   62,   87,   88,   59,   60,   42,   62,  257,  278,   59,
   60,  269,   62,   41,  110,   41,   23,   39,   59,  115,
  106,   41,  257,   20,    3,   22,    5,   41,  105,   44,
    9,   43,  257,   45,   60,   44,   62,  258,  257,  135,
   60,   41,   62,   44,  130,  264,   60,   59,   62,  268,
   59,   41,  257,  274,  275,   41,  279,  276,   95,  264,
   60,   41,   62,  268,  101,  102,   60,   43,   62,   45,
   60,  276,   62,   44,   60,   72,   62,   40,   57,   43,
   60,   45,   62,   43,   60,   45,   62,   45,   59,   96,
  112,  270,  129,  257,   40,   59,  260,   76,  262,  263,
  264,   59,  257,   44,  268,  269,  270,  271,   44,  257,
  257,  108,  276,  277,  278,  122,  264,  264,   59,   40,
  268,  269,  270,   59,  257,  261,  262,  260,  276,  276,
  278,  264,  168,   41,  170,  172,  269,  270,  145,  175,
   42,  270,  179,  276,  277,   47,  262,  263,  155,  257,
   41,  148,   43,   59,   45,   59,   41,  194,   43,  196,
   45,  158,  199,  160,  257,  258,  257,  260,   40,  260,
  167,  264,  257,  264,  267,   41,  269,   43,  269,   45,
   41,  274,  275,  276,  277,  276,  277,   41,   59,   43,
  273,   45,  257,   85,   86,   41,  269,  258,  257,  261,
  258,  258,  267,  265,  266,  261,  257,   59,  257,  265,
  266,  261,  280,  274,  275,  265,  266,  274,  275,  281,
  282,  283,  284,  272,  273,  281,  282,  283,  284,  263,
  258,  281,  282,  283,  284,  261,   41,  270,  269,  265,
  266,  261,  257,  258,  263,  265,  266,  261,   41,   59,
  280,  265,  266,  270,   41,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,  263,  269,  269,  265,  266,   59,   58,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,  257,
  269,  281,  282,  283,  284,  281,  282,  283,  284,  258,
   40,   58,   40,   40,  272,  273,  271,   41,  267,   43,
   40,   45,  271,   59,   40,  274,  275,   59,   59,  268,
  270,  268,  270,   59,   40,  268,   40,   59,   40,  270,
   59,   59,   59,  270,   59,  270,   59,   59,   41,    9,
   99,   76,  160,  123,   -1,   47,
};
#define YYFINAL 2
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 284
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,
"':'","';'","'<'",0,"'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,"ID","CADENA","UNTIL","IF","THEN","ELSE","ENDIF","PRINT","AND",
"OR","FUNC","RETURN","BEGIN","END","BREAK","CTE_ULONG","CTE_DOUBLE","DOUBLE",
"ULONG","REPEAT","TRY","CATCH","ASIGNACION","PRE","MENOR_IGUAL","MAYOR_IGUAL",
"IGUAL_IGUAL","DISTINTO",
};
char *yyrule[] = {
"$accept : programa",
"programa : ID BEGIN conjunto_sentencias_declarativas conjunto_sentencias_ejecutables END ';'",
"conjunto_sentencias_declarativas : sentencia_declarativa",
"conjunto_sentencias_declarativas : conjunto_sentencias_declarativas sentencia_declarativa",
"conjunto_sentencias_declarativas : BREAK ';'",
"conjunto_sentencias_ejecutables : sentencia_ejecutable",
"conjunto_sentencias_ejecutables : conjunto_sentencias_ejecutables sentencia_ejecutable",
"sentencia_declarativa : tipo lista_variables ';'",
"sentencia_declarativa : tipo FUNC ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'",
"sentencia_declarativa : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"sentencia_declarativa : FUNC ID ',' lista_variables ';'",
"sentencia_declarativa : error_sentencias_declarativas",
"error_sentencias_declarativas : tipo ';'",
"error_sentencias_declarativas : FUNC ',' lista_variables ';'",
"error_sentencias_declarativas : FUNC tipo ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"error_sentencias_declarativas : tipo FUNC ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END",
"error_sentencias_declarativas : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"conjunto_sentencias_declarativas_funcion : tipo lista_variables ';'",
"conjunto_sentencias_ejecutables_funcion : ejecutable",
"conjunto_sentencias_ejecutables_funcion : conjunto_sentencias_ejecutables_funcion ejecutable",
"lista_variables : ID",
"lista_variables : lista_variables ',' ID",
"sentencia_ejecutable : BEGIN ejecutable END ';'",
"sentencia_ejecutable : TRY ejecutable CATCH BEGIN ejecutable END ';'",
"sentencia_ejecutable : sentencia_if",
"sentencia_ejecutable : error_sentencias_ejecutables",
"error_sentencias_ejecutables : ejecutable END ';'",
"error_sentencias_ejecutables : TRY ejecutable BEGIN ejecutable END ';'",
"error_sentencias_ejecutables : TRY ejecutable CATCH ';'",
"error_sentencias_ejecutables : BEGIN ejecutable END",
"sentencia_if : IF '(' condicion ')' rama_then_sola ENDIF",
"sentencia_if : IF '(' condicion ')' rama_then_sola rama_else ENDIF",
"sentencia_if : error_sentencia_if",
"error_sentencia_if : IF '(' condicion rama_then_sola ENDIF",
"error_sentencia_if : IF '(' condicion ')' rama_else ENDIF",
"rama_then_sola : THEN sentencia_ejecutable",
"rama_else : ELSE sentencia_ejecutable",
"ejecutable : asignacion",
"ejecutable : sentencia_control_repeat",
"ejecutable : sentencia_salida",
"sentencia_control_repeat : REPEAT '(' asignacion condicion_repeat factor ')' sentencia_ejecutable",
"sentencia_control_repeat : REPEAT '(' asignacion condicion_repeat ';' factor ')' sentencia_ejecutable BREAK ';'",
"sentencia_control_repeat : errores_repeat",
"errores_repeat : REPEAT '(' asignacion condicion_repeat factor ')' sentencia_ejecutable",
"errores_repeat : REPEAT '(' asignacion condicion_repeat ';' factor ')' sentencia_ejecutable BREAK",
"operador : AND",
"operador : OR",
"comparador : '<'",
"comparador : '>'",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : DISTINTO",
"comparador : IGUAL_IGUAL",
"sentencia_salida : PRINT '(' CADENA ')' ';'",
"sentencia_salida : error_sentencia_salida",
"error_sentencia_salida : PRINT CADENA ')'",
"error_sentencia_salida : PRINT '(' ')'",
"tipo : ULONG",
"tipo : DOUBLE",
"tipo : CADENA",
"condicion_repeat : ID comparador expresion ';'",
"asignacion : ID ASIGNACION expresion ';'",
"condicion : condicion comparador expresion",
"condicion : condicion operador expresion",
"condicion : expresion comparador expresion",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : CTE_DOUBLE",
"factor : CTE_ULONG",
"factor : '-' CTE_DOUBLE",
"parametro : tipo ID",
"parametro : error_parametro",
"error_parametro : tipo",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 155 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";


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
#line 373 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 12:
#line 32 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 13:
#line 33 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 14:
#line 34 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 15:
#line 35 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 16:
#line 36 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 26:
#line 56 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el BEGIN");}
break;
case 27:
#line 57 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 28:
#line 58 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 29:
#line 59 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ';' ");}
break;
case 33:
#line 68 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 34:
#line 69 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 43:
#line 89 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el ';' luego de la condicion "); }
break;
case 44:
#line 90 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el ';' final "); }
break;
case 55:
#line 108 "g19_gramatica.y"
{ System.out.println (" Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 56:
#line 109 "g19_gramatica.y"
{ System.out.println (" Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la cadena a imprimir");}
break;
case 74:
#line 141 "g19_gramatica.y"
{
        ts.cambiarNegativo(yyvsp[0].sval,aLexico);
        		 }
break;
case 77:
#line 151 "g19_gramatica.y"
{System.out.println (" Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el identificador");}
break;
#line 583 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
