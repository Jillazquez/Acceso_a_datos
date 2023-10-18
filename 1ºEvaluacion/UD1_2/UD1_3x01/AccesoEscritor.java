package AD_3x01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccesoEscritor {
    private static String NOMBRE_FICHERO = "escritor.dat";
    
    public static void escribirEscritor(List<Escritor> escritores) throws IOException, FileNotFoundException {       
        try (ObjectOutputStream archivoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
            archivoSalida.writeObject(escritores);
            System.out.println("Empleado guardados en " + NOMBRE_FICHERO);
        }
    }
    
    public static void escribirEscritor(Escritor e) throws IOException, FileNotFoundException {
        List<Escritor> escritores = consultaEscritores();
        escritores.add(e);
        
        try (ObjectOutputStream archivoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
            archivoSalida.writeObject(escritores);
            System.out.println("Empleado guardados en " + NOMBRE_FICHERO);
        }
    }
    
    public static List<Escritor> consultaEscritores(){
    	List<Escritor> escritores = new ArrayList<>();
        try (ObjectInputStream archivoEntrada = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
        	escritores = (ArrayList<Escritor>) archivoEntrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return escritores;
    }
    
    public static void verificarArchivo() {
        File file = new File(NOMBRE_FICHERO);

        if (!file.exists()) {
            try {
            	file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }