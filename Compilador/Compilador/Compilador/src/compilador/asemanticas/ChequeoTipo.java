package compilador.asemanticas;

import compilador.Semantico.Parser;
import compilador.maquina_estado.MaquinaEstados;
import compilador.simbolo.TablaSimbolos;
import compilador.util.CodigoFuente;
import compilador.util.TablaPalabrasReserv;

public class ChequeoTipo extends AccionSemantica{
    private  MaquinaEstados maquina ;
    private TablaSimbolos tabla ;
    private CodigoFuente codigo;
    public ChequeoTipo(MaquinaEstados maquina, TablaSimbolos tabla, CodigoFuente codigoFuente){
        this.maquina=maquina;
        this.tabla = tabla;
        this.codigo=codigoFuente;
    }

    @Override
    public void ejecutar() {
        Retrocede_Fuente retroceder = new Retrocede_Fuente(codigo);
        if (TablaPalabrasReserv.esReservada(getString())){ // es una palabra reservada
            GeneraTPr gtp = new GeneraTPr(maquina);
            gtp.ejecutar();
            retroceder.ejecutar();
        }
        else{ //  es un Id
            retroceder.ejecutar();
            TruncId trunca = new TruncId(maquina.getaLexico());
            trunca.ejecutar();
            GeneraTokenTSimbolos generaId = new GeneraTokenTSimbolos(maquina,tabla,Parser.ID);
            generaId.ejecutar();
        }
    }
}
