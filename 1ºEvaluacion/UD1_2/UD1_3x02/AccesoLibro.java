package AD_3x02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccesoLibro {

    private static String NOMBRE_FICHERO = "libros.dat";
    
    public static void escribirLibro(List<Libro> escritores) throws IOException, FileNotFoundException {       
        try (ObjectOutputStream archivoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
            archivoSalida.writeObject(escritores);
            System.out.println("Empleado guardados en " + NOMBRE_FICHERO);
        }
    }
    
    public static void escribirLibro(Libro l) throws IOException, FileNotFoundException, ClassNotFoundException {
        List<Libro> escritores = consultaLibro();
        escritores.add(l);
        
        try (ObjectOutputStream archivoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_FICHERO))) {
            archivoSalida.writeObject(escritores);
            System.out.println("Empleado guardados en " + NOMBRE_FICHERO);
        }
    }
    
    public static List<Libro> consultaLibro() throws IOException, FileNotFoundException, ClassNotFoundException{
    	List<Libro> escritores = new ArrayList<>();
        try (ObjectInputStream archivoEntrada = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
        	escritores = (ArrayList<Libro>) archivoEntrada.readObject();
        }
        return escritores;
    }
    
    public static void verificarArchivo() throws IOException, FileNotFoundException{
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

