CREATE TABLE IF NOT EXISTS HistorialCalculos (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    endpoint VARCHAR(255) NOT NULL,
    parametros VARCHAR(255) NOT NULL,
    respuesta TEXT NOT NULL,
    error BOOLEAN DEFAULT false,
    mensaje_error TEXT
);

DROP TRIGGER IF EXISTS trg_set_fecha_before_insert ON HistorialCalculos;

DROP FUNCTION IF EXISTS set_fecha_before_insert();

CREATE OR REPLACE FUNCTION set_fecha_before_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.fecha IS NULL THEN
        NEW.fecha := NOW();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_fecha_before_insert
BEFORE INSERT ON HistorialCalculos
FOR EACH ROW
EXECUTE FUNCTION set_fecha_before_insert();

CREATE INDEX IF NOT EXISTS idx_historial_fecha ON HistorialCalculos(fecha);