CREATE TABLE tb_customer(
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_customer PRIMARY KEY(id),
    CONSTRAINT uk_customer_email UNIQUE(email)
);

CREATE TABLE tb_event(
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(50) NOT NULL,
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_event PRIMARY KEY(id)
);

CREATE TABLE tb_ticket_lot(
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total_quantity INTEGER NOT NULL,
    available_quantity INTEGER NOT NULL,
    sales_start_at TIMESTAMP NOT NULL,
    sales_end_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    event_id BIGINT NOT NULL,

    CONSTRAINT pk_ticket_lot PRIMARY KEY (id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES tb_event(id)
);

CREATE TABLE tb_reservation(
    id BIGSERIAL NOT NULL,
    quantity INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    customer_id BIGINT NOT NULL,
    ticket_lot_id BIGINT NOT NULL,

    CONSTRAINT pk_reservation PRIMARY KEY (id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer (id),
    CONSTRAINT fk_ticket_lot FOREIGN KEY (ticket_lot_id) REFERENCES tb_ticket_lot (id)
);

CREATE TABLE tb_order(
    id BIGSERIAL NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    reservation_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,

    CONSTRAINT pk_order PRIMARY KEY (id),
    CONSTRAINT fk_reservation FOREIGN KEY (reservation_id) REFERENCES tb_reservation(id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);
