-- ===========================================
-- TABLE: userCore
-- ===========================================
CREATE TABLE USER_CORE (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    create TIMESTAMP,
    last_login TIMESTAMP,
    modified TIMESTAMP,
    token VARCHAR(255),
    active INT
);

-- ===========================================
-- TABLE: phone
-- ===========================================
CREATE TABLE phone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    city_code VARCHAR(10),
    contry_code VARCHAR(10),
    user_id VARCHAR(36),
    CONSTRAINT fk_phone_user FOREIGN KEY (user_id)
        REFERENCES USER_CORE(id)
);
