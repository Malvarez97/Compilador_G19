package compilador.asemanticas;

public abstract class AccionSemantica {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	private static StringBuilder sTemporal= new StringBuilder("");

	
	public void inicString() { // vacio el string en caso de que tenga basura, dejandolo vacio 
		sTemporal.delete(0,sTemporal.length());	
	}

	
	public void  concatenaChar (char nuevo) {
		sTemporal.append(nuevo);
	}
	
	public int tamString() {
		return sTemporal.length();
	}
	
	public void truncaString(int limite) {
		sTemporal.delete(limite, sTemporal.length()); // elimina los char en las ubicaciones que ya fueron almacenadas
	}
	
	public String getString() {
		return sTemporal.toString();
	}

	public abstract void ejecutar();
}
