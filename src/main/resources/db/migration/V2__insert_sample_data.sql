-- Insertar datos de ejemplo para la tabla deliveries
-- Asegúrate de que el team_id 301 exista en tu tabla teams

INSERT INTO deliveries (title, description, file_url, created_at, team_id) VALUES
('Entrega Sprint 1', 'Primera entrega del proyecto de Ingeniería de Software', 'https://drive.google.com/file/d/ejemplo1', NOW(), 301),
('Entrega Sprint 2', 'Segunda entrega con las funcionalidades del módulo de usuarios', 'https://drive.google.com/file/d/ejemplo2', NOW(), 301),
('Entrega Sprint 3', 'Tercera entrega - integración de servicios', 'https://drive.google.com/file/d/ejemplo3', NOW(), 301),
('Documentación Técnica', 'Documentación completa del sistema', 'https://docs.google.com/document/ejemplo4', NOW(), 301),
('Prototipo UI/UX', 'Diseños y prototipos de interfaces', 'https://figma.com/file/ejemplo5', NOW(), 301);
