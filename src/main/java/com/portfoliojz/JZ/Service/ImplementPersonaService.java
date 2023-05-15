package com.portfoliojz.JZ.Service;


import com.portfoliojz.JZ.Entity.Persona;
import org.springframework.stereotype.Service;
import com.portfoliojz.JZ.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class ImplementPersonaService{
    @Autowired
    IPersonaRepository ipersonaRepository;
    
    public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return ipersonaRepository.findById(id); 
    }

    public Optional<Persona> getByNombre(String nombre){
        return ipersonaRepository.findByNombre(nombre);
    }
    
    public void save(Persona persona){
       ipersonaRepository.save(persona); 
    }
    public void delete(int id){
        ipersonaRepository.deleteById(id);
    }
    
    public boolean  existsById(int id){
        return ipersonaRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return ipersonaRepository.existsByNombre(nombre);
    }
}
