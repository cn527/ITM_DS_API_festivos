package com.festivos.api.aplicacion.servicios;

import com.festivos.api.core.servicios.ITipoServicio;
import com.festivos.api.dominio.entidades.Tipo;
import com.festivos.api.infraestructura.repositorios.ITipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicio implements ITipoServicio {

    @Autowired
    private ITipoRepositorio repo;

    @Override
    public List<Tipo> listar() {
        return repo.findAll();
    }

    @Override
    public Tipo obtener(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Tipo> buscar(String nombre) {
        return repo.buscarPorNombre(nombre);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        return repo.save(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        return repo.save(tipo);
    }

    @Override
    public Tipo eliminar(int id) {
        Tipo tipo = obtener(id);
        if (tipo != null) {
            repo.delete(tipo);
        }
        return tipo;
    }
}