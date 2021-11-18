package compilador;

import compilador.simbolo.Casilla;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Terceto {
    public String operador;
    public String operando1;
    public String operando2;
    public String tipo;
    public String variableAuxiliar;
    public static ArrayList<String> codigo = new ArrayList<String>();
    public static AnalizadorLex al;
    // Pense en ir metiendo en cada lista dependiendo el tipo y luego de esa lista agregarselo, aunque quizas se podria hacer antes.
    public static ArrayList<String> variables = new ArrayList<String>();
    public static ArrayList<String> nombrefuncion = new ArrayList<String>();
    public static ArrayList<String> nombreparametro = new ArrayList<String>();
    public static ArrayList<Integer> pilaSaltos = new ArrayList<Integer>();
    public static ArrayList<Terceto> pilaExpreseionRepeat = new ArrayList<Terceto>();
    public String nombreprograma;
    public static ArrayList<String> variables_Ambito  = new ArrayList<String>();

    public static int contadorVariableAuxiliar = 0;
    public TablaSimbolos tablaSimbolos;
    ArrayList<Terceto> tercetos = new ArrayList<Terceto>();


    HashMap<String, String> noTerminalTercetos = new HashMap<String, String>();
    String ambito ="";

    public Terceto(String operador, String operando1, String operando2, String tipo, TablaSimbolos tablaSimbolos) {
        this.operador = operador;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.tipo = tipo;
        this.tablaSimbolos = tablaSimbolos;
        this.variableAuxiliar = "_var" + contadorVariableAuxiliar++;
    }


    // chequeo de tipos compatibles
    public boolean tiposCompatibles(String op1, String op2) {
        if (!tablaSimbolos.getTipo(op1).equals(tablaSimbolos.getTipo(op2).equals(tipo))) {
            Notificacion.addError(al.getLineaActual(), "Tipos Incompatibles");
            return false;
        }
        return true;
    }

    public void insertarNoTerminal(String noTerminal, String value) {
        if (!Notificacion.hayErrores()) {
            noTerminalTercetos.put(noTerminal, value); // no se para que ingreso el noterminal ..
        }
    }

    // retorno un string del terceto tal cual la catedra, este terceto se utiliza para luego ser recorrido en el generar archivo
    public String crearTerceto(String operador, String operando1, String operando2, String vtipo) {
        if (!Notificacion.hayErrores()) {
            tercetos.add(new Terceto(operador, operando1, operando2, vtipo, this.tablaSimbolos));
            return new String("[" + Integer.toString(tercetos.size() - 1) + "]");
        } else {
            return null;
        }
    }

    public void nombrePrograma(String m){
        this.nombreprograma = m;
        ambito=nombreprograma;
    }

    public void cambiarAmbitoFuncion(){
        for(int i=1; i<this.variables_Ambito.size(); i++){
            Casilla aux = tablaSimbolos.getEntrada(this.variables_Ambito.get(i));
            aux.setLexema(this.variables_Ambito.get(0)+"."+this.variables_Ambito.get(i));
        }
    }




    // hay que setear el uso de la varibla como por ejemplo, variable, nombre funcion, nombre de parametro, etc
    public void agregarUsoVaribalesTS(String valor,String uso){
            // aca hay que poner el uso, el problema es de donde lo saco
    }

    // se queda con los valores del numero del terceto
    public void apilarSalto(String numeroTerceto){
        if (!Notificacion.hayErrores()){
            pilaSaltos.add((Integer.parseInt(numeroTerceto.substring(1,numeroTerceto.length()-1))));
        }
    }
    // por ahora fueron tomadas como ulong en el repeat al  no tener el tipo Int
    public void apilarExpresionRepeat(String operando1,String operando2){
        if(!Notificacion.hayErrores()){
            if(tablaSimbolos.getTipo(operando1).equals("ULONG") && tablaSimbolos.getTipo(operando2).equals("ULONG"))
            pilaExpreseionRepeat.add(new Terceto(":=",operando1,null,"ULONG",tablaSimbolos));
            pilaExpreseionRepeat.add(new Terceto("+",operando1,operando2,"ULONG",tablaSimbolos)); // ver bien que haria acaa, como lo realizaria
        }
    }
    public void desapilarExpresionRepeat(){
        if(!Notificacion.hayErrores()) {
            tercetos.add(pilaExpreseionRepeat.remove(pilaExpreseionRepeat.size()-1)); // desapilo el primer operando del for
            tercetos.add(pilaExpreseionRepeat.remove(pilaExpreseionRepeat.size()-1)); // desapilo el segundo operando del for
            tercetos.get(tercetos.size()-1).operando2 = new String("[" + (tercetos.size()-2)+ "]");
            }
        }
    public void desapilarSalto(Integer posicion){
        if(!Notificacion.hayErrores()){
            Integer pos = pilaSaltos.get(pilaSaltos.size()-1);
            tercetos.get(pos).operando2= new String ("["+posicion+"]");
            pilaSaltos.remove(pilaSaltos.size()-1);
        }
    }
    public String CrearTerceto(String operador,String operando1,String operando2, String tipo){
        if(!Notificacion.hayErrores()&& tiposCompatibles(operando1,operando2)){
            crearTerceto(operador, operando1, operando2, tipo);
            return new String("["+Integer.toString(tercetos.size()-1)+"]");
        }
        return null;
    }

    public void cambiarAmbitoVariable(String variable){

        Casilla aux =tablaSimbolos.getEntrada(variable);
        ambito= ambito+"."+variable;
        aux.setLexema(variable);
        }

    public static void  CrearCodigo(){}

    public void agregarUsoVariablesTS(String sval, String nombre_programa) {
            tablaSimbolos.getEntrada(sval).setUso(nombre_programa);
        }

    public ArrayList<String> getVariables_Ambito() {
        return variables_Ambito;
    }

    public void setVariables_Ambito(ArrayList<String> variables_Ambito) {
        this.variables_Ambito = variables_Ambito;
    }
}
