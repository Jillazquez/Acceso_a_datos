package EJ_01;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entrada.Teclado;

public class Ej01 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean menu = true;
		while(menu) {
			int opcion = Teclado.leerEntero("Introduce tu opcion");
			switch(opcion) {
			case 0:
				menu = false;
				break;
			case 1:
				insertarDepartamento();
				break;
			case 2:
				listarDepartamentos();
				break;
			case 3:
				leerDepartamento();
				break;
			case 4:
				actualizarDepartamento();
				break;
			case 5:
				eliminarDepartamento();
				break;
			}
		}
	}
	
	public static void insertarDepartamento() throws ClassNotFoundException, SQLException
	{
		int codigo = Teclado.leerEntero("Codigo");
		String nombre = Teclado.leerCadena("Nombre del dep");
		String ubi = Teclado.leerCadena("Ubicacion del departamento");
		Departamento aux = new Departamento(codigo,nombre,ubi);
		AccesoDepartamento.InsertarDep(aux);
	}
	
	public static void listarDepartamentos() throws ClassNotFoundException, SQLException
	{
		List<Departamento> listaDepartamentos = new ArrayList<>();
		listaDepartamentos = AccesoDepartamento.listarDepartamentos();
		for (Departamento d : listaDepartamentos) {
			System.out.println(d.toString());
		}
		if (listaDepartamentos.size() == 0) {
			System.out.println("No se ha encontrado ning�n departamento en la base de datos.");
		}
		else {
			System.out.println("Se han consultado " + 
		                       listaDepartamentos.size() + " departamentos de la base de datos.");
		}
		
	}
	
	public static void leerDepartamento() throws ClassNotFoundException, SQLException
	{
		int codigo = Teclado.leerEntero("Codigo del departamento a buscar");
		AccesoDepartamento.consultar(codigo);
	}
	
	public static void actualizarDepartamento() throws ClassNotFoundException, SQLException
	{
		int codigo = Teclado.leerEntero("Codigo");
		String nombre = Teclado.leerCadena("Nombre del dep");
		String ubi = Teclado.leerCadena("Ubicacion del departamento");
		Departamento aux = new Departamento(codigo,nombre,ubi);
		AccesoDepartamento.actualizarDepartamento(aux);
	}
	
	public static void eliminarDepartamento() throws ClassNotFoundException, SQLException 
	{
		int codigo = Teclado.leerEntero("Codigo del departamento a borrar");
		AccesoDepartamento.borrarDep(codigo);
	}
}
