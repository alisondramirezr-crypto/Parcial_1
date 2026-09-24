import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Supermercado {

    // Atributos
    private String nombreComercial;
    private String direccion;
    private int telefono;

    // Listas
    private List<Cliente> listaClientes;
    private List<Producto> listaProductos;
    private List<Compra> listaCompras;

    public Supermercado(String nombreComercial, String direccion, int telefono) {
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.telefono = telefono;

        this.listaClientes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaCompras = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public List<Cliente> getListaClientes() { return listaClientes; }
    public void setListaClientes(List<Cliente> listaClientes) { this.listaClientes = listaClientes; }

    public List<Producto> getListaProductos() { return listaProductos; }
    public void setListaProductos(List<Producto> listaProductos) { this.listaProductos = listaProductos; }

    public List<Compra> getListaCompras() { return listaCompras; }
    public void setListaCompras(List<Compra> listaCompras) { this.listaCompras = listaCompras; }

    @Override
    public String toString() {
        return nombreComercial + '\'' +
                ", direccion: " + direccion +
                ", telefono: " + telefono +
                ", lista de clientes: " + listaClientes +
                ", lista de productos: " + listaProductos +
                ", lista de compras: " + listaCompras;
    }

    // =======================================================
    //             MÉTODOS DECLARADOS EN EL DIAGRAMA UML
    // =======================================================

    //Metodo Verifircar cliente
    public boolean verificarCliente (int documentoIdentidad){
        boolean existe = false;
        for (Cliente cliente: listaClientes){
            if (cliente.getDocumentoIdentidad()==documentoIdentidad){
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Metodo registrar clientes
    public boolean registrarCliente(Cliente cliente) {
        boolean registrado = false;
        boolean existe = verificarCliente(cliente.getDocumentoIdentidad());
        if (existe == false) {
            listaClientes.add(cliente);
            registrado = true;
        }
        return registrado;
    }

    //MetodoVerificarProducto
    public boolean verificarProducto (int codigo){
        boolean existe = false;
        for (Producto producto: listaProductos){
            if (producto.getCodigo() == codigo){
                existe = true;
                break;
            }
        }
        return existe;
    }

    //Metodo registrar producto
    public boolean registrarProducto(Producto producto) {
        boolean registrado = false;
        boolean existe = verificarProducto(producto.getCodigo());
        if (existe = false){
            listaProductos.add(producto);
            registrado = true;
        }
        return registrado;
    }

    public void registrarCompra(Compra compra) {
        this.listaCompras.add(compra);
    }

    public Cliente buscarCliente(int documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumentoIdentidad() == documento) {
                return cliente;
            }
        }
        return null;
    }

    public Producto buscarProducto(int codigo) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public Compra buscarCompra(int codigoCompra) {
        for (Compra compra : listaCompras) {
            if (compra.getCodigoCompra() == codigoCompra) {
                return compra;
            }
        }
        return null;
    }

    public List<Compra> consultarHistorialComprasCliente(int documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente != null) {
            return cliente.consultarHistorialCompras();
        }
        return new ArrayList<>();
    }

    public double generarReporteVentasPorFecha(LocalDate fecha) {
        double totalAcumulado = 0.0;
        for (Compra compra : listaCompras) {
            if (compra.getFecha().equals(fecha)) {
                totalAcumulado += compra.getValorTotal();
            }
        }
        return totalAcumulado;
    }
}
