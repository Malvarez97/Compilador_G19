package compilador.simbolo;

import compilador.AnalizadorLex;
import compilador.Semantico.Parser;
import compilador.util.Notificacion;

import java.util.Hashtable;
import java.util.Map;

    public  class TablaSimbolos {
        private final Map<String, Casilla> tablaSimbolos;

        public TablaSimbolos() {
            tablaSimbolos = new Hashtable<>();
        }

        public void remove (String lexema){
            if (this.tablaSimbolos.containsKey(lexema))
                this.tablaSimbolos.remove(lexema);
        }

        public void cambiarNegativo (String valor, AnalizadorLex analizadorLex ){
            Double val = Double.valueOf(valor);
            if(-val>=-1.7976931348623157E+308 && -val<=-2.2250738585072014E-308 ) {
                this.remove((valor));
                this.agregarEntrada(Parser.DOUBLE, ("-" + valor), "DOUBLE");
                this.setUsoEntrada(("-" + valor), "CTE");
                this.setDeclaracionEntrada(("-" + valor), true);
            }
            else {
                Notificacion.addError(analizadorLex.getLineaActual(),"Base negativa Double Fuera de Rango");
            }
        }
        public String getTipo(String lexema){
            return tablaSimbolos.get(lexema).getTipo();
        }

        public  String toString() {
            {
                if (tablaSimbolos.isEmpty()) return "Tabla de simbolos vacia.";
                StringBuilder builder = new StringBuilder();
                for (Casilla c : tablaSimbolos.values())
                    builder.append(c.toString()).append('\n');
                return builder.toString();
            }
        }
      // agrega la entrada a la tabla de simbolos
        public void agregarEntrada(int token, String lexema, String tipo) {
            Casilla casilla;

            if (tablaSimbolos.containsKey(lexema))
                //Si el lexema existe saco la casilla para actulizarla
                casilla = getEntrada(lexema);
            else {
                // si no creo una nueva casilla y la agrego
                casilla = new Casilla(token, lexema, tipo);
                tablaSimbolos.put(lexema, casilla);
            }
            casilla.actualizarReferencias(1);
        }
        public void cambiarNombre(String nombre){
            Casilla aux = tablaSimbolos.get(nombre);
            aux.setLexema(nombre+"."+Parser.ambitoActual);
        }

        // Retorno la entrega dado un lexema
        public Casilla getEntrada(String lexema) {
          Casilla casilla = tablaSimbolos.get(lexema);
            if (casilla == null) //Agrege la excepcion por si llega a fallar el get, que no ande el null dando vueltas.
                throw new IllegalStateException("El lexema '" + lexema + "' no esta en la tabla de simbolos.");
            return casilla;
        }

        public void setUsoEntrada(String lexema, String uso){
            Casilla entrada = tablaSimbolos.get(lexema);
            if (entrada == null) throw new IllegalStateException("Lexema '" + lexema + "' no esta en la tabla de Simbolos");
            entrada.setUso(uso);
        }

        public void setDeclaracionEntrada(String lexema, boolean declarada){
            Casilla entrada = tablaSimbolos.get(lexema);
            entrada.setDeclarada(declarada);
        }
        public boolean existeLexema(String lexema){
            if (tablaSimbolos.containsKey(lexema))
                return true;
            return false;
        }

    }

