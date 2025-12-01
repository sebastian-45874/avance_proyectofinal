import java.util.ArrayList;
import java.util.List;

public class GestorPacientes {

    private List<Paciente> listaPacientes;

    public GestorPacientes() {
        listaPacientes = new ArrayList<Paciente>();
    }

    public int agregarPaciente(Paciente p) {
        for (Paciente x : listaPacientes) {
            if (x.getCedula().equals(p.getCedula())) {
                return 0;
            }
        }
        listaPacientes.add(p);
        return 1;
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente p : listaPacientes) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }
}
