package compilador.Semantico;//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "g19_gramatica.y"
import compilador.AnalizadorLex;
import compilador.Compilador;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;
import compilador.Terceto;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
public ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short CADENA=258;
public final static short UNTIL=259;
public final static short IF=260;
public final static short THEN=261;
public final static short ELSE=262;
public final static short ENDIF=263;
public final static short PRINT=264;
public final static short AND=265;
public final static short OR=266;
public final static short FUNC=267;
public final static short RETURN=268;
public final static short BEGIN=269;
public final static short END=270;
public final static short BREAK=271;
public final static short CTE_ULONG=272;
public final static short CTE_DOUBLE=273;
public final static short DOUBLE=274;
public final static short ULONG=275;
public final static short REPEAT=276;
public final static short TRY=277;
public final static short CATCH=278;
public final static short ASIGNACION=279;
public final static short PRE=280;
public final static short MENOR_IGUAL=281;
public final static short MAYOR_IGUAL=282;
public final static short IGUAL_IGUAL=283;
public final static short DISTINTO=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    1,    0,    2,    2,    3,    3,    3,    3,    5,    5,
    5,    5,    7,    7,    4,    4,    4,    4,    4,   19,
   19,   19,   19,   15,   16,   16,   13,   13,    6,    6,
   11,   11,   11,   22,   22,   20,   21,    9,    9,    9,
   25,   26,   26,   27,   27,   27,   27,   27,   27,   10,
   10,   28,   28,   12,   12,   12,   23,    8,   18,   18,
   18,   17,   17,   17,   29,   29,   29,   24,   24,   24,
   24,   14,
};
final static short yylen[] = {                            2,
    0,    7,    1,    2,    1,    4,    7,    1,    2,    2,
    1,    1,    6,    4,    3,   15,   21,    5,    1,    2,
    4,   20,   20,    3,    1,    2,    1,    3,    1,    2,
    6,    7,    1,    5,    6,    2,    2,    9,   10,    1,
    8,    1,    1,    1,    1,    1,    1,    1,    1,    4,
    1,    3,    3,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    1,    3,    3,    1,    1,    1,    1,
    2,    2,
};
final static short yydefred[] = {                         0,
    1,    0,    0,    0,   56,    0,   55,   54,    0,    3,
    0,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    5,    8,    0,    0,   11,   12,   33,
   40,   51,   27,    0,   20,    0,    0,    0,    0,    0,
    0,    0,    0,   29,    0,    0,    0,    0,    9,   10,
    0,   15,    0,    0,   21,    0,   68,   70,   69,    0,
    0,   67,    0,    0,    0,   52,    0,   53,    0,   30,
    0,    0,    0,    0,    0,    2,    0,   28,   18,    0,
    0,    0,   71,    0,    0,    0,    0,   46,   47,   49,
   48,   44,   45,    0,    0,   42,   43,    0,    0,    0,
    0,   50,    6,    0,    0,    0,    0,    0,   14,    0,
    0,    0,   72,    0,    0,    0,   65,   66,    0,   36,
    0,    0,    0,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   37,   31,    0,   35,
    0,    0,   13,    0,    0,   24,    0,    0,   32,    0,
    0,    7,    0,    0,    0,    0,    0,   41,   25,    0,
    0,    0,    0,    0,    0,   26,    0,    0,    0,   39,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   22,
    0,   23,   17,
};
final static short yydgoto[] = {                          2,
    3,    9,   22,   10,   24,   45,   25,   26,   27,   28,
   29,  111,   36,   82,  112,  160,   64,   65,   12,   99,
  123,   30,   73,   62,   31,  100,  101,   32,   63,
};
final static short yysindex[] = {                      -234,
    0,    0, -243, -146,    0,    6,    0,    0, -142,    0,
   64,    0,  -15, -223, -179, -199,   46,  -37,  -96,   60,
  -96, -160,    0,    0,    0,   55,   61,    0,    0,    0,
    0,    0,    0, -131,    0,   -1, -223,   16,   99,  -34,
  -34,  107,  -13,    0, -117, -106, -204,   93,    0,    0,
  114,    0, -102,   20,    0,  -40,    0,    0,    0, -111,
   63,    0,    5,   45,   -5,    0,  124,    0,  117,    0,
   51,  119,  127,  -96,  -52,    0, -221,    0,    0, -221,
  -91,  147,    0,  -34,  -34,  -34,  -34,    0,    0,    0,
    0,    0,    0,  -34, -119,    0,    0, -177,  -74,  -34,
  -34,    0,    0,  -34,  -67,  -34,  -79,  -96,    0,  151,
 -223,  -76,    0, -221,    5,    5,    0,    0,   63,    0,
 -119, -132,  -69,    0,   63,   63,   63,   27,  136,  155,
  138,  -72, -221,   65,  -81,  -68,    0,    0,  -63,    0,
  -34, -119,    0,  143,  -66,    0,  146,  -53,    0,  164,
  -65,    0,  -96,  167,  150,  169, -119,    0,    0,  -97,
  -34,  170,  -34,  -60,  172,    0,   21,  -34,   35,    0,
  -34,  154,   41,  156,  101,  -54,  157,  -49,  -48,  188,
  -39,  193,  185,  -34,  210,  -34,    0,  129,  -34,  132,
  192,  140,  198,  -12,  200,    4,  206,   23,  211,    0,
  212,    0,    0,
};
final static short yyrindex[] = {                         0,
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
    0,    0,    0,    0,  -35,  -29,    0,    0,    1,    0,
    0,    0,    0,    0,    7,   31,  235,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  236,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -86,  260,   -4,    0,    0,  252,    0,    0,
    0,   40,    8,  222,  -82,    0,   -2,  -95,    0,  238,
  215,    0,  233,  -47,    0,    0,  -51,    0,   52,
};
final static int YYTABLESIZE=338;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         64,
   80,   64,   43,   64,  156,   62,  109,   62,  120,   62,
   60,   63,   94,   63,   44,   63,   47,   64,   64,  104,
   64,   38,    1,   62,   62,    4,   62,   68,   37,   63,
   63,  136,   63,   33,  137,   98,    5,   61,  117,  118,
   70,   61,   53,   11,   54,   15,   86,   60,   11,   14,
  145,   87,    7,    8,   92,  151,   93,   52,  130,   53,
   61,  172,   61,   53,   74,  167,   60,  169,   60,  107,
  164,   59,  173,   75,   55,  174,  104,   39,   79,   40,
   92,  177,   93,   95,  121,   41,   92,   84,   93,   85,
   59,  119,   59,  150,   92,   81,   93,  125,  126,   46,
   92,  127,   93,  132,   92,   84,   93,   85,   53,   48,
   92,    5,   93,   49,   16,    5,   81,   17,  134,   50,
    6,   18,   35,  146,    6,   51,   19,    7,    8,  121,
  138,    7,    8,   20,   21,  115,  116,   16,   56,   16,
   17,  179,   17,   84,   18,   85,   18,   66,  159,   19,
   71,   76,   69,   77,   78,  166,   20,   21,   20,   16,
   16,   83,   17,   17,  102,  113,   18,   18,  175,  191,
  165,   84,  193,   85,   84,  103,   85,  105,   20,   20,
  195,  188,   84,  190,   85,  106,  192,  114,  124,  128,
  131,  133,  135,  140,  141,  142,  143,  144,  147,  149,
  148,  152,  153,  154,  157,  158,  161,  162,  163,  168,
  170,  171,  176,  180,  178,  181,  108,    5,  182,   64,
   42,  183,   57,   64,   64,   62,  155,  184,  185,   62,
   62,   63,  186,    7,    8,   63,   63,   58,   59,   64,
   64,   64,   64,  187,   67,   62,   62,   62,   62,  189,
  194,   63,   63,   63,   63,   95,  196,  197,  198,   96,
   97,   61,   13,    5,  200,   61,   61,   60,   23,  202,
  203,   60,   60,  199,   58,   88,   89,   90,   91,    7,
    8,   61,   61,   61,   61,   96,   97,   60,   60,   60,
   60,   59,  201,   57,   38,   59,   59,   72,  110,   96,
   97,   88,   89,   90,   91,   96,   97,   88,   89,   90,
   91,   59,   59,   59,   59,   88,   89,   90,   91,    0,
   33,   88,   89,   90,   91,   88,   89,   90,   91,   40,
   34,   88,   89,   90,   91,  122,  139,  129,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   41,   43,   40,   45,   58,   41,   59,   43,   95,   45,
   45,   41,   64,   43,   19,   45,   21,   59,   60,   71,
   62,   14,  257,   59,   60,  269,   62,   41,   44,   59,
   60,  114,   62,  257,  121,   41,  258,   40,   86,   87,
   45,   41,   44,    4,   37,    6,   42,   41,    9,   44,
  133,   47,  274,  275,   60,  142,   62,   59,  106,   44,
   60,   41,   62,   44,  269,  161,   60,  163,   62,   74,
  157,   41,  168,  278,   59,   41,  128,  257,   59,  279,
   60,   41,   62,  261,  262,   40,   60,   43,   62,   45,
   60,   94,   62,  141,   60,   56,   62,  100,  101,   40,
   60,  104,   62,  108,   60,   43,   62,   45,   44,  270,
   60,  258,   62,   59,  257,  258,   77,  260,  111,   59,
  267,  264,   59,   59,  267,  257,  269,  274,  275,  262,
  263,  274,  275,  276,  277,   84,   85,  257,   40,  257,
  260,   41,  260,   43,  264,   45,  264,   41,  153,  269,
  257,   59,  270,   40,  257,  160,  276,  277,  276,  257,
  257,  273,  260,  260,   41,  257,  264,  264,  171,   41,
  268,   43,   41,   45,   43,   59,   45,   59,  276,  276,
   41,  184,   43,  186,   45,   59,  189,   41,  263,  257,
  270,   41,  269,  263,   59,   41,   59,  270,  280,  263,
  269,   59,  269,   58,   41,  271,   40,   58,   40,   40,
  271,   40,   59,  268,   59,   59,  269,  258,  268,  261,
  258,  270,  257,  265,  266,  261,  280,   40,  268,  265,
  266,  261,   40,  274,  275,  265,  266,  272,  273,  281,
  282,  283,  284,   59,  258,  281,  282,  283,  284,   40,
   59,  281,  282,  283,  284,  261,   59,  270,   59,  265,
  266,  261,  257,  258,   59,  265,  266,  261,    9,   59,
   59,  265,  266,  270,   59,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,  270,   59,   59,  265,  266,   46,   77,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,   -1,
  257,  281,  282,  283,  284,  281,  282,  283,  284,  279,
  267,  281,  282,  283,  284,   98,  122,  105,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=284;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"ID","CADENA","UNTIL","IF","THEN","ELSE","ENDIF",
