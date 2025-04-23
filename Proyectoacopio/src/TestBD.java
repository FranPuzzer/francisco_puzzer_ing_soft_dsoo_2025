import java.sql.Date;              // Importa la clase Date para trabajar con fechas en formato SQL
import java.util.List;             // Importa la clase List para manejar listas de objetos
import java.util.Scanner;          // Importa la clase Scanner para leer entradas desde la consola

public class TestBD {              // Define la clase TestBD, que contiene el método main
    public static void main(String[] args) {   // Método principal que se ejecuta al iniciar el programa

        // Instancia de DAO, que es una clase responsable de manejar las operaciones en la base de datos
        DAO dao = new DAO();              

        // Instancia de Scanner para leer las entradas del usuario desde la consola
        Scanner scanner = new Scanner(System.in);
        int opcion;                         // Variable para almacenar la opción seleccionada por el usuario

        do {  // Inicia un bucle que se repetirá hasta que el usuario elija salir (opción 5)
            // Muestra el menú de opciones en la consola
            System.out.println("\n--REGISTRO DE ACOPIO AGRÍCOLA--");
            System.out.println("-> 1. Agregar registro");
            System.out.println("-> 2. Listar registros");
            System.out.println("-> 3. Modificar registro");
            System.out.println("-> 4. Eliminar registro");
            System.out.println("-> 5. Salir");

            // Solicita al usuario que seleccione una opción
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();    // Lee la opción seleccionada
            scanner.nextLine();            // Consume el salto de línea dejado por nextInt()

            switch (opcion) {              // Empieza un bloque de selección de opciones basado en la opción del usuario
                case 1:                    // Si el usuario selecciona "1", va a agregar un nuevo registro
                    System.out.println("\n--SECCIÓN DE AGREGAR REGISTRO--");
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    Date fecha = Date.valueOf(scanner.nextLine());   // Lee y convierte la fecha ingresada por el usuario
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();             // Lee el apellido
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();               // Lee el nombre
                    System.out.print("Patente: ");
                    String patente = scanner.nextLine();              // Lee la patente
                    System.out.print("Cereal: ");
                    String cereal = scanner.nextLine();               // Lee el tipo de cereal
                    System.out.print("Campo: ");
                    String campo = scanner.nextLine();                // Lee el nombre del campo
                    System.out.print("Lote: ");
                    String lote = scanner.nextLine();                 // Lee el número de lote
                    System.out.print("Kilos: ");
                    int kilo = scanner.nextInt();                     // Lee la cantidad de kilos
                    System.out.print("Humedad: ");
                    int humedad = scanner.nextInt();                  // Lee el porcentaje de humedad
                    System.out.print("Silo: ");
                    int silo = scanner.nextInt();                     // Lee el número de silo
                    scanner.nextLine();                                // Consume el salto de línea restante

                    // Crea un nuevo objeto Registro con los datos ingresados
                    Registro nuevoRegistro = new Registro(0, fecha, apellido, nombre, patente, cereal, campo, lote, kilo, humedad, silo);
                    // Llama al método agregarRegistro de DAO para guardar el nuevo registro en la base de datos
                    dao.agregarRegistro(nuevoRegistro);

                    System.out.println("¡REGISTRO GUARDADO!\n");  // Muestra un mensaje de confirmación
                    break;  // Sale del bloque de opciones

                case 2:  // Si el usuario selecciona "2", va a listar todos los registros
                    System.out.println("\n--SECCIÓN DE LISTAR REGISTROS--");
                    List<Registro> registros = dao.obtenerRegistros();  // Llama al método obtenerRegistros para obtener la lista de registros
                    for (Registro r : registros) {                        // Recorre cada registro en la lista
                        // Muestra la información de cada registro en la consola
                        System.out.println("ID: " + r.getId() + ", Fecha: " + r.getFecha() + ", Apellido: " + r.getApellido() +
                                ", Nombre: " + r.getNombre() + ", Patente: " + r.getPatente() + ", Cereal: " + r.getCereal() +
                                ", Campo: " + r.getCampo() + ", Lote: " + r.getLote() + ", Kilos: " + r.getKilo() +
                                ", Humedad: " + r.getHumedad() + ", Silo: " + r.getSilo());
                    }
                    break;  // Sale del bloque de opciones

                case 3:  // Si el usuario selecciona "3", va a modificar un registro existente
                    System.out.println("\n--SECCIÓN DE MODIFICAR REGISTRO--");
                    System.out.print("ID del registro a modificar: ");
                    int idMod = scanner.nextInt();   // Lee el ID del registro a modificar
                    scanner.nextLine();               // Consume el salto de línea restante

                    // Solicita al usuario los nuevos datos para el registro
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    Date nuevaFecha = Date.valueOf(scanner.nextLine());  // Lee la nueva fecha
                    System.out.print("Apellido: ");
                    String nuevoApellido = scanner.nextLine();            // Lee el nuevo apellido
                    System.out.print("Nombre: ");
                    String nuevoNombre = scanner.nextLine();              // Lee el nuevo nombre
                    System.out.print("Patente: ");
                    String nuevaPatente = scanner.nextLine();             // Lee la nueva patente
                    System.out.print("Cereal: ");
                    String nuevoCereal = scanner.nextLine();              // Lee el nuevo cereal
                    System.out.print("Campo: ");
                    String nuevoCampo = scanner.nextLine();               // Lee el nuevo campo
                    System.out.print("Lote: ");
                    String nuevoLote = scanner.nextLine();                // Lee el nuevo lote
                    System.out.print("Kilos: ");
                    int nuevoKilo = scanner.nextInt();                    // Lee la nueva cantidad de kilos
                    System.out.print("Humedad: ");
                    int nuevaHumedad = scanner.nextInt();                 // Lee el nuevo porcentaje de humedad
                    System.out.print("Silo: ");
                    int nuevoSilo = scanner.nextInt();                    // Lee el nuevo número de silo
                    scanner.nextLine();                                    // Consume el salto de línea restante

                    // Crea un nuevo objeto Registro con los nuevos datos
                    Registro registroMod = new Registro(idMod, nuevaFecha, nuevoApellido, nuevoNombre, nuevaPatente, nuevoCereal, nuevoCampo, nuevoLote, nuevoKilo, nuevaHumedad, nuevoSilo);
                    // Llama al método modificarRegistro de DAO para actualizar el registro en la base de datos
                    dao.modificarRegistro(registroMod);

                    System.out.println("¡REGISTRO MODIFICADO!\n");  // Muestra un mensaje de confirmación
                    break;  // Sale del bloque de opciones

                case 4:  // Si el usuario selecciona "4", va a eliminar un registro
                    System.out.println("\n--SECCIÓN DE ELIMINAR REGISTRO--");
                    System.out.print("ID del registro a eliminar: ");
                    int idElim = scanner.nextInt();  // Lee el ID del registro a eliminar
                    scanner.nextLine();               // Consume el salto de línea restante

                    // Llama al método eliminarRegistro de DAO para eliminar el registro en la base de datos
                    dao.eliminarRegistro(idElim);
                    System.out.println("¡REGISTRO ELIMINADO!\n");  // Muestra un mensaje de confirmación
                    break;  // Sale del bloque de opciones

                case 5:  // Si el usuario selecciona "5", finaliza el programa
                    System.out.println("Finalizando...");  // Muestra un mensaje de despedida
                    break;  // Sale del bloque de opciones

                default:  // Si el usuario selecciona una opción no válida, muestra un mensaje de error
                    System.out.println("¡Opción inválida! Intente nuevamente.");
            }
        } while (opcion != 5);  // El bucle sigue repitiéndose hasta que el usuario seleccione la opción 5 (Salir)

        scanner.close();  // Cierra el objeto Scanner al final del programa
    }
}  // Fin de la clase TestBD
