package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.AsignadoAModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AsignadoAView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    public AsignadoAView() {
        frame = new JFrame("Gestión de Asignaciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Cientifico");
        tableModel.addColumn("Proyecto");
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

    public void mostrarAsignacionesEnVista(List<AsignadoAModel> asignaciones) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (AsignadoAModel asignacion : asignaciones) {
            tableModel.addRow(new Object[]{asignacion.getCientifico(), asignacion.getProyecto()});
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
    public JTable getTable() {
    	return table;
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
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
