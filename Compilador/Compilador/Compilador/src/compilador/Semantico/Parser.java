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
<<<<<<< HEAD
    1,    0,    2,    2,    3,    3,    3,    3,    5,    5,
    5,    5,    7,    7,    4,   14,    4,   18,    4,    4,
   16,   16,   13,   13,    6,    6,   11,   11,   11,   22,
   22,   20,   21,    9,    9,    9,   25,   26,   26,   27,
   27,   27,   27,   27,   27,   10,   10,   28,   28,   12,
   12,   12,   23,    8,   19,   19,   19,   17,   17,   17,
   29,   29,   29,   24,   24,   24,   24,   15,
};
final static short yylen[] = {                            2,
    0,    7,    1,    2,    1,    4,    7,    1,    2,    2,
    1,    1,    6,    4,    3,    0,   16,    0,   22,    5,
    1,    2,    1,    3,    1,    2,    6,    7,    1,    5,
    6,    2,    2,    9,   10,    1,    8,    1,    1,    1,
    1,    1,    1,    1,    1,    4,    1,    3,    3,    1,
    1,    1,    3,    3,    3,    3,    3,    3,    3,    1,
    3,    3,    1,    1,    1,    1,    2,    2,
};
final static short yydefred[] = {                         0,
    1,    0,    0,    0,   52,    0,   51,   50,    0,    3,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    4,    5,    8,    0,    0,   11,   12,   29,   36,   47,
   23,    0,    0,    0,   18,    0,    0,    0,    0,   25,
    0,    0,    0,    0,    9,   10,   16,   15,    0,    0,
    0,   64,   66,   65,    0,    0,   63,    0,    0,    0,
   48,    0,   49,    0,   26,    0,    0,    0,    0,    0,
    2,    0,   24,   20,    0,   67,    0,    0,    0,    0,
   42,   43,   45,   44,   40,   41,    0,    0,   38,   39,
    0,    0,    0,    0,   46,    6,    0,    0,    0,    0,
    0,   14,    0,    0,    0,    0,    0,   61,   62,    0,
   32,    0,    0,    0,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   68,    0,   33,   27,    0,   31,
    0,    0,   13,    0,    0,    0,   28,    0,    0,    7,
    0,    0,    0,   37,    0,    0,    0,   21,    0,    0,
   35,    0,   22,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   17,    0,    0,    0,    0,    0,   19,
};
final static short yydgoto[] = {                          2,
    3,    9,   20,   10,   22,   41,   23,   24,   25,   26,
   27,   11,   33,   72,  105,  149,   59,   51,   60,   92,
  114,   28,   68,   57,   29,   93,   94,   30,   58,
};
final static short yysindex[] = {                      -248,
    0,    0, -254, -122,    0, -154,    0,    0, -151,    0,
 -203,  -15, -186, -205,   50,  -37, -130,   72, -130, -155,
    0,    0,    0,   60,   70,    0,    0,    0,    0,    0,
    0, -135,  -22, -120,    0,  -44,  -44,   91,  -36,    0,
 -225, -106, -235,  101,    0,    0,    0,    0,  -96,  -21,
  122,    0,    0,    0, -104,   39,    0,    5,   25,   -5,
    0,  126,    0,  112,    0,   37,  114,  115, -130,  -52,
    0,  135,    0,    0, -199,    0,  -44,  -44,  -44,  -44,
    0,    0,    0,    0,    0,    0,  -44, -211,    0,    0,
 -173,  -87,  -44,  -44,    0,    0,  -44,  -80,  -44,  -92,
 -130,    0, -199,  -78,  139,    5,    5,    0,    0,   39,
    0, -211, -107,  -82,    0,   39,   39,   39,   17,  123,
  142,  127,  -86,  144,    0, -122,    0,    0,  -76,    0,
  -44, -211,    0,  129, -122, -125,    0,  148,  -81,    0,
 -110,  -89, -211,    0, -130,  134,  -77,    0, -129,  153,
    0,  155,    0,  -44,  -44,   21,   53,  137,  -73,  -70,
  140,  160,    0,  -44,  125,  143,  -69,  145,    0,
=======
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
    8,    1,    1,    1,    1,    1,    1,    1,    1,    4,
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
   50,    5,    0,    0,    0,    0,    0,   13,    0,    0,
    0,    0,   72,    0,    0,    0,   65,   66,    0,   36,
    0,    0,    0,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   37,   31,    0,
   35,    0,    0,   12,    0,    0,    0,   24,    0,    0,
   32,    0,    0,    6,   25,    0,    0,    0,    0,    0,
    0,   41,    0,   26,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   22,    0,    0,    0,    0,
    0,    0,    0,    0,   15,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   21,    0,   23,
   16,
};
final static short yydgoto[] = {                          2,
    8,   21,    9,   23,   44,   24,   25,   26,   27,   28,
  111,   35,   81,  112,  156,   63,   64,   11,   98,  123,
   29,   72,   61,   30,   99,  100,   31,   62,
};
final static short yysindex[] = {                      -248,
 -246,    0, -120,    0,    6,    0,    0, -132,    0,  -44,
    0,    9, -214, -184, -202,   44,  -37, -206,   46, -206,
 -176,    0,    0,    0,   47,   49,    0,    0,    0,    0,
    0,    0, -157,    0,  -31, -214,  -12,   64,  -28,  -28,
   79,   -7,    0, -143, -135, -204,   77,    0,    0,  112,
    0, -100,  102,    0,  -40,    0,    0,    0, -111,   -8,
    0,   38,   45,   -5,    0,  127,    0,  116,    0,   51,
  119,  121, -206,  -52,    0,  -36,    0,    0, -124,  -86,
  146,    0,  -28,  -28,  -28,  -28,    0,    0,    0,    0,
    0,    0,  -28, -104,    0,    0, -221,  -74,  -28,  -28,
    0,    0,  -28,  -63,  -28,  -78, -206,    0, -124,  163,
 -214,  -62,    0, -124,   38,   38,    0,    0,   -8,    0,
 -104, -133,  -57,    0,   -8,   -8,   -8,   27,  149,  168,
  151,  -59,  -55, -124,  105,  -68,  -54,    0,    0,  -13,
    0,  -28, -104,    0,  169, -206,  -53,    0,  161,  -47,
    0,  186,  -14,    0,    0, -145, -206,  218,  201,  225,
 -104,    0,  229,    0,  -94,  -28,  230,  -28,    3,  -28,
  231,   21,  -28,   35,    0,  140,  -28,  216,   41,  234,
   24,  145,   30,  236,   52,    0,   29,  281,   68,  291,
  278,  -28,  298,  -28,    0,  154,  -28,  157,  280,  160,
  282,   70,  283,   73,  285,   75,  287,    0,  288,    0,
    0,
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
<<<<<<< HEAD
    0,    0,    0,    0,    0,  146,    0,  -41,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
=======
    0,    0,    0,    0,    0,    0,    0,    0,    0,  289,
    0,  -41,    0,    0,    0,    0,    0,    0,    0,    0,
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -35,  -29,    0,    0,    1,
    0,    0,    0,    0,    0,    7,   31,  147,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
<<<<<<< HEAD
=======
    0,    0,    0,    0,  -35,  -29,    0,    0,    1,    0,
    0,    0,    0,    0,    7,   31,  290,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  149,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
<<<<<<< HEAD
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -18,  -32,    2,    9,    0,    0,  161,    0,    0,
    0,   11,  173,    0,  106,    0,    8,    0,   56,  120,
   99,    0,  116,  -39,    0,    0,  -46,    0,   63,
};
final static int YYTABLESIZE=321;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
   55,   60,   39,   60,   63,   58,  102,   58,    1,   58,
   21,   59,   87,   59,    4,   59,   13,   60,   60,   97,
   60,   49,   49,   58,   58,   40,   58,   43,   34,   59,
   59,   14,   59,   69,   15,   91,   48,   74,   16,  108,
  109,   57,   70,   56,   64,   14,   79,   56,   15,   65,
   18,   80,   16,   31,   85,  111,   86,   17,    5,  121,
   57,  158,   57,   32,   18,   19,   56,   77,   56,   78,
   35,   55,   97,   36,    7,    8,   85,  100,   86,  127,
   85,   77,   86,   78,   85,  104,   86,   88,  112,   37,
   55,  138,   55,  159,  110,   77,   85,   78,   86,  139,
  116,  117,   12,    5,  118,   14,    5,  136,   15,  123,
  147,   42,   16,  104,   44,    6,  141,   17,   45,    7,
    8,   47,    7,    8,   18,   19,   14,   14,   46,   15,
   15,   61,    5,   16,   16,    5,   31,   21,  152,  106,
  107,    6,   21,  142,    6,   18,   18,    5,    7,    8,
   66,    7,    8,  148,  112,  128,    6,  153,  145,   71,
   73,   75,  157,    7,    8,  166,   95,   77,   76,   78,
   96,  165,   98,   99,  103,  115,  119,  122,  125,  126,
  130,  131,  132,  134,  135,  133,  137,  140,  143,  144,
  146,  150,  154,  151,  155,  160,  161,  162,  163,  164,
  168,  167,   67,  169,   54,   53,   50,   34,  124,  156,
  113,  129,   52,  120,    0,    0,  101,    0,    0,   60,
   38,   62,    0,   60,   60,   58,    0,   53,   54,   58,
   58,   59,    0,    0,    0,   59,   59,    0,    0,   60,
   60,   60,   60,    0,    0,   58,   58,   58,   58,    0,
    0,   59,   59,   59,   59,   88,    0,    0,    0,   89,
   90,   57,    0,    0,    0,   57,   57,   56,    0,    0,
    0,   56,   56,    0,    0,   81,   82,   83,   84,    0,
    0,   57,   57,   57,   57,   89,   90,   56,   56,   56,
   56,   55,    0,    0,    0,   55,   55,   81,   82,   83,
   84,   81,   82,   83,   84,   81,   82,   83,   84,    0,
    0,   55,   55,   55,   55,   36,    0,   81,   82,   83,
   84,
