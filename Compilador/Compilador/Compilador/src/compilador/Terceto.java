package compilador;

import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;

import java.util.ArrayList;

public class Terceto {
    public String operador;
    public String operando1;
    public String operando2;
    public String tipo;
    public String variableAuxiliar;
    public static ArrayList<String> codigo = new ArrayList<String>();
    public static AnalizadorLex al;
    public static int contadorVariableAuxiliar = 0;
    public TablaSimbolos tablaSimbolos;

    public Terceto(String operador,String operando1,String operando2, String tipo,TablaSimbolos tablaSimbolos) {
        this.operador = operador;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.tipo = tipo;
        this.tablaSimbolos = tablaSimbolos;
        this.variableAuxiliar = "_var" + contadorVariableAuxiliar++;
    }
    public boolean tiposCompatibles(){
        if (!tablaSimbolos.getTipo(operando1).equals(tablaSimbolos.getTipo(operando2).equals(tipo))) {
            Notificacion.addError(al.getLineaActual(),"Tipos Incompatibles");
            return false;
        }
        return true;
    }


    public static void  CrearCodigo(){}

}
