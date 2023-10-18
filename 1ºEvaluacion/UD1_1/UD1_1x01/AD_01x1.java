package AD_01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class AD_01x1 {
    public static void main(String[] args) {
        String nombreArchivo1 = "entrada.txt";
        String nombreArchivo2 = "salida.txt";
        File archivo1 = new File(nombreArchivo1);
        File archivo2 = new File(nombreArchivo2);
        BufferedReader reader = null;
        BufferedWriter escritor = null;
        ArrayList<String> lista = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(archivo1));
            escritor = new BufferedWriter(new FileWriter(archivo2));
            String linea;
            while ((linea = reader.readLine()) != null) {
                lista.add(linea);
            }

            Collections.reverse(lista);//Invierto el orden de las lineas para añadirlas al nuevo fichero

            for (String lineainversa : lista) {
                escritor.write(lineainversa);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error procesando los archivos");
        } finally {
            try {       //Uso un try para cerrar los ficheros y manejar excepciones
            	if(escritor != null)
                escritor.close();
            	if(reader != null)
                reader.close();
            }catch(java.lang.Error e){
                System.err.println("Error no de puede cerrar fichero que no se ha abierto");
            } 
            catch (IOException e) {
                System.err.println("Error procesando los archivos");
            }
        }

    }
}