=======
    0,    0,    0,    0,    0,    0,    0,    0,  292,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -65,  342,    2,    0,    0,  307,    0,    0,    0,
   63,   13,  277,  -70,  197,   -1,   11,    0,  258,  235,
    0,  252,  -26,    0,    0,  -18,    0,   57,
};
final static int YYTABLESIZE=357;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         64,
   79,   64,   42,   64,  109,   62,  108,   62,    1,   62,
  160,   63,   52,   63,   34,   63,   59,   64,   64,   43,
   64,   46,    3,   62,   62,   37,   62,   51,  120,   63,
   63,   52,   63,   67,   83,   97,   84,   60,  133,   94,
  121,   61,   32,  137,   93,   69,   54,   60,   53,   13,
   15,  103,   36,   16,   91,  138,   92,   17,  117,  118,
   61,  178,   61,  147,   73,   10,   60,   14,   60,   19,
   10,   59,   38,   74,  106,  180,   39,  153,  130,   85,
   91,  184,   92,   40,   86,   45,   91,   83,   92,   84,
   59,  119,   59,   47,   91,  169,   92,  125,  126,   50,
   91,  127,   92,   55,   91,   48,   92,   49,  132,  103,
   91,   15,   92,   15,   16,  152,   16,   80,   17,   65,
   17,   70,  163,  135,   15,    4,   68,   16,  121,  139,
   19,   17,   19,    4,    5,   75,   18,    4,   80,  115,
  116,    6,    7,   19,   20,   52,    5,  155,   52,    6,
    7,   76,   15,    6,    7,   16,   77,  164,  155,   17,
   78,   82,   15,  148,   18,   16,  164,  101,  176,   17,
  113,   19,   20,  171,  102,  182,  172,  104,  174,  105,
  181,   19,   83,  179,   84,  187,  114,   83,  124,   84,
  196,  131,  198,  128,  199,  200,   83,  201,   84,   83,
  203,   84,   83,  134,   84,  141,  136,  142,  143,  144,
  145,  149,   32,  146,  150,  157,  107,    4,  158,   64,
   41,    4,   33,   64,   64,   62,  161,  154,   56,   62,
   62,   63,  159,    6,    7,   63,   63,    6,    7,   64,
   64,   64,   64,   57,   58,   62,   62,   62,   62,  151,
   66,   63,   63,   63,   63,   94,  162,  166,  167,   95,
   96,   61,   12,    4,  168,   61,   61,   60,  170,  173,
  177,   60,   60,  175,  183,   87,   88,   89,   90,    6,
    7,   61,   61,   61,   61,   95,   96,   60,   60,   60,
   60,   59,  185,  186,  189,   59,   59,  188,  191,   95,
   96,   87,   88,   89,   90,   95,   96,   87,   88,   89,
   90,   59,   59,   59,   59,   87,   88,   89,   90,  190,
  192,   87,   88,   89,   90,   87,   88,   89,   90,   39,
  194,   87,   88,   89,   90,  193,  195,  197,  202,  205,
  204,  206,  207,  208,  209,  210,  211,   58,   57,   22,
   38,   71,  110,  165,  122,  129,  140,
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
<<<<<<< HEAD
   45,   43,   40,   45,   41,   41,   59,   43,  257,   45,
    9,   41,   59,   43,  269,   45,    6,   59,   60,   66,
   62,   44,   44,   59,   60,   17,   62,   19,   44,   59,
   60,  257,   62,  269,  260,   41,   59,   59,  264,   79,
   80,   41,  278,   36,  270,  257,   42,   41,  260,   41,
  276,   47,  264,  257,   60,   88,   62,  269,  258,   99,
   60,   41,   62,  267,  276,  277,   60,   43,   62,   45,
  257,   41,  119,  279,  274,  275,   60,   69,   62,  112,
   60,   43,   62,   45,   60,   75,   62,  261,  262,   40,
   60,  131,   62,   41,   87,   43,   60,   45,   62,  132,
   93,   94,  257,  258,   97,  257,  258,  126,  260,  101,
  143,   40,  264,  103,  270,  267,  135,  269,   59,  274,
  275,  257,  274,  275,  276,  277,  257,  257,   59,  260,
  260,   41,  258,  264,  264,  258,  257,  136,  268,   77,
   78,  267,  141,  269,  267,  276,  276,  258,  274,  275,
  257,  274,  275,  145,  262,  263,  267,  149,  269,   59,
  257,   40,  155,  274,  275,   41,   41,   43,  273,   45,
   59,  164,   59,   59,   40,  263,  257,  270,  257,   41,
  263,   59,   41,  270,   41,   59,  263,   59,   41,  271,
  280,   58,   40,  271,   40,   59,  270,  268,   59,   40,
  270,   59,   42,   59,   59,   59,   34,   59,  103,  154,
   91,  113,  257,   98,   -1,   -1,  269,   -1,   -1,  261,
  258,  258,   -1,  265,  266,  261,   -1,  272,  273,  265,
  266,  261,   -1,   -1,   -1,  265,  266,   -1,   -1,  281,
  282,  283,  284,   -1,   -1,  281,  282,  283,  284,   -1,
   -1,  281,  282,  283,  284,  261,   -1,   -1,   -1,  265,
  266,  261,   -1,   -1,   -1,  265,  266,  261,   -1,   -1,
   -1,  265,  266,   -1,   -1,  281,  282,  283,  284,   -1,
   -1,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,   -1,   -1,   -1,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,   -1,
   -1,  281,  282,  283,  284,  279,   -1,  281,  282,  283,
  284,
