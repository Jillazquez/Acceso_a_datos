import java.io.Serializable;

public class Escritor implements Serializable{
    private int codigo;
    private String nombre;
    private String date;
    private String nacionalidad;

    public Escritor(int codigo, String nombre, String date, String nacionalidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.date = date;
        this.nacionalidad = nacionalidad;
    }

    public String toString(){
        return "Escritor nombre: "+nombre+" codigo: "+codigo+" fecha: "+date+" nacionalidad "+nacionalidad;
    }
}
