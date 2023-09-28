package med.voll.api.domain.consultas.validacione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultas.DatosAgentarConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Service
public class MedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgentarConsulta datos) {

        if (datos.idMedico() == null)
            return;

        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo)
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
    }
}
