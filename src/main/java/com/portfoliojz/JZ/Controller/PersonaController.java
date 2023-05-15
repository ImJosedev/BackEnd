package com.portfoliojz.JZ.Controller;

import com.portfoliojz.JZ.Dto.dtoPersona;
import com.portfoliojz.JZ.Entity.Persona;
import com.portfoliojz.JZ.Security.Controller.Mensaje;
import com.portfoliojz.JZ.Service.ImplementPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins ="https://frontend-portfolio-53ce7.web.app")
public class PersonaController {
    @Autowired
    ImplementPersonaService personaS;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!personaS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = personaS.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoper) {
        if (!personaS.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        if (personaS.existsByNombre(dtoper.getNombre()) && personaS.getByNombre(dtoper.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Es nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaS.getOne(id).get();
        persona.setNombre(dtoper.getNombre());
        persona.setApellido(dtoper.getApellido());
        persona.setDescripcion(dtoper.getDescripcion());
        persona.setImg(dtoper.getImg());

        personaS.save(persona);

        return new ResponseEntity(new Mensaje("Persona Actualizada correctamente"), HttpStatus.OK);
    }
    
        /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoper) {
        if (StringUtils.isBlank(dtoper.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (personaS.existsByNombreEd(dtoper.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre que intenta ingresar ya existe"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(dtoper.getNombreEd(), dtoper.getDescripcionEd());
        personaS.save(persona);
        return new ResponseEntity(new Mensaje("Educacion Creada Correctamente"), HttpStatus.OK);
    } */

   /* @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personaS.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID que desea eliminar"), HttpStatus.NOT_FOUND);
        }
        personaS.delete(id);
        return new ResponseEntity(new Mensaje("Persona Eliminada"), HttpStatus.OK);
    } */
}