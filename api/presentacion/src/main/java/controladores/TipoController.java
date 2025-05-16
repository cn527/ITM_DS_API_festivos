package com.festivos.api.aplicacion.controladores;

import com.festivos.api.core.servicios.ITipoServicio;
import com.festivos.api.dominio.entidades.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoController {

    @Autowired
    private ITipoServicio tipoServicio;

    @GetMapping
    public ResponseEntity<List<Tipo>> listarTipos() {
        return ResponseEntity.ok(tipoServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> obtenerTipo(@PathVariable int id) {
        Tipo tipo = tipoServicio.obtener(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tipo>> buscarTipos(@RequestParam String nombre) {
        return ResponseEntity.ok(tipoServicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Tipo> agregarTipo(@RequestBody Tipo tipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoServicio.agregar(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> modificarTipo(@PathVariable int id, @RequestBody Tipo tipo) {
        Tipo tipoExistente = tipoServicio.obtener(id);
        if (tipoExistente != null) {
            tipo.setId(id);
            return ResponseEntity.ok(tipoServicio.modificar(tipo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tipo> eliminarTipo(@PathVariable int id) {
        Tipo tipo = tipoServicio.eliminar(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}