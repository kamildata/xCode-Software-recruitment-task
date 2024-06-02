CREATE TABLE IF NOT EXISTS "history"(
    id                      BIGSERIAL   PRIMARY KEY,
    currency                VARCHAR     NOT NULL,
    name                    VARCHAR     NOT NULL,
    request_send_date       DATE        NOT NULL,
    currency_value          NUMERIC     NOT NULL
)