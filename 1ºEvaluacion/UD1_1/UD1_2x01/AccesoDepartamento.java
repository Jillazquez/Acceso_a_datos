package AD_2x01;

import java.io.*;
import java.util.*;

public class AccesoDepartamento {
	
		private static final String NOMBREFICHERO = "departamento.txt";
		File fichero = new File(NOMBREFICHERO);
		  public static void escribirDepartamento(Departamento departamento) throws IOException {
			  try {
				  BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBREFICHERO, true));
				  escritor.write(departamento.toStringWithSeparators());
				  escritor.newLine();
				  escritor.close();
			  }catch(IOException e) {
				  System.err.println("Error con el fichero");
			  }
		  }
		  
		  public static List<Departamento> leerDepartamentos() throws IOException {
			  List<Departamento> departamentos = new ArrayList<>();
			  String linea = null;
			  try {
				  BufferedReader lector = new BufferedReader(new FileReader(NOMBREFICHERO));
				  linea = null;
				  while((linea = lector.readLine())!=null) {
					  departamentos.add(new Departamento(linea));
				  }
				  lector.close();

			  }catch(IOException e) {
			  System.err.println("Error con el fichero");
		  	}
			  return departamentos;
		  }
		  
		  
		  public static void actualizarDepartamentos(List<Departamento> deps) throws IOException {
			  BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBREFICHERO));
			  for(Departamento i : deps) {
				  escritor.write(i.toStringWithSeparators());
				  escritor.newLine();
			  }
			  escritor.close();
		  }
	}

