//Michael Cordova
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Ventana {

    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    private JTextField txtNombrePaciente;
    private JTextField txtCedulaPaciente;
    private JTextField txtEdadPaciente;
    private JTextField txtContactoPaciente;
    private JComboBox<String> cboAlertaPaciente;
    private JButton btnRegistrarPaciente;

    private JTextField txtNombreCuidador;
    private JTextField txtCedulaCuidador;
    private JTextField txtContactoCuidador;
    private JTextField txtCedulaDelPacienteCuidado;
    private JButton btnRegistrarCuidador;

    private JTextField txtCedulaCrearTratamiento;
    private JComboBox<String> comboBox2;
    private JButton btnAgregarMedicamento;
    private JButton btnReactivarTratamiento;
    private JButton btnFinalizarTratamiento;
    private JList lstGestion_Tratamientos;

    private JList lstAgenda_Recordatorios;
    private JButton btnGenerarRecordatoriosAutomaticos;
    private JButton btnVisualizarAgendaDiariaSemanal;
    private JComboBox<String> cboRegistrarEstadoRecordatorio;
    private JTextField txtIngresarCedulaPaciente;

    private JList lstEditarOEliminarRecordatorios;
    private JTextField txtIngresoCedulaPacienteBuscarOEliminar;
    private JTextField txtIngresoEditarRecordatorio;    // hora
    private JButton btnEliminarRecordatorio;
    private JButton btnEditarRecordatorio;              // editar hora
    private JTextField txtEditarMedicamento;
    private JButton btnEditarMedicamento;
    private JButton btnRegistrarEstadoDeRecordatorio;

    private GestorPacientes gestorPacientes = new GestorPacientes();
    private GestorTratamientos gestorTratamientos = new GestorTratamientos();
    private GestorRecordatorios gestorRecordatorios = new GestorRecordatorios();

    private boolean esNumero(String texto) {
        if (texto == null || texto.length() == 0) return false;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    private void actualizarListaTratamientos(String cedula) {
        if (!esNumero(cedula)) return;
        String opcion = (String) comboBox2.getSelectedItem();
        if (opcion == null) opcion = "SI";
        List<Tratamiento> lista = gestorTratamientos.obtenerPorEstado(cedula, opcion);
        DefaultListModel modelo = new DefaultListModel();
        for (Tratamiento t : lista) modelo.addElement(t.toString());
        lstGestion_Tratamientos.setModel(modelo);
    }

    private void actualizarListaAgendaPorCedula(String cedula) {
        if (!esNumero(cedula)) return;
        List<Recordatorio> lista = gestorRecordatorios.getPorCedula(cedula);
        DefaultListModel modelo = new DefaultListModel();
        for (Recordatorio r : lista) modelo.addElement(r.toString());
        lstAgenda_Recordatorios.setModel(modelo);
    }

    private void actualizarListaEditar(String cedula) {
        if (!esNumero(cedula)) return;
        List<Recordatorio> lista = gestorRecordatorios.getPorCedula(cedula);
        DefaultListModel modelo = new DefaultListModel();
        for (Recordatorio r : lista) modelo.addElement(r.toString());
        lstEditarOEliminarRecordatorios.setModel(modelo);
    }

    private void cargarDatosEjemplo() {
        Paciente p1 = new Paciente("1111", "Ana Pérez", 30, "099111111", "ROJA");
        gestorPacientes.agregarPaciente(p1);
        p1.asignarCuidador("2222", "Carlos López", "099222222");

        Paciente p2 = new Paciente("3333", "Luis Gómez", 45, "099333333", "VERDE");
        gestorPacientes.agregarPaciente(p2);

        gestorTratamientos.agregarTratamiento(new Tratamiento("1111", "Paracetamol", "500mg", 8, 1));
        gestorTratamientos.agregarTratamiento(new Tratamiento("1111", "Enalapril", "10mg", 12, 1));
        gestorTratamientos.agregarTratamiento(new Tratamiento("1111", "Ibuprofeno", "400mg", 8, 0));

        if (cboAlertaPaciente.getItemCount() == 0) {
            cboAlertaPaciente.addItem("ROJA");
            cboAlertaPaciente.addItem("AMARILLA");
            cboAlertaPaciente.addItem("VERDE");
        }
        if (comboBox2.getItemCount() == 0) {
            comboBox2.addItem("SI");
            comboBox2.addItem("NO");
        }
        if (cboRegistrarEstadoRecordatorio.getItemCount() == 0) {
            cboRegistrarEstadoRecordatorio.addItem("TOMADO");
            cboRegistrarEstadoRecordatorio.addItem("OMITIDO");
            cboRegistrarEstadoRecordatorio.addItem("POSPUESTO");
        }

        txtCedulaCrearTratamiento.setText("1111");
        txtIngresarCedulaPaciente.setText("1111");
        txtIngresoCedulaPacienteBuscarOEliminar.setText("1111");

        actualizarListaTratamientos("1111");
    }

    public Ventana() {

        cargarDatosEjemplo();

        // ==================== PACIENTE ====================
        btnRegistrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombrePaciente.getText();
                String cedula = txtCedulaPaciente.getText();
                String edadTxt = txtEdadPaciente.getText();
                String contacto = txtContactoPaciente.getText();
                String alerta = (String) cboAlertaPaciente.getSelectedItem();

                if (nombre.isEmpty() || cedula.isEmpty() || edadTxt.isEmpty() || contacto.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Complete todos los campos del paciente");
                    return;
                }
                if (!esNumero(cedula)) {
                    JOptionPane.showMessageDialog(panel1, "La cédula debe ser numérica");
                    return;
                }
                if (!esNumero(contacto)) {
                    JOptionPane.showMessageDialog(panel1, "El contacto debe ser numérico");
                    return;
                }
                if (!esNumero(edadTxt)) {
                    JOptionPane.showMessageDialog(panel1, "La edad debe ser numérica");
                    return;
                }

                int edad = Integer.parseInt(edadTxt);

                int resp = gestorPacientes.agregarPaciente(new Paciente(
                        cedula, nombre, edad, contacto, alerta
                ));

                if (resp == 0) {
                    JOptionPane.showMessageDialog(panel1, "La cédula ya existe");
                } else {
                    JOptionPane.showMessageDialog(panel1, "Paciente registrado correctamente");
                }
            }
        });

        // ==================== CUIDADOR ====================
        btnRegistrarCuidador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreCuidador.getText();
                String cedula = txtCedulaCuidador.getText();
                String contacto = txtContactoCuidador.getText();
                String cedulaPaciente = txtCedulaDelPacienteCuidado.getText();

                if (nombre.isEmpty() || cedula.isEmpty() || contacto.isEmpty() || cedulaPaciente.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Complete todos los campos");
                    return;
                }
                if (!esNumero(cedula) || !esNumero(contacto) || !esNumero(cedulaPaciente)) {
                    JOptionPane.showMessageDialog(panel1, "Datos numéricos incorrectos");
                    return;
                }

                Paciente p = gestorPacientes.buscarPorCedula(cedulaPaciente);
                if (p == null) {
                    JOptionPane.showMessageDialog(panel1, "El paciente no existe");
                    return;
                }

                int r = p.asignarCuidador(cedula, nombre, contacto);
                if (r == 0) {
                    JOptionPane.showMessageDialog(panel1, "El paciente ya tiene cuidador");
                } else {
                    JOptionPane.showMessageDialog(panel1, "Cuidador asignado correctamente");
                }
            }
        });

        // ==================== TRATAMIENTOS ====================
        btnAgregarMedicamento.addActionListener(e -> {
            String cedula = txtCedulaCrearTratamiento.getText();
            if (!esNumero(cedula)) {
                JOptionPane.showMessageDialog(panel1, "Ingrese cédula válida");
                return;
            }
            Paciente p = gestorPacientes.buscarPorCedula(cedula);
            if (p == null) {
                JOptionPane.showMessageDialog(panel1, "Paciente no encontrado");
                return;
            }

            String med = JOptionPane.showInputDialog(panel1, "Medicamento:");
            String dosis = JOptionPane.showInputDialog(panel1, "Dosis:");
            String cada = JOptionPane.showInputDialog(panel1, "Cada cuántas horas:");

            if (med == null || dosis == null || cada == null) return;
            if (med.isEmpty() || dosis.isEmpty() || cada.isEmpty()) return;
            if (!esNumero(cada)) {
                JOptionPane.showMessageDialog(panel1, "Las horas deben ser numéricas");
                return;
            }

            int horas = Integer.parseInt(cada);
            gestorTratamientos.agregarTratamiento(new Tratamiento(
                    cedula, med, dosis, horas, 1
            ));
            actualizarListaTratamientos(cedula);
            JOptionPane.showMessageDialog(panel1, "Medicamento agregado");
        });

        comboBox2.addActionListener(e -> {
            String cedula = txtCedulaCrearTratamiento.getText();
            if (!esNumero(cedula)) return;
            actualizarListaTratamientos(cedula);
        });

        btnFinalizarTratamiento.addActionListener(e -> {
            String cedula = txtCedulaCrearTratamiento.getText();
            if (!esNumero(cedula)) return;
            int idx = lstGestion_Tratamientos.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un tratamiento");
                return;
            }
            String opcion = (String) comboBox2.getSelectedItem();
            gestorTratamientos.cambiarEstado(cedula, opcion, idx, 0);
            actualizarListaTratamientos(cedula);
            JOptionPane.showMessageDialog(panel1, "Tratamiento finalizado");
        });

        btnReactivarTratamiento.addActionListener(e -> {
            String cedula = txtCedulaCrearTratamiento.getText();
            if (!esNumero(cedula)) return;
            int idx = lstGestion_Tratamientos.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un tratamiento");
                return;
            }
            String opcion = (String) comboBox2.getSelectedItem();
            gestorTratamientos.cambiarEstado(cedula, opcion, idx, 1);
            actualizarListaTratamientos(cedula);
            JOptionPane.showMessageDialog(panel1, "Tratamiento reactivado");
        });

        // ==================== AGENDA / GENERAR ====================
        btnGenerarRecordatoriosAutomaticos.addActionListener(e -> {
            String cedula = txtIngresarCedulaPaciente.getText();
            if (!esNumero(cedula)) {
                JOptionPane.showMessageDialog(panel1, "Cédula inválida");
                return;
            }

            List<Tratamiento> activos = gestorTratamientos.obtenerPorEstado(cedula, "SI");
            if (activos.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "El paciente no tiene tratamientos activos");
                return;
            }

            String fecha = JOptionPane.showInputDialog(panel1,
                    "Ingrese fecha de inicio (AAAA-MM-DD):", "2025-01-01");
            if (fecha == null || fecha.trim().isEmpty()) return;

            String hora = JOptionPane.showInputDialog(panel1,
                    "Ingrese hora (HH:MM):", "08:00");
            if (hora == null || hora.trim().isEmpty()) return;

            gestorRecordatorios.generarDesdeTratamientos(activos, fecha.trim(), hora.trim());
            actualizarListaAgendaPorCedula(cedula);
            actualizarListaEditar(cedula);

            JOptionPane.showMessageDialog(panel1, "Recordatorios generados");
        });

        // ==================== AGENDA DIARIA / SEMANAL ====================
        btnVisualizarAgendaDiariaSemanal.addActionListener(e -> {
            String cedula = txtIngresarCedulaPaciente.getText();
            if (!esNumero(cedula)) {
                JOptionPane.showMessageDialog(panel1, "Cédula inválida");
                return;
            }

            String[] opciones = {"DIARIA", "SEMANAL"};
            String tipo = (String) JOptionPane.showInputDialog(
                    panel1,
                    "Seleccione tipo de agenda:",
                    "Agenda",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    "DIARIA"
            );
            if (tipo == null) return;

            String fecha = JOptionPane.showInputDialog(panel1,
                    "Ingrese fecha (AAAA-MM-DD):");
            if (fecha == null || fecha.trim().isEmpty()) return;
            fecha = fecha.trim();

            List<Recordatorio> lista;
            if (tipo.equals("DIARIA")) {
                lista = gestorRecordatorios.getAgendaDiaria(cedula, fecha);
            } else {
                lista = gestorRecordatorios.getAgendaSemanal(cedula, fecha);
            }

            DefaultListModel modelo = new DefaultListModel();
            for (Recordatorio r : lista) modelo.addElement(r.toString());
            lstAgenda_Recordatorios.setModel(modelo);
        });

        // ==================== REGISTRAR ESTADO ====================
        btnRegistrarEstadoDeRecordatorio.addActionListener(e -> {
            String ced = txtIngresarCedulaPaciente.getText();
            if (!esNumero(ced)) {
                JOptionPane.showMessageDialog(panel1, "Cédula inválida");
                return;
            }
            int idx = lstAgenda_Recordatorios.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un recordatorio en la lista");
                return;
            }
            String estado = (String) cboRegistrarEstadoRecordatorio.getSelectedItem();
            gestorRecordatorios.registrarEstado(ced, idx, estado);
            actualizarListaAgendaPorCedula(ced);
            actualizarListaEditar(ced);
            JOptionPane.showMessageDialog(panel1, "Estado actualizado");
        });

        lstAgenda_Recordatorios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    btnRegistrarEstadoDeRecordatorio.doClick();
                }
            }
        });

        // ==================== BUSCAR PARA EDITAR/ELIMINAR ====================
        txtIngresoCedulaPacienteBuscarOEliminar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String ced = txtIngresoCedulaPacienteBuscarOEliminar.getText();
                if (!esNumero(ced)) return;
                actualizarListaEditar(ced);
            }
        });

        // ==================== ELIMINAR RECORDATORIO ====================
        btnEliminarRecordatorio.addActionListener(e -> {
            String ced = txtIngresoCedulaPacienteBuscarOEliminar.getText();
            if (!esNumero(ced)) return;
            int idx = lstEditarOEliminarRecordatorios.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un recordatorio");
                return;
            }
            gestorRecordatorios.eliminar(ced, idx);
            actualizarListaEditar(ced);
            actualizarListaAgendaPorCedula(ced);
            JOptionPane.showMessageDialog(panel1, "Recordatorio eliminado");
        });

        // ==================== EDITAR HORA ====================
        btnEditarRecordatorio.addActionListener(e -> {
            String ced = txtIngresoCedulaPacienteBuscarOEliminar.getText();
            if (!esNumero(ced)) return;
            int idx = lstEditarOEliminarRecordatorios.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un recordatorio");
                return;
            }
            String nuevaHora = txtIngresoEditarRecordatorio.getText();
            if (nuevaHora.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "Ingrese la nueva hora");
                return;
            }
            gestorRecordatorios.editarHora(ced, idx, nuevaHora);
            actualizarListaEditar(ced);
            actualizarListaAgendaPorCedula(ced);
            JOptionPane.showMessageDialog(panel1, "Hora actualizada");
        });

        // ==================== EDITAR MEDICAMENTO ====================
        btnEditarMedicamento.addActionListener(e -> {
            String ced = txtIngresoCedulaPacienteBuscarOEliminar.getText();
            if (!esNumero(ced)) return;
            int idx = lstEditarOEliminarRecordatorios.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(panel1, "Seleccione un recordatorio");
                return;
            }
            String nuevoMed = txtEditarMedicamento.getText();
            if (nuevoMed.isEmpty()) {
                JOptionPane.showMessageDialog(panel1, "Ingrese el medicamento");
                return;
            }
            gestorRecordatorios.editar(ced, idx, nuevoMed);
            actualizarListaEditar(ced);
            actualizarListaAgendaPorCedula(ced);
            JOptionPane.showMessageDialog(panel1, "Medicamento actualizado");
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Medicamentos");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
