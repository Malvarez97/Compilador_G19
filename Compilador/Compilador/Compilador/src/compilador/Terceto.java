package compilador;

import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;

import java.util.ArrayList;
import java.util.HashMap;

public class Terceto {
    public String operador;
    public String operando1;
    public String operando2;
    public String tipo;
    public String variableAuxiliar;
    public static ArrayList<String> codigo = new ArrayList<String>();
    public static AnalizadorLex al;
    // Pense en ir metiendo en cada lista dependiendo el tipo y luego de esa lista agregarselo, aunque quizas se podria hacer antes.
    public static ArrayList<String> variables= new ArrayList<String>();
    public static ArrayList<String> nombrefuncion= new ArrayList<String>();
    public static ArrayList<String> nombreparametro= new ArrayList<String>();



    public static int contadorVariableAuxiliar = 0;
    public TablaSimbolos tablaSimbolos;
    ArrayList<Terceto> tercetos = new ArrayList<Terceto>();

    HashMap<String,String> noTerminalTercetos = new HashMap<String,String>();
    String ambito ;

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

    public void insertarNoTerminal(String noTerminal, String value){
        if(!Notificacion.hayErrores()){
            noTerminalTercetos.put(noTerminal,value); // no se para que ingreso el noterminal ..
        }
    }
    // retorno un string del terceto tal cual la catedra, este terceto se utiliza para luego ser recorrido en el generar archivo
    public String crearTerceto(String operador,String operando1,String operando2,String vtipo) {
        if(!Notificacion.hayErrores()){
            tercetos.add(new Terceto(operador,operando1,operando2,vtipo,this.tablaSimbolos));
            return new String("["+Integer.toString(tercetos.size()-1)+"]");
        }else{
            return null;
        }
    }

    public String cambiarVariableAmbito (String variable){
        return ambito+"."+variable;
    }
    // hay que setear el uso de la varibla como por ejemplo, variable, nombre funcion, nombre de parametro, etc
    public void agregarUsoVaribalesTS(String uso){
        for (String s: variables){
            // aca hay que poner el uso, el problema es de donde lo saco
            tablaSimbolos.getEntrada(s).setUso("poner uso ");
        }
        variables.clear();
    }




    public static void  CrearCodigo(){}

}
