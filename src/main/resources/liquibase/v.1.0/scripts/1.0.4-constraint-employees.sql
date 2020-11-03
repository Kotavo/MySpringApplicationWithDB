ALTER TABLE public.employees
    ADD CONSTRAINT fkgy4qe3dnqrm3ktd76sxp7n4c2 FOREIGN KEY (department_id)
        REFERENCES public.departments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;