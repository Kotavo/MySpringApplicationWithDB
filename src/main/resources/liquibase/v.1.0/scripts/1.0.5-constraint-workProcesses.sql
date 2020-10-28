ALTER TABLE public.workprocesses
    ADD CONSTRAINT fk25hybaxwl9hug0qtwinj7jw4p FOREIGN KEY (employee_id)
        REFERENCES public.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;
