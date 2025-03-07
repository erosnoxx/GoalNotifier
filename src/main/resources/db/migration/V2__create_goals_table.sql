-- V2__create_goals_table.sql

CREATE TABLE IF NOT EXISTS goals
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title           VARCHAR(255) NOT NULL,
    description     TEXT         NOT NULL,
    target_datetime TIMESTAMP    NOT NULL,
    status          VARCHAR(20)  NOT NULL,
    completed       BOOLEAN      DEFAULT FALSE,
    user_id         UUID         NOT NULL,
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NULL,

    -- Definindo a chave estrangeira para a tabela de usuários
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);
