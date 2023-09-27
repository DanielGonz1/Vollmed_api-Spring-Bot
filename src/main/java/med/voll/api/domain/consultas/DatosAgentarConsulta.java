package med.voll.api.domain.consultas;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

public record DatosAgentarConsulta(Long id, @NotNull Long idPaciente, Long idMedico,
                @NotNull @Future LocalDateTime fecha, Especialidad especialidad) {
}
