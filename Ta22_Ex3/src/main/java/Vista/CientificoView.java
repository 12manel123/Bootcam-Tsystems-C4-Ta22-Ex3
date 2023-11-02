package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.CientificoModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CientificoView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    public CientificoView() {
        frame = new JFrame("Gestión de Científicos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre y Apellidos");
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

    public void mostrarCientificosEnVista(List<CientificoModel> cientificos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (CientificoModel cientifico : cientificos) {
            tableModel.addRow(new Object[]{cientifico.getDNI(), cientifico.getNomApels()});
        }
    }

    public String pedirDNI() {
        return JOptionPane.showInputDialog("Introduce el DNI:");
    }

    public String pedirNomApels() {
        return JOptionPane.showInputDialog("Introduce el Nombre y Apellidos:");
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
}
