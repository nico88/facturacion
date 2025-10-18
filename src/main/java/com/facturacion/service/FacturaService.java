package com.facturacion.service;

import com.facturacion.entity.Factura;
import com.facturacion.entity.ItemFactura;
import com.facturacion.entity.Producto;
import com.facturacion.repository.FacturaRepository;
import com.facturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    private static final double IMPUESTO_PORCENTAJE = 0.19; // 19% IVA
    
    public List<Factura> findAll() {
        return facturaRepository.findAllOrderByFechaDesc();
    }
    
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }
    
    public Factura crearFactura(List<ItemFactura> items) {
        Factura factura = new Factura();
        factura.setNumeroFactura(generarNumeroFactura());
        factura.setFecha(LocalDateTime.now());
        
        // Asociar items a la factura
        for (ItemFactura item : items) {
            item.setFactura(factura);
            item.setPrecioUnitario(item.getProducto().getPrecio());
            item.calcularSubtotal();
            
            // Actualizar stock del producto
            actualizarStockProducto(item.getProducto().getId(), -item.getCantidad());
        }
        
        factura.setItems(items);
        
        // Calcular totales
        calcularTotales(factura);
        
        return facturaRepository.save(factura);
    }
    
    private void actualizarStockProducto(Long productoId, Integer cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(productoId);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() + cantidad < 0) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() + cantidad);
            productoRepository.save(producto);
        }
    }
    
    private void calcularTotales(Factura factura) {
        double subtotal = factura.getItems().stream()
                .mapToDouble(ItemFactura::getSubtotal)
                .sum();
        
        double impuestos = subtotal * IMPUESTO_PORCENTAJE;
        double total = subtotal + impuestos;
        
        factura.setSubtotal(subtotal);
        factura.setImpuestos(impuestos);
        factura.setTotal(total);
    }
    
    private String generarNumeroFactura() {
        return "FAC-" + System.currentTimeMillis();
    }
    
    public List<Factura> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return facturaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    public Double getTotalVentas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return facturaRepository.sumTotalByFechaBetween(fechaInicio, fechaFin);
    }
    
    public Long getCantidadFacturas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return facturaRepository.countByFechaBetween(fechaInicio, fechaFin);
    }
}
