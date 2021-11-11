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
//#line 22 "Parser.java"




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
    0,    1,    1,    1,    2,    2,    3,    3,    3,    3,
    3,   12,   12,   12,   12,   12,    8,    9,    9,    6,
    6,    4,    4,    4,    4,   15,   15,   15,   15,   14,
   14,   14,   18,   18,   16,   17,   13,   13,   13,   20,
   20,   20,   24,   25,   25,   26,   26,   26,   26,   26,
   26,   21,   21,   27,   27,    5,    5,    5,   22,   19,
   11,   11,   11,   10,   10,   10,   28,   28,   28,   23,
   23,   23,   23,    7,
};
final static short yylen[] = {                            2,
    6,    1,    2,    2,    1,    2,    3,   15,   21,    5,
    1,    2,    4,   20,   13,   20,    3,    1,    2,    1,
    3,    4,    7,    1,    1,    3,    6,    4,    3,    6,
    7,    1,    5,    6,    2,    2,    1,    1,    1,    8,
   10,    1,    9,    1,    1,    1,    1,    1,    1,    1,
    1,    5,    1,    3,    3,    1,    1,    1,    3,    4,
    3,    3,    3,    3,    3,    1,    3,    3,    1,    1,
    1,    1,    2,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   58,    0,    0,   57,   56,    0,    2,
    0,   11,    0,    0,    0,    4,    0,    0,    0,    0,
    0,    0,    0,    3,    5,    0,   24,   25,   32,   37,
   38,   39,   42,   53,   20,    0,   12,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    6,
    0,    0,    7,    0,    0,   13,    0,   70,   72,   71,
    0,    0,   69,    0,    0,    0,   54,    0,   55,    0,
    0,    0,    0,    1,   26,    0,   21,   10,    0,    0,
    0,   73,   60,    0,    0,    0,    0,   48,   49,   51,
   50,   46,   47,    0,    0,   44,   45,    0,    0,    0,
    0,    0,   22,    0,    0,    0,    0,   28,    0,    0,
    0,    0,   74,    0,    0,    0,   67,   68,    0,   35,
    0,    0,    0,   33,    0,    0,   52,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   36,   30,    0,   34,
    0,    0,   27,    0,    0,    0,   17,    0,    0,   31,
    0,   23,    0,   18,    0,    0,    0,    0,    0,    0,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   15,    0,    0,    0,    0,    0,    0,    0,
    0,    8,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   14,    0,   16,    9,
};
final static short yydgoto[] = {                          2,
    9,   23,   10,   25,  111,   38,   81,  112,  153,   65,
   66,   12,   26,   27,   28,   99,  123,   29,   30,   31,
   32,  105,   63,   33,  100,  101,   34,   64,
};
final static short yysindex[] = {                      -248,
 -228,    0,  -68,    0,    6,   -8,    0,    0,  -93,    0,
  -44,    0,   22, -183, -180,    0, -199,   84,  -37, -178,
   92, -178, -114,    0,    0, -171,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -108,    0,   50, -183,  143,
  113,  -28,  -28,  116,   -7, -110,  -91, -205,  111,    0,
  130,  151,    0,  -62,  149,    0,  -40,    0,    0,    0,
  -54,   -6,    0,   28,   45,   -5,    0,  160,    0,  152,
  -30, -178,  -52,    0,    0,  -36,    0,    0, -158,    8,
  187,    0,    0,  -28,  -28,  -28,  -28,    0,    0,    0,
    0,    0,    0,  -28,  -72,    0,    0, -233,  -13,  -28,
  -28,  198,    0,   27,  199,    4, -178,    0, -158,  218,
 -183,   25,    0, -158,   28,   28,    0,    0,   59,    0,
  -72, -122,   12,    0,   59,   59,    0,  -28,  -28,  210,
   23,   26, -158,  150,  -10,   29,    0,    0,   36,    0,
   59,  230,    0,  261, -178,   52,    0,  278,  -47,    0,
  -72,    0, -208,    0, -178,  297,  280,  299,   69,  301,
    0,  -96,  -28,  302,  -28,  284,  -28,  304,   21,  -28,
   35,    0,    2,  -28,  286,   41,  287,   77,  155,   80,
  291,   81,    0,   82,  311,   85,  314,  296,  -28,  316,
  -28,    0,  169,  -28,  289,  298,  290,  300,   88,  303,
   90,  305,   91,  306,    0,  307,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -41,    0,    0,    0,    0,    0, -149,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -35,  -29,    0,    0,    1,    0,
    0,    0,    0,    0,    7,   31,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  308,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -134,    0,
    0,    0,    0,    0,    0, -131,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  354,   -3,   49,   -1,  292,  -74,  214,  -16,
  -11,    0,   24,    0,    0,  272,  249,    0,  325,    0,
    0,    0,  -64,    0,    0,  -33,    0,  131,
};
final static int YYTABLESIZE=372;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         66,
   79,   66,   45,   66,  109,   64,  108,   64,    1,   64,
  158,   65,   40,   65,   37,   65,   61,   66,   66,   50,
   66,  117,  118,   64,   64,   62,   64,   95,  121,   65,
   65,   94,   65,   69,  132,   98,   84,   55,   85,  136,
    3,   63,  178,   46,   84,   48,   85,   62,   17,   14,
   16,   11,   83,   15,   92,   19,   93,   11,  146,  160,
   63,  175,   63,   72,  142,   39,   62,   21,   62,   86,
  128,   61,   73,   35,   87,  177,   41,  119,   17,   42,
   92,  181,   93,  125,  126,   19,   92,   84,   93,   85,
   61,  120,   61,   54,   92,  106,   93,   21,   51,    4,
   92,   84,   93,   85,   92,   80,   93,   29,   53,  134,
   29,  141,   29,   29,   29,    7,    8,  137,   29,   29,
   29,   29,   40,   43,   80,   43,   29,   29,   29,   40,
  131,   47,   43,   40,   40,   40,   43,   43,   43,  121,
  138,   40,   17,   40,   43,   18,   43,  159,   52,   19,
  173,  169,   57,  171,   20,   49,   67,  179,  176,   70,
   17,   21,   22,   17,    4,   17,   18,   19,  154,   74,
   19,  168,  193,    5,  195,   20,  161,  197,  154,   21,
    7,    8,   21,   22,   17,  161,   54,   18,   75,    4,
   76,   19,   54,   54,   77,  184,   20,   84,    5,   85,
  102,   56,    6,   21,   22,    7,    8,   78,  147,  196,
  103,   84,   35,   85,  115,  116,  107,    4,   82,   66,
   44,    4,   36,   66,   66,   64,  104,  114,   58,   64,
   64,   65,  157,    7,    8,   65,   65,    7,    8,   66,
   66,   66,   66,   59,   60,   64,   64,   64,   64,  124,
   68,   65,   65,   65,   65,   95,  127,  129,  133,   96,
   97,   63,   13,    4,  113,   63,   63,   62,  143,  148,
  151,   62,   62,  130,  140,   88,   89,   90,   91,    7,
    8,   63,   63,   63,   63,   96,   97,   62,   62,   62,
   62,   61,  144,  135,  145,   61,   61,  149,  150,   96,
   97,   88,   89,   90,   91,   96,   97,   88,   89,   90,
   91,   61,   61,   61,   61,   88,   89,   90,   91,  152,
  155,   88,   89,   90,   91,   88,   89,   90,   91,  198,
  200,   84,   84,   85,   85,  156,  163,  164,  165,  166,
  167,  170,  172,  174,  180,  182,  183,  185,  187,  186,
  189,  188,  190,  191,  192,  194,  199,  202,  201,  204,
  206,  203,   24,  205,  207,  208,   59,  110,  162,  122,
  139,   71,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   41,   43,   40,   45,   41,   41,   59,   43,  257,   45,
   58,   41,   14,   43,   59,   45,   45,   59,   60,   23,
   62,   86,   87,   59,   60,   42,   62,  261,  262,   59,
   60,   65,   62,   41,  109,   41,   43,   39,   45,  114,
  269,   41,   41,   20,   43,   22,   45,   41,  257,   44,
   59,    3,   59,    5,   60,  264,   62,    9,  133,  268,
   60,   41,   62,  269,  129,   44,   60,  276,   62,   42,
  104,   41,  278,  257,   47,   41,  257,   94,  257,  279,
   60,   41,   62,  100,  101,  264,   60,   43,   62,   45,
   60,   95,   62,   44,   60,   72,   62,  276,  270,  258,
   60,   43,   62,   45,   60,   57,   62,  257,   59,  111,
  260,  128,  262,  263,  264,  274,  275,  121,  268,  269,
  270,  271,  257,   40,   76,  257,  276,  277,  278,  264,
  107,   40,  264,  268,  269,  270,  268,  269,  270,  262,
  263,  276,  257,  278,  276,  260,  278,  151,  257,  264,
  167,  163,   40,  165,  269,  270,   41,  174,  170,  270,
  257,  276,  277,  257,  258,  257,  260,  264,  145,   59,
  264,  268,  189,  267,  191,  269,  153,  194,  155,  276,
  274,  275,  276,  277,  257,  162,   44,  260,   59,  258,
   40,  264,   44,   44,  257,   41,  269,   43,  267,   45,
   41,   59,  271,  276,  277,  274,  275,   59,   59,   41,
   59,   43,  257,   45,   84,   85,  269,  258,  273,  261,
  258,  258,  267,  265,  266,  261,  257,   41,  257,  265,
  266,  261,  280,  274,  275,  265,  266,  274,  275,  281,
  282,  283,  284,  272,  273,  281,  282,  283,  284,  263,
  258,  281,  282,  283,  284,  261,   59,   59,   41,  265,
  266,  261,  257,  258,  257,  265,  266,  261,   59,  280,
   41,  265,  266,  270,  263,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,  270,  269,  269,  265,  266,  269,  263,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,   59,
  269,  281,  282,  283,  284,  281,  282,  283,  284,   41,
   41,   43,   43,   45,   45,   58,   40,   58,   40,  271,
   40,   40,   59,   40,   59,   59,  270,  268,  268,   59,
   40,  270,  268,   40,   59,   40,   59,  270,   59,  270,
  270,   59,    9,   59,   59,   59,   59,   76,  155,   98,
  122,   47,
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
"sentencia_control_repeat : REPEAT '(' asignacion condicion_repeat ';' factor ')' sentencia_ejecutable",
"sentencia_control_repeat : REPEAT '(' asignacion condicion_repeat ';' factor ')' sentencia_ejecutable BREAK ';'",
"sentencia_control_repeat : error_repeat",
"error_repeat : REPEAT '(' asignacion condicion_repeat ';' factor ')' sentencia_ejecutable BREAK",
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
"condicion_repeat : ID comparador expresion",
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
};

//#line 148 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";


public Parser (AnalizadorLex aLexico, TablaSimbolos ts) {
this.ts= ts;
this.aLexico=aLexico;
}

public int yylex() {
        int token = aLexico.tokengenerado();
        System.out.println(token+"->"+ Compilador.traductor(token));
        yylval = new ParserVal(aLexico.ultimoLexemaGenerado);
        // aca mete los valores en una estructura para leer desde ahi(calculo)
        return token;
    }

private void yyerror (String mensajeerror) {
   Notificacion.addError(aLexico.getLineaActual(), mensajeerror);
    }
//#line 431 "Parser.java"
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
case 12:
//#line 32 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 13:
//#line 33 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 14:
//#line 34 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 15:
//#line 35 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 16:
//#line 36 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 26:
//#line 56 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el BEGIN");}
break;
case 27:
//#line 57 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 28:
//#line 58 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 29:
//#line 59 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ';' ");}
break;
case 33:
//#line 68 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 34:
//#line 69 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 43:
//#line 89 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el ';' final "); }
break;
case 54:
//#line 108 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 55:
//#line 109 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 73:
//#line 141 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
//#line 640 "Parser.java"
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
