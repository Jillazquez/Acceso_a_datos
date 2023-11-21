package Ejericionoseque;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.sqlite.SQLiteConfig;

import entrada.Teclado;

public class Actividad3x01 {

    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = Teclado.leerEntero("Opcion");

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                case 1:
                    insertarDepartamentos();
                    break;
                case 2:
                    insertarEmpleados();
                    break;
                case 3:
                    actualizarSalarios();
                    break;
                case 4:
                    eliminarDepartamento(2);
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 4.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("0) Salir del programa.");
        System.out.println("1) Insertar todos los departamentos de un fichero de texto en la base de datos.");
        System.out.println("2) Insertar todos los empleados de un fichero de texto en la base de datos.");
        System.out.println("3) Actualizar los salarios de los empleados, por departamento, de la base de datos.");
        System.out.println("4) Eliminar un departamento, por código, de la base de datos.");
        System.out.print("Elija una opción: ");
    }

    private static void insertarDepartamentos() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/personal.db")) {
            String archivoDepartamentos = "departamentos.txt";
            int departamentosInsertados = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(archivoDepartamentos))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    String nombre = datos[0];
                    String ubicacion = datos[1];

                    String sql = "INSERT INTO departamento (nombre, ubicacion) VALUES (?, ?)";
                    try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        pstmt.setString(1, nombre);
                        pstmt.setString(2, ubicacion);
                        int filasInsertadas = pstmt.executeUpdate();
                        departamentosInsertados += filasInsertadas;
                    }
                }
            }

            System.out.println("Se han insertado " + departamentosInsertados + " departamentos en la base de datos.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertarEmpleados() throws SQLException, FileNotFoundException, IOException {
    	try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/personal.db")) {
            String archivoDepartamentos = "empleados.txt";
            int departamentosInsertados = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(archivoDepartamentos))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int dep = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    double sueldo = Float.parseFloat(datos[2]);

                    String sql = "INSERT INTO empleado (nombre, fecha_alta, salario, codigo_departamento) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, nombre);
                    pstmt.setString(2, "00/00/2000");
                    pstmt.setDouble(3, sueldo);
                    pstmt.setInt(4, dep);
                    int filasInsertadas = pstmt.executeUpdate();
                    departamentosInsertados += filasInsertadas;
                    }
                }
            }
    }

    private static void actualizarSalarios() throws SQLException {
    	try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db/personal.db")) {
            int departamentosInsertados = 0;
            
            connection.setAutoCommit(false);
            
            Map<Integer,Double> nose = new HashMap<>();
            nose.put(2, 1.01);
            nose.put(4, 1.02);
            nose.put(6, 1.03);
            
            String sql = "UPDATE empleado SET salario = salario * ? where codigo_departamento = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            try {
                for (Map.Entry<Integer, Double> entry : nose.entrySet()) {
                    int codigoDepartamento = entry.getKey();
                    double porcentajeAumento = entry.getValue();
                    
                    preparedStatement.setDouble(1, porcentajeAumento);
                    preparedStatement.setInt(2, codigoDepartamento);
                    
                    // Ejecutar la actualización
                    preparedStatement.executeUpdate();
                    
                    departamentosInsertados++;
                    System.out.println(departamentosInsertados);
                }
            } finally {
                // Cerrar el PreparedStatement en el bloque finally
                preparedStatement.close();
            }
            connection.commit();
            }
    }

    public static int[] eliminarDepartamento(int codigo) throws ClassNotFoundException, SQLException{
    	
    	//Este metodo devuelve el numero de dep y emp que ha borrado en el examen tengo que gestionar eso
		Connection conexion = null;
		int[] eliminados= new int[2];
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();//--> esta linea y la de abajo son para configurar que tenga en cuenta   
			config.enforceForeignKeys(true); //las claves ajenas
			conexion = DriverManager.getConnection("jdbc:sqlite:db/personal.db", 
					config.toProperties());//y poner en la conexion  , config.toProperties()
			System.out.println("Conectado");
			conexion.setAutoCommit(false);
			
			String sentenciaEliminar = "DELETE FROM empleado WHERE codigo_departamento = " + codigo;
					
			Statement sentencia=conexion.createStatement();
			eliminados[1]=sentencia.executeUpdate(sentenciaEliminar);
			
			sentenciaEliminar = "DELETE FROM departamento " +
					"WHERE codigo = " + codigo;
			sentencia=conexion.createStatement();
			eliminados[0]=sentencia.executeUpdate(sentenciaEliminar);
			conexion.commit();
			
		}catch(SQLException sqle){
		
		
			if (conexion != null) {
				conexion.rollback();
			}
			throw sqle;
		}finally {
			if (conexion != null) {
				conexion.close();
			}
		}
		return eliminados;

	}
}
