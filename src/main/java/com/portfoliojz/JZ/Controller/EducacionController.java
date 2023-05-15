/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Controller;

import com.portfoliojz.JZ.Dto.dtoEducacion;
import com.portfoliojz.JZ.Entity.Educacion;
import com.portfoliojz.JZ.Security.Controller.Mensaje;
import com.portfoliojz.JZ.Service.EducacionService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    @Autowired
    EducacionService educacionS;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = educacionS.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEdu) {
        if (StringUtils.isBlank(dtoEdu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educacionS.existsByNombreEd(dtoEdu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre que intenta ingresar ya existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(dtoEdu.getNombreEd(), dtoEdu.getDescripcionEd());
        educacionS.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion Creada Correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoEdu) {
        if (!educacionS.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        if (educacionS.existsByNombreEd(dtoEdu.getNombreEd()) && educacionS.getByNombreEd(dtoEdu.getNombreEd()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Es nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEdu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionS.getOne(id).get();
        educacion.setNombreEd(dtoEdu.getNombreEd());
        educacion.setDescripcionEd(dtoEdu.getDescripcionEd());

        educacionS.save(educacion);

        return new ResponseEntity(new Mensaje("Educación Actualizada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID que desea eliminar"), HttpStatus.NOT_FOUND);
        }
        educacionS.delete(id);
        return new ResponseEntity(new Mensaje("Educacion Eliminada"), HttpStatus.OK);
    }
}
