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
import compilador.util.*;
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
    0,    1,    1,    2,    2,    2,    2,    4,    4,    4,
    4,    6,    6,    3,    3,    3,    3,    3,   18,   18,
   18,   18,   18,   14,   15,   15,   12,   12,    5,    5,
   10,   10,   10,   21,   21,   19,   20,    8,    8,    8,
   24,   25,   25,   26,   26,   26,   26,   26,   26,    9,
    9,   27,   27,   11,   11,   11,   22,    7,   17,   17,
   17,   16,   16,   16,   28,   28,   28,   23,   23,   23,
   23,   13,
};
final static short yylen[] = {                            2,
    6,    1,    2,    1,    4,    7,    1,    2,    2,    1,
    1,    6,    4,    3,   15,   21,    5,    1,    2,    4,
   20,   13,   20,    3,    1,    2,    1,    3,    1,    2,
    6,    7,    1,    5,    6,    2,    2,    9,   10,    1,
    8,    1,    1,    1,    1,    1,    1,    1,    1,    5,
    1,    3,    3,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    1,    3,    3,    1,    1,    1,    1,
    2,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   56,    0,   55,   54,    0,    2,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    3,    4,    7,    0,    0,   10,   11,   33,   40,
   51,   27,    0,   19,    0,    0,    0,    0,    0,    0,
    0,    0,   29,    0,    0,    0,    0,    8,    9,    0,
   14,    0,    0,   20,    0,   68,   70,   69,    0,    0,
   67,    0,    0,    0,   52,    0,   53,    0,   30,    0,
    0,    0,    0,    0,    1,    0,   28,   17,    0,    0,
    0,   71,    0,    0,    0,    0,   46,   47,   49,   48,
   44,   45,    0,    0,   42,   43,    0,    0,    0,    0,
    0,    5,    0,    0,    0,    0,    0,   13,    0,    0,
    0,    0,   72,    0,    0,    0,   65,   66,    0,   36,
    0,    0,    0,   34,    0,    0,   50,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,   31,
    0,   35,    0,    0,   12,    0,    0,    0,   24,    0,
    0,   32,    0,    0,    6,   25,    0,    0,    0,    0,
    0,    0,   41,    0,   26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   39,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   22,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   21,    0,
   23,   16,
};
final static short yydgoto[] = {                          2,
    8,   21,    9,   23,   44,   24,   25,   26,   27,   28,
  111,   35,   81,  112,  157,   63,   64,   11,   98,  123,
   29,   72,   61,   30,   99,  100,   31,   62,
};
final static short yysindex[] = {                      -248,
 -240,    0, -127,    0,    6,    0,    0, -132,    0,  -44,
    0,   16, -220, -214, -234,   34,  -37, -180,   46, -180,
 -176,    0,    0,    0,   53,   60,    0,    0,    0,    0,
    0,    0, -153,    0,  -31, -220,   -3,   90,  -28,  -28,
   97,   -7,    0, -143, -121, -246,   91,    0,    0,  106,
    0, -105,   20,    0,  -40,    0,    0,    0, -117,   77,
    0,   87,   45,   -5,    0,  117,    0,  122,    0,   51,
  131,  140, -180,  -52,    0,  -36,    0,    0, -223,  -92,
  160,    0,  -28,  -28,  -28,  -28,    0,    0,    0,    0,
    0,    0,  -28, -103,    0,    0, -208,  -61,  -28,  -28,
  145,    0,  -28,  -54,  -28,  -65, -180,    0, -223,  165,
 -220,  -62,    0, -223,   87,   87,    0,    0,   77,    0,
 -103, -100,  -55,    0,   77,   77,    0,   77,   27,  151,
  168,  152,  -58,  -53, -223,   26,  -66,  -50,    0,    0,
  -48,    0,  -28, -103,    0,  169, -180,  -42,    0,  192,
  -47,    0,  216,  -13,    0,    0,  -93, -180,  219,  207,
  229, -103,    0,  230,    0,  -88,  -28,  231,  -28,    3,
  -28,  235,   21,  -28,   35,    0,  110,  -28,  234,   41,
  236,   24,  141,   30,  240,   52,    0,   61,  281,   68,
  297,  279,  -28,  299,  -28,    0,  144,  -28,  150,  282,
  155,  283,   70,  284,   74,  286,   76,  288,    0,  289,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  290,
    0,  -41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -35,  -29,    0,    0,    1,    0,
    0,    0,    0,    0,    7,   31,    0,  291,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  292,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -21,  344,    2,    0,    0,  308,    0,    0,    0,
   63,   13,  278,  -70,  197,   -1,  -59,    0,  259,  237,
    0,  253,  -27,    0,    0,  -23,    0,   95,
};
final static int YYTABLESIZE=359;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         64,
   79,   64,   42,   64,  109,   62,  108,   62,    1,   62,
  161,   63,   52,   63,   34,   63,   59,   64,   64,   43,
   64,   46,   73,   62,   62,   37,   62,   51,    3,   63,
   63,   74,   63,   67,    4,   97,   32,   60,  134,   93,
   52,   61,   38,  138,   39,   69,  103,   60,   53,   13,
    6,    7,   94,  121,   91,   54,   92,  117,  118,   36,
   61,  179,   61,   52,  148,   10,   60,   14,   60,   52,
   10,   59,  120,   40,  106,  181,   15,  131,   78,   16,
   91,  185,   92,   17,  149,   45,   91,   83,   92,   84,
   59,  119,   59,   47,   91,   19,   92,  125,  126,  139,
   91,  128,   92,   50,   91,  103,   92,  173,  133,  175,
   91,   48,   92,   15,  180,  153,   16,   80,   49,   83,
   17,   84,  154,  136,   15,    4,   68,   16,   85,   55,
    4,   17,   19,   86,    5,   70,   18,   65,   80,    5,
  170,    6,    7,   19,   20,   76,    6,    7,  156,   75,
  182,   77,   83,   15,   84,   82,   16,  101,  165,  156,
   17,  121,  140,   15,  113,   18,   16,  165,   15,  177,
   17,   16,   19,   20,  164,   17,  183,  115,  116,  172,
  102,  188,   19,   83,  200,   84,   83,   19,   84,  104,
  202,  197,   83,  199,   84,  204,  201,   83,  105,   84,
  114,  124,  129,  127,  132,  135,  137,  142,  144,  143,
  145,  146,   32,  150,  152,  147,  107,    4,  151,   64,
   41,    4,   33,   64,   64,   62,  158,  155,   56,   62,
   62,   63,  160,    6,    7,   63,   63,    6,    7,   64,
   64,   64,   64,   57,   58,   62,   62,   62,   62,  159,
   66,   63,   63,   63,   63,   94,  162,  163,  167,   95,
   96,   61,   12,    4,  168,   61,   61,   60,  169,  171,
  174,   60,   60,  176,  178,   87,   88,   89,   90,    6,
    7,   61,   61,   61,   61,   95,   96,   60,   60,   60,
   60,   59,  184,  187,  186,   59,   59,  189,  190,   95,
   96,   87,   88,   89,   90,   95,   96,   87,   88,   89,
   90,   59,   59,   59,   59,   87,   88,   89,   90,  191,
  193,   87,   88,   89,   90,   87,   88,   89,   90,   39,
  192,   87,   88,   89,   90,  194,  195,  196,  198,  206,
  203,  205,  207,  208,  209,  210,  211,  212,   58,   57,
   38,   22,   71,  110,  166,  122,  130,    0,  141,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   41,   43,   40,   45,   41,   41,   59,   43,  257,   45,
   58,   41,   44,   43,   59,   45,   45,   59,   60,   18,
   62,   20,  269,   59,   60,   13,   62,   59,  269,   59,
   60,  278,   62,   41,  258,   41,  257,   39,  109,   63,
   44,   41,  257,  114,  279,   44,   70,   41,   36,   44,
  274,  275,  261,  262,   60,   59,   62,   85,   86,   44,
   60,   41,   62,   44,  135,    3,   60,    5,   62,   44,
    8,   41,   94,   40,   73,   41,  257,  105,   59,  260,
   60,   41,   62,  264,   59,   40,   60,   43,   62,   45,
   60,   93,   62,  270,   60,  276,   62,   99,  100,  121,
   60,  103,   62,  257,   60,  129,   62,  167,  107,  169,
   60,   59,   62,  257,  174,  143,  260,   55,   59,   43,
  264,   45,  144,  111,  257,  258,  270,  260,   42,   40,
  258,  264,  276,   47,  267,  257,  269,   41,   76,  267,
  162,  274,  275,  276,  277,   40,  274,  275,  147,   59,
   41,  257,   43,  257,   45,  273,  260,   41,  157,  158,
  264,  262,  263,  257,  257,  269,  260,  166,  257,  171,
  264,  260,  276,  277,  268,  264,  178,   83,   84,  268,
   59,   41,  276,   43,   41,   45,   43,  276,   45,   59,
   41,  193,   43,  195,   45,   41,  198,   43,   59,   45,
   41,  263,  257,   59,  270,   41,  269,  263,   41,   59,
   59,  270,  257,  280,  263,  269,  269,  258,  269,  261,
  258,  258,  267,  265,  266,  261,  269,   59,  257,  265,
  266,  261,  280,  274,  275,  265,  266,  274,  275,  281,
  282,  283,  284,  272,  273,  281,  282,  283,  284,   58,
  258,  281,  282,  283,  284,  261,   41,  271,   40,  265,
  266,  261,  257,  258,   58,  265,  266,  261,   40,   40,
   40,  265,  266,  271,   40,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,   59,  270,   59,  265,  266,  268,   59,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,  268,
   40,  281,  282,  283,  284,  281,  282,  283,  284,  279,
  270,  281,  282,  283,  284,  268,   40,   59,   40,  270,
   59,   59,   59,  270,   59,  270,   59,   59,   59,   59,
   59,    8,   45,   76,  158,   97,  104,   -1,  122,
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
"programa : ID BEGIN conjunto_sentencias_declarativas sentencia_ejecutable END ';'",
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
"error_sentencias_declarativas : tipo FUNC ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END",
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
"sentencia_salida : PRINT '(' CADENA ')' ';'",
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

//#line 146 "g19_gramatica.y"
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
//#line 428 "Parser.java"
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
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 13:
//#line 33 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 19:
//#line 43 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 20:
//#line 44 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 21:
//#line 45 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 22:
//#line 46 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 23:
//#line 47 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 34:
//#line 71 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 35:
//#line 72 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 41:
//#line 87 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 52:
//#line 106 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 53:
//#line 107 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 71:
//#line 139 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
//#line 629 "Parser.java"
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
