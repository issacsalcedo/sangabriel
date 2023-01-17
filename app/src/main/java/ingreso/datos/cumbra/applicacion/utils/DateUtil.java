package ingreso.datos.cumbra.applicacion.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//import tareo.digital.gym.applicacion.data.cache.entities.ObreroBD;

public class DateUtil {

    public static Date getDate(String fechaEntrada) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/M/yyyy");
        Date miFecha = null;

        try {
            miFecha = formato.parse(fechaEntrada);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return miFecha;
    }

    public static String getChangeFormate(String fecha) {
        String fecha_formate = "";

        try {
            Date date = new SimpleDateFormat("dd/MM/yyy").parse(fecha);
            fecha_formate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha_formate;
    }

    public static String getShowDateFormat(String fecha) {
        String fecha_formate = "";

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            fecha_formate = new SimpleDateFormat("dd/MM/yyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha_formate;
    }

    public static String getDayOfTheWeek(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY: {
                return "Lunes";
            }
            case Calendar.TUESDAY: {
                return "Martes";
            }
            case Calendar.WEDNESDAY: {
                return "Miércoles";
            }
            case Calendar.THURSDAY: {
                return "Jueves";
            }
            case Calendar.FRIDAY: {
                return "Viernes";
            }
            case Calendar.SATURDAY: {
                return "Sábado";
            }
            case Calendar.SUNDAY: {
                return "Domingo";
            }
        }

        return "";
    }

    public static String getMes(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        String[] strMonths = new String[]{
                "Enero",
                "Febrero",
                "Marzo",
                "Abril",
                "Mayo",
                "Junio",
                "Julio",
                "Agosto",
                "Septiembre",
                "Octubre",
                "Noviembre",
                "Diciembre"};

        return strMonths[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR);
    }

    public static String getDiaNumero(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_MONTH) + "";
    }

    public static String getFechaYHora() {
        return DateFormat.format("dd-MM-yyyy HH:mm:ss", new Date()).toString();
    }

    public static String getFechaYHoraIternational() {
        return DateFormat.format("yyy-MM-dd HH:mm:ss", new Date()).toString();
    }

    public static Date obtenerDateDesdeTexto(String fecha) {
        if (fecha == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMATOS.FECHA_HORA);
        Date convertedDate = null;

        try {
            convertedDate = dateFormat.parse(fecha);
        } catch (ParseException ex) {
        }

        return convertedDate;
    }
}
