alter table public.employees_role
    add constraint employees_role_employees_id_fkey foreign key (employee_id)
        references public.employees (id)
        on update restrict
        on delete cascade;