CREATE TABLE public.departments
(
    id bigserial NOT NULL,
    deleted boolean NOT NULL default false,
    location character varying(255),
    name character varying(255),
    CONSTRAINT departments_pkey PRIMARY KEY (id)
);
