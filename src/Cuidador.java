//Mateo Cuascota
public class Cuidador {
    private String cedula;
    private String nombre;
    private String contacto;

    public Cuidador(String cedula, String nombre, String contacto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public String toString() {
        return "Cuidador: " + nombre
                + " Cédula: " + cedula
                + " Teléfono: " + contacto;
    }
}
