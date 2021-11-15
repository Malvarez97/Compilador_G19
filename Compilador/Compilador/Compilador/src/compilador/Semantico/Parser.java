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
    0,    1,    1,    2,    2,    2,    2,    4,    4,    4,
    4,    6,    6,    3,    3,    3,    3,    3,   18,   18,
   18,   18,   14,   15,   15,   12,   12,    5,    5,   10,
   10,   10,   21,   21,   19,   20,    8,    8,    8,   24,
   25,   25,   26,   26,   26,   26,   26,   26,    9,    9,
   27,   27,   11,   11,   11,   22,    7,   17,   17,   17,
   16,   16,   16,   28,   28,   28,   23,   23,   23,   23,
   13,
};
final static short yylen[] = {                            2,
    6,    1,    2,    1,    4,    7,    1,    2,    2,    1,
    1,    6,    4,    3,   15,   21,    5,    1,    2,    4,
   20,   20,    3,    1,    2,    1,    3,    1,    2,    6,
    7,    1,    5,    6,    2,    2,    9,   10,    1,    8,
    1,    1,    1,    1,    1,    1,    1,    1,    4,    1,
    3,    3,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    1,    3,    3,    1,    1,    1,    1,    2,
    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   55,    0,   54,   53,    0,    2,    0,
   18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    3,    4,    7,    0,    0,   10,   11,   32,   39,
   50,   26,    0,   19,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    8,    9,    0,
   14,    0,    0,   20,    0,   67,   69,   68,    0,    0,
   66,    0,    0,    0,   51,    0,   52,    0,   29,    0,
    0,    0,    0,    0,    1,    0,   27,   17,    0,    0,
    0,   70,    0,    0,    0,    0,   45,   46,   48,   47,
   43,   44,    0,    0,   41,   42,    0,    0,    0,    0,
   49,    5,    0,    0,    0,    0,    0,   13,    0,    0,
    0,   71,    0,    0,    0,   64,   65,    0,   35,    0,
    0,    0,   33,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   36,   30,    0,   34,    0,
    0,   12,    0,    0,   23,    0,    0,   31,    0,    0,
    6,    0,    0,    0,    0,    0,   40,   24,    0,    0,
    0,    0,    0,    0,   25,    0,    0,    0,   38,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   21,    0,
   22,   16,
};
final static short yydgoto[] = {                          2,
    8,   21,    9,   23,   44,   24,   25,   26,   27,   28,
  110,   35,   81,  111,  159,   63,   64,   11,   98,  122,
   29,   72,   61,   30,   99,  100,   31,   62,
};
final static short yysindex[] = {                      -228,
 -230,    0,  -97,    0,    6,    0,    0, -133,    0,   64,
    0,    2, -214, -203, -215,   44,  -37, -220,   46, -220,
 -195,    0,    0,    0,   37,   57,    0,    0,    0,    0,
    0,    0, -138,    0,  -10, -214,   56,   97,  -34,  -34,
   99,  -13,    0, -110, -100, -204,  103,    0,    0,  118,
    0,  -94,   73,    0,  -40,    0,    0,    0, -108,   85,
    0,   11,   45,   -5,    0,  123,    0,  109,    0,   51,
  121,  126, -220,  -52,    0, -223,    0,    0, -223,  -71,
  147,    0,  -34,  -34,  -34,  -34,    0,    0,    0,    0,
    0,    0,  -34, -131,    0,    0, -239,  -74,  -34,  -34,
    0,    0,  -34,  -67,  -34,  -79, -220,    0,  151, -214,
  -76,    0, -223,   11,   11,    0,    0,   85,    0, -131,
 -141,  -69,    0,   85,   85,   85,   27,  136,  155,  139,
  -73, -223,   76,  -81,  -68,    0,    0,  -63,    0,  -34,
 -131,    0,  143,  -66,    0,  146,  -53,    0,  164,  -65,
    0, -220,  167,  150,  169, -131,    0,    0, -109,  -34,
  170,  -34,  -60,  172,    0,   21,  -34,   35,    0,  -34,
  154,   41,  156,   67,  -54,  157,  -49,  -48,  188,  -39,
  193,  185,  -34,  210,  -34,    0,  130,  -34,  131,  192,
  138,  198,  -12,  200,    4,  206,    5,  211,    0,  212,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  234,
    0,  -41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -35,  -29,    0,    0,    1,    0,    0,
    0,    0,    0,    7,   31,  235,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  236,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -47,  261,   -3,    0,    0,  253,    0,    0,    0,
   63,   -4,  223,  -87,    0,   -1,  -82,    0,  239,  199,
    0,  233,  -26,    0,    0,  -50,    0,   69,
};
final static int YYTABLESIZE=337;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         63,
   79,   63,   42,   63,  155,   61,  108,   61,   37,   61,
   59,   62,   93,   62,   43,   62,   46,   63,   63,  103,
   63,   94,  120,   61,   61,  135,   61,   67,    1,   62,
   62,   53,   62,   52,    4,   97,   15,   60,    3,   16,
   69,   60,   32,   17,  144,   36,  119,   59,   51,   13,
    6,    7,   85,   38,   91,   19,   92,   86,  116,  117,
   60,  171,   60,   39,   73,   10,   59,   14,   59,  106,
   10,   58,  136,   74,   47,  173,  103,  166,  129,  168,
   91,  176,   92,   40,  172,   45,   91,   83,   92,   84,
   58,  118,   58,  150,   91,   48,   92,  124,  125,   52,
   91,  126,   92,  131,   91,  133,   92,  178,  163,   83,
   91,   84,   92,  149,   54,   49,   52,   80,   50,   52,
  120,  137,   34,   15,    4,   15,   16,   83,   16,   84,
   17,   78,   17,    5,  145,   18,   55,   18,   80,   65,
    6,    7,   19,   20,   19,   20,   15,   15,  158,   16,
   16,  114,  115,   17,   17,  165,   70,   76,  164,   68,
    4,   75,   77,  101,   82,   19,   19,  102,  174,    5,
  190,  192,   83,   83,   84,   84,    6,    7,  194,  104,
   83,  187,   84,  189,  105,  112,  191,  113,  123,  127,
  130,  132,  134,  139,  140,  141,  143,  142,  146,  148,
  147,  151,  152,  153,  156,  157,  160,  161,  162,  167,
  169,  170,  175,  179,  177,  180,  107,    4,  181,   63,
   41,  182,   56,   63,   63,   61,  154,  183,  184,   61,
   61,   62,  185,    6,    7,   62,   62,   57,   58,   63,
   63,   63,   63,  186,   66,   61,   61,   61,   61,  188,
  193,   62,   62,   62,   62,   94,  195,  196,  197,   95,
   96,   60,   12,    4,  199,   60,   60,   59,   22,  201,
  202,   59,   59,  198,  200,   87,   88,   89,   90,    6,
    7,   60,   60,   60,   60,   95,   96,   59,   59,   59,
   59,   58,   57,   56,   37,   58,   58,   71,  109,   95,
   96,   87,   88,   89,   90,   95,   96,   87,   88,   89,
   90,   58,   58,   58,   58,   87,   88,   89,   90,  138,
   32,   87,   88,   89,   90,   87,   88,   89,   90,   39,
   33,   87,   88,   89,   90,  121,  128,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   41,   43,   40,   45,   58,   41,   59,   43,   13,   45,
   45,   41,   63,   43,   18,   45,   20,   59,   60,   70,
   62,  261,  262,   59,   60,  113,   62,   41,  257,   59,
   60,   36,   62,   44,  258,   41,  257,   39,  269,  260,
   44,   41,  257,  264,  132,   44,   94,   41,   59,   44,
  274,  275,   42,  257,   60,  276,   62,   47,   85,   86,
   60,   41,   62,  279,  269,    3,   60,    5,   62,   73,
    8,   41,  120,  278,  270,   41,  127,  160,  105,  162,
   60,   41,   62,   40,  167,   40,   60,   43,   62,   45,
   60,   93,   62,  141,   60,   59,   62,   99,  100,   44,
   60,  103,   62,  107,   60,  110,   62,   41,  156,   43,
   60,   45,   62,  140,   59,   59,   44,   55,  257,   44,
  262,  263,   59,  257,  258,  257,  260,   43,  260,   45,
  264,   59,  264,  267,   59,  269,   40,  269,   76,   41,
  274,  275,  276,  277,  276,  277,  257,  257,  152,  260,
  260,   83,   84,  264,  264,  159,  257,   40,  268,  270,
  258,   59,  257,   41,  273,  276,  276,   59,  170,  267,
   41,   41,   43,   43,   45,   45,  274,  275,   41,   59,
   43,  183,   45,  185,   59,  257,  188,   41,  263,  257,
  270,   41,  269,  263,   59,   41,  270,   59,  280,  263,
  269,   59,  269,   58,   41,  271,   40,   58,   40,   40,
  271,   40,   59,  268,   59,   59,  269,  258,  268,  261,
  258,  270,  257,  265,  266,  261,  280,   40,  268,  265,
  266,  261,   40,  274,  275,  265,  266,  272,  273,  281,
  282,  283,  284,   59,  258,  281,  282,  283,  284,   40,
   59,  281,  282,  283,  284,  261,   59,  270,   59,  265,
  266,  261,  257,  258,   59,  265,  266,  261,    8,   59,
   59,  265,  266,  270,  270,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,   59,   59,   59,  265,  266,   45,   76,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,  121,
  257,  281,  282,  283,  284,  281,  282,  283,  284,  279,
  267,  281,  282,  283,  284,   97,  104,
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

