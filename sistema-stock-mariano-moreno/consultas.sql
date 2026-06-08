USE gestion_stock_mm;

INSERT INTO usuario (nombre, usuario, contrasena, rol) VALUES
('Administrador General', 'admin', 'admin123', 'Administrador'),
('Daniela Nieto', 'daniela', 'daniela123', 'Administrativo');

INSERT INTO ingrediente (nombre, unidad_medida, cantidad_disponible, stock_minimo) VALUES
('Harina 000', 'kg', 20, 5),
('Huevos', 'unidad', 60, 24),
('Azúcar', 'kg', 10, 3),
('Sal', 'kg', 5, 1),
('Levadura', 'kg', 1, 2);

INSERT INTO orden_compra (estado, id_usuario) VALUES
('Pendiente', 2);

INSERT INTO detalle_orden_compra (id_orden, id_ingrediente, cantidad_solicitada) VALUES
(1, 5, 3);

SELECT * FROM ingrediente;

SELECT nombre, cantidad_disponible, stock_minimo
FROM ingrediente
WHERE cantidad_disponible <= stock_minimo;

SELECT oc.id_orden, oc.fecha, oc.estado, i.nombre, doc.cantidad_solicitada
FROM orden_compra oc
INNER JOIN detalle_orden_compra doc ON oc.id_orden = doc.id_orden
INNER JOIN ingrediente i ON doc.id_ingrediente = i.id_ingrediente;

DELETE FROM detalle_orden_compra
WHERE id_detalle = 1;

SELECT * FROM detalle_orden_compra;