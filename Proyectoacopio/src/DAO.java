import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection;

    // Constructor que establece la conexión a la base de datos
    public DAO() {
        try {
            // Asegúrate de que esta URL de conexión es la correcta
            String url = "jdbc:mysql://localhost:3306/acopiadora_bd"; // Agrega parámetros si es necesario
            String user = "root";  // Cambia el usuario según tu configuración
            String password = "1234";  // Cambia la contraseña según tu configuración

            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
          //  es el encargado de permitir la conexión y la ejecución de consultas SQL en una base de datos MySQL
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            // Captura errores de SQL
            System.out.println("Error de SQL al conectar a la base de datos:");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Captura si no se encuentra la clase del driver
            System.out.println("Error al cargar el driver JDBC:");
            e.printStackTrace();
        }
    }

    // Método para agregar un nuevo registro
    public void agregarRegistro(Registro registro) {
        String query = "INSERT INTO registros (fecha, apellido, nombre, patente, cereal, campo, lote, kilo, humedad, silo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, registro.getFecha());
            stmt.setString(2, registro.getApellido());
            stmt.setString(3, registro.getNombre());
            stmt.setString(4, registro.getPatente());
            stmt.setString(5, registro.getCereal());
            stmt.setString(6, registro.getCampo());
            stmt.setString(7, registro.getLote());
            stmt.setInt(8, registro.getKilo());
            stmt.setInt(9, registro.getHumedad());
            stmt.setInt(10, registro.getSilo());

            // Ejecutar la consulta de inserción
            stmt.executeUpdate();
            System.out.println("¡Registro agregado exitosamente!");

        } catch (SQLException e) {
            // Si ocurre un error al ejecutar la consulta SQL, mostramos detalles del error
            System.out.println("Error al agregar el registro:");
            e.printStackTrace(); // Imprime detalles del error
            System.out.println("Código de error: " + e.getErrorCode());
            System.out.println("Mensaje de error: " + e.getMessage());
        }
    }

    // Método para obtener todos los registros
    public List<Registro> obtenerRegistros() {
        List<Registro> registros = new ArrayList<>();
        String query = "SELECT * FROM registros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Registro registro = new Registro(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getString("apellido"),
                        rs.getString("nombre"),
                        rs.getString("patente"),
                        rs.getString("cereal"),
                        rs.getString("campo"),
                        rs.getString("lote"),
                        rs.getInt("kilo"),
                        rs.getInt("humedad"),
                        rs.getInt("silo")
                );
                registros.add(registro);
            }
        } catch (SQLException e) {
            // Captura errores de SQL al realizar la consulta
            System.out.println("Error al obtener los registros:");
            e.printStackTrace();
        }
        return registros;
    }

    // Método para modificar un registro
    public void modificarRegistro(Registro registro) {
        String query = "UPDATE registros SET fecha = ?, apellido = ?, nombre = ?, patente = ?, cereal = ?, campo = ?, lote = ?, kilo = ?, humedad = ?, silo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, registro.getFecha());
            stmt.setString(2, registro.getApellido());
            stmt.setString(3, registro.getNombre());
            stmt.setString(4, registro.getPatente());
            stmt.setString(5, registro.getCereal());
            stmt.setString(6, registro.getCampo());
            stmt.setString(7, registro.getLote());
            stmt.setInt(8, registro.getKilo());
            stmt.setInt(9, registro.getHumedad());
            stmt.setInt(10, registro.getSilo());
            stmt.setInt(11, registro.getId());

            stmt.executeUpdate();
            System.out.println("¡Registro modificado exitosamente!");

        } catch (SQLException e) {
            // Si ocurre un error de SQL, se captura y se imprime el detalle
            System.out.println("Error al modificar el registro:");
            e.printStackTrace(); // Imprime detalles del error
        }
    }

    // Método para eliminar un registro
    public void eliminarRegistro(int id) {
        String query = "DELETE FROM registros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("¡Registro eliminado exitosamente!");
        } catch (SQLException e) {
            // Si ocurre un error al eliminar, se captura y muestra el detalle
            System.out.println("Error al eliminar el registro:");
            e.printStackTrace(); // Imprime detalles del error
        }
    }
}
