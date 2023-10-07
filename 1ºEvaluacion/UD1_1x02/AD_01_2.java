package AD_01;

import entrada.Teclado;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AD_01_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombreFicheroFinal = Teclado.leerCadena("Nombre del fichero?");
		String nombrefichero = "estadisticas.txt";
		File fichero = new File(nombrefichero);
		File ficherofinal = new File(nombreFicheroFinal);
		BufferedReader lector = null;
		BufferedWriter escritor = null;
		String linea;
		int caracteres=0;
		int espacios = 0;
		if (!fichero.exists()) {
		}
		else {
			if (fichero.isFile()) {
				try {
					lector = new BufferedReader(new FileReader(fichero));
					escritor = new BufferedWriter(new FileWriter(ficherofinal));
					int numLinea = 1;
					while ((linea = lector.readLine()) != null) {
							System.out.println(linea);
							for(int i = 0; i < linea.length();i++) {
								if(linea.charAt(i) == ' ') {
									espacios++;
								}
								else {
									caracteres++;
								}
							}
							espacios++;
							escritor.write("Linea "+numLinea+": "+caracteres+" caracteres y "+espacios+" palabras");
							escritor.newLine();
							espacios = 0;
							caracteres = 0;
						}
					

					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (lector != null) {
							lector.close();
						}
						if (escritor != null) {
							escritor.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("El archivo no es un fichero o no existe.");
			}
		}
	}
}