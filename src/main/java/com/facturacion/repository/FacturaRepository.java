package com.facturacion.repository;

import com.facturacion.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
    Optional<Factura> findByNumeroFactura(String numeroFactura);
    
    List<Factura> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    @Query("SELECT f FROM Factura f ORDER BY f.fecha DESC")
    List<Factura> findAllOrderByFechaDesc();
    
    @Query("SELECT COALESCE(SUM(f.total), 0) FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    Double sumTotalByFechaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                  @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT COUNT(f) FROM Factura f WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin")
    Long countByFechaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, 
                            @Param("fechaFin") LocalDateTime fechaFin);
}
