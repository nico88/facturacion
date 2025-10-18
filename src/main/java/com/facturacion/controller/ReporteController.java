package com.facturacion.controller;

import com.facturacion.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {
    
    @Autowired
    private ReporteService reporteService;
    
    @GetMapping("/ventas")
    public ResponseEntity<Map<String, Object>> getReporteVentas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        
        Map<String, Object> reporte = reporteService.getReporteVentas(fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
    
    @GetMapping("/stock")
    public ResponseEntity<Map<String, Object>> getReporteStock() {
        Map<String, Object> reporte = reporteService.getReporteStock();
        return ResponseEntity.ok(reporte);
    }
}
