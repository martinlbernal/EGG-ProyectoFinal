package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Vehiculo;
import com.example.demo.entidades.enumeraciones.Tipo;
import com.example.demo.excepciones.ErrorServicio;
import com.example.demo.repositorios.ClienteRepositorio;
import com.example.demo.repositorios.VehiculoRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VehiculoServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;
    
    @Transactional
    public void agregarVehiculo(String idCliente, String identificador, Tipo tipo) throws ErrorServicio {
        
        Cliente cliente = clienteRepositorio.findById(idCliente).get();
        
        validarTipo(tipo);
        
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCliente(cliente);
        vehiculo.setTipo(tipo);
        vehiculo.setAlta(true);
        
        if (!tipo.toString().equals(tipo.BICICLETA)) {
            validarIdentificador(identificador);
            vehiculo.setIdentificador(identificador);
        }
        
        vehiculoRepositorio.save(vehiculo);
    }
    
    @Transactional
    public void modificar(String idCliente, String idVehiculo, String identificador, Tipo tipo) throws  ErrorServicio {
        
        validarTipo(tipo);
        
        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(idVehiculo);
        if (respuesta.isPresent()) {
            Vehiculo vehiculo = respuesta.get();
            if (vehiculo.getCliente().getId().equals(idCliente)) { 
                vehiculo.setTipo(tipo);
                if (!tipo.toString().equals(tipo.BICICLETA)) {
                    validarIdentificador(identificador);
                    
                    vehiculo.setIdentificador(identificador);
                }
                vehiculoRepositorio.save(vehiculo);
            } else {
                throw new ErrorServicio("No tiene permisos suficientes para realizar la operación");
            }
        } else {
            throw new ErrorServicio("No existe vehículo con el identificador solicitado");
        }
    }
    
    @Transactional
    public void eliminar(String idCliente, String idVehiculo) throws  ErrorServicio {
        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(idVehiculo);
        if (respuesta.isPresent()) {
            Vehiculo vehiculo = respuesta.get();
            if (vehiculo.getCliente().getId().equals(idCliente)) {
                vehiculo.setAlta(false);
                vehiculoRepositorio.save(vehiculo);
            } 
        } else {
            throw new ErrorServicio("No existe un vehículo con el identificador solicitado");
        }
    }
    
    private void validarTipo(Tipo tipo) throws ErrorServicio {
        if (tipo==null) {
            throw new ErrorServicio("El tipo de vehículo no debe ser nulo");
        }
    }
    
    private void validarIdentificador(String identificador) throws ErrorServicio {
        if (identificador==null || identificador.isEmpty()) {
            throw new ErrorServicio("El identificador del vehículo no debe ser nula");
        }
    }
    
    public Vehiculo buscarPorId(String id) throws ErrorServicio{
        Optional<Vehiculo> respuesta = vehiculoRepositorio.findById(id);
        if(respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ErrorServicio("El Vehículo no existe.");
        }
    }
    
    public List<Vehiculo> buscarVehiculosPorCliente(String id) {
        return vehiculoRepositorio.buscarVehiculosPorCliente(id);
    }
    
}
