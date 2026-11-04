/**
 * Author:  matthewyeend
 * Created: 4 Nov 2026
 */

/*
 * Phone Directory schema.
 * Run as a privileged MySQL account. The application itself never uses this account.
 */
CREATE DATABASE IF NOT EXISTS phone_directory
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE phone_directory;

CREATE TABLE IF NOT EXISTS contacts (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name   VARCHAR(50)     NOT NULL,
    last_name    VARCHAR(50)     NOT NULL,
    phone_number VARCHAR(20)     NOT NULL,
    email        VARCHAR(254)    NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uq_contacts_phone (phone_number),
    KEY idx_contacts_name (last_name, first_name)
) ENGINE = InnoDB;