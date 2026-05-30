CREATE TYPE document_type AS ENUM (
    'DNI',
    'PASSPORT',
    'ID_CARD',
    'OTHER'
);

CREATE TABLE client (
    unique_code     VARCHAR(255) PRIMARY KEY,
    name            VARCHAR(255),
    last_name       VARCHAR(255),
    document_type   document_type NOT NULL,
    document_number VARCHAR(50)
);
