-- Table: incentive

-- DROP TABLE incentive CASCADE;

CREATE TABLE incentive
(
    id integer NOT NULL,
    dtype character varying(31),
    name character varying(255),
    version integer,
    CONSTRAINT incentive_pkey PRIMARY KEY (id)
)
WITH (
 OIDS=FALSE
);
ALTER TABLE incentive
    OWNER to tester;

-- Table: beverage

-- DROP TABLE beverage CASCADE;

CREATE TABLE beverage
(
    id integer NOT NULL,
    availablequantity integer,
    manufacturer character varying(255),
    name character varying(255),
    price double precision,
    quantity integer,
    version integer,
    incentive_id integer,
    CONSTRAINT beverage_pkey PRIMARY KEY (id),
    CONSTRAINT fk_beverage_incentive_id FOREIGN KEY (incentive_id)
        REFERENCES incentive (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
 OIDS=FALSE
);
ALTER TABLE beverage
    OWNER to tester;

-- Table: customer_order

-- DROP TABLE customer_order CASCADE;

CREATE TABLE customer_order
(
    id integer NOT NULL,
    issuedate timestamp without time zone,
    orderamount integer,
    order_id integer,
    version integer,
    beverage_id integer,
    CONSTRAINT customer_order_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_order_beverage_id FOREIGN KEY (beverage_id)
        REFERENCES beverage (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
 OIDS=FALSE
);
ALTER TABLE customer_order
    OWNER to tester;

-- Table: sequence

-- DROP TABLE sequence CASCADE;

CREATE TABLE sequence
(
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0),
    CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
)
WITH (
 OIDS=FALSE
);
ALTER TABLE sequence
    OWNER to tester;

-- Sequence: seq_gen_sequence

-- DROP SEQUENCE seq_gen_sequence;

CREATE SEQUENCE seq_gen_sequence
  INCREMENT 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 800
  CACHE 1;
ALTER TABLE seq_gen_sequence
  OWNER TO tester;

