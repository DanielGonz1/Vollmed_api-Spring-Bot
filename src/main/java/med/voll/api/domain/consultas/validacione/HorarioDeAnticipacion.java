package med.voll.api.domain.consultas.validacione;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgentarConsulta;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgentarConsulta datos) {
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();
        var diferenciaDe30min = Duration.between(ahora, horaDeConsulta).toMinutes() < 30;

        if (diferenciaDe30min)
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación.");
    }
}
