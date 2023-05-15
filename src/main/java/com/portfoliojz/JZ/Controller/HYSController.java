/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Controller;

import com.portfoliojz.JZ.Dto.dtoHYS;
import com.portfoliojz.JZ.Entity.HYS;
import com.portfoliojz.JZ.Security.Controller.Mensaje;
import com.portfoliojz.JZ.Service.HYSService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hys")
public class HYSController {

    @Autowired
    HYSService shys;

    @GetMapping("/lista")
    public ResponseEntity<List<HYS>> list() {
        List<HYS> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<HYS> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        HYS hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHYS dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorío"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa Habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }

        HYS hys = new HYS(dtohys.getNombre(), dtohys.getPorcentaje());
        shys.save(hys);

        return new ResponseEntity(new Mensaje("Habilidad Guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHYS dtohys) {
        // Validacion por ID
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        //Evita repeticion de nombres de Habilidades
        if (shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa Habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }

        //El campo no puede quedar vacio
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HYS hys = shys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje(dtohys.getPorcentaje());

        shys.save(hys);
        return new ResponseEntity(new Mensaje("Habilidad Actualizada"), HttpStatus.OK);
    }

    //Eliminar una Experiencia por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID que desea eliminar no Existe"), HttpStatus.BAD_REQUEST);
        }

        shys.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad Elimina"), HttpStatus.OK);
    }
}