"PRINT","AND","OR","FUNC","RETURN","BEGIN","END","BREAK","CTE_ULONG",
"CTE_DOUBLE","DOUBLE","ULONG","REPEAT","TRY","CATCH","ASIGNACION","PRE",
"MENOR_IGUAL","MAYOR_IGUAL","IGUAL_IGUAL","DISTINTO",
};
final static String yyrule[] = {
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
"sentencia_declarativa : tipo FUNC ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'",
"sentencia_declarativa : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"sentencia_declarativa : FUNC ID ',' lista_variables ';'",
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

//#line 168 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";
Terceto t;
String ambitoActual;

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
//#line 425 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 14 "g19_gramatica.y"
{System.out.println(ambitoActual);
ambitoActual=val_peek(0).sval;
System.out.println("imprime al final"+ambitoActual);
 }
break;
case 2:
//#line 17 "g19_gramatica.y"
{ t.cambiarAmbitoTerceto(val_peek(6).sval);
                                                                                    System.out.println(ambitoActual);
                                                                                    ambitoActual=val_peek(6).sval;
                                                                                    System.out.println("imprime al final"+ambitoActual);
                                                                                   t.agregarUsoVariablesTS(val_peek(6).sval,"nombre_programa");
                                                                                  }
break;
case 13:
//#line 41 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 14:
//#line 42 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 16:
//#line 46 "g19_gramatica.y"
{
                                                                                                                                                                    /*Cambiara el ambito de las variables en caso de ser necesario*/
                                                                                                                                                                    System.out.println("a"+ambitoActual);
                                                                                                                                                                    ambitoActual=ambitoActual+"."+val_peek(12).sval;
                                                                                                                                                                    System.out.println(ambitoActual);
                                                                                                                                                                    t.cambiarAmbitoTerceto(val_peek(12).sval);
                                                                                                                                                                    t.agregarUsoVariablesTS(val_peek(12).sval,"nombre_funcion");

		                                                                                                                                                         }
break;
case 17:
//#line 55 "g19_gramatica.y"
{t.cambiarAmbitoTerceto(val_peek(18).sval);
		                                                                                                                                                   t.agregarUsoVariablesTS(val_peek(18).sval,"nombre_funcion");
		                                                                                                                                                   System.out.println("a"+ambitoActual);
		                                                                                                                                                   ambitoActual=ambitoActual+"."+val_peek(18).sval;
		                                                                                                                                                   System.out.println(ambitoActual);
		                                                                                                                                                   }
break;
case 18:
//#line 61 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(3).sval,"variable");}
break;
case 20:
//#line 65 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 21:
//#line 66 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 22:
//#line 67 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 23:
//#line 68 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 27:
//#line 78 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
                      t.cambiarAmbitoVariable(val_peek(0).sval);}
break;
case 28:
//#line 80 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
		                         t.cambiarAmbitoVariable(val_peek(0).sval);}
break;
case 34:
//#line 93 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 35:
//#line 94 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 41:
//#line 109 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 52:
//#line 128 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 53:
//#line 129 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 71:
//#line 161 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
case 72:
//#line 164 "g19_gramatica.y"
{ t.agregarUsoVariablesTS(val_peek(0).sval,"variable");}
break;
//#line 677 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
