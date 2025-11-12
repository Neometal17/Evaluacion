-- ===========================================
-- INSERTS DE EJEMPLO PARA userCore
-- ===========================================
INSERT INTO USER_CORE (id, name, email, password, create, last_login, modified, token, active)
VALUES (
    '5e495c14-4861-4d1a-a1e9-47276c8190d0',
    'egonzalez',
    'emmanuel@example.com',
    'Adam21$',
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    '',
    1
);

INSERT INTO USER_CORE (id, name, email, password, create, last_login, modified, token, active)
VALUES (
    '5e495c14-4861-4d1a-a1e9-47276c8190d1',
    'cperez',
    'carlos@example.com',
    'Adam21$',
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    '',
    1
);

-- ===========================================
-- INSERTS DE EJEMPLO PARA phone
-- ===========================================
INSERT INTO phone (number, city_code, contry_code, user_id)
VALUES ('987654321', '1', '57', '5e495c14-4861-4d1a-a1e9-47276c8190d0');

INSERT INTO phone (number, city_code, contry_code, user_id)
VALUES ('912345678', '1', '57', '5e495c14-4861-4d1a-a1e9-47276c8190d1');
