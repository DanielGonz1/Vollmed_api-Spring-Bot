package med.voll.api.domain.consultas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consultas.validacione.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    List<ValidadorDeConsultas> validadores;

    public void agendar(DatosAgentarConsulta datos) {

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("Este ID para el paciente no fue encontrado");
        }

        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("Este ID para el medico no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgentarConsulta datos) {
        if (datos.idMedico() != null)
            return medicoRepository.getReferenceById(datos.idMedico());
        if (datos.especialidad() == null)
            throw new ValidacionDeIntegridad("Debe seleccionarse una especialidad para el medico");

        return medicoRepository.SeleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());

    }
}
