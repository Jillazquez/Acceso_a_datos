package practicaexamen;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class AccesoEscritor {
    
    public static final String NOMBREFICHERO = "escritores.dat";

    public static void escribirEscritor(){

    }

    
    
    public static ArrayList<Escritor> leerEscritores() throws ClassNotFoundException, IOException
    {
        ObjectInputStream flujoEntrada = null;
        ArrayList<Escritor> listaEscritores = new ArrayList<Escritor>();
        try{
            File fichero = new File(NOMBREFICHERO);
			flujoEntrada = new ObjectInputStream(new FileInputStream(fichero));

            boolean finalFichero = false;
            try{
                while(finalFichero == false) {//Control final de fichero (EOFException)
					Escritor escritor = (Escritor) flujoEntrada.readObject();
					listaEscritores.add(escritor);
				}
            }catch(EOFException e){
            System.out.println("asdasda");
        }
        }finally{
            if(flujoEntrada != null)
                flujoEntrada = null;
        }
        return listaEscritores;
    }

    public Escritor buscaEscritor(int codigo) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Escritor escritorConCodigo = null;
		ObjectInputStream flujoEntrada = null;
		try {
			File fichero = new File(NOMBREFICHERO);
			//Por sobrecarga del constructor, podemos usar solo NOMBRE_FICHERO en FileIntputStream(rutaFichero)
			flujoEntrada = new ObjectInputStream(new FileInputStream(fichero));
			boolean finalFichero = false;
			//Control excepción de final de fichero
			while (!finalFichero && escritorConCodigo == null) {
				try {
					Escritor escritor = (Escritor) flujoEntrada.readObject();
					if(escritor.getCodigo() == codigo) {
						escritorConCodigo = escritor;
					}	
				}
				catch(EOFException eofe) {
					finalFichero = true;
				}
			}	

		}
		finally {
			if(flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
		return escritorConCodigo;
    }

    	public static void escribirEscritores(ArrayList<Escritor> listaEscritores) throws IOException {
		ObjectOutputStream flujoSalida1 = null;
		MyObjectOutputStream flujoSalida2 = null;
		try {
			File fichero = new File(NOMBREFICHERO);
			flujoSalida1 = new ObjectOutputStream(new FileOutputStream(fichero));
			flujoSalida2 = new MyObjectOutputStream(new FileOutputStream(fichero,true));

			/*
			 * ¿Mejor escribir el primero, que contiene header,
			 * y luego comenzar el bucle desde posicion 1?
			 * Quizás sería un problema si listaEscritores.size() == 1
			 */
			for(int posicion = 0; posicion < listaEscritores.size(); posicion++) {
				Escritor escritor = listaEscritores.get(posicion);
				if(posicion == 0) {
					flujoSalida1.writeObject(escritor);
				}
				else {
					flujoSalida2.writeObject(escritor);	
				}
			}
		}
		finally {
			if(flujoSalida1 != null) {
				flujoSalida1.close();
			}
			if(flujoSalida2 != null) {
				flujoSalida2.close();
			}
		}
	}

    public static ArrayList<Escritor> eliminarEscritor(int codigo) throws FileNotFoundException, 
	IOException, StreamCorruptedException, ClassNotFoundException{
		ArrayList<Escritor> listaEscritores = leerEscritores();

		boolean encontrado = false;
		int posicion = 0;
		while(!encontrado) { //Busca el escritor en la lista para actualizarlo
			if(codigo == listaEscritores.get(posicion).getCodigo()) {
				encontrado = true;
			}
			else {
				posicion++;
			}
		}
		listaEscritores.remove(posicion);	
		return listaEscritores;	
	}

    public static void insertarEscritor(Escritor escritor) throws IOException {
		ObjectOutputStream flujoSalida1 = null;
		MyObjectOutputStream  flujoSalida2 = null;
		try {
			File fichero = new File(NOMBREFICHERO);
			if (fichero.exists()) {
				flujoSalida2 = new MyObjectOutputStream(new FileOutputStream(fichero, true));
				flujoSalida2.writeObject(escritor);
			}
			// Crear el fichero e insertar el alumno al principio del fichero.
			else {
				flujoSalida1 = new ObjectOutputStream(new FileOutputStream(fichero));
				flujoSalida1.writeObject(escritor);
			}
		}
		finally {
			if(flujoSalida1 != null) {
				flujoSalida1.close();
			}
			if(flujoSalida2 != null) {
				flujoSalida2.close();
			}		
		}
	}

    public static ArrayList<Escritor> actualizarUno(int codigo, Escritor escritorParam) throws FileNotFoundException, 
	IOException, StreamCorruptedException, ClassNotFoundException{

		ArrayList<Escritor> listaEscritores = leerEscritores();
		ObjectInputStream flujoEntrada = null;
		Escritor escritor = null;
		
		Boolean encontrado = false;
		int posicion = 0;
		while(encontrado == false) { //Busca el escritor en la lista para actualizarlo
			escritor = listaEscritores.get(posicion);
			if(escritorParam.getCodigo() == escritor.getCodigo()) {
				encontrado = true;
			}
			else {
				posicion++;
			}
		}
		escritor.setNombre(escritorParam.getNombre());
		escritor.setFecha(escritorParam.getFecha());
		escritor.setNacionalidad(escritorParam.getNacionalidad());
		return listaEscritores;	
	}
}
