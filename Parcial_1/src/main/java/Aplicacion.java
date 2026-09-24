import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
    static void main() {

        //Menú interactivo

        Scanner sc = new Scanner(System.in);
        Supermercado supermercado = new Supermercado("MarketPlus", "Carrera 15 #12-34", 7359300);

        int opcion;
        do {
            System.out.println("\n====== Menú Supermercado MarketPlus =======");
            System.out.println("1. Registrar cliente.");
            System.out.println("2. Registrar producto.");
            System.out.println("3. Mostrar clientes.");
            System.out.println("4. Mostrar productos.");
            System.out.println("5. Realizar y registrar compra.");
            System.out.println("6. Buscar cliente.");
            System.out.println("7. Buscar producto.");
            System.out.println("8. Buscar compra.");
            System.out.println("9. Consultar historial de compras de cliente.");
            System.out.println("10. Generar reporte de ventas por fecha.");
            System.out.println("0. Salir.");

            System.out.print("Seleccione la opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                //Casos del menú
                case 1:
                    System.out.println("------ Registrar cliente ----");
                    System.out.print("Nombre Completo: ");
                    String nombreCli = sc.nextLine();

                    System.out.print("Documento de Identidad: ");
                    int docCli = sc.nextInt();

                    System.out.print("Teléfono: ");
                    int telCli = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Correo Electrónico: ");
                    String correoCli = sc.nextLine();


                    Cliente cliente = new Cliente(nombreCli, docCli, telCli, correoCli);
                    if (supermercado.registrarCliente(cliente)){
                        System.out.println("Cliente agregado correctamente");
                    } else {
                        System.out.println("El cliente ya existe.");
                    }
                    break;

                case 2:
                    System.out.println("------ Registrar producto ----");
                    System.out.print("Código: ");
                    int codProd = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre del producto: ");
                    String nombreProd = sc.nextLine();

                    System.out.print("Precio Unitario: ");
                    double precioProd = sc.nextDouble();

                    System.out.print("Cantidad Disponible (Stock): ");
                    int stockProd = sc.nextInt();

                    System.out.println("Categoría (1. ALIMENTOS, 2. BEBIDAS, 3. PRODUCTOS_ASEO, 4. CUIDADO_PERSONAL): ");
                    int catOp = sc.nextInt();
                    CategoriaProducto categoria = CategoriaProducto.ALIMENTOS;
                    if (catOp == 2) categoria = CategoriaProducto.BEBIDAS;
                    else if (catOp == 3) categoria = CategoriaProducto.PRODUCTOS_ASEO;
                    else if (catOp == 4) categoria = CategoriaProducto.CUIDADO_PERSONAL;


                    Producto producto = new Producto(codProd, nombreProd, precioProd, stockProd, categoria);
                    if (supermercado.registrarProducto(producto)){
                        System.out.println("Producto registrado correctamente.");
                    } else {
                        System.out.println("El producto ya existe");
                    }
                    break;

                case 3:
                    System.out.println("------ Lista de Clientes ----");
                    if (supermercado.getListaClientes().isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        for (Cliente c : supermercado.getListaClientes()) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 4:
                    System.out.println("------ Lista de Productos ----");
                    if (supermercado.getListaProductos().isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        for (Producto p : supermercado.getListaProductos()) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 5:
                    System.out.println("------ Realizar Compra ----");
                    System.out.print("Ingrese documento del cliente: ");
                    int docComprador = sc.nextInt();

                    Cliente clienteComprador = supermercado.buscarCliente(docComprador);
                    if (clienteComprador == null) {
                        System.out.println("El cliente no se encuentra registrado.");
                        break;
                    }

                    System.out.print("Código de Compra: ");
                    int codCompra = sc.nextInt();

                    System.out.print("Año de realización (YYYY): ");
                    int anioComp = sc.nextInt();
                    System.out.print("Mes de realización (1-12): ");
                    int mesComp = sc.nextInt();
                    System.out.print("Día de realización (1-31): ");
                    int diaComp = sc.nextInt();
                    LocalDate fechaCompra = LocalDate.of(anioComp, mesComp, diaComp);

                    System.out.println("Método de Pago (1. TARJETA, 2. TRANSFERENCIA_BANCARIA, 3. EFECTIVO): ");
                    int pagoOp = sc.nextInt();
                    MetodoPago metodoPago = MetodoPago.TARJETA;
                    if (pagoOp == 2) metodoPago = MetodoPago.TRANSFERENCIA_BANCARIA;
                    else if (pagoOp == 3) metodoPago = MetodoPago.EFECTIVO;

                    Compra compra = new Compra(codCompra, fechaCompra, metodoPago, clienteComprador);

                    int opcionProd;
                    do {
                        System.out.print("Ingrese código del producto a comprar: ");
                        int codProdComprar = sc.nextInt();

                        Producto prodSel = supermercado.buscarProducto(codProdComprar);
                        if (prodSel != null) {
                            System.out.print("Cantidad deseada: ");
                            int cantDeseada = sc.nextInt();

                            if (compra.agregarProducto(prodSel, cantDeseada)) {
                                System.out.println("Producto agregado a la compra.");
                            } else {
                                System.out.println("Stock insuficiente para este producto.");
                            }
                        } else {
                            System.out.println("Producto no encontrado.");
                        }

                        System.out.print("¿Desea agregar otro producto? (1. Sí / 0. No): ");
                        opcionProd = sc.nextInt();
                    } while (opcionProd != 0);

                    if (!compra.getListaDetalles().isEmpty()) {
                        compra.confirmarCompra();
                        supermercado.registrarCompra(compra);
                        System.out.println("Compra realizada y registrada correctamente.");
                        // MODIFICACIÓN: Muestra el valor total de la compra al momento de realizarla
                        System.out.println("Valor total de la compra: $" + compra.getValorTotal());
                    } else {
                        System.out.println("No se seleccionaron productos. Compra no realizada.");
                    }
                    break;

                case 6:
                    System.out.println("------ Buscar Cliente ----");
                    System.out.print("Ingrese documento del cliente: ");
                    int docBuscado = sc.nextInt();
                    Cliente cliHallado = supermercado.buscarCliente(docBuscado);
                    if (cliHallado != null) {
                        System.out.println(cliHallado);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 7:
                    System.out.println("------ Buscar Producto ----");
                    System.out.print("Ingrese código del producto: ");
                    int codBuscado = sc.nextInt();
                    Producto prodHallado = supermercado.buscarProducto(codBuscado);
                    if (prodHallado != null) {
                        System.out.println(prodHallado);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 8:
                    System.out.println("------ Buscar Compra ----");
                    System.out.print("Ingrese código de la compra: ");
                    int codCompBuscada = sc.nextInt();
                    Compra compHallada = supermercado.buscarCompra(codCompBuscada);
                    if (compHallada != null) {
                        System.out.println(compHallada);
                    } else {
                        System.out.println("Compra no encontrada.");
                    }
                    break;

                case 9:
                    System.out.println("------ Consultar Historial de Compras ----");
                    System.out.print("Ingrese documento del cliente: ");
                    int docHistorial = sc.nextInt();

                    List<Compra> historial = supermercado.consultarHistorialComprasCliente(docHistorial);
                    if (historial.isEmpty()) {
                        System.out.println("No se encontraron compras para este cliente.");
                    } else {
                        for (Compra c : historial) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 10:
                    System.out.println("------ Reporte de Ventas por Fecha ----");
                    System.out.print("Año a consultar (YYYY): ");
                    int anioRep = sc.nextInt();
                    System.out.print("Mes a consultar (1-12): ");
                    int mesRep = sc.nextInt();
                    System.out.print("Día a consultar (1-31): ");
                    int diaRep = sc.nextInt();

                    LocalDate fechaReporte = LocalDate.of(anioRep, mesRep, diaRep);
                    double totalVendido = supermercado.generarReporteVentasPorFecha(fechaReporte);
                    System.out.println("El valor total acumulado para la fecha " + fechaReporte + " es: $" + totalVendido);
                    break;

                case 0:
                    System.out.println("Programa finalizado.....");
                    break;

                default:
                    System.out.println("Opción no válida....");
            }

        } while (opcion != 0);

        sc.close();
    }
}