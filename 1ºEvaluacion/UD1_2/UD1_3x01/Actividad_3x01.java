package AD_3x01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;

public class Actividad_3x01 {
	public static void main(String[] args) {
		int opcion;
		boolean menu = true;
		opcion = Teclado.leerEntero("Dime la opcion");
		try {
			switch (opcion) {
			case 0:
				menu = false;
				break;
			case 1:
				insertarEscritor();
				break;
			case 2:
				consultaEscritores();
				break;
			case 3:
				consultarEscritorCodigo();
				break;
			case 4:
                actualizoEscritor();
				break;
			case 5:
                eliminarEscritor();
				break;
			default:
				System.err.println("La opción de menú debe estar comprendida entre 0 y 5.");

			}
		} catch (IOException e) {
			System.out.println("Error con el fichero");
		}
	}

	public static void mostrarMenu() {
		System.out.println("Pulsa 0 para cerrar");
		System.out.println("Pulsa 1 para insertar escritor");
		System.out.println("Pulsa 2 consultar los escritores");
	}

	public static void insertarEscritor() throws FileNotFoundException, IOException {
		List<Escritor> escritores = new ArrayList<>();
		boolean repetido = false;
		escritores = AccesoEscritor.consultaEscritores();
		int codigo = Teclado.leerEntero("Dime el codigo");
		String nombre = Teclado.leerCadena("Dime el nombre");
		String fecha = Teclado.leerCadena("Dime la fecha");
		String nacionalidad = Teclado.leerCadena("dimela");
		for (Escritor e : escritores) {
			if (e.getCodigo() == codigo)
				repetido = true;
		}
		if (!repetido) {
			Escritor e = new Escritor(codigo, nombre, fecha, nacionalidad);
			AccesoEscritor.escribirEscritor(e);
		} else
			System.err.println("Error ese codigo ya esta asociado a otro escritor");
	}

	public static void consultaEscritores() {
		List<Escritor> escritores = new ArrayList<>();
		escritores = AccesoEscritor.consultaEscritores();
		if (!escritores.isEmpty()) {
			for (Escritor e : escritores) {
				System.out.println(e);
			}
		} else {
			System.err.println("Error el fichero esta vacio");
		}
	}

	public static void consultarEscritorCodigo() {
		List<Escritor> escritores = new ArrayList<>();
		escritores = AccesoEscritor.consultaEscritores();
		int codigo = Teclado.leerEntero("Dime el codigo");
		boolean encontrado = false;
		for (Escritor e : escritores) {
			if (e.getCodigo() == codigo) {
				System.out.println(e);
				return;
			}
		}
		System.err.println("No existe ningún escritor con ese código en el fichero binario.");
		/*
		 * Como lo quiere Sonia boolean encontrado = false int posi =0; for(int i = 0; i
		 * < e.size(); i++) { if(e.getCodigo() == codigo) { encontrado = true; posi = i;
		 * } if(encontrado) System.out.println(e[posi]); else System.err.
		 * println("No existe ningún escritor con ese código en el fichero binario."); }
		 */
	}

	public static void actualizoEscritor() throws FileNotFoundException, IOException {
		List<Escritor> escritores = new ArrayList<>();
		escritores = AccesoEscritor.consultaEscritores();
		int codigo = Teclado.leerEntero("Dime el codigo");
		String nombre = Teclado.leerCadena("Dime el nombre");
		String fecha = Teclado.leerCadena("Dime la fecha");
		String nacionalidad = Teclado.leerCadena("dimela");
		for (Escritor e : escritores) {
			if (e.getCodigo() == codigo) {
				e.setDate(fecha);
				e.setNacionalidad(nacionalidad);
				e.setNombre(nombre);
				AccesoEscritor.escribirEscritor(escritores);
				System.out.println("Se ha actualizado un escritor del fichero binario.");
				return;
			}
		}
		System.err.println("No existe ningún escritor con ese código en el fichero binario.");

	}

	public static void eliminarEscritor() {
		List<Escritor> escritores = new ArrayList<>();
		escritores = AccesoEscritor.consultaEscritores();
		int codigo = Teclado.leerEntero("Dime el codigo");
		boolean encontrado = false;
		Escritor aux = null;
		for (Escritor e : escritores) {
			if (e.getCodigo() == codigo) {
				aux = e;
			}
		}
		if (encontrado) {
			escritores.remove(aux);
			System.out.println("Se ha eliminado un escritor del fichero binario.");
		} else {
			System.err.println("No existe ningún escritor con ese código en el fichero binario.");
		}
	}

}
