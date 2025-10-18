package com.facturacion.service;

import com.facturacion.entity.Producto;
import com.facturacion.repository.ItemFacturaRepository;
import com.facturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {
    
    @Autowired
    private ItemFacturaRepository itemFacturaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public Map<String, Object> getReporteVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Map<String, Object> reporte = new HashMap<>();
        
        // Productos más vendidos
        List<Object[]> productosMasVendidos = itemFacturaRepository.findProductosMasVendidos(fechaInicio, fechaFin);
        reporte.put("productosMasVendidos", productosMasVendidos);
        
        // Total de ventas por categoría
        Map<String, Double> ventasPorCategoria = new HashMap<>();
        List<String> categorias = productoRepository.findDistinctCategorias();
        
        for (String categoria : categorias) {
            List<Producto> productos = productoRepository.findByCategoria(categoria);
            double totalCategoria = 0.0;
            
            for (Producto producto : productos) {
                Integer cantidadVendida = itemFacturaRepository.sumCantidadByProductoId(producto.getId());
                if (cantidadVendida != null) {
                    totalCategoria += cantidadVendida * producto.getPrecio();
                }
            }
            
            ventasPorCategoria.put(categoria, totalCategoria);
        }
        
        reporte.put("ventasPorCategoria", ventasPorCategoria);
        
        return reporte;
    }
    
    public Map<String, Object> getReporteStock() {
        Map<String, Object> reporte = new HashMap<>();
        
        List<Producto> productosStockBajo = productoRepository.findByStockLessThan(10);
        reporte.put("productosStockBajo", productosStockBajo);
        
        List<Producto> todosProductos = productoRepository.findAll();
        reporte.put("todosProductos", todosProductos);
        
        return reporte;
    }
}
