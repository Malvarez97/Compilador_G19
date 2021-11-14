#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "g19_gramatica.y"
import compilador.AnalizadorLex;
import compilador.Compilador;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;
import compilador.Terceto;
#line 12 "y.tab.c"
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
    1,    0,    2,    2,    3,    3,    3,    3,    5,    5,
    5,    5,    7,    7,    4,   14,    4,    4,   20,    4,
    4,   21,   21,   21,   21,   16,   17,   17,   13,   13,
    6,    6,   11,   11,   11,   24,   24,   22,   23,    9,
    9,    9,   27,   28,   28,   29,   29,   29,   29,   29,
   29,   10,   10,   30,   30,   12,   12,   12,   25,    8,
   19,   19,   19,   18,   18,   18,   31,   31,   31,   26,
   26,   26,   26,   15,
};
short yylen[] = {                                         2,
    0,    7,    1,    2,    1,    4,    7,    1,    2,    2,
    1,    1,    6,    4,    3,    0,   16,   21,    0,    6,
    1,    2,    4,   20,   20,    3,    1,    2,    1,    3,
    1,    2,    6,    7,    1,    5,    6,    2,    2,    9,
   10,    1,    8,    1,    1,    1,    1,    1,    1,    1,
    1,    4,    1,    3,    3,    1,    1,    1,    3,    3,
    3,    3,    3,    3,    3,    1,    3,    3,    1,    1,
    1,    1,    2,    2,
};
short yydefred[] = {                                      0,
    1,    0,    0,    0,   58,    0,   57,   56,    0,    3,
    0,   21,   19,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    5,    8,    0,    0,   11,   12,   35,
   42,   53,   29,    0,   22,    0,    0,    0,    0,    0,
    0,    0,    0,   31,    0,    0,    0,    0,    9,   10,
   16,   15,    0,    0,   23,    0,   70,   72,   71,    0,
    0,   69,    0,    0,    0,   54,    0,   55,    0,   32,
    0,    0,    0,    0,    0,    2,    0,   30,    0,    0,
    0,    0,   73,    0,    0,    0,    0,   48,   49,   51,
   50,   46,   47,    0,    0,   44,   45,    0,    0,    0,
    0,   52,    6,    0,    0,    0,    0,    0,   14,    0,
   20,    0,    0,   74,    0,    0,    0,   67,   68,    0,
   38,    0,    0,    0,   36,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   39,   33,    0,
   37,    0,    0,   13,    0,    0,   26,    0,    0,   34,
    0,    0,    7,    0,    0,    0,    0,    0,   43,    0,
    0,    0,    0,    0,   27,    0,    0,    0,    0,   41,
    0,   28,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   17,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   24,    0,   25,   18,
};
short yydgoto[] = {                                       2,
    3,    9,   22,   10,   24,   45,   25,   26,   27,   28,
   29,  112,   36,   77,   82,  113,  166,   64,   65,   37,
   12,   99,  124,   30,   73,   62,   31,  100,  101,   32,
   63,
};
short yysindex[] = {                                   -228,
    0,    0, -234, -146,    0,    6,    0,    0, -142,    0,
   64,    0,    0, -218, -214, -232,   14,  -37, -107,   19,
 -107, -205,    0,    0,    0,   18,   20,    0,    0,    0,
    0,    0,    0, -161,    0,   86,   73,   93,   80,  -34,
  -34,   85,  -13,    0, -116, -126, -198,   77,    0,    0,
    0,    0, -114, -218,    0,  -40,    0,    0,    0, -112,
    8,    0,   72,   45,   -5,    0,  106,    0,  109,    0,
   51,  113,  123, -107,  -52,    0,  144,    0,   96, -200,
  -71,  146,    0,  -34,  -34,  -34,  -34,    0,    0,    0,
    0,    0,    0,  -34, -118,    0,    0, -239,  -74,  -34,
  -34,    0,    0,  -34,  -67,  -34,  -79, -107,    0, -200,
    0, -218,  -77,    0, -200,   72,   72,    0,    0,    8,
    0, -118, -177,  -70,    0,    8,    8,    8,   27,  137,
  153,  138,  -75,  157,  105,  -81,  -69,    0,    0,  -62,
    0,  -34, -118,    0,  143, -200,    0,  145,  -53,    0,
  163,  -66,    0,  -63,  167,  150,  169, -118,    0, -107,
  -34,  170,  -34,  -60,    0, -208,   21,  -34,   35,    0,
  172,    0,  154,   41,  155,  -34,  -46,  156,  -39,   65,
  176,  -24,  179,  -20,  -34,  188,  -34,  174,  130,  -34,
  135,    0,  192,  136,  198,  -12,  200,   -1,  206,    4,
  211,    0,  212,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  216,    0,  -41,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -35,  -29,    0,    0,    1,
    0,    0,    0,    0,    0,    7,   31,  234,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  235,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,    0,  -49,  286,   -4,    0,    0,  252,    0,    0,
    0,   28,   12,    0,  189, -106,    0,   -2,    2,    0,
    0,  222,  213,    0,  232,  -42,    0,    0,  -51,    0,
   82,
};
#define YYTABLESIZE 337
short yytable[] = {                                      66,
   80,   66,   43,   66,  157,   64,  109,   64,  137,   64,
   60,   65,   94,   65,   44,   65,   47,   66,   66,  104,
   66,   95,  122,   64,   64,   38,   64,   68,    1,   65,
   65,   11,   65,   15,    4,   98,   11,   61,   33,  154,
   70,   63,   39,  118,  119,  121,   40,   62,   16,   14,
   84,   17,   85,   41,   92,   18,   93,    5,   46,  171,
   63,  173,   63,  131,   48,   79,   62,   20,   62,  107,
   74,   61,  138,    7,    8,  175,   49,  104,   50,   75,
   92,  178,   93,   81,  122,  139,   92,   84,   93,   85,
   61,  120,   61,  152,   92,   51,   93,  126,  127,  151,
   92,  128,   93,  133,   92,  184,   93,   84,  164,   85,
   92,    5,   93,   86,   16,    5,   54,   17,   87,   56,
    6,   18,   35,  135,    6,   66,   19,    7,    8,   53,
   71,    7,    8,   20,   21,   76,   53,   81,   16,   53,
   16,   17,   78,   17,   52,   18,  102,   18,   53,   16,
   19,   55,   17,   69,  111,  165,   18,   20,   21,   20,
   83,  172,  167,  147,  169,  116,  117,  103,   20,  174,
  193,  105,   84,  180,   85,  195,  197,   84,   84,   85,
   85,  106,  189,  110,  191,  114,  115,  194,  125,  129,
  132,  136,  141,  143,  145,  142,  144,  146,  148,  149,
  150,  153,  155,  158,  159,  160,  161,  162,  163,  168,
  170,  176,  177,  179,  182,  185,  108,    5,  187,   66,
   42,  181,   57,   66,   66,   64,  156,  190,  183,   64,
   64,   65,  192,    7,    8,   65,   65,   58,   59,   66,
   66,   66,   66,  186,   67,   64,   64,   64,   64,  188,
  196,   65,   65,   65,   65,   95,  198,  199,  200,   96,
   97,   63,   13,    5,  202,   63,   63,   62,  201,  204,
  205,   62,   62,  203,   60,   88,   89,   90,   91,    7,
    8,   63,   63,   63,   63,   96,   97,   62,   62,   62,
   62,   61,   59,   40,   23,   61,   61,   72,  134,   96,
   97,   88,   89,   90,   91,   96,   97,   88,   89,   90,
   91,   61,   61,   61,   61,   88,   89,   90,   91,  123,
   33,   88,   89,   90,   91,   88,   89,   90,   91,   40,
   34,   88,   89,   90,   91,  140,  130,
};
short yycheck[] = {                                      41,
   41,   43,   40,   45,   58,   41,   59,   43,  115,   45,
   45,   41,   64,   43,   19,   45,   21,   59,   60,   71,
   62,  261,  262,   59,   60,   14,   62,   41,  257,   59,
   60,    4,   62,    6,  269,   41,    9,   40,  257,  146,
   45,   41,  257,   86,   87,   95,  279,   41,  257,   44,
   43,  260,   45,   40,   60,  264,   62,  258,   40,  268,
   60,   41,   62,  106,  270,   54,   60,  276,   62,   74,
  269,   41,  122,  274,  275,   41,   59,  129,   59,  278,
   60,   41,   62,   56,  262,  263,   60,   43,   62,   45,
   60,   94,   62,  143,   60,  257,   62,  100,  101,  142,
   60,  104,   62,  108,   60,   41,   62,   43,  158,   45,
   60,  258,   62,   42,  257,  258,   44,  260,   47,   40,
  267,  264,   59,  112,  267,   41,  269,  274,  275,   44,
  257,  274,  275,  276,  277,   59,   44,  110,  257,   44,
  257,  260,  257,  260,   59,  264,   41,  264,   44,  257,
  269,   59,  260,  270,   59,  160,  264,  276,  277,  276,
  273,  166,  161,   59,  163,   84,   85,   59,  276,  168,
   41,   59,   43,  176,   45,   41,   41,   43,   43,   45,
   45,   59,  185,   40,  187,  257,   41,  190,  263,  257,
  270,  269,  263,   41,  270,   59,   59,   41,  280,  269,
  263,   59,   58,   41,  271,  269,   40,   58,   40,   40,
  271,   40,   59,   59,   59,   40,  269,  258,   40,  261,
  258,  268,  257,  265,  266,  261,  280,   40,  268,  265,
  266,  261,   59,  274,  275,  265,  266,  272,  273,  281,
  282,  283,  284,  268,  258,  281,  282,  283,  284,  270,
   59,  281,  282,  283,  284,  261,   59,  270,   59,  265,
  266,  261,  257,  258,   59,  265,  266,  261,  270,   59,
   59,  265,  266,  270,   59,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,   59,   59,    9,  265,  266,   46,  110,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,   98,
  257,  281,  282,  283,  284,  281,  282,  283,  284,  279,
  267,  281,  282,  283,  284,  123,  105,
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
"$$1 :",
"programa : ID $$1 BEGIN conjunto_sentencias_declarativas sentencia_ejecutable END ';'",
"conjunto_sentencias_declarativas : sentencia_declarativa",
"conjunto_sentencias_declarativas : conjunto_sentencias_declarativas sentencia_declarativa",
"sentencia_ejecutable : ejecutable",
"sentencia_ejecutable : BEGIN conjunto_sentencias_ejecutables END ';'",
"sentencia_ejecutable : TRY ejecutable CATCH BEGIN ejecutable END ';'",
"sentencia_ejecutable : error_sentencias_ejecutables",
"ejecutable : asignacion ';'",
"ejecutable : sentencia_control_repeat ';'",
"ejecutable : sentencia_salida",
"ejecutable : sentencia_if",
"error_sentencias_ejecutables : TRY ejecutable BEGIN ejecutable END ';'",
"error_sentencias_ejecutables : TRY ejecutable CATCH ';'",
"sentencia_declarativa : tipo lista_variables ';'",
"$$2 :",
"sentencia_declarativa : tipo FUNC ID $$2 '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'",
"sentencia_declarativa : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"$$3 :",
"sentencia_declarativa : FUNC ID $$3 ',' lista_variables ';'",
"sentencia_declarativa : error_sentencias_declarativas",
"error_sentencias_declarativas : tipo ';'",
"error_sentencias_declarativas : FUNC ',' lista_variables ';'",
"error_sentencias_declarativas : FUNC tipo ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"error_sentencias_declarativas : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"conjunto_sentencias_declarativas_funcion : tipo lista_variables ';'",
"conjunto_sentencias_ejecutables_funcion : ejecutable",
"conjunto_sentencias_ejecutables_funcion : conjunto_sentencias_ejecutables_funcion ejecutable",
"lista_variables : ID",
"lista_variables : lista_variables ',' ID",
"conjunto_sentencias_ejecutables : ejecutable",
"conjunto_sentencias_ejecutables : conjunto_sentencias_ejecutables ejecutable",
"sentencia_if : IF '(' condicion ')' rama_then_sola ENDIF",
"sentencia_if : IF '(' condicion ')' rama_then_sola rama_else ENDIF",
"sentencia_if : error_sentencia_if",
"error_sentencia_if : IF '(' condicion rama_then_sola ENDIF",
"error_sentencia_if : IF '(' condicion ')' rama_else ENDIF",
"rama_then_sola : THEN sentencia_ejecutable",
"rama_else : ELSE sentencia_ejecutable",
"sentencia_control_repeat : REPEAT '(' asignacion ';' condicion_repeat ';' factor ')' sentencia_ejecutable",
"sentencia_control_repeat : REPEAT '(' asignacion ';' condicion_repeat ';' factor ')' sentencia_ejecutable BREAK",
"sentencia_control_repeat : error_repeat",
"error_repeat : REPEAT '(' condicion_repeat ';' factor ')' sentencia_ejecutable BREAK",
"operador : AND",
"operador : OR",
"comparador : '<'",
"comparador : '>'",
"comparador : MENOR_IGUAL",
"comparador : MAYOR_IGUAL",
"comparador : DISTINTO",
"comparador : IGUAL_IGUAL",
"sentencia_salida : PRINT '(' CADENA ')'",
"sentencia_salida : error_sentencia_salida",
"error_sentencia_salida : PRINT CADENA ')'",
"error_sentencia_salida : PRINT '(' ')'",
"tipo : ULONG",
"tipo : DOUBLE",
"tipo : CADENA",
"condicion_repeat : ID comparador expresion",
"asignacion : ID ASIGNACION expresion",
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
#line 145 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";
Terceto t;


public Parser (AnalizadorLex aLexico, TablaSimbolos ts) {
this.ts= ts;
this.aLexico=aLexico;
this.t=new Terceto(null,null,null,null,aLexico.getTs());

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
#line 365 "y.tab.c"
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
case 1:
#line 14 "g19_gramatica.y"
{t.agregarUsoVariablesTS(yyvsp[0].sval,"nombre_programa");}
break;
case 13:
#line 33 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 14:
#line 34 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 16:
#line 38 "g19_gramatica.y"
{t.agregarUsoVariablesTS(yyvsp[0].sval,"nombre_funcion");}
break;
case 18:
#line 39 "g19_gramatica.y"
{t.agregarUsoVariablesTS(yyvsp[-18].sval,"nombre_funcion");}
break;
case 19:
#line 40 "g19_gramatica.y"
{t.agregarUsoVariablesTS(yyvsp[0].sval,"variable");}
break;
case 22:
#line 44 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 23:
#line 45 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 24:
#line 46 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 25:
#line 47 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 36:
#line 70 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 37:
#line 71 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 43:
#line 86 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 54:
#line 105 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 55:
#line 106 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 73:
#line 138 "g19_gramatica.y"
{ ts.cambiarNegativo(yyvsp[0].sval,aLexico); }
break;
#line 569 "y.tab.c"
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
