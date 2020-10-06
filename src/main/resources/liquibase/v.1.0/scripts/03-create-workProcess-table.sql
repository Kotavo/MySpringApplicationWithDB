CREATE TABLE public.workprocesses
(
    id          bigserial NOT NULL,
    deleted     boolean   NOT NULL,
    description character varying(255),
    employee_id bigint    NOT NULL,
    CONSTRAINT workprocesses_pkey PRIMARY KEY (id)
);