=======
   41,   43,   40,   45,   41,   41,   59,   43,  257,   45,
   58,   41,   44,   43,   59,   45,   45,   59,   60,   18,
   62,   20,  269,   59,   60,   13,   62,   59,   94,   59,
   60,   44,   62,   41,   43,   41,   45,   39,  109,  261,
  262,   41,  257,  114,   63,   44,   59,   41,   36,   44,
  257,   70,   44,  260,   60,  121,   62,  264,   85,   86,
   60,   41,   62,  134,  269,    3,   60,    5,   62,  276,
    8,   41,  257,  278,   73,   41,  279,  143,  105,   42,
   60,   41,   62,   40,   47,   40,   60,   43,   62,   45,
   60,   93,   62,  270,   60,  161,   62,   99,  100,  257,
   60,  103,   62,   40,   60,   59,   62,   59,  107,  128,
   60,  257,   62,  257,  260,  142,  260,   55,  264,   41,
  264,  257,  268,  111,  257,  258,  270,  260,  262,  263,
  276,  264,  276,  258,  267,   59,  269,  258,   76,   83,
   84,  274,  275,  276,  277,   44,  267,  146,   44,  274,
  275,   40,  257,  274,  275,  260,  257,  156,  157,  264,
   59,  273,  257,   59,  269,  260,  165,   41,  170,  264,
  257,  276,  277,  268,   59,  177,  166,   59,  168,   59,
   41,  276,   43,  173,   45,   41,   41,   43,  263,   45,
  192,  270,  194,  257,   41,  197,   43,   41,   45,   43,
   41,   45,   43,   41,   45,  263,  269,   59,   41,   59,
  270,  280,  257,  269,  269,  269,  269,  258,   58,  261,
  258,  258,  267,  265,  266,  261,   41,   59,  257,  265,
  266,  261,  280,  274,  275,  265,  266,  274,  275,  281,
  282,  283,  284,  272,  273,  281,  282,  283,  284,  263,
  258,  281,  282,  283,  284,  261,  271,   40,   58,  265,
  266,  261,  257,  258,   40,  265,  266,  261,   40,   40,
   40,  265,  266,  271,   59,  281,  282,  283,  284,  274,
  275,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  261,   59,  270,   59,  265,  266,  268,  270,  265,
  266,  281,  282,  283,  284,  265,  266,  281,  282,  283,
  284,  281,  282,  283,  284,  281,  282,  283,  284,  268,
   40,  281,  282,  283,  284,  281,  282,  283,  284,  279,
   40,  281,  282,  283,  284,  268,   59,   40,   59,  270,
   59,   59,  270,   59,  270,   59,   59,   59,   59,    8,
   59,   45,   76,  157,   97,  104,  122,
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
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
"$$2 :",
"sentencia_declarativa : tipo FUNC ID $$2 '(' parametro ')' conjunto_sentencias_declarativas BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END ';'",
"$$3 :",
"sentencia_declarativa : FUNC tipo ID $$3 '(' parametro ')' conjunto_sentencias_declarativas BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"sentencia_declarativa : FUNC ID ',' lista_variables ';'",
<<<<<<< HEAD
=======
"sentencia_declarativa : error_sentencias_declarativas",
"error_sentencias_declarativas : tipo ';'",
"error_sentencias_declarativas : FUNC ',' lista_variables ';'",
"error_sentencias_declarativas : FUNC tipo ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN PRE ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"error_sentencias_declarativas : tipo FUNC ID '(' ')' conjunto_sentencias_declarativas_funcion BEGIN conjunto_sentencias_ejecutables_funcion RETURN '(' expresion ')' END",
"error_sentencias_declarativas : FUNC tipo ID '(' parametro ')' conjunto_sentencias_declarativas_funcion BEGIN ':' '(' condicion ')' ';' RETURN '(' expresion ')' ';' END ';'",
"conjunto_sentencias_declarativas_funcion : tipo lista_variables ';'",
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
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

