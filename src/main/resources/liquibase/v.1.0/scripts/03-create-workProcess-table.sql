CREATE TABLE public.workprocesses
(
    id bigserial NOT NULL,
    deleted boolean NOT NULL,
    description character varying(255),
    employee_id bigint NOT NULL,
    CONSTRAINT workprocesses_pkey PRIMARY KEY (id)
/*    CONSTRAINT uk_moii1ad2v6s4oe5dseghkimmq UNIQUE (employee_id),
    CONSTRAINT fk25hybaxwl9hug0qtwinj7jw4p FOREIGN KEY (employee_id)
        REFERENCES public.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION*/
);

/*ANDNEXTSCRIPT

ALTER TABLE public.workprocesses
    ADD CONSTRAINT workprocesses_pkey PRIMARY KEY (id);

ANDNEXTSCRIPT

ALTER TABLE public.workprocesses
    ADD CONSTRAINT fk25hybaxwl9hug0qtwinj7jw4p FOREIGN KEY (employee_id)
        REFERENCES public.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ANDNEXTSCRIPT

ALTER TABLE public.workprocesses
    ADD CONSTRAINT uk_moii1ad2v6s4oe5dseghkimmq UNIQUE (employee_id);*/