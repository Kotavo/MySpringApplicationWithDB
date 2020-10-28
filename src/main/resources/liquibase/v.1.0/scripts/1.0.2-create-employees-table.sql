CREATE TABLE public.employees
(
    id            bigserial NOT NULL,
    deleted       boolean   NOT NULL default false,
    mail          character varying(255) unique,
    name          character varying(255),
    position      character varying(255),
    surname       character varying(255),
    department_id bigint    NOT NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (id)
);
