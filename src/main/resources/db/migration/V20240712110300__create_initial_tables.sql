-- V1__CreateTables.sql

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE contacts (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    contact_type VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL
);

CREATE TABLE addresses (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    street VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    document VARCHAR(255) NOT NULL,
    is_adult BOOLEAN NOT NULL,
    responsible_user_id UUID,
    address_id UUID,
    contact_id UUID,
    CONSTRAINT fk_user_responsible FOREIGN KEY (responsible_user_id) REFERENCES users(id),
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES addresses(id),
    CONSTRAINT fk_user_contact FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE schools (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL,
    responsible_user_id UUID,
    CONSTRAINT fk_school_responsible FOREIGN KEY (responsible_user_id) REFERENCES users(id)
);

CREATE TABLE branches (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    address_id UUID,
    contact_id UUID,
    responsible_user_id UUID,
    school_id UUID,
    CONSTRAINT fk_branch_address FOREIGN KEY (address_id) REFERENCES addresses(id),
    CONSTRAINT fk_branch_contact FOREIGN KEY (contact_id) REFERENCES contacts(id),
    CONSTRAINT fk_branch_responsible FOREIGN KEY (responsible_user_id) REFERENCES users(id),
    CONSTRAINT fk_branch_school FOREIGN KEY (school_id) REFERENCES schools(id)
);

CREATE TABLE user_profiles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    profile VARCHAR(255) NOT NULL,
    user_id UUID,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES users(id)
);
