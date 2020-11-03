alter table public.employees_role
    add constraint employees_role_role_name_fkey foreign key (role_name)
        references public.roles (name)
        on update restrict
        on delete cascade;