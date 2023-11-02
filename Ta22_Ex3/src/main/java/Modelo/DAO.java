package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection;

    public DAO() {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/T22_Cientificos";
            String user = "root";
            String password = "password";

            connection = DriverManager.getConnection(jdbcURL, user, password);
            System.out.println("Conectado");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
            
        }
    }

    public List<CientificoModel> obtenerTodosCientificos() {
        List<CientificoModel> cientificos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Cientificos";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                CientificoModel cientifico = new CientificoModel();
                cientifico.setDNI(result.getString("DNI"));
                cientifico.setNomApels(result.getString("nomApels"));
                cientificos.add(cientifico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cientificos;
    }

    public void crearCientifico(CientificoModel cientifico) {
        try {
            String sql = "INSERT INTO Cientificos (DNI, nomApels) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cientifico.getDNI());
            statement.setString(2, cientifico.getNomApels());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCientifico(CientificoModel cientifico) {
        try {
            String sql = "UPDATE Cientificos SET nomApels = ? WHERE DNI = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cientifico.getNomApels());
            statement.setString(2, cientifico.getDNI());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCientifico(String DNI) {
        try {
            String sql = "DELETE FROM Cientificos WHERE DNI = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, DNI);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ProyectoModel> obtenerTodosProyectos() {
        List<ProyectoModel> proyectos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Proyecto";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ProyectoModel proyecto = new ProyectoModel();
                proyecto.setId(result.getString("id"));
                proyecto.setNombre(result.getString("nombre"));
                proyecto.setHoras(result.getInt("horas"));
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proyectos;
    }

    public void crearProyecto(ProyectoModel proyecto) {
        try {
            String sql = "INSERT INTO Proyecto (nombre, horas) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, proyecto.getNombre());
            statement.setInt(2, proyecto.getHoras());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProyecto(ProyectoModel proyecto) {
        try {
            String sql = "UPDATE Proyecto SET nombre = ?, horas = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, proyecto.getNombre());
            statement.setInt(2, proyecto.getHoras());
            statement.setString(3, proyecto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProyecto(String id) {
        try {
            String sql = "DELETE FROM Proyecto WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<AsignadoAModel> obtenerTodasAsignaciones() {
        List<AsignadoAModel> asignaciones = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Asignado_A";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                AsignadoAModel asignacion = new AsignadoAModel();
                asignacion.setCientifico(result.getString("cientifico"));
                asignacion.setProyecto(result.getString("proyecto"));
                asignaciones.add(asignacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignaciones;
    }

    public void crearAsignacion(AsignadoAModel asignacion) {
        try {
            String sql = "INSERT INTO Asignado_A (cientifico, proyecto) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, asignacion.getCientifico());
            statement.setString(2, asignacion.getProyecto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAsignacion(String cientifico, String proyecto) {
        try {
            String sql = "DELETE FROM Asignado_A WHERE cientifico = ? AND proyecto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cientifico);
            statement.setString(2, proyecto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarAsignacion(AsignadoAModel nuevaAsignacion, String cientificoActual, String proyectoActual) {
        // Primero, elimina la asignación existente
		eliminarAsignacion(cientificoActual, proyectoActual);

		// Luego, crea una nueva asignación con los datos actualizados
		crearAsignacion(nuevaAsignacion);
    }
    
}