<<<<<<< HEAD
//#line 157 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";
Terceto t;
public  static String ambitoActual;
=======
//#line 145 "g19_gramatica.y"
private  AnalizadorLex aLexico;
private TablaSimbolos ts;
private String aux_negacion = "";

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
<<<<<<< HEAD
//#line 405 "Parser.java"
=======
//#line 428 "Parser.java"
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
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
<<<<<<< HEAD
case 1:
//#line 14 "g19_gramatica.y"
{ambitoActual=val_peek(0).sval;}
break;
case 2:
//#line 15 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(6).sval,"nombre_programa");}
break;
case 13:
//#line 34 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el CATCH");}
break;
case 14:
//#line 35 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta el ejecutable");}
break;
case 16:
//#line 39 "g19_gramatica.y"
{ambitoActual=ambitoActual+"."+val_peek(0).sval;}
break;
case 17:
//#line 40 "g19_gramatica.y"
{
                                                                                                                                                                    /*Cambiara el ambito de las variables en caso de ser necesario*/
                                                                                                                                                                    StringBuilder reverse = new StringBuilder( ambitoActual);
                                                                                                                                                                    StringBuilder aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                                    reverse = new StringBuilder(aux.substring(aux.lastIndexOf(".")+1, aux.length()));
                                                                                                                                                                    aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                                    ambitoActual=aux.toString();
                                                                                                                                                                    t.agregarUsoVariablesTS(val_peek(13).sval,"nombre_funcion");
		                                                                                                                                                         }
