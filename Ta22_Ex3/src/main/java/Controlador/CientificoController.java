package Controlador;

import Modelo.CientificoModel;
import Modelo.DAO;
import Vista.CientificoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CientificoController {
    private DAO DAO;
    private CientificoView cientificoView;

    public CientificoController(DAO DAO, CientificoView cientificoView) {
        this.DAO = DAO;
        this.cientificoView = cientificoView;
        
        cientificoView.agregarButtonListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String DNI = cientificoView.pedirDNI();
                if (DNI != null && !DNI.isEmpty()) {
                    String nomApels = cientificoView.pedirNomApels();
                    if (nomApels != null && !nomApels.isEmpty()) {
                        CientificoModel cientifico = new CientificoModel();
                        cientifico.setDNI(DNI);
                        cientifico.setNomApels(nomApels);
                        DAO.crearCientifico(cientifico);
                        mostrarCientificos();
                    } else {
                        // Mostrar mensaje de error
                        cientificoView.mostrarMensajeError("El nombre y apellidos no pueden estar vacíos.");
                    }
                } else {
                    // Mostrar mensaje de error
                    cientificoView.mostrarMensajeError("El DNI no puede estar vacío.");
                }
            }
        });
        
        cientificoView.editarButtonListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String DNI = cientificoView.pedirDNI();
                if (DNI != null && !DNI.isEmpty()) {
                    if (verificarExistenciaCientifico(DNI)) {
    	                String nomApels = cientificoView.pedirNomApels();
    	                if (nomApels != null && !nomApels.isEmpty()) {
    	                    CientificoModel cientifico = new CientificoModel();
    	                    cientifico.setDNI(DNI);
    	                    cientifico.setNomApels(nomApels);
    	                    DAO.actualizarCientifico(cientifico);
    	                    mostrarCientificos();
    	                } 
    	                else {
    	                    // Mostrar mensaje de error
    	                    cientificoView.mostrarMensajeError("El nombre y apellidos no pueden estar vacíos.");
    	                }}
                    else {
                        cientificoView.mostrarMensajeError("El científico con DNI " + DNI + " no existe.");
                    }
                } 
                else {
                    // Mostrar mensaje de error
                    cientificoView.mostrarMensajeError("El DNI no puede estar vacío.");
                }
            }
        });
        
        cientificoView.eliminarButtonListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String DNI = cientificoView.pedirDNI();
                if (DNI != null && !DNI.isEmpty()) {
                	if(verificarExistenciaCientifico(DNI)) {
                        DAO.eliminarCientifico(DNI);
                        mostrarCientificos();
                	}
                	else {
                        cientificoView.mostrarMensajeError("El científico con DNI " + DNI + " no existe.");

                	}
                } else {
                    // Mostrar mensaje de error
                    cientificoView.mostrarMensajeError("El DNI no puede estar vacío.");
                }
            }
        });


        mostrarCientificos();
    }

    private void mostrarCientificos() {
        List<CientificoModel> cientificos = DAO.obtenerTodosCientificos();
        cientificoView.mostrarCientificosEnVista(cientificos);
    }
    private boolean verificarExistenciaCientifico(String DNI) {
        // Consultar la base de datos para verificar si el científico con DNI existe
        List<CientificoModel> cientificos = DAO.obtenerTodosCientificos();
        for (CientificoModel cientifico : cientificos) {
            if (cientifico.getDNI().equals(DNI)) {
                return true;
            }
        }
        return false;
    }

       
	
}
