package com.facturacion.repository;

import com.facturacion.entity.ItemFactura;
import com.facturacion.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemFacturaRepository extends JpaRepository<ItemFactura, Long> {
    
    List<ItemFactura> findByProducto(Producto producto);
    
    @Query("SELECT i FROM ItemFactura i WHERE i.factura.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<ItemFactura> findByFacturaFechaBetween(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                               @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT i.producto, SUM(i.cantidad) as totalVendido " +
           "FROM ItemFactura i " +
           "WHERE i.factura.fecha BETWEEN :fechaInicio AND :fechaFin " +
           "GROUP BY i.producto " +
           "ORDER BY totalVendido DESC")
    List<Object[]> findProductosMasVendidos(@Param("fechaInicio") LocalDateTime fechaInicio, 
                                           @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT SUM(i.cantidad) FROM ItemFactura i WHERE i.producto.id = :productoId")
    Integer sumCantidadByProductoId(@Param("productoId") Long productoId);
}
