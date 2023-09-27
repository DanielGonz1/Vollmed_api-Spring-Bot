package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaDeConsultaService;
import med.voll.api.domain.consultas.DatosAgentarConsulta;
import med.voll.api.domain.consultas.DatosDetalleConsulta;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleConsulta> agentar(@RequestBody @Valid DatosAgentarConsulta datosAgentarConsulta) {
        agendaDeConsultaService.agendar(datosAgentarConsulta);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }
}
