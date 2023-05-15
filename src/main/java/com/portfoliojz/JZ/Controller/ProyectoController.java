/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliojz.JZ.Controller;

import com.portfoliojz.JZ.Dto.dtoProyecto;
import com.portfoliojz.JZ.Entity.Proyecto;
import com.portfoliojz.JZ.Security.Controller.Mensaje;
import com.portfoliojz.JZ.Service.ProyectoService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://frontend-portfolio-53ce7.web.app")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoS;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = proyectoS.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproy) {
        if (StringUtils.isBlank(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoS.existsByNombre(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre que intenta ingresar ya existe"), HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = new Proyecto(dtoproy.getNombre(), dtoproy.getDescripcion(), dtoproy.getImg());
        proyectoS.save(proyecto);
        return new ResponseEntity(new Mensaje("Educacion Creada Correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproy) {
        if (!proyectoS.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        if (proyectoS.existsByNombre(dtoproy.getNombre()) && proyectoS.getByNombre(dtoproy.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoproy.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = proyectoS.getOne(id).get();
        proyecto.setNombre(dtoproy.getNombre());
        proyecto.setDescripcion(dtoproy.getDescripcion());
        proyecto.setImg(dtoproy.getImg());

        proyectoS.save(proyecto);

        return new ResponseEntity(new Mensaje("Persona Actualizada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID que desea eliminar"), HttpStatus.NOT_FOUND);
        }
        proyectoS.delete(id);
        return new ResponseEntity(new Mensaje("Persona Eliminada"), HttpStatus.OK);
    }
}
