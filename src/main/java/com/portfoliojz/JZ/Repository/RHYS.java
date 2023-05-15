/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfoliojz.JZ.Repository;

import com.portfoliojz.JZ.Entity.HYS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHYS extends JpaRepository<HYS, Integer>{
    Optional<HYS> findByNombre(String Nombre);
    public boolean existsByNombre(String Nombre);
    
}
