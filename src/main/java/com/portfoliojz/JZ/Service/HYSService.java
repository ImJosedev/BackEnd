/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Service;

import com.portfoliojz.JZ.Entity.HYS;
import com.portfoliojz.JZ.Repository.RHYS;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class HYSService {
    @Autowired
    RHYS rhys;
    
    public List<HYS> list(){
        return rhys.findAll();
    }
    
    public Optional<HYS> getOne(int id){
        return rhys.findById(id);
    }
    
    public Optional<HYS> getByNombre(String nombre){
        return rhys.findByNombre(nombre);
    }
    
    public void save(HYS hskill){
        rhys.save(hskill);
    }
    
    public void delete(int id){
        rhys.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rhys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhys.existsByNombre(nombre);
    }
}
