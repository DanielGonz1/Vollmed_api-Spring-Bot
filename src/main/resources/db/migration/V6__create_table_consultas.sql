CREATE TABLE consultas(
    id BIGINT NOT NULL auto_increment,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    primary key (id),
    constraint fk_consultas_medico_id foreign key (medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key (paciente_id) references pacientes(id)
);