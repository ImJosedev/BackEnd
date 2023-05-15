/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Service;

import com.portfoliojz.JZ.Entity.Proyecto;
import com.portfoliojz.JZ.Repository.RProyecto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProyectoService {
    @Autowired
    RProyecto rPro;
    
    public List<Proyecto> list(){
        return rPro.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return rPro.findById(id);
    }
    
    public Optional<Proyecto> getByNombre(String nombre){
        return rPro.findByNombre(nombre);
    }
    
    public void save(Proyecto proyecto){
        rPro.save(proyecto);
    }
    
    public void delete(int id){
        rPro.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rPro.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rPro.existsByNombre(nombre);
    }
}
