package EJ_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

import entrada.Teclado;

public class AccesoDepartamento {
	public static void InsertarDep(Departamento d) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db");
			System.out.println("Conectado");
			String sentenciaInsertar = "INSERT INTO departamento " +
                    "VALUES (" + d.getCodigo() + 
                    ", '" + d.getNombre() + 
                    "', '" + d.getUbicacion() + "')";
			Statement sentencia = conexion.createStatement();
			int filasInsertadas = sentencia.executeUpdate(sentenciaInsertar);
			if(filasInsertadas == 0) {
				System.out.println("Ya existe un departamento con ese c�digo en la base de datos.");
			}else {
				System.out.println("SIUUUUUUUUUUUu.");
			}
		}finally {
			conexion.close();
		}
	}
	
	
	public static List<Departamento> listarDepartamentos() throws ClassNotFoundException, SQLException
	{
		List<Departamento> listaDepartamentos = new ArrayList<>();
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db");
			System.out.println("Conectado");
			String sentenciaConsultar = "Select * FROM departamento";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while(resultados.next()) {
				Departamento departamento = 
						new Departamento(resultados.getInt("codigo"),
						                 resultados.getString("nombre"),
						                 resultados.getString("ubicacion"));
					listaDepartamentos.add(departamento);
			}
			resultados.close();
			sentencia.close();
		}finally {
			if(conexion != null)
				conexion.close();
		}
		return listaDepartamentos;
	}
	
	public static void consultar(int codigo) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db");
			String sentenciaConsultar = "SELECT * FROM departamento " +
			                            "WHERE codigo = '" + codigo +
			                            "' ORDER BY nombre";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);	
			while (resultados.next()) {
				System.out.println("C�digo = " + resultados.getInt("codigo") +
		                   ", Nombre = " + resultados.getString("nombre") +
		                   ", Ubicaci�n = " + resultados.getString("ubicacion"));
			}
			resultados.close();
			sentencia.close();
		} 	
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
	}
	
	
	public static boolean actualizarDepartamento(Departamento aux) throws SQLException, ClassNotFoundException {
		Connection conexion = null;
		int filasActualizadas = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db");
			String sentenciaActualizar = "UPDATE departamento " +
								                    "SET nombre = '" + aux.nombre + 
								                    "', ubicacion = '" + aux.ubicacion + "' " +
								                    "WHERE codigo = " + aux.codigo;
			Statement sentencia = conexion.createStatement();
			filasActualizadas = sentencia.executeUpdate(sentenciaActualizar);
		} 	
		finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		if(filasActualizadas > 0)
			return true;
		else
			return false;
	}
	
	public static void borrarDep(int codigo) throws ClassNotFoundException, SQLException {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true); 
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db", 
			                                       config.toProperties());
			System.out.println("Conectado");
			String sentenciaEliminar = "DELETE FROM departamento " +
			                           "WHERE codigo = " + codigo;
			Statement sentencia = conexion.createStatement();
			int filasEliminadas = sentencia.executeUpdate(sentenciaEliminar);
			if (filasEliminadas == 0) {
				System.out.println("No se ha encontrado ning�n departamento con ese c�digo.");
			}
			else {
				System.out.println("Se ha eliminado un departamento de la base de datos.");
			}
		} 
		finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} 
			catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
	}
}


