CREATE TABLE public.employees
(
    id bigint NOT NULL,
    deleted boolean NOT NULL,
    mail character varying(255),
    name character varying(255),
    "position" character varying(255),
    surname character varying(255),
    department_id bigint NOT NULL,
    CONSTRAINT employees_pkey PRIMARY KEY (id)
/*    CONSTRAINT fkgy4qe3dnqrm3ktd76sxp7n4c2 FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION*/
);

/*ANDNEXTSCRIPT

ALTER TABLE public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);

ANDNEXTSCRIPT

ALTER TABLE public.employees
    ADD CONSTRAINT fkgy4qe3dnqrm3ktd76sxp7n4c2 FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;
*/