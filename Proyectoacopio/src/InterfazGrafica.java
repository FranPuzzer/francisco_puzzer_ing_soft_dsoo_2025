import javax.swing.*; // Importa las clases necesarias para crear la interfaz gráfica con Swing.
import javax.swing.border.TitledBorder; // Importa la clase para crear bordes con título.
import java.awt.*; // Importa las clases para los componentes gráficos y disposición.
import java.awt.event.*; // Importa las clases para manejar los eventos de la interfaz.
import java.sql.Date; // Importa la clase para manejar fechas en formato SQL.
import java.text.ParseException; // Importa la clase para manejar excepciones en el parseo de fechas.
import java.text.SimpleDateFormat; // Importa la clase para formatear y analizar fechas.
import javax.swing.table.DefaultTableModel; // Importa la clase para manipular tablas en la interfaz.
import java.util.List; // Importa la clase para trabajar con listas de objetos.

public class InterfazGrafica {
    private DAO dao; // Declara una variable de tipo DAO que manejará las interacciones con la base de datos.
    private JFrame frame; // Declara una variable para la ventana principal de la aplicación.
    
    // Declara los campos de texto donde el usuario ingresará los datos.
    private JTextField txtFecha, txtApellido, txtNombre, txtPatente, txtCereal, txtCampo, txtLote, txtKilo, txtHumedad, txtSilo;
    private JTable table; // Declara una variable para la tabla que mostrará los registros.
    private DefaultTableModel tableModel; // Declara el modelo de datos para la tabla.

