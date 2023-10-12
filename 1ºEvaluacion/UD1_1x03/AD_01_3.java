package AD_01;

import entrada.Teclado;
import java.io.*;

public class AD_01_3 {
	
	static void pasaMinuscula(File fichero) {
		String ficheromayus = "minusculas.txt";
		try {
			File minus = new File(ficheromayus);
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			BufferedWriter escritor = new BufferedWriter(new FileWriter(minus));
			String linea = null;
			while ((linea = lector.readLine())!= null) {
				escritor.write(linea.toLowerCase());
				escritor.newLine();
			}
			escritor.close();
			lector.close();
		}catch(IOException e) {
			System.err.println("Error con el fichero");
		}
	}
	
	static void pasaMayuscula(File fichero) {
		String ficherominus = "mayusculas.txt";
		try {
			File mayus = new File(ficherominus);
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			BufferedWriter escritor = new BufferedWriter(new FileWriter(mayus));
			String linea = null;
			while ((linea = lector.readLine())!= null) {
				escritor.write(linea.toUpperCase());
				escritor.newLine();
			}
			escritor.close();
			lector.close();
		}catch(IOException e) {
			System.err.println("Error con el fichero");
		}
	}

	static void encuentraPalabra(File fichero) {

		String palabra = Teclado.leerCadena("Que palabra quieres encontrar no puede tener espacios")
		if (palabra.contains(" ")) {
			System.err.println("No puede contener espacios");
			encuentraPalabra(fichero);
		}else{

		try {
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			String linea = null;
			int lineas =0;
			while ((linea = lector.readLine()) != null) {
				lineas++;
				if(linea.contains(palabra)) {
					System.out.println("La palabra "+ palabra +" esta contenida en el fichero en la linea "+lineas);
					lector.close();
					return;
				}
				lector.close();
			}

			System.err.println("La palabra no esta contenida en el fichero");
		} catch (IOException e) {
			System.err.println("Error con el fichero");
		}
		}

	}

	// Metodo para leer las frases del archivo
	static void leerFrases(File fichero) {
		String linea = null;
		try {
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);

			}
			lector.close();
		} catch (IOException e) {
			System.err.println("Error con el fichero");
		}
	}

	// Metodo para contar las lineas de un fichero
	public static void cuentoLineas(File fichero) {

		String linea = null;
		int lineas = 0;
		int palabras = 0;
		try {
			BufferedReader lector = new BufferedReader(new FileReader(fichero));
			while ((linea = lector.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					if (linea.charAt(i) == ' ') {
						palabras++;
					}
				}
				lineas++;
				palabras++;
			}
			lector.close();
			System.out.println("El archivo tiene " + lineas + " lineas");
			System.out.println("Y " + palabras + " palabras");
		} catch (IOException e) {
			System.err.println("Error abriendo el archivo");
		}
	}

	// Metodo para mostrar el menu
	public static void mostrarMenu() {
		System.out.println("*************************************");
		System.out.println("Bienvenido al menu de ficheros pulsa");
		System.out.println("0 para cerrar el menu");
		System.out.println("1 para escribir lineas");
		System.out.println("2 para ver el contenido del fichero");
		System.out.println("3 para contar lineas");
		System.out.println("4 para saber si una palabra esta en el fichero");
		System.out.println("5 para escribir el fichero en mayusculas");
		System.out.println("6 para escribir el fichero en minuscula");
		System.out.println("*************************************");
	}

	// Metrodo para escribir las frases en el archivosaber si una palabra esta en el fichero
	public static void escriboFrases(File fichero) {
		try {
			String linea = null;
			BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, true));
			boolean termina = false;
			while (!termina) {
				linea = Teclado.leerCadena("Ingrese frases (ingrese *** para finalizar):");
				if (linea.equals("***")) {
					termina = true;
				} else {
					escritor.write(linea);
					escritor.newLine();
				}
				escritor.close();
			}

		} catch (IOException e) {
			System.err.println("Error con el fichero");
		}
	}

	public static void main(String[] args) {
		boolean menu = true;
		String nombreFichero = "frases.txt";
		File fichero = new File(nombreFichero);
		while (menu) {
			mostrarMenu();
			int opcion = Teclado.leerEntero("Que opcion quieres");
			switch (opcion) {
			case 0:

				menu = false;
				break;
			/*
			 * return;
			 */
			case 1:
				escriboFrases(fichero);
				break;
			case 2:
				leerFrases(fichero);
				break;
			case 3:
				cuentoLineas(fichero);
				break;
			case 4:
				encuentraPalabra(fichero);
				break;
			case 5:
				pasaMayuscula(fichero);
				break;
			case 6:
				pasaMinuscula(fichero);
				break;
			default:
				System.err.println("La opción de menú debe estar comprendida entre 0 y 6.");
				break;
			}
		}
	}
}
