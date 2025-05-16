package com.festivos.api.infraestructura.repositorios;

import com.festivos.api.dominio.entidades.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {
    @Query("SELECT t FROM Tipo t WHERE LOWER(t.tipo) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Tipo> buscarPorNombre(@Param("nombre") String nombre);
}