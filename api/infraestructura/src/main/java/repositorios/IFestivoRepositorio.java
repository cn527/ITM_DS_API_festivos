package com.festivos.api.infraestructura.repositorios;

import com.festivos.api.dominio.entidades.Festivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {

    @Query("SELECT f FROM Festivo f WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Festivo> buscar(@Param("nombre") String nombre);
    
    List<Festivo> findByDiaAndMes(Integer dia, Integer mes);
    
    List<Festivo> findByDiaAndMesAndTipoId(Integer dia, Integer mes, Integer tipoId);
    
    @Query("SELECT f FROM Festivo f")
    List<Festivo> obtenerTodos();
    
}