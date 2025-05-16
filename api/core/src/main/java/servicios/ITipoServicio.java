package com.festivos.api.core.servicios;

import com.festivos.api.dominio.entidades.Tipo;
import java.util.List;

public interface ITipoServicio {
    List<Tipo> listar();
    Tipo obtener(int id);
    List<Tipo> buscar(String nombre);
    Tipo agregar(Tipo tipo);
    Tipo modificar(Tipo tipo);
    Tipo eliminar(int id);
}