package med.voll.api.domain.consultas.validacione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DatosAgentarConsulta;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgentarConsulta datos) {

        var primerHorario = datos.fecha().withHour(7);
        var utlimoHorario = datos.fecha().withHour(18);

        var PacienteConConsulta = consultaRepository.existsByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario,
                utlimoHorario);

        if (PacienteConConsulta)
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
    }
}
