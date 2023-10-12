package AD_2x02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AD_2x01.Departamento;

public class AccesoEmpleado {
	private static final String NOMBREFICHERO = "empleado.txt";
	
	public static void insertoEmpleado(Empleado empleado) throws IOException {
		String linea;
		BufferedWriter escritor = null;
		try {
			escritor = new BufferedWriter(new FileWriter(NOMBREFICHERO, true));//True es para que no sobreescriba lo que ya hay
			escritor.write(empleado.toStringWithSeparators());
			escritor.newLine();
			}
		finally {
			if(escritor != null)
				escritor.close();
		}
		}
	public static List<Empleado> leoEmpleado() throws IOException {
		BufferedReader lector = null;
		List<Empleado> empleados = new ArrayList<>();
		String linea;
		try {
			lector = new BufferedReader(new FileReader(NOMBREFICHERO));
			while((linea = lector.readLine()) != null) {
				Empleado emp = new Empleado(linea);
				empleados.add(emp);
			}
			return empleados;
		}finally {
			if(lector != null)
				lector.close();
		}
			}
}