package med.voll.api.domain.consultas.validacione;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DatosAgentarConsulta;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {

    private ConsultaRepository consultaRepository;

    public void validar(DatosAgentarConsulta datos) {
        if (datos.idMedico() == null)
            return;

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());

        if (medicoConConsulta)
            throw new ValidationException("Este medico ya tiene una consulta en ese horario");
    }
}
