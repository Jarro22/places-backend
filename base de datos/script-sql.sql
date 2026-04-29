
CREATE TABLE lugares (
                id_lugares INT NOT NULL,
                nombre VARCHAR(200) NOT NULL,
                descripcion VARCHAR(500) NOT NULL,
                latitud DECIMAL NOT NULL,
                longitud DECIMAL(100) NOT NULL,
                ubicacion DECIMAL(300) NOT NULL,
                municipio VARCHAR(100) NOT NULL,
                provincia VARCHAR(100) NOT NULL,
                departamento VARCHAR(100) NOT NULL,
                url VARCHAR(300) NOT NULL,
                PRIMARY KEY (id_lugares)
);


CREATE TABLE horarios (
                id_horarios INT NOT NULL,
                id_lugares INT NOT NULL,
                dia VARCHAR(50) NOT NULL,
                apertura TIME NOT NULL,
                cierre TIME NOT NULL,
                PRIMARY KEY (id_horarios)
);


CREATE TABLE funcionalidades (
                id_funcionalidades INT NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                PRIMARY KEY (id_funcionalidades)
);


CREATE TABLE roles (
                id_rol INT NOT NULL,
                nombre VARCHAR(100),
                PRIMARY KEY (id_rol)
);


CREATE TABLE privilegios (
                id_rol INT NOT NULL,
                id_funcionalidades INT NOT NULL,
                PRIMARY KEY (id_rol, id_funcionalidades)
);


CREATE TABLE personas (
                Id_personas INT AUTO_INCREMENT NOT NULL,
                nombres VARCHAR(100) NOT NULL,
                primer_apellido VARCHAR(100) NOT NULL,
                segundo_apellido VARCHAR(100) NOT NULL,
                dni VARCHAR(50) NOT NULL,
                complemento VARCHAR(2) NOT NULL,
                fecha_nacimiento DATE NOT NULL,
                genero VARCHAR(50) NOT NULL,
                direccion VARCHAR(200) NOT NULL,
                telefono_fijo INT NOT NULL,
                celular INT NOT NULL,
                email VARCHAR(100) NOT NULL,
                PRIMARY KEY (Id_personas)
);


CREATE TABLE usuario (
                Id_personas INT NOT NULL,
                usuario VARCHAR(50) NOT NULL,
                password VARCHAR(200) NOT NULL,
                PRIMARY KEY (Id_personas)
);


CREATE UNIQUE INDEX usuario_idx
 ON usuario
 ( usuario );

CREATE TABLE favoritos (
                Id_personas INT NOT NULL,
                id_lugares INT NOT NULL,
                PRIMARY KEY (Id_personas, id_lugares)
);


CREATE TABLE comentarios (
                id_comentarios INT NOT NULL,
                comentario VARCHAR(500) NOT NULL,
                calificacion INT NOT NULL,
                fecha DATE NOT NULL,
                Id_personas INT NOT NULL,
                id_lugares INT NOT NULL,
                Parent_id_comentarios INT NOT NULL,
                PRIMARY KEY (id_comentarios)
);


CREATE TABLE fotos (
                id_fotos INT NOT NULL,
                id_lugares INT NOT NULL,
                id_comentarios INT NOT NULL,
                url VARCHAR(300) NOT NULL,
                resea VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_fotos)
);


CREATE TABLE cuentas (
                id_rol INT NOT NULL,
                Id_personas INT NOT NULL,
                PRIMARY KEY (id_rol, Id_personas)
);


ALTER TABLE horarios ADD CONSTRAINT lugares_horarios_fk
FOREIGN KEY (id_lugares)
REFERENCES lugares (id_lugares)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE fotos ADD CONSTRAINT lugares_fotos_fk
FOREIGN KEY (id_lugares)
REFERENCES lugares (id_lugares)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE comentarios ADD CONSTRAINT lugares_comentarios_fk
FOREIGN KEY (id_lugares)
REFERENCES lugares (id_lugares)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE favoritos ADD CONSTRAINT lugares_favoritos_fk
FOREIGN KEY (id_lugares)
REFERENCES lugares (id_lugares)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE privilegios ADD CONSTRAINT funcionalidades_roles_funcionalidades_fk
FOREIGN KEY (id_funcionalidades)
REFERENCES funcionalidades (id_funcionalidades)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE cuentas ADD CONSTRAINT roles_cuentas_fk
FOREIGN KEY (id_rol)
REFERENCES roles (id_rol)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE privilegios ADD CONSTRAINT roles_roles_funcionalidades_fk
FOREIGN KEY (id_rol)
REFERENCES roles (id_rol)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE usuario ADD CONSTRAINT personas_usuario_fk
FOREIGN KEY (Id_personas)
REFERENCES personas (Id_personas)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE cuentas ADD CONSTRAINT usuario_cuentas_fk
FOREIGN KEY (Id_personas)
REFERENCES usuario (Id_personas)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE comentarios ADD CONSTRAINT usuario_comentarios_fk
FOREIGN KEY (Id_personas)
REFERENCES usuario (Id_personas)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE favoritos ADD CONSTRAINT usuario_favoritos_fk
FOREIGN KEY (Id_personas)
REFERENCES usuario (Id_personas)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE fotos ADD CONSTRAINT comentarios_fotos_fk
FOREIGN KEY (id_comentarios)
REFERENCES comentarios (id_comentarios)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE comentarios ADD CONSTRAINT comentarios_comentarios_fk
FOREIGN KEY (Parent_id_comentarios)
REFERENCES comentarios (id_comentarios)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
