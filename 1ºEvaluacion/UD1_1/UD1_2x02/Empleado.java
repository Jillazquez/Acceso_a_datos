package AD_2x02;

import java.io.Serializable;

public class Empleado implements Serializable{
	private int codigo;
	private int codigoDepartamento;
	private String nombre;
	private String fecha;
	private int salario;
	
	public static final String SEPARADOR = ";";
	
	public Empleado(int codigo, int codigoDepartamento, String nombre, String fecha, int salario) {
		super();
		this.codigo = codigo;
		this.codigoDepartamento = codigoDepartamento;
		this.nombre = nombre;
		this.fecha = fecha;
		this.salario = salario;
	}
	
	public Empleado(String lineaTexto) {
		String[] partes = lineaTexto.split(SEPARADOR);
		
		if(partes.length == 5) {
			this.codigo = Integer.parseInt(partes[0]);
			this.codigoDepartamento = Integer.parseInt(partes[1]);
			this.nombre = partes[2];
			this.fecha = partes[3];
			this.salario = Integer.parseInt(partes[4]);
		}
	}
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", codigoDepartamento=" + codigoDepartamento + ", nombre=" + nombre
				+ ", fecha=" + fecha + ", salario=" + salario + "]";
	}
	
	// Método toStringWithSeparators para representación de texto en fichero
    public String toStringWithSeparators() {
        return codigo + ";" + codigoDepartamento + ";" + nombre + ";" + fecha + ";" + salario;
    }
	
	
}
