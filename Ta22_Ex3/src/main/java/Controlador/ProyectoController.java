package Controlador;

import Modelo.DAO;
import Modelo.ProyectoModel;
import Vista.ProyectoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProyectoController {
    private DAO DAO;
    private ProyectoView proyectoView;

    public ProyectoController(DAO DAO, ProyectoView cientificoView) {
        this.DAO = DAO;
        this.proyectoView = cientificoView;
        
        // Agregar un ActionListener para el botón "Agregar"
        proyectoView.agregarButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = proyectoView.pedirNombreProyecto();
                int horas = proyectoView.pedirHorasProyecto();

                if (nombre != null && !nombre.isEmpty()) {
                    ProyectoModel proyecto = new ProyectoModel();
                    proyecto.setNombre(nombre);
                    proyecto.setHoras(horas);

                    DAO.crearProyecto(proyecto);
                    proyectoView.mostrarProyectosEnVista(DAO.obtenerTodosProyectos());
                }
                else {
                    proyectoView.mostrarMensajeError("El id no puede ser vacío");
                }
            }
        });

        // Agregar un ActionListener para el botón "Editar"
        proyectoView.editarButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = proyectoView.pedirIdProyecto();
                if (id != null && !id.isEmpty()) {
                	
                	if (verificarExistenciaProyecto(id)) {
                		 String nombre = proyectoView.pedirNombreProyecto();
                         int horas = proyectoView.pedirHorasProyecto();

                         ProyectoModel proyecto = new ProyectoModel();
                         proyecto.setId(id);
                         proyecto.setNombre(nombre);
                         proyecto.setHoras(horas);

                         DAO.actualizarProyecto(proyecto);
                         proyectoView.mostrarProyectosEnVista(DAO.obtenerTodosProyectos());
                     
                	}
                	else {
                        proyectoView.mostrarMensajeError("El proyecto con ID " + id + " no existe.");

                	}
                   }
                else {
                    proyectoView.mostrarMensajeError("El id no puede ser vacío");
                }
            }
        });

        // Agregar un ActionListener para el botón "Eliminar"
        proyectoView.eliminarButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = proyectoView.pedirIdProyecto();
                if (id != null && !id.isEmpty()) {
                	if (verificarExistenciaProyecto(id)) {
                		DAO.eliminarProyecto(id);
                        proyectoView.mostrarProyectosEnVista(DAO.obtenerTodosProyectos());
                	}
                	else {
                        proyectoView.mostrarMensajeError("El proyecto con ID " + id + " no existe.");
                	}
                    
                }
                else {
                    proyectoView.mostrarMensajeError("El id no puede ser vacío");
                }
            }
        });
        List<ProyectoModel> proyectos = DAO.obtenerTodosProyectos();
    	proyectoView.mostrarProyectosEnVista(proyectos);
        }
    	
    private boolean verificarExistenciaProyecto(String id) {
        // Consultar la base de datos para verificar si el proyecto con ID existe
        List<ProyectoModel> proyectos = DAO.obtenerTodosProyectos();
        for (ProyectoModel proyecto : proyectos) {
            if (proyecto.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
       
	
}

