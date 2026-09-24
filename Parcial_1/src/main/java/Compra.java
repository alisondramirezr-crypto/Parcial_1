import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {

    //Atributos
    private int codigoCompra;
    private LocalDate fecha;
    private MetodoPago metodoPago;
    private double valorTotal;
    private Cliente cliente;
    private List<DetalleCompra> listaDetalles;

    //Constructor
    public Compra(int codigoCompra, LocalDate fecha, MetodoPago metodoPago, Cliente cliente) {
        this.codigoCompra = codigoCompra;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.valorTotal = 0.0;
        this.listaDetalles = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto.validarStockDisponible(cantidad)) {
            DetalleCompra detalle = new DetalleCompra(producto, cantidad);
            this.listaDetalles.add(detalle);
            return true;
        }
        return false;
    }

    public double calcularValorTotal() {
        this.valorTotal = 0.0;
        for (DetalleCompra detalle : listaDetalles) {
            this.valorTotal += detalle.getSubtotal();
        }
        return this.valorTotal;
    }

    public void confirmarCompra() {
        for (DetalleCompra detalle : listaDetalles) {
            detalle.getProducto().descontarStock(detalle.getCantidadComprada());
        }
        calcularValorTotal();
        cliente.agregarCompra(this);
    }

    //Getters y setters
    public int getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleCompra> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleCompra> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    //toString ()
    @Override
    public String toString() {
        return "Compra: " +
                "\nCodigo de compra: " + codigoCompra +
                "\nFecha: " + fecha +
                "\nMetodo de pago: " + metodoPago +
                "\nValor de total: " + valorTotal +
                "\nCliente: " + cliente.getNombreCompleto() +
                "\nDetalles: " + listaDetalles;
    }
}