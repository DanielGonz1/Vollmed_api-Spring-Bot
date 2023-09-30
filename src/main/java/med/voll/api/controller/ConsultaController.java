package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaDeConsultaService;
import med.voll.api.domain.consultas.DatosAgentarConsulta;
import med.voll.api.domain.consultas.DatosCancelamientoConsulta;
import med.voll.api.domain.consultas.DatosDetalleConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleConsulta> agentar(@RequestBody @Valid DatosAgentarConsulta datosAgentarConsulta)
            throws ValidacionDeIntegridad {
        var response = agendaDeConsultaService.agendar(datosAgentarConsulta);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DatosCancelamientoConsulta datosCancelamientoConsulta) {
        agendaDeConsultaService.cancelar(datosCancelamientoConsulta);
        return ResponseEntity.noContent().build();
    }
}
