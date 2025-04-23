import java.sql.Date;  // Importa la clase Date de java.sql para manejar fechas en formato SQL

public class Registro {
    
    // Atributos privados de la clase Registro
    private int id;             
    private Date fecha;         
    private String apellido;    
    private String nombre;      
    private String patente;     
    private String cereal;      
    private String campo;       
    private String lote;       
    private int kilo;           
    private int humedad;        
    private int silo;         

    // Constructor de la clase Registro, inicializa todos los atributos
    public Registro(int id, Date fecha, String apellido, String nombre, String patente, String cereal, String campo,
            String lote, int kilo, int humedad, int silo) {
        
        super();  // Llama al constructor de la clase Object (opcional)
        
        // Asigna los valores pasados por parámetro a los atributos de la clase
        this.id = id;
        this.fecha = fecha;
        this.apellido = apellido;
        this.nombre = nombre;
        this.patente = patente;
        this.cereal = cereal;
        this.campo = campo;
        this.lote = lote;
        this.kilo = kilo;
        this.humedad = humedad;
        this.silo = silo;
    }
    
    // Métodos getter y setter para cada atributo
    
    public int getId() {
        return id;  // Devuelve el valor del atributo id
    }
    
    public void setId(int id) {
        this.id = id;  // Establece el valor del atributo id
    }
    
    public Date getFecha() {
        return fecha;  // Devuelve el valor del atributo fecha
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;  // Establece el valor del atributo fecha
    }
    
    public String getApellido() {
        return apellido;  // Devuelve el valor del atributo apellido
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;  // Establece el valor del atributo apellido
    }
    
    public String getNombre() {
        return nombre;  // Devuelve el valor del atributo nombre
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;  // Establece el valor del atributo nombre
    }
    
    public String getPatente() {
        return patente;  // Devuelve el valor del atributo patente
    }
    
    public void setPatente(String patente) {
        this.patente = patente;  // Establece el valor del atributo patente
    }
    
    public String getCereal() {
        return cereal;  // Devuelve el valor del atributo cereal
    }
    
    public void setCereal(String cereal) {
        this.cereal = cereal;  // Establece el valor del atributo cereal
    }
    
    public String getCampo() {
        return campo;  // Devuelve el valor del atributo campo
    }
    
    public void setCampo(String campo) {
        this.campo = campo;  // Establece el valor del atributo campo
    }
    
    public String getLote() {
        return lote;  // Devuelve el valor del atributo lote
    }
    
    public void setLote(String lote) {
        this.lote = lote;  // Establece el valor del atributo lote
    }
    
    public int getKilo() {
        return kilo;  // Devuelve el valor del atributo kilo
    }
    
    public void setKilo(int kilo) {
        this.kilo = kilo;  // Establece el valor del atributo kilo
    }
    
    public int getHumedad() {
        return humedad;  // Devuelve el valor del atributo humedad
    }
    
    public void setHumedad(int humedad) {
        this.humedad = humedad;  // Establece el valor del atributo humedad
    }
    
    public int getSilo() {
        return silo;  // Devuelve el valor del atributo silo
    }
    
    public void setSilo(int silo) {
        this.silo = silo;  // Establece el valor del atributo silo
    }
    
}  // Fin de la clase Registro
