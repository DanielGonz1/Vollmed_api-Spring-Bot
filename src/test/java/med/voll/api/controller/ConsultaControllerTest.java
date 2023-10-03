package med.voll.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaDeConsultaService;
import med.voll.api.domain.consultas.DatosAgentarConsulta;
import med.voll.api.domain.consultas.DatosDetalleConsulta;
import med.voll.api.domain.medico.Especialidad;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
@TestPropertySource(properties = "api.security.secret=123456")
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgentarConsulta> agendaJacksonTester;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleJacksonTester;

    @MockBean
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("Deberia retornar estado http 400 cuando los datos ingresados son invalidos")
    @WithMockUser
    void agendarEscenario1() throws Exception {
        // given //when
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void agendarEscenario2() throws Exception {
        // given
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datos = new DatosDetalleConsulta(null, 2l, 5l, fecha);
        // when

        when(agendaDeConsultaService.agendar(any())).thenReturn(datos);

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agendaJacksonTester.write(new DatosAgentarConsulta(null, 2l, 5l, fecha, especialidad))
                        .getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var json = detalleJacksonTester.write(datos).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}
