package compilador;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			Compilador compilador=new Compilador();
			File file= new File(System.getProperty("user.dir")+"\\src\\compilador\\Ejemplos\\");
			file.getPath();
			String base =file.getPath();
			System.out.println(base);
			System.out.println("---------------------------Menu de Seleccion de codigo a compilar------------------------");
			System.out.println("Ingrese 1 si desea compilar ejemplo cadena");
			System.out.println("Ingrese 2 si desea compilar ejemplo cadena mal escrita ");
			System.out.println("Ingrese 3 si desea compilar ejemplo comentario");
			System.out.println("Ingrese 4 si desea compilar ejemplo comentario mal escrito ");
			System.out.println("Ingrese 5 si desea compilar ejemplo declaracion de funciones");
			System.out.println("Ingrese 6 si desea compilar ejemplo de identificador_");
			System.out.println("Ingrese 7 si desea compilar ejemplo con PRE");
			System.out.println("Ingrese 8 si desea compilar ejemplo REPEAT y print cadena ");
			System.out.println("Ingrese 9 si desea compilar ejemplo truncar id mayor a 32 ");
			System.out.println("Ingrese 10 si desea compilar ejemplo Valores Doubles positivos ");
			System.out.println("Ingrese 11 si desea compilar ejemplo Variablesytiposvaros");
			System.out.println("Ingrese 12 si desea compilar ejemplo de Rangos Negativos");
			System.out.println("Ingrese 13 si desea compilar ejemplo de TRY CATCH");
			System.out.println("Ingrese 14 si desea compilar ejemplo con errores de FUNC");
		    System.out.println("Ingrese 15 si desea compilar ejemplo con errores de REPEAT");

			Scanner opcion =new Scanner(System.in);
			int num= opcion.nextInt();
			System.out.println("-----------------------------------------------------");
			switch (num){
				case 1:
					compilador.ejecutar(base+"\\Cadena.txt");
					return;
				case 2:
					compilador.ejecutar(base+"\\CadenaMalEscritayCorregida.txt");
					return;
				case 3:
					compilador.ejecutar(base+"\\COMENTARIO.txt");
					return;
				case 4:
					compilador.ejecutar(base+"\\COMENTARIO_MAL_ESCRITO.txt");
					return;
				case 5:
					compilador.ejecutar(base+"\\Declaracion_Funciones.txt");
					return;
				case 6:
					compilador.ejecutar(base+"\\IDENTIFICADOR_.txt");
					return;
				case 7:
					compilador.ejecutar(base+"\\PRE.txt");
					return;
				case 8:
					compilador.ejecutar(base+"\\REPEAT_PrintCADENA.txt");
					return;
				case 9:
					compilador.ejecutar(base+"\\TRuncaIDMAYOR22.txt");
					return;
				case 10:
					compilador.ejecutar(base+"\\DOUBLEConBaseFueraDeRango.txt");
					return;
				case 11:
					compilador.ejecutar(base+"\\VariablesytiposVarios.txt");
					return;
				case 12:
					compilador.ejecutar(base+"\\Rangos_Negativos.txt");
					return;
				case 13:
					compilador.ejecutar(base+"\\TRY_CATCH.txt");
					return;
				case 14:
					compilador.ejecutar(base+"\\ERRORES_FUNC.txt");
					return;
				case 15: compilador.ejecutar(base+"\\ERRORES_REPEAT.txt");
				return;
				default:
					System.out.println("Ingreso mal el numero del caso deseado ");
			}
	}

}
