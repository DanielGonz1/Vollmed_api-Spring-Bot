package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }

    public long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getDocumento() {
        return this.documento;
    }

    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void actualizacacion(DatosActualizaMedico datosActualizaMedico) {
        if (datosActualizaMedico.nombre() != null)
            this.nombre = datosActualizaMedico.nombre();
        if (datosActualizaMedico.documento() != null)
            this.documento = datosActualizaMedico.documento();
        if (datosActualizaMedico.direccion() != null)
            this.direccion = direccion.actualizarDireccion(datosActualizaMedico.direccion());
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
