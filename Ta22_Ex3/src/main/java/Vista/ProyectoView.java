package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.ProyectoModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProyectoView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    public ProyectoView() {
        frame = new JFrame("Gestión de Proyectos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Horas");
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        agregarButton = new JButton("Agregar");
        editarButton = new JButton("Editar");
        eliminarButton = new JButton("Eliminar");
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void mostrarProyectosEnVista(List<ProyectoModel> proyectos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (ProyectoModel proyecto : proyectos) {
            tableModel.addRow(new Object[]{proyecto.getId(), proyecto.getNombre(), proyecto.getHoras()});
        }
    }

    public JButton getAgregarButton() {
        return agregarButton;
    }

    public JButton getEditarButton() {
        return editarButton;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void agregarButtonListener(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void editarButtonListener(ActionListener listener) {
        editarButton.addActionListener(listener);
    }

    public void eliminarButtonListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }
    public String pedirIdProyecto() {
        return JOptionPane.showInputDialog("Ingrese el ID del proyecto:");
    }

    public String pedirNombreProyecto() {
        return JOptionPane.showInputDialog("Ingrese el nombre del proyecto:");
    }

    public int pedirHorasProyecto() {
        try {
            String horasStr = JOptionPane.showInputDialog("Ingrese las horas del proyecto:");
            return Integer.parseInt(horasStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Las horas deben ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Valor inválido
        }
    }
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
