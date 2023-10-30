package AD_3x02;

public class Libro {
	int codigo,codigoEscritor,añoPublicacion,precio;
	String titulo;

	public Libro(int codigo, int codigoEscritor, int añoPublicacion, int precio,String titulo) {
		this.codigo = codigo;
		this.codigoEscritor = codigoEscritor;
		this.añoPublicacion = añoPublicacion;
		this.precio = precio;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoEscritor() {
		return codigoEscritor;
	}

	public void setCodigoEscritor(int codigoEscritor) {
		this.codigoEscritor = codigoEscritor;
	}

	public int getAñoPublicacion() {
		return añoPublicacion;
	}

	public void setAñoPublicacion(int añoPublicacion) {
		this.añoPublicacion = añoPublicacion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", codigoEscritor=" + codigoEscritor + ", añoPublicacion=" + añoPublicacion
				+ ", precio=" + precio + ", titulo=" + titulo + "]";
	}

	
	
	
}
