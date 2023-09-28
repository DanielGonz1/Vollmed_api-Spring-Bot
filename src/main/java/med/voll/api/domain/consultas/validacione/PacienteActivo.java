package med.voll.api.domain.consultas.validacione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgentarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class PacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgentarConsulta datos) {
        if (datos.idPaciente() == null) {
            return;
        }

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if (!pacienteActivo)
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
    }
}
