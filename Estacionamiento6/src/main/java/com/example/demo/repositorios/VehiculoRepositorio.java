package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String>{
    
    @Query("SELECT c FROM Vehiculo c WHERE c.cliente.id = :id") // Es la query o consulta en la dase de datos
    public List<Vehiculo> buscarVehiculosPorCliente(@Param("id") String id);
    
}
