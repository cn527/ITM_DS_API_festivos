package com.festivos.api.aplicacion.controladores;

import com.festivos.api.core.servicios.IFestivoServicio;
import com.festivos.api.dominio.entidades.Festivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
@CrossOrigin(origins = "*")
public class FestivoCrudController {

    @Autowired
    private IFestivoServicio festivoServicio;

    @GetMapping
    public ResponseEntity <String> listarFestivos(@RequestParam String TextoFecha) {
        Date date = festivoServicio.strToDate(TextoFecha);
        return ResponseEntity.ok(festivoServicio.validarFecha(date));
    }
}