package com.facturacion.controller;

import com.facturacion.entity.Factura;
import com.facturacion.entity.ItemFactura;
import com.facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable Long id) {
        Optional<Factura> factura = facturaService.findById(id);
        return factura.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody List<ItemFactura> items) {
        try {
            Factura factura = facturaService.crearFactura(items);
            return ResponseEntity.ok(factura);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/fecha")
    public List<Factura> getFacturasByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        return facturaService.findByFechaBetween(fechaInicio, fechaFin);
    }
    
    @GetMapping("/reporte/ventas")
    public ResponseEntity<Object> getReporteVentas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        
        Double totalVentas = facturaService.getTotalVentas(fechaInicio, fechaFin);
        Long cantidadFacturas = facturaService.getCantidadFacturas(fechaInicio, fechaFin);
        
        return ResponseEntity.ok(Map.of(
            "totalVentas", totalVentas != null ? totalVentas : 0.0,
            "cantidadFacturas", cantidadFacturas != null ? cantidadFacturas : 0L,
            "fechaInicio", fechaInicio,
            "fechaFin", fechaFin
        ));
    }
}
