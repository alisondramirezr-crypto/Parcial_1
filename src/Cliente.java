import java.util.ArrayList;
import java.util.List;

public class Cliente {

    //Atributos
    private String nombreCompleto;
    private int documentoIdentidad;
    private int telefono;
    private String correoElectronico;
    private List<Compra> listaCompras;

    //Constructor
    public Cliente(String nombreCompleto, int documentoIdentidad, int telefono, String correoElectronico) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.listaCompras = new ArrayList<>();
    }

    //Getter y setters
    public void agregarCompra(Compra compra) {
        this.listaCompras.add(compra);
    }

    public List<Compra> consultarHistorialCompras() {
        return this.listaCompras;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(int documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    @Override
    public String toString() {
        return "Cliente: " + " | " +
                "nombreCompleto= " + nombreCompleto + " | " +
                "documentoIdentidad= " + documentoIdentidad + " | " +
                "telefono= " + telefono + " | " +
                "correoElectronico= " + correoElectronico +
                '}' + " | ";
    }
}

