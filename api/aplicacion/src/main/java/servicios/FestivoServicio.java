
package com.festivos.api.aplicacion.servicios;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.festivos.api.dominio.entidades.Festivo;
import com.festivos.api.infraestructura.repositorios.IFestivoRepositorio;
import com.festivos.api.core.servicios.IFestivoServicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    private IFestivoRepositorio repositorio;

    @Override
    public String validarFecha(Date fecha) {
        if (fecha == null) {
            return "La fecha ingresada no es válida.";
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);

        List<Festivo> festivos = repositorio.obtenerTodos();

        for (Festivo festivo : festivos) {
            Date fechaFestivo = calcularFechaFestivo(festivo, anio);
            if (mismaFecha(fecha, fechaFestivo)) {
                return formatearFecha(fecha) + " es festivo: " + festivo.getNombre();
            }
        }

        return formatearFecha(fecha) + " no es festivo";
    }

    @Override
    public Date calcularFechaFestivo(Festivo festivo, int anio) {
        Date fecha;
        switch (festivo.getTipo().getId()) {
            case 1:
                fecha = new Date(anio - 1900, festivo.getMes() - 1, festivo.getDia());
                break;
            case 2:
                fecha = new Date(anio - 1900, festivo.getMes() - 1, festivo.getDia());
                fecha = getSiguienteLunes(fecha);
                break;
            case 3:
                Date pascua = getInicioSemanaSanta(anio);
                fecha = agregarDias(pascua, festivo.getDiasPascua());
                break;
            case 4:
                Date base = getInicioSemanaSanta(anio);
                fecha = agregarDias(base, festivo.getDiasPascua());
                fecha = getSiguienteLunes(fecha);
                break;
            default:
                fecha = null;
        }
        return fecha;
    }

    @Override
    public boolean mismaFecha(Date f1, Date f2) {
        if (f1 == null || f2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(f1);
        c2.setTime(f2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String formatearFecha(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);
        return String.format("%02d de %s de %d", dia, nombreMes(mes), anio);
    }

    @Override
    public String nombreMes(int mes) {
        String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" };
        return meses[mes - 1];
    }

    @Override
    public Date getInicioSemanaSanta(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = (d + (2 * b + 4 * c + 6 * d + 5)) % 7;

        int dia = 15 + dias;
        int mes = 3; // Marzo

        return new Date(año - 1900, mes - 1, dia);
    }

    @Override
    public Date agregarDias(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    @Override
    public Date getSiguienteLunes(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        if (diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY)
                fecha = agregarDias(fecha, 9 - diaSemana);
            else
                fecha = agregarDias(fecha, 1);
        }
        return fecha;
    }

    @Override
    public Date strToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            System.err.println("Fecha inválida: " + date);
            return null;
        }
    }
}
