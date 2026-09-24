public class Producto {


    //Atributicos
    private int codigo;
    private String nombre;
    private double precioUnitario;
    private int cantidadDisponible;
    private CategoriaProducto categoria;
    //Metodos
    public Producto(int codigo, String nombre, double precioUnitario, int cantidadDisponible, CategoriaProducto categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidadDisponible = cantidadDisponible;
        this.categoria = categoria;
    }

    public boolean validarStockDisponible(int cantidad) {
        return this.cantidadDisponible >= cantidad;
    }

    public void descontarStock(int cantidad) {
        if (validarStockDisponible(cantidad)) {
            this.cantidadDisponible -= cantidad;
        }
    }
    //Getter y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
    //:D
    @Override
    public String toString() {
        return "Producto:  " + " | " +
                "codigo= " + codigo + " | " +
                "nombre= " + nombre + " | " +
                "precioUnitario= " + precioUnitario + " | " +
                "cantidadDisponible= " + cantidadDisponible + " | " +
                "categoria= " + categoria +
                '}' + " | ";
    }
}