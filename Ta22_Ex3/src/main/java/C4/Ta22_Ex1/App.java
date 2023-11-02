package C4.Ta22_Ex1;

import Controlador.AsignadoAController;
import Controlador.CientificoController;
import Controlador.ProyectoController;
import Modelo.DAO;
import Vista.AsignadoAView;
import Vista.CientificoView;
import Vista.ProyectoView;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Crear instancias de las vistas
        CientificoView cientificoView = new CientificoView();
        ProyectoView proyectoView = new ProyectoView();
        AsignadoAView asignadoAView = new AsignadoAView();

        // Crear instancias de los controladores
        DAO dao = new DAO();
        CientificoController cientificoController = new CientificoController(dao, cientificoView);
        ProyectoController proyectoController = new ProyectoController(dao, proyectoView);
        AsignadoAController asignadoAController = new AsignadoAController(dao, asignadoAView);

        // Mostrar las vistas
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cientificoView.getFrame().setVisible(true);
                proyectoView.getFrame().setVisible(true); // Ocultar la vista de proyectos inicialmente
                asignadoAView.getFrame().setVisible(true); // Ocultar la vista de asignaciones inicialmente
            }
        });
    }
}
