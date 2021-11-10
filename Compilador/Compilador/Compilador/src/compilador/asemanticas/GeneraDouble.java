package compilador.asemanticas;

import compilador.Compilador;
import compilador.Semantico.Parser;
import compilador.Semantico.ParserVal;
import compilador.maquina_estado.Contenedor_Inputs;
import compilador.maquina_estado.MaquinaEstados;
import compilador.simbolo.TablaSimbolos;
import compilador.util.Notificacion;

import java.util.function.DoubleBinaryOperator;
// esta clase verifica los exponenetes, si  el double se encuentra en el rango correcto y si convierte el numero en caso de ser necsario

public class GeneraDouble extends AccionSemantica{

    private final  double INFERIOR_DOUBLE = 2.2250738585072014;
    private final  int MAX_EXP = 308;
    private final  double SUPERIOR_DOUBLE = 1.7976931348623157;
    private double base;
    private Double exp=0.0;
    private String signo ="+";// por defecto es +
    private Double double_normalizado =0.0;

    private MaquinaEstados maquina;
    private TablaSimbolos ts;
    private short token ;

    public GeneraDouble(MaquinaEstados maquina,TablaSimbolos ts, short token){
        this.maquina=maquina;
        this.ts=ts;
        this.token=token;
    }

    private void  getValores() {
        String base = getString();
        String valorBase = new String();
        int posf = 0;
        char e = "E".charAt(0);
        // extraigo la base
        for (int posicion = 0; posicion <base.length(); posicion++) {
            if (base.charAt(posicion) != (e)) {
                valorBase += base.charAt(posicion);

            }
            else { posf=posicion;
                this.base=Double.valueOf(valorBase);
                break;
                }
            if (posicion+1 == base.length()){
                posf=0;
                this.base=Double.valueOf(base);
                return;
            }
        }
        //sacamos el exponente y si existen los valores
        if(posf+1>base.length()) {// si no hay exponente, el exponente es 1
            this.exp=1.0;//ejemplo 3.5E
        }
        String sexp= new String();
        for (int pos=posf+1;pos<base.length();pos++){
            if ((base.charAt(pos)=="+".charAt(0)) || (base.charAt(pos)=="-".charAt(0))){
                this.signo=base.substring(pos,pos+1);
            }
            else{
                sexp += base.charAt(pos);
            }
        }
        if(sexp.isEmpty()) {
            this.exp=1.0;
        }
        else {
            this.exp = Double.valueOf(sexp);
        }
    }

    @Override
    public void ejecutar() {
        /* /*System.out.println(ANSI_BLUE+"genero double"+token +ANSI_RESET);*/
        getValores();
        maquina.setVariablesSintactico(Parser.CTE_DOUBLE, "DOUBLE");
        Double numfinal;
        if (signo.equals("-")) {
            numfinal = this.base * Math.pow(10, -this.exp);
        } else {
            numfinal = this.base * Math.pow(10, this.exp);
        }
        if (this.doubleValido()) {// el double cumple con las caracteristicas deseadas
            maquina.setVariablesSintactico(Parser.CTE_DOUBLE, String.valueOf(numfinal));
            ts.agregarEntrada(token, String.valueOf(numfinal), "DOUBLE");
            ts.setUsoEntrada(String.valueOf(numfinal), "CTE");
            ts.setDeclaracionEntrada(String.valueOf(numfinal), true);
            Compilador.parser.yylval=new ParserVal(numfinal); // entregamos referencia a la tabla de simbolos.
            maquina.setVariablesSintactico(Parser.CTE_DOUBLE,String.valueOf( numfinal));}
        this.exp=0.0;
        signo="+";
        }

    private boolean doubleValido() {
        boolean valido = true;
                if (expFueraRango(this.exp)) {
                     maquina.reiniciar(); //Evita que la maquina quede en el estado final, para que el lexico no genere un token.
                     valido = false;
                     Notificacion.addError(maquina.getLineaActual(),"El exponente del DOUBLE esta fuera de rango.");
                 }
                  else if (doubleFueraRango()) {
                     maquina.reiniciar(); //Evita que la maquina quede en el estado final, para que el lexico no genere un token.
                     valido = false;
                     Notificacion.addError(maquina.getLineaActual(),"El numero DOUBLE con base "+base+ " esta fuera de rango.");
                 }
            return valido;
             }

   private boolean expFueraRango(double numero) { return numero < -MAX_EXP || numero > MAX_EXP;}

   private boolean doubleFueraRango() {
                 double min = INFERIOR_DOUBLE * Math.pow(10, -MAX_EXP);
                 double max = SUPERIOR_DOUBLE * Math.pow(10, MAX_EXP);
                 if (signo.equals("-")) {
                     this.double_normalizado = this.base * Math.pow(10, -this.exp);
                 }
                 else
                     this.double_normalizado = this.base * Math.pow(10, this.exp);
                 return double_normalizado != 0.0 && (double_normalizado < min || double_normalizado > max);
                    }
             }