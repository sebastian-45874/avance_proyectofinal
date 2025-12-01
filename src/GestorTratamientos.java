import java.util.ArrayList;
import java.util.List;
//Julian Gordon

public class GestorTratamientos {

    private List<Tratamiento> listaTratamientos;

    public GestorTratamientos() {
        listaTratamientos = new ArrayList<Tratamiento>();
    }

    public void agregarTratamiento(Tratamiento t) {
        listaTratamientos.add(t);
    }

    public List<Tratamiento> obtenerPorEstado(String cedulaPaciente, String opcionSiNo) {
        List<Tratamiento> resultado = new ArrayList<Tratamiento>();
        int estadoBuscado;

        if (opcionSiNo.equalsIgnoreCase("SI")) {
            estadoBuscado = 1;
        } else {
            estadoBuscado = 0;
        }

        for (Tratamiento t : listaTratamientos) {
            if (t.getCedulaPaciente().equals(cedulaPaciente) && t.getActivo() == estadoBuscado) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public void cambiarEstado(String cedulaPaciente, String opcionSiNo, int indiceEnLista, int nuevoEstado) {
        List<Integer> indicesGlobales = new ArrayList<Integer>();

        int estadoActual;
        if (opcionSiNo.equalsIgnoreCase("SI")) {
            estadoActual = 1;
        } else {
            estadoActual = 0;
        }

        for (int i = 0; i < listaTratamientos.size(); i++) {
            Tratamiento t = listaTratamientos.get(i);
            if (t.getCedulaPaciente().equals(cedulaPaciente) && t.getActivo() == estadoActual) {
                indicesGlobales.add(i);
            }
        }

        if (indiceEnLista >= 0 && indiceEnLista < indicesGlobales.size()) {
            int indiceReal = indicesGlobales.get(indiceEnLista);
            listaTratamientos.get(indiceReal).setActivo(nuevoEstado);
        }
    }
}