    public InterfazGrafica() {
        dao = new DAO(); // Crea una instancia de la clase DAO para interactuar con la base de datos.

        // Configuración del frame (ventana principal).
        frame = new JFrame("Gestión de Registros de Acopiadora");
        frame.setSize(800, 600); // Establece el tamaño de la ventana (800x600 píxeles).
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece que la aplicación se cierra al cerrar la ventana.
        frame.setLayout(new BorderLayout()); // Establece un layout de tipo BorderLayout (para organizar los componentes).

        // Panel para los campos de entrada (en la parte superior de la ventana).
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2, 5, 5)); // Usa GridLayout con 11 filas y 2 columnas para los campos de texto.
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Información de Registro", TitledBorder.CENTER, TitledBorder.TOP));

        // Añade etiquetas y campos de texto al panel (para cada dato que se desea ingresar).
        panel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panel.add(txtApellido);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Patente:"));
        txtPatente = new JTextField();
        panel.add(txtPatente);

        panel.add(new JLabel("Cereal:"));
        txtCereal = new JTextField();
        panel.add(txtCereal);

        panel.add(new JLabel("Campo:"));
        txtCampo = new JTextField();
        panel.add(txtCampo);

        panel.add(new JLabel("Lote:"));
        txtLote = new JTextField();
        panel.add(txtLote);

        panel.add(new JLabel("Kilos:"));
        txtKilo = new JTextField();
        panel.add(txtKilo);

        panel.add(new JLabel("Humedad:"));
        txtHumedad = new JTextField();
        panel.add(txtHumedad);

        panel.add(new JLabel("Silo:"));
        txtSilo = new JTextField();
        panel.add(txtSilo);

        // Panel para los botones de acción (agregar, modificar, eliminar).
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(new Color(230, 230, 250)); // Define un color de fondo para los botones.

        // Crea los botones de acción.
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        // Añade los botones al panel de acción.
        actionPanel.add(btnAgregar);
        actionPanel.add(btnModificar);
        actionPanel.add(btnEliminar);

        // Añade los paneles al frame: el panel de campos de texto en la parte norte y el panel de botones en la parte sur.
        frame.add(panel, BorderLayout.NORTH);
        frame.add(actionPanel, BorderLayout.SOUTH);

        // Crear la tabla para mostrar los registros existentes.
        String[] columnNames = {"ID", "Fecha", "Apellido", "Nombre", "Patente", "Cereal", "Campo", "Lote", "Kilos", "Humedad", "Silo"};
        tableModel = new DefaultTableModel(columnNames, 0); // Define las columnas de la tabla.
        table = new JTable(tableModel); // Crea la tabla con el modelo definido.
        frame.add(new JScrollPane(table), BorderLayout.CENTER); // Añade la tabla al centro del frame, con barra de desplazamiento.

        // Acción para agregar un nuevo registro.
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Convierte la fecha desde el JTextField a java.sql.Date.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date utilDate = sdf.parse(txtFecha.getText()); // Parsear la fecha desde el campo de texto.
                    Date fecha = new Date(utilDate.getTime()); // Convertir la fecha a tipo SQL.

                    // Obtiene los demás datos de los campos de texto.
                    String apellido = txtApellido.getText();
                    String nombre = txtNombre.getText();
                    String patente = txtPatente.getText();
                    String cereal = txtCereal.getText();
                    String campo = txtCampo.getText();
                    String lote = txtLote.getText();
                    int kilo = Integer.parseInt(txtKilo.getText());
                    int humedad = Integer.parseInt(txtHumedad.getText());
                    int silo = Integer.parseInt(txtSilo.getText());

                    // Crea un objeto Registro con los datos obtenidos y lo pasa al DAO para agregarlo a la base de datos.
                    Registro registro = new Registro(0, fecha, apellido, nombre, patente, cereal, campo, lote, kilo, humedad, silo);
                    dao.agregarRegistro(registro); // Agrega el registro a la base de datos.
                    actualizarTabla(); // Actualiza la tabla con los registros actuales.
                    limpiarCampos(); // Limpia los campos de texto después de agregar el registro.
                    JOptionPane.showMessageDialog(frame, "Registro agregado", "Información", JOptionPane.INFORMATION_MESSAGE); // Muestra un mensaje de éxito.
                } catch (NumberFormatException ex) {
                    // Si hay un error al convertir los números (por ejemplo, si no es un número válido).
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException e1) {
                    // Si el formato de la fecha es incorrecto.
                    JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para modificar un registro seleccionado en la tabla.
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow(); // Obtiene la fila seleccionada en la tabla.
                    if (selectedRow >= 0) { // Si hay una fila seleccionada.
                        int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtiene el ID del registro seleccionado.
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date utilDate = sdf.parse(txtFecha.getText()); // Convierte la fecha desde el JTextField.
                        Date fecha = new Date(utilDate.getTime()); // Convierte a java.sql.Date.

                        // Obtiene los datos de los otros campos de texto.
                        String apellido = txtApellido.getText();
                        String nombre = txtNombre.getText();
                        String patente = txtPatente.getText();
                        String cereal = txtCereal.getText();
                        String campo = txtCampo.getText();
                        String lote = txtLote.getText();
                        int kilo = Integer.parseInt(txtKilo.getText());
                        int humedad = Integer.parseInt(txtHumedad.getText());
                        int silo = Integer.parseInt(txtSilo.getText());

                        // Crea un objeto Registro con los datos obtenidos y lo pasa al DAO para modificar el registro.
                        Registro registro = new Registro(id, fecha, apellido, nombre, patente, cereal, campo, lote, kilo, humedad, silo);
                        dao.modificarRegistro(registro); // Modifica el registro en la base de datos.
                        actualizarTabla(); // Actualiza la tabla con los registros actuales.
                        limpiarCampos(); // Limpia los campos de texto después de modificar.
                        JOptionPane.showMessageDialog(frame, "Registro modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Seleccione un registro para modificar.", "Error", JOptionPane.ERROR_MESSAGE); // Si no se seleccionó un registro.
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para eliminar un registro seleccionado.
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = table.getSelectedRow(); // Obtiene la fila seleccionada en la tabla.
                    if (selectedRow >= 0) { // Si hay una fila seleccionada.
                        int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtiene el ID del registro seleccionado.
                        dao.eliminarRegistro(id); // Elimina el registro de la base de datos.
                        actualizarTabla(); // Actualiza la tabla con los registros actuales.
                        limpiarCampos(); // Limpia los campos de texto después de eliminar.
                        JOptionPane.showMessageDialog(frame, "Registro eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Seleccione un registro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE); // Si no se seleccionó un registro.
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ocurrió un error al eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Maneja el evento de hacer clic en una fila de la tabla.
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); // Obtiene la fila seleccionada.
                if (selectedRow >= 0) {
                    // Obtiene los valores de la fila seleccionada y los asigna a los campos de texto.
                    Date fecha = (Date) tableModel.getValueAt(selectedRow, 1); // Obtiene la fecha.
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaFormateada = sdf.format(fecha); // Formatea la fecha.
                    txtFecha.setText(fechaFormateada); // Asigna la fecha formateada al campo de texto.

                    txtApellido.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtNombre.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtPatente.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    txtCereal.setText(tableModel.getValueAt(selectedRow, 5).toString());
                    txtCampo.setText(tableModel.getValueAt(selectedRow, 6).toString());
                    txtLote.setText(tableModel.getValueAt(selectedRow, 7).toString());
                    txtKilo.setText(tableModel.getValueAt(selectedRow, 8).toString());
                    txtHumedad.setText(tableModel.getValueAt(selectedRow, 9).toString());
                    txtSilo.setText(tableModel.getValueAt(selectedRow, 10).toString());
                }
            }
        });

        // Carga los registros existentes cuando se inicia la aplicación.
        actualizarTabla();

        // Hace visible la ventana.
        frame.setVisible(true);
    }

    // Método para actualizar la tabla con los registros de la base de datos.
    private void actualizarTabla() {
        List<Registro> registros = dao.obtenerRegistros(); // Obtiene los registros de la base de datos.
        tableModel.setRowCount(0); // Limpiar las filas de la tabla.
        for (Registro registro : registros) {
            // Crea una fila para la tabla con los datos del registro.
            Object[] row = {
                registro.getId(),
                registro.getFecha(),
                registro.getApellido(),
                registro.getNombre(),
                registro.getPatente(),
                registro.getCereal(),
                registro.getCampo(),
                registro.getLote(),
                registro.getKilo(),
                registro.getHumedad(),
                registro.getSilo()
            };
            tableModel.addRow(row); // Agrega la fila a la tabla.
        }
    }

    // Método para limpiar los campos de texto.
    private void limpiarCampos() {
        txtFecha.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtPatente.setText("");
        txtCereal.setText("");
        txtCampo.setText("");
        txtLote.setText("");
        txtKilo.setText("");
        txtHumedad.setText("");
        txtSilo.setText("");
    }

    // Método principal que arranca la aplicación.
    public static void main(String[] args) {
        new InterfazGrafica(); // Crea una nueva instancia de la interfaz gráfica y la muestra.
    }
}
