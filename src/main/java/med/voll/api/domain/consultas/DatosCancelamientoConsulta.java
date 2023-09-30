package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(
        @NotNull Long idConsulta,
        @NotNull MotivoCancelamiento motivo) {

}
