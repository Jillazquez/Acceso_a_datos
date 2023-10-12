package AD_2x02;

import entrada.Teclado;
import java.util.*;

import AD_2x01.AccesoDepartamento;
import AD_2x01.Departamento;

import java.io.*;

public class Actividad_2x02 {

	public static void main(String[] args) {
		boolean menu = true;
		while(menu) {
			leerMenu();
			int opcion = Teclado.leerEntero("Que opcion quieres");
			try {
			switch(opcion) {
			case 0:
				menu = false;
				break;
			case 1:
				añadirEmpleados();
				break;
			case 2:
				leerEmpleados();
				break;
			case 3:
				leoDepartamentoCodigo();
				break;
			case 4:
				actualizoDepartamento();
				break;
			case 5:
				
				break;
			}
			}catch(IOException e) {
				System.err.println("Error con el fichero");
			}
		}
	}
	public static void añadirEmpleados() throws IOException {
		/*
		BufferedReader lector = new BufferedReader(new FileReader("departamentos.txt"));
		String linea;
		while((linea = lector.readLine()) != null) {
			System.out.println(linea);
		}
		*/
		int codigoEmp = Teclado.leerEntero("Codigo Empleado");
		int codigo = Teclado.leerEntero("Codigo del departamento");
		String nombre = Teclado.leerCadena("Nombre del empleado");
		String fecha = Teclado.leerCadena("Fecha");
		int salario = Teclado.leerEntero("Cuanto cobra");
		Empleado emp = new Empleado (codigo,codigoEmp,nombre,fecha,salario);
		AccesoEmpleado.insertoEmpleado(emp);
		
	}
	public static void leerEmpleados() throws IOException{
		List<Empleado>empleados = new ArrayList<>();
		empleados = AccesoEmpleado.leoEmpleado();
		if(empleados.isEmpty()) {
			System.err.println("No hay departamentos");
			return;
		}
		for(Empleado emp : empleados) {
			System.out.println("emp");
		}
	}
	
	public static void leoDepartamentoCodigo() throws IOException {
		int codigo = Teclado.leerEntero("Introduce el codigo que quieres buscar");
		List<Empleado> empleados = new ArrayList<>();
		empleados = AccesoEmpleado.leoEmpleado();
		for (Empleado i : empleados) {
			if (i.getCodigo()== codigo) {
				System.out.println(i);
				return;
			}
		}
		System.err.println("No existe ningun empleado con ese numero");
	}
	private static void actualizoDepartamento() throws IOException {
		List<Empleado> empleados = new ArrayList<>();
		empleados = AccesoEmpleado.leoEmpleado();
		int codigoDep = Teclado.leerEntero("Introduce el codigo del departamento a cambiar");
		int salario = Teclado.leerEntero("Introduce el nuevo salario");
		String fecha = Teclado.leerCadena("Fecha");
		
		for (Empleado i : empleados) {
			if (i.getCodigo() == codigoDep) {
				i.setSalario(salario);
				i.setFecha(fecha);
				System.out.println("Se ha actualizado");
				return;
			}
			System.err.println("No se ha encontrado");
			return;
		}
	}
	static void borroEmpleado() throws IOException {
		List<Empleado> empleados = new ArrayList<>();
		empleados = AccesoEmpleado.leoEmpleado();
		boolean eliminado = false;
		int codigo = Teclado.leerEntero("Introduce el codigo del empleado a borrar");
		for(int i = 0; i < empleados.size(); i++) {
			if(empleados.get(i).getCodigo() == codigo) {
				empleados.remove(i);
				eliminado = true;
				}
			}
		}

	public static void leerMenu() {
		System.out.println("################");
		System.out.println("Pulsa 1 para insertar un departamento");
		System.out.println("Pulsa 2 para consultar los departamentos");
		System.out.println("Pulsa 3 para leer un codigo con un departamento");
		System.out.println("Pulsa 4 para actualizar datos de un departamento");
		System.out.println("Pulsa 5 para borrar un departamento");
		System.out.println("################");
	}
}
