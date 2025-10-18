package com.facturacion.config;

import com.facturacion.entity.Producto;
import com.facturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private ProductoService productoService;
    
    @Override
    public void run(String... args) throws Exception {
        if (productoService.findAll().isEmpty()) {
            cargarDatosIniciales();
        }
    }
    
    private void cargarDatosIniciales() {
        // Cervezas
        productoService.save(crearProducto("Corona Extra", "Cerveza", 2500.0, 50, "Cerveza lager mexicana", "Corona", "330ml"));
        productoService.save(crearProducto("Heineken", "Cerveza", 2800.0, 45, "Cerveza lager holandesa", "Heineken", "330ml"));
        productoService.save(crearProducto("Stella Artois", "Cerveza", 3000.0, 40, "Cerveza lager belga", "Stella Artois", "330ml"));
        productoService.save(crearProducto("Budweiser", "Cerveza", 2200.0, 60, "Cerveza lager americana", "Budweiser", "355ml"));
        productoService.save(crearProducto("Quilmes", "Cerveza", 2000.0, 55, "Cerveza lager argentina", "Quilmes", "473ml"));
        
        // Vinos
        productoService.save(crearProducto("Malbec Trapiche", "Vino", 8500.0, 25, "Vino tinto argentino", "Trapiche", "750ml"));
        productoService.save(crearProducto("Cabernet Sauvignon", "Vino", 12000.0, 20, "Vino tinto chileno", "Concha y Toro", "750ml"));
        productoService.save(crearProducto("Chardonnay", "Vino", 9500.0, 18, "Vino blanco", "Santa Rita", "750ml"));
        productoService.save(crearProducto("Merlot", "Vino", 7800.0, 22, "Vino tinto suave", "Casillero del Diablo", "750ml"));
        
        // Whisky
        productoService.save(crearProducto("Johnnie Walker Black", "Whisky", 45000.0, 15, "Whisky escocés", "Johnnie Walker", "750ml"));
        productoService.save(crearProducto("Chivas Regal 12", "Whisky", 38000.0, 12, "Whisky escocés", "Chivas Regal", "750ml"));
        productoService.save(crearProducto("Jack Daniel's", "Whisky", 35000.0, 18, "Whisky americano", "Jack Daniel's", "750ml"));
        productoService.save(crearProducto("Jameson", "Whisky", 32000.0, 20, "Whisky irlandés", "Jameson", "750ml"));
        
        // Vodka
        productoService.save(crearProducto("Absolut Vodka", "Vodka", 25000.0, 25, "Vodka sueco", "Absolut", "750ml"));
        productoService.save(crearProducto("Smirnoff", "Vodka", 18000.0, 30, "Vodka ruso", "Smirnoff", "750ml"));
        productoService.save(crearProducto("Grey Goose", "Vodka", 55000.0, 8, "Vodka premium francés", "Grey Goose", "750ml"));
        
        // Ron
        productoService.save(crearProducto("Bacardí Superior", "Ron", 15000.0, 35, "Ron blanco cubano", "Bacardí", "750ml"));
        productoService.save(crearProducto("Havana Club 7", "Ron", 18000.0, 28, "Ron añejo cubano", "Havana Club", "750ml"));
        productoService.save(crearProducto("Captain Morgan", "Ron", 16000.0, 32, "Ron especiado", "Captain Morgan", "750ml"));
        
        // Tequila
        productoService.save(crearProducto("Jose Cuervo Especial", "Tequila", 22000.0, 20, "Tequila blanco mexicano", "Jose Cuervo", "750ml"));
        productoService.save(crearProducto("Don Julio 70", "Tequila", 45000.0, 12, "Tequila añejo premium", "Don Julio", "750ml"));
        productoService.save(crearProducto("Patrón Silver", "Tequila", 35000.0, 15, "Tequila blanco premium", "Patrón", "750ml"));
        
        // Champagne
        productoService.save(crearProducto("Moët & Chandon", "Champagne", 65000.0, 10, "Champagne francés", "Moët & Chandon", "750ml"));
        productoService.save(crearProducto("Veuve Clicquot", "Champagne", 58000.0, 8, "Champagne francés", "Veuve Clicquot", "750ml"));
        productoService.save(crearProducto("Dom Pérignon", "Champagne", 120000.0, 5, "Champagne premium", "Dom Pérignon", "750ml"));
        
        // Licores
        productoService.save(crearProducto("Baileys", "Licor", 28000.0, 20, "Licor de crema irlandés", "Baileys", "750ml"));
        productoService.save(crearProducto("Kahlúa", "Licor", 22000.0, 25, "Licor de café mexicano", "Kahlúa", "750ml"));
        productoService.save(crearProducto("Cointreau", "Licor", 30000.0, 18, "Licor de naranja francés", "Cointreau", "750ml"));
        
        // Bebidas sin alcohol
        productoService.save(crearProducto("Coca Cola", "Otros", 2000.0, 100, "Refresco de cola", "Coca Cola", "500ml"));
        productoService.save(crearProducto("Agua Mineral", "Otros", 1500.0, 80, "Agua mineral natural", "Villavicencio", "500ml"));
        productoService.save(crearProducto("Jugo de Naranja", "Otros", 2500.0, 60, "Jugo natural de naranja", "Del Valle", "1L"));
        
        System.out.println("Datos de prueba cargados exitosamente!");
    }
    
    private Producto crearProducto(String nombre, String categoria, Double precio, Integer stock, 
                                  String descripcion, String marca, String tamaño) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setDescripcion(descripcion);
        producto.setMarca(marca);
        producto.setTamaño(tamaño);
        return producto;
    }
}
