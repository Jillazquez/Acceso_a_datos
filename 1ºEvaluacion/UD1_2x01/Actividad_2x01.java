package AD_2x01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;

public class Actividad_2x01 {

	public static void main(String[] args) throws IOException {
		boolean menu = true;
		while (menu) {
			mostrarMenu();
			int opcion = Teclado.leerEntero("Que opcion quieres");
			try{
			switch (opcion) {
			case 0:
				menu = false;
				break;
			/*
			 * return;
			 */
			case 1:
				insertarDepartamento();
				break;
			case 2:
				consultoDepartamentos();
				break;
			case 3:
				leoDepartamentoCodigo();
				break;
			case 4:
				actualizoDepartamento();
				break;
			case 5:
				borroDepartamento();
				break;
			default:
				System.err.println("La opción de menú debe estar comprendida entre 0 y 5.");
				break;
			}
			}catch(IOException e){
				System.err.println("Error con el fichero");
			}
		}

	}// fin main

	static void mostrarMenu() {
		System.out.println("################");
		System.out.println("Pulsa 1 para insertar un departamento");
		System.out.println("Pulsa 2 para consultar departamentos");
		System.out.println("Pulsa 3 para leer departamentos por codigo");
		System.out.println("Pulsa 4 para actualizar departamentos");
		System.out.println("Pulsa 5 para borrar departamento");
		System.out.println("################");
	}

	static void insertarDepartamento() throws IOException {
		int codigo = Teclado.leerEntero("Ingrese el código del departamento: ");
		String nombre = Teclado.leerCadena("Ingrese el nombre del departamento: ");
		String ubicacion = Teclado.leerCadena("Ingrese la ubicación del departamento: ");

		Departamento nuevoDepartamento = new Departamento(codigo, nombre, ubicacion);
		AccesoDepartamento.escribirDepartamento(nuevoDepartamento);
	}

	static void consultoDepartamentos() throws IOException {
		List<Departamento> departamentos = new ArrayList<>();
		departamentos = AccesoDepartamento.leerDepartamentos();
		if (departamentos.isEmpty()) {
			System.err.println("No hay departamentos");
			return;
		}
		for (Departamento i : departamentos) {
			System.out.println(i);
		}
	}

	static void leoDepartamentoCodigo() throws IOException {
		int codigo = Teclado.leerEntero("Introduce el codigo que quieres buscar");
		List<Departamento> departamentos = new ArrayList<>();
		departamentos = AccesoDepartamento.leerDepartamentos();
		for (Departamento i : departamentos) {
			if (i.getCodigo() == codigo) {
				System.out.println(i);
				return;
			}
		}
		System.err.println("No existe ningun departamento con ese numero");
	}

	static void actualizoDepartamento() throws IOException {
		List<Departamento> departamentos = new ArrayList<>();
		departamentos = AccesoDepartamento.leerDepartamentos();
		int codigo = Teclado.leerEntero("Introduce el codigo del departamento a cambiar");
		String ubicacion = Teclado.leerCadena("Introduce la ubicacion del departamento");
		String nombre = Teclado.leerCadena("Introduce el nombre de el departamento");
		for (Departamento i : departamentos) {
			if (i.getCodigo() == codigo) {
				i.setNombre(nombre);
				i.setUbicacion(ubicacion);
			}
			break;
		}
	}
	
	static void borroDepartamento() throws IOException {
		List<Departamento> departamentos = new ArrayList<>();
		departamentos = AccesoDepartamento.leerDepartamentos();
		boolean eliminado = false;
		int codigo = Teclado.leerEntero("Introduce el codigo del departamento a borrar");
		for(int i = 0; i < departamentos.size(); i++) {
			if(departamentos.get(i).getCodigo() == codigo) {
				departamentos.remove(i);
				eliminado = true;
			}
		}
		
		if(eliminado) {
			AccesoDepartamento.actualizarDepartamentos(departamentos);
			System.out.println("Departamento eliminado con exito");
		}else {
			System.err.println("No existe ningun departamento con ese nombre");
		}
	}

}
