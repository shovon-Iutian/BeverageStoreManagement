-- Table: division

-- DROP TABLE division;

--CREATE TABLE division
--(
--  id integer NOT NULL,
--  name character varying(255),
--  numemps integer,
--  version integer,
--  CONSTRAINT division_pkey PRIMARY KEY (id )
--)
--WITH (
--  OIDS=FALSE
--);
--ALTER TABLE division
--  OWNER TO tester;
--
--  
---- Table: room
--
---- DROP TABLE room;
--
--CREATE TABLE room
--(
--  id integer NOT NULL,
--  name character varying(255),
--  squaremeters double precision,
--  version integer,
--  division_id integer,
--  CONSTRAINT room_pkey PRIMARY KEY (id ),
--  CONSTRAINT fk_room_division_id FOREIGN KEY (division_id)
--      REFERENCES division (id) MATCH SIMPLE
--      ON UPDATE NO ACTION ON DELETE NO ACTION
--)
--WITH (
--  OIDS=FALSE
--);
--ALTER TABLE room
--  OWNER TO tester;
--
--  


CREATE TABLE promotional_gift_incentive
(
  id integer NOT NULL,
  name character varying(255),
  CONSTRAINT promotional_gift_incentive_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE promotional_gift_incentive
  OWNER TO tester;


CREATE TABLE trial_package_incentive
(
  id integer NOT NULL,
  name character varying(255),
  CONSTRAINT trial_package_incentive_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE trial_package_incentive
  OWNER TO tester;
  
  
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

