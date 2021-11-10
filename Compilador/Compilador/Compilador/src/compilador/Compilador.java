package compilador;


import compilador.Semantico.Parser;
import compilador.simbolo.TablaSimbolos;
import compilador.util.CodigoFuente;

import compilador.util.*;

public class Compilador {
    public static Parser parser ;

    // Inicializo reservadas
    private static void inicPalabrasReseradas() {
        TablaPalabrasReserv.clear();
        TablaPalabrasReserv.agregar("ID", Parser.ID);
        TablaPalabrasReserv.agregar("CADENA", Parser.CADENA);
        TablaPalabrasReserv.agregar("IF", Parser.IF);
        TablaPalabrasReserv.agregar("THEN", Parser.THEN);
        TablaPalabrasReserv.agregar("ELSE", Parser.ELSE);
        TablaPalabrasReserv.agregar("ENDIF", Parser.ENDIF);
        TablaPalabrasReserv.agregar("PRINT", Parser.PRINT);
        TablaPalabrasReserv.agregar("FUNC", Parser.FUNC);
        TablaPalabrasReserv.agregar("RETURN", Parser.RETURN);
        TablaPalabrasReserv.agregar("BEGIN", Parser.BEGIN);
        TablaPalabrasReserv.agregar("END", Parser.END);
        TablaPalabrasReserv.agregar("BREAK", Parser.BREAK);
        TablaPalabrasReserv.agregar("DOUBLE", Parser.DOUBLE);
        TablaPalabrasReserv.agregar("ULONG", Parser.ULONG);
        TablaPalabrasReserv.agregar("REPEAT", Parser.REPEAT);
        TablaPalabrasReserv.agregar("TRY", Parser.TRY);
        TablaPalabrasReserv.agregar("CATCH",Parser.CATCH);
        TablaPalabrasReserv.agregar("PRE", Parser.PRE);
    }
    // Inicializo Tokens

    private static void inicTokens (){
        AlmacenToken.clear();
        AlmacenToken.add(AnalizadorLex.T_EOF,"EOF");
        AlmacenToken.add((short) ':', ":");
        AlmacenToken.add((short) '(', "(");
        AlmacenToken.add((short) ')', ")");
        AlmacenToken.add((short) '<',"<");
        AlmacenToken.add((short) '>',">");
        AlmacenToken.add((short) '=',"=");
        AlmacenToken.add((short)',',",");
        AlmacenToken.add(Parser.MENOR_IGUAL,"<=");
        AlmacenToken.add(Parser.MAYOR_IGUAL,">=");
        AlmacenToken.add(Parser.DISTINTO,"<>");
        AlmacenToken.add(Parser.IGUAL_IGUAL,"==");
        AlmacenToken.add(Parser.ASIGNACION,":=");
        AlmacenToken.add(Parser.AND,"&&");
        AlmacenToken.add(Parser.OR,"||");

    }
    public void imprimirFinal(TablaSimbolos tabla){
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("----Tabla de Simbolos----" );
        System.out.print(tabla.toString());
        System.out.println("------------------------------------------------------");
        System.out.println("----Errores----");
        System.out.println(Notificacion.getErrores());
        System.out.println("------------------------------------------------------");
        System.out.println("----Warnings----");
        System.out.println(Notificacion.getWarnings());
        System.out.println("----------------------------------------------------");

    }
    public void ejecutar(String patshSrc){
        TablaSimbolos ts  = new TablaSimbolos();
        // empieza a compilar
        if(ManejadorArchivo.existeArchivo(patshSrc)){
            // existe el archivo pruebo
            inicTokens();
            inicPalabrasReseradas();
            CodigoFuente codigo = new CodigoFuente(ManejadorArchivo.getfuente(patshSrc));
            AnalizadorLex lexico = new AnalizadorLex(codigo,ts);
            parser = new Parser(lexico,ts);
            parser.run();
            imprimirFinal(ts);
        }
        else {
            System.out.println("no existe el archivo :   " + patshSrc);
        }
    }

    public static String traductor(int token){
        switch (token){
            case Parser.ID: return " PR ID";
            case Parser.ASIGNACION: return "ASIGNACION";
            case Parser.BEGIN: return "PR BEGIN";
            case Parser.CADENA: return " PR Cadena";
            case Parser.IF: return " PR IF";
            case Parser.THEN: return " PR THEN";
            case Parser.ELSE: return " PR ELSE";
            case Parser.ENDIF: return " PR ENDIF";
            case Parser.PRINT: return " PR PRINT";
            case Parser.FUNC: return "PR FUNC";
            case Parser.DOUBLE: return " PR DOUBLE";
            case Parser.ULONG: return "PR ULONG";
            case Parser.BREAK: return "PR BREAK";
            case Parser.TRY: return "PR TRY";
            case Parser.CATCH: return "PR CATCH";
            case Parser.PRE: return "PR PRE";
            case Parser.CTE_DOUBLE: return "CTE_DOUBLE";
            case Parser.CTE_ULONG: return "CTE_ULONG";
            case Parser.END: return "END";
            case Parser.DISTINTO:return "DISTINTO";
            case Parser.AND:return "AND";
            case Parser.OR:return "OR";
            case Parser.REPEAT:return "REPEAT";
            case Parser.IGUAL_IGUAL: return "IGUAL_IGUAL";
            case Parser.MAYOR_IGUAL:return "MAYORIGUAL";
            case Parser.MENOR_IGUAL:return "MENORIGUAL";
            case Parser.RETURN:return "PR RETURN";
            case 60:return " MENOR";
            case 59 : return ";";
            case 43 :return "+";
            case 45: return "-";
            case 47: return "/";
            case 42: return "*";
            case 40 :return "(";
            case 41 :return ")";
            case 44 :return ",";
            case 32 :return ":";
            default: return  "no se reconocio";

        }
    }
}
