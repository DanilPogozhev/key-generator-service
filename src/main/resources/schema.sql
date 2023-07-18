DROP TABLE IF EXISTS unique_keys;

CREATE TABLE IF NOT EXISTS unique_keys
(
    id         INT AUTO_INCREMENT NOT NULL,
    unique_key VARCHAR(50) NOT NULL UNIQUE,
    is_used    BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);