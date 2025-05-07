CREATE TABLE HistorialCalculos (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    endpoint VARCHAR(255) NOT NULL,
    parametros VARCHAR(255) NOT NULL,
    respuesta TEXT NOT NULL,
    error BOOLEAN,
    mensaje_error TEXT
);


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