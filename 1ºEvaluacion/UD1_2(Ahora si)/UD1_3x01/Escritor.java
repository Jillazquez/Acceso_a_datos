package practicaexamen;

import java.io.Serializable;

public class Escritor implements Serializable{
    int codigo;
    String nombre;
    String fecha;
    String nacionalidad;

    public Escritor(int codigo, String nombre, String fecha, String nacionalidad)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha = fecha;
        this.nacionalidad = nacionalidad;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getFecha()
    {
        return fecha;
    }

    public String getNacionalidad()
    {
        return nacionalidad;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setFecha(String nombre)
    {
        this.fecha = nombre;
    }

    public void setNacionalidad(String nombre)
    {
        this.nacionalidad = nombre;
    }

    public String toString(){
        return "Escritor nombre: "+nombre+" codigo: "+codigo+" fecha: "+fecha+" nacionalidad "+nacionalidad;
    }
}
