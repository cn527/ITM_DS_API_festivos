package com.festivos.api.core.servicios;

import com.festivos.api.dominio.entidades.Festivo;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public interface IFestivoServicio {
    String validarFecha(Date fecha);

    Date calcularFechaFestivo(Festivo festivo, int anio);

    boolean mismaFecha(Date f1, Date f2);

    String formatearFecha(Date fecha);

    String nombreMes(int mes);

    Date getInicioSemanaSanta(int año);

    Date agregarDias(Date fecha, int dias);

    Date getSiguienteLunes(Date fecha);
    
    Date strToDate(String date);
}