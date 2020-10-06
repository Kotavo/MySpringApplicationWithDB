CREATE TABLE public.departments
(
    id bigserial NOT NULL,
    deleted boolean NOT NULL,
    location character varying(255),
    name character varying(255),
    CONSTRAINT departments_pkey PRIMARY KEY (id)
);

/*ANDNEXTSCRIPT

ALTER TABLE public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);

ANDNEXTSCRIPT*/
