public class DetalleCompra {

    //Atributos
    private Producto producto;
    private int cantidadComprada;
    private double subtotal;

    //Constructor
    public DetalleCompra(Producto producto, int cantidadComprada) {
        this.producto = producto;
        this.cantidadComprada = cantidadComprada;
        this.subtotal = calcularSubtotal();
    }

    //Getter y Setter
    public double calcularSubtotal() {
        return this.producto.getPrecioUnitario() * this.cantidadComprada;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
        this.subtotal = calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleCompra: " + " | " +
                "producto= " + producto.getNombre() + " | " +
                "cantidadComprada= " + cantidadComprada + " | " +
                "subtotal= " + subtotal +
                '}' + " | ";
    }
}
