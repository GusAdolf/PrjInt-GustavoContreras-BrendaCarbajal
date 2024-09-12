CREATE TABLE odontologo (
    matricula BIGINT NOT NULL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL
);


CREATE TABLE paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25),
    apellido VARCHAR(50),
    domicilio VARCHAR(50),
    dni VARCHAR(20) UNIQUE,
    fecha_alta TIMESTAMP
);

CREATE TABLE turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    odontologo_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    FOREIGN KEY (odontologo_id) REFERENCES odontologo(matricula),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);


INSERT INTO paciente (nombre, apellido, domicilio, dni, fecha_alta) VALUES
('Juan', 'Pérez', 'Calle Falsa 123', '12345678901234567890', CURRENT_TIMESTAMP),
('Ana', 'Martínez', 'Avenida Siempre Viva 456', '12345678901234567891', CURRENT_TIMESTAMP),
('Carlos', 'López', 'Calle del Sol 789', '12345678901234567892', CURRENT_TIMESTAMP),
('María', 'Rodríguez', 'Calle de la Luna 101', '12345678901234567893', CURRENT_TIMESTAMP),
('José', 'García', 'Avenida del Norte 202', '12345678901234567894', CURRENT_TIMESTAMP),
('Lucía', 'Hernández', 'Calle del Mar 303', '12345678901234567895', CURRENT_TIMESTAMP),
('Pedro', 'Fernández', 'Calle Azul 404', '12345678901234567896', CURRENT_TIMESTAMP);

INSERT INTO odontologo (apellido, nombre, matricula) VALUES
('García', 'Juan', 1234567890),
('Martínez', 'Ana', 1234567891),
('Pérez', 'Carlos', 1234567892),
('Gómez', 'María', 1234567893),
('Rodríguez', 'José', 1234567894);