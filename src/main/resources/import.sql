INSERT INTO COMPETICIONES (nombre_competicion, descripcion, plazas, lugar_Evento, dificultad) VALUES('Competicion Feria de Abril', 'Esta vez, queremos juntar a los institutos de la comunidad para celebrar la feria de abril como mas nos gusta, compitiendo y mejorando nuestro aprendizaje a la vez que pasamos un buen rato y disfrutamos posteriormente de una barbacoa entre todos. ',20,'Pabellón 12 IFEMA Feria de Madrid', 2);
INSERT INTO COMPETICIONES (nombre_competicion, descripcion, plazas, lugar_Evento, dificultad) VALUES('Competicion Navidad', 'En estas fechas tan señaladas, queremos recompensar a nuestros jovenes talentos todo el esfuerzo que hacen con los mayores premios jamás vistos.',50,'Pabellón 11 IFEMA Feria de Madrid', 3);


INSERT INTO INSTITUTOS(CIF_INSTITUTO, NOMBRE_INSTITUTO, LOCALIZACION, TELEFONO_CONTACTO, EMAIL_CONTACTO) VALUES('B854875', 'QUEVEDO', 'MADRID', 999999999, 'HOLA@HOLA.COM');
INSERT INTO INSTITUTOS(CIF_INSTITUTO, NOMBRE_INSTITUTO, LOCALIZACION, TELEFONO_CONTACTO, EMAIL_CONTACTO) VALUES('B11111', 'ELIPA', 'MADRID', 999999999, 'HOLA@HOLA.COM');
INSERT INTO INSTITUTOS(CIF_INSTITUTO, NOMBRE_INSTITUTO, LOCALIZACION, TELEFONO_CONTACTO, EMAIL_CONTACTO) VALUES('b5847', 'GOYA', 'MADRID', 999999999, 'HOLA@HOLA.COM');
INSERT INTO INSTITUTOS(CIF_INSTITUTO, NOMBRE_INSTITUTO, LOCALIZACION, TELEFONO_CONTACTO, EMAIL_CONTACTO) VALUES('B354875', 'GOYA', 'MADRID', 999999999, 'HOLA@HOLA.COM');

INSERT INTO usuarios(DNI, NOMBRE_USUARIO, APELLIDO_USUARIO, PASSWORD, EMAIL_USUARIO) VALUES('52907600X', 'diego', 'BELLO', '$2a$10$f4y.a1iHQ0H18aE0C099junFj2Csst/bjTaQ9oKvumB0QHVF.Hq9i', 'DIEGO@DIEGO.COM');
INSERT INTO usuarios(DNI, NOMBRE_USUARIO, APELLIDO_USUARIO, PASSWORD, EMAIL_USUARIO) VALUES('52984600X', 'steven', 'FIRU', '$2a$10$M0b4mj7r8s6KW7ap94p72ui9S8BJitVkAKIrRUbr2qXkXOAHjA6eC', 'FIRU@LAY.COM');

INSERT INTO ROLES(nombre) VALUES('ROLE_USER');
INSERT INTO ROLES(nombre) VALUES('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id_usuario, roles_id)VALUES(1,1);
INSERT INTO `usuarios_roles` (usuario_id_usuario, roles_id)VALUES(2,1);
INSERT INTO `usuarios_roles` (usuario_id_usuario, roles_id)VALUES(2,2);