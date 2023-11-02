package Controlador;

import Modelo.AsignadoAModel;
import Modelo.DAO;
import Vista.AsignadoAView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class AsignadoAController {
    private DAO DAO;
    private AsignadoAView asignadoAView;

    public AsignadoAController(DAO DAO, AsignadoAView asignadoAView) {
        this.DAO = DAO;
        this.asignadoAView = asignadoAView;

        // Agregar ActionListener para el botón "Agregar"
        asignadoAView.agregarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener datos del usuario, por ejemplo, mediante cuadros de diálogo
                String cientifico = JOptionPane.showInputDialog("Ingrese el DNI del científico:");
                String proyecto = JOptionPane.showInputDialog("Ingrese el ID del proyecto:");

                // Verificar si se ingresaron datos
                if (cientifico != null && proyecto != null) {
                	if(verificarExistenciaAsignacion(cientifico,proyecto)) {
                		AsignadoAModel nuevaAsignacion = new AsignadoAModel();
                        nuevaAsignacion.setCientifico(cientifico);
                        nuevaAsignacion.setProyecto(proyecto);

                        // Llamar al método de DAO para crear la asignación
                        DAO.crearAsignacion(nuevaAsignacion);

                        // Actualizar la vista con las asignaciones
                        mostrarAsignacionesEnVista();
                	}
                	else {
                        asignadoAView.mostrarMensajeError("El cientifico o/y proyecto no existe.");
                	}
                }
                else {
                    asignadoAView.mostrarMensajeError("El cientifico y el proyecto no pueden estar vacíos.");
                }
                
            }
        });

        // Agregar ActionListener para el botón "Editar"
        asignadoAView.editarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada en la tabla
                int filaSeleccionada = asignadoAView.getTable().getSelectedRow();

                if (filaSeleccionada == -1) {
                	JOptionPane.showMessageDialog(asignadoAView.getFrame(), "Seleccione una asignación para editar.");
                    return;
                }

                // Obtener los datos de la fila seleccionada
                String cientificoActual = (String) asignadoAView.getTable().getValueAt(filaSeleccionada, 0);
                String proyectoActual = (String) asignadoAView.getTable().getValueAt(filaSeleccionada, 1);

                // Solicitar al usuario que ingrese los nuevos datos
                String nuevoCientifico = JOptionPane.showInputDialog("Ingrese el nuevo DNI del científico:", cientificoActual);
                String nuevoProyecto = JOptionPane.showInputDialog("Ingrese el nuevo DNI del proyecto:", proyectoActual);

                // Verificar si se ingresaron nuevos datos
                if (nuevoCientifico != null && nuevoProyecto != null) {
                	if(verificarExistenciaAsignacion(nuevoCientifico,nuevoProyecto)) {
                		AsignadoAModel asignacionActualizada = new AsignadoAModel();
                        asignacionActualizada.setCientifico(nuevoCientifico);
                        asignacionActualizada.setProyecto(nuevoProyecto);

                        // Llamar al método de DAO para actualizar la asignación
                        DAO.actualizarAsignacion(asignacionActualizada, cientificoActual, proyectoActual);

                        // Actualizar la vista con las asignaciones
                        mostrarAsignacionesEnVista();
                	}
                	else {
                       asignadoAView.mostrarMensajeError("El cientifico o/y proyecto no existe.");

                	}
                    
                }
                else {
                    asignadoAView.mostrarMensajeError("El cientifico y el proyecto no pueden estar vacíos.");
                }
            }
        });

        // Agregar ActionListener para el botón "Eliminar"
        asignadoAView.eliminarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada en la tabla
                int filaSeleccionada = asignadoAView.getTable().getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(asignadoAView.getFrame(), "Seleccione una asignación para eliminar.");
                    return;
                }

                // Obtener los datos de la fila seleccionada
                String cientifico = (String) asignadoAView.getTable().getValueAt(filaSeleccionada, 0);
                String proyecto = (String) asignadoAView.getTable().getValueAt(filaSeleccionada, 1);

                if(verificarExistenciaAsignacion(cientifico,proyecto)) {
                	// Confirmar la eliminación
                    int confirmacion = JOptionPane.showConfirmDialog(asignadoAView.getFrame(), "¿Seguro que desea eliminar esta asignación?",
                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Llamar al método de DAO para eliminar la asignación
                        DAO.eliminarAsignacion(cientifico, proyecto);

                        // Actualizar la vista con las asignaciones
                        mostrarAsignacionesEnVista();
                    }
                }
                else {
                    asignadoAView.mostrarMensajeError("El cientifico o/y proyecto no existe.");
                }
            }
        });

        // Actualizar la vista con las asignaciones existentes
        mostrarAsignacionesEnVista();
    }

    // Método para mostrar las asignaciones en la vista
    private void mostrarAsignacionesEnVista() {
        List<AsignadoAModel> asignaciones = DAO.obtenerTodasAsignaciones();
        asignadoAView.mostrarAsignacionesEnVista(asignaciones);
    }
    private boolean verificarExistenciaAsignacion(String cientifico, String proyecto) {
        // Consultar la base de datos para verificar si la asignación existe
        List<AsignadoAModel> asignaciones = DAO.obtenerTodasAsignaciones();
        for (AsignadoAModel asignacion : asignaciones) {
            if (asignacion.getCientifico().equals(cientifico) && asignacion.getProyecto().equals(proyecto)) {
                return true;
            }
        }
        return false;
    }
}