//#line 156 "g19_gramatica.y"
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
//#line 424 "Parser.java"
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
{ t.nombrePrograma(val_peek(5).sval);
                                                                                   t.agregarUsoVariablesTS(val_peek(5).sval,"nombre_programa");
                                                                                  }
break;
case 12:
//#line 35 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 13:
//#line 36 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 15:
//#line 40 "g19_gramatica.y"
{
                                                                                                                                                                    /*Cambiara el ambito de las variables en caso de ser necesario*/
                                                                                                                                                                    t.cambiarAmbitoTerceto(val_peek(12).sval);
                                                                                                                                                                    t.agregarUsoVariablesTS(val_peek(12).sval,"nombre_funcion");

		                                                                                                                                                         }
break;
case 16:
//#line 46 "g19_gramatica.y"
{t.cambiarAmbitoTerceto(val_peek(18).sval);
		                                                                                                                                                   t.agregarUsoVariablesTS(val_peek(18).sval,"nombre_funcion");
		                                                                                                                                                   }
break;
case 17:
//#line 49 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(3).sval,"variable");}
break;
case 19:
//#line 53 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la lista de lista_variables");}
break;
case 20:
//#line 54 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ID");}
break;
case 21:
//#line 55 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el parametro");}
break;
case 22:
//#line 56 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el PRE");}
break;
case 26:
//#line 66 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
                      t.cambiarAmbitoVariable(val_peek(0).sval);}
break;
case 27:
//#line 68 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
		                         t.cambiarAmbitoVariable(val_peek(0).sval);}
break;
case 33:
//#line 81 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 34:
//#line 82 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 40:
//#line 97 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 51:
//#line 116 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 52:
//#line 117 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 70:
//#line 149 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
case 71:
//#line 152 "g19_gramatica.y"
{ t.agregarUsoVariablesTS(val_peek(0).sval,"variable");}
break;
//#line 660 "Parser.java"
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
