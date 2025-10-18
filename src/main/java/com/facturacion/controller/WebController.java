package com.facturacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/productos")
    public String productos() {
        return "productos";
    }
    
    @GetMapping("/facturacion")
    public String facturacion() {
        return "facturacion";
    }
    
    @GetMapping("/reportes")
    public String reportes() {
        return "reportes";
    }
}
