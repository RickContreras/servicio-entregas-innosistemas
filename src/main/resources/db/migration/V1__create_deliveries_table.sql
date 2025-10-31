-- Crear la tabla deliveries
CREATE TABLE IF NOT EXISTS deliveries (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    file_url VARCHAR(512),
    created_at TIMESTAMP NOT NULL,
    team_id INTEGER NOT NULL
);

-- Crear índice para mejorar búsquedas por team_id
CREATE INDEX IF NOT EXISTS idx_deliveries_team_id ON deliveries(team_id);

-- Crear índice para mejorar búsquedas por fecha de creación
CREATE INDEX IF NOT EXISTS idx_deliveries_created_at ON deliveries(created_at);
