package AD_3x02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entrada.Teclado;

public class Actividad_3x02 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		AccesoLibro.verificarArchivo();
		int opcion;
		boolean menu = true;
		while(menu) {
		mostrarMenu();
		opcion = Teclado.leerEntero("Dime la opcion");
		try {
			switch (opcion) {
			case 0:
				menu = false;
				break;
			case 1:
				insertarLibro();
				break;
			case 2:
				consultaLibros();
				break;
			case 3:
				consultarLibroCodigo();
				break;
			case 4:
				actualizoLibro();
				break;
			case 5:
				eliminarLibro();
				break;
			default:
				System.err.println("La opción de menú debe estar comprendida entre 0 y 5.");

			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error con el fichero");
			fnfe.printStackTrace();
		}catch (IOException e) {
			System.out.println("Error con el fichero");
			e.printStackTrace();
		}catch(ClassNotFoundException cnf) {
			
		}
		}
	
	}
	public static void mostrarMenu() {
		System.out.println("Pulsa 0 para cerrar");
		System.out.println("Pulsa 1 para insertar libro");
		System.out.println("Pulsa 2 consultar los libro");
		System.out.println("Pulsa 3 consultar los libro por codigo");
		System.out.println("Pulsa 4 actualizar el libro");
		System.out.println("Pulsa 5 eliminar libro");
	}
	
	public static void insertarLibro() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Libro> libros = new ArrayList<>();
		boolean repetido = false;
		libros = AccesoLibro.consultaLibro();
		int codigo = Teclado.leerEntero("Dime el codigo");
		int codigoLibro = Teclado.leerEntero("Dime el codigo");
		int año = Teclado.leerEntero("Dime el año");
		int precio = Teclado.leerEntero("Dime el precio");
		String titulo = Teclado.leerCadena("Dime el titulo");
		for (Libro l : libros) {
			if (l.getCodigo() == codigo)
				repetido = true;
		}
		if (!repetido) {
			Libro l = new Libro(codigo, codigoLibro, año, precio,titulo);
			AccesoLibro.escribirLibro(l);
		} else
			System.err.println("Error ese codigo ya esta asociado a otro libro");
	}
	
	public static void consultaLibros() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Libro> libros = new ArrayList<>();
		libros = AccesoLibro.consultaLibro();
		if (!libros.isEmpty()) {
			for (Libro e : libros) {
				System.out.println(e);
			}
		} else {
			System.err.println("Error el fichero esta vacio");
		}
	}
	
	public static void consultarLibroCodigo() throws FileNotFoundException, IOException, ClassNotFoundException{
		List<Libro> libros = new ArrayList<>();
		libros = AccesoLibro.consultaLibro();
		int codigo = Teclado.leerEntero("Dime el codigo");
		boolean encontrado = false;
		for (Libro e : libros) {
			if (e.getCodigo() == codigo) {
				System.out.println(e);
				return;
			}
		}
		System.err.println("No existe ningún escritor con ese código en el fichero binario.");
	}
	
	public static void actualizoLibro() throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Libro> libros = new ArrayList<>();
		libros = AccesoLibro.consultaLibro();
		int codigo = Teclado.leerEntero("Dime el codigo");
		int codigoLibro = Teclado.leerEntero("Dime el codigo");
		int año = Teclado.leerEntero("Dime el año");
		int precio = Teclado.leerEntero("Dime el precio");
		String titulo = Teclado.leerCadena("Dime el titulo");
		for (Libro e : libros) {
			if (e.getCodigo() == codigo) {
				e.setAñoPublicacion(año);
				e.setCodigoEscritor(codigoLibro);
				e.setPrecio(precio);
				e.setTitulo(titulo);
				AccesoLibro.escribirLibro(libros);
				System.out.println("Se ha actualizado un escritor del fichero binario.");
				return;
			}
		}
		System.err.println("No existe ningún escritor con ese código en el fichero binario.");

	}
	
	public static void eliminarLibro() throws FileNotFoundException, IOException, ClassNotFoundException{
		List<Libro> libros = new ArrayList<>();
		libros = AccesoLibro.consultaLibro();
		int codigo = Teclado.leerEntero("Dime el codigo");
		boolean encontrado = false;
		Libro aux = null;
		for (Libro e : libros) {
			if (e.getCodigo() == codigo) {
				aux = e;
			}
		}
		if (encontrado) {
			libros.remove(aux);
			System.out.println("Se ha eliminado un libro del fichero binario.");
		} else {
			System.err.println("No existe ningún escritor con ese código en el fichero binario.");
		}
	}
	
	
	
}
