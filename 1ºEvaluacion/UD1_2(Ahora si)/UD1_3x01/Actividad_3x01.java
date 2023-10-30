package practicaexamen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Actividad_3x01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner teclado = new Scanner(System.in);
        boolean menu = true;
        while(menu){
            mostrarMenu();
            int opcion = teclado.nextInt();
            try{
            switch(opcion)
            {
                case 0:
                    menu = false;
                    break;
                case 1:
                    insertarEscritor();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                System.out.println("Numero incorrecto");
                    break;
            }
            }catch (FileNotFoundException fnfe) {
                System.out.println("Error con el fichero");
            }catch (IOException e) {
                System.out.println("Error con el fichero");
            }
        }
    }

    public static void insertarEscritor() throws FileNotFoundException, IOException, ClassNotFoundException {
         Scanner teclado = new Scanner(System.in);
		List<Escritor> escritores = new ArrayList<>();
		boolean repetido = false;
		escritores = AccesoEscritor.leerEscritores();
		int codigo = teclado.nextInt();
		String nombre = teclado.nextLine();
		String nacionalidad = teclado.nextLine();
        String fecha = teclado.nextLine();
		for (Escritor e : escritores) {
			if (e.getCodigo() == codigo)
				repetido = true;
		}
		if (!repetido) {
			Escritor e = new Escritor(codigo, nombre, fecha, nacionalidad);
			AccesoEscritor.insertarEscritor(e);
		} else
			System.err.println("Error ese codigo ya esta asociado a otro escritor");
	}
    
    public static void mostrarMenu()
    {
        System.out.println("////////////");
        System.out.println("Pulsa 1 para");
        System.out.println("////////////");
    }
}