break;
case 18:
//#line 49 "g19_gramatica.y"
{ambitoActual=ambitoActual+"."+val_peek(0).sval;}
break;
case 19:
//#line 50 "g19_gramatica.y"
{            StringBuilder reverse = new StringBuilder( ambitoActual);
                                                                                                                                                        StringBuilder aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                        reverse = new StringBuilder(aux.substring(aux.lastIndexOf(".")+1, aux.length()));
                                                                                                                                                        aux = new StringBuilder(reverse.reverse().toString());
                                                                                                                                                        ambitoActual=aux.toString();
                                                                                                                                                        t.agregarUsoVariablesTS(val_peek(19).sval,"nombre_funcion");
		                                                                                                                                                   }
break;
case 20:
//#line 57 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(3).sval,"variable");}
break;
case 23:
//#line 67 "g19_gramatica.y"
{t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
                       ts.cambiarNombre(val_peek(0).sval);}
break;
case 24:
//#line 69 "g19_gramatica.y"
{ t.agregarUsoVariablesTS(val_peek(0).sval,"variable");
		                           ts.cambiarNombre(val_peek(0).sval);}
break;
case 30:
//#line 82 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 31:
//#line 83 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 37:
//#line 98 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 48:
//#line 117 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 49:
//#line 118 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 67:
//#line 150 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
case 68:
//#line 153 "g19_gramatica.y"
{ t.agregarUsoVariablesTS(val_peek(0).sval,"variable");}
break;
//#line 642 "Parser.java"
=======
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
//#line 70 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de cierre ')' "); }
break;
case 35:
//#line 71 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() + "| falta la rama 'THEN' "); }
break;
case 41:
//#line 86 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la asignación "); }
break;
case 52:
//#line 105 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta el parentesis de apertura de la sentencia PRINT ");}
break;
case 53:
//#line 106 "g19_gramatica.y"
{Notificacion.addError(aLexico.getLineaActual()," Error semántico en la linea : "  + aLexico.getLineaActual() +  "| falta la cadena a imprimir");}
break;
case 71:
//#line 138 "g19_gramatica.y"
{ ts.cambiarNegativo(val_peek(0).sval,aLexico); }
break;
//#line 629 "Parser.java"
>>>>>>> 515a6264eb4d7dedb908f675e6e74f4e54bb0e8a
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
