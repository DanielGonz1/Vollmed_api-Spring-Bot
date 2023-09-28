package med.voll.api.domain.consultas.validacione;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgentarConsulta;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {

    public void validar(DatosAgentarConsulta datos) {

        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour() < 7;
        var despuesDelCierre = datos.fecha().getHour() > 19;

        if (domingo || antesDeApertura || despuesDelCierre)
            throw new ValidationException(
                    "El horario de atención de la clinica es de Lunes a Sábado de 07:00 a 19:00 horas.");
    }
}
