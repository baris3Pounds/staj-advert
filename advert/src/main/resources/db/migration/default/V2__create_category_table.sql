create table public.category
(
    is_active boolean,
    id        uuid not null
        primary key,
    name      varchar(255)
);

alter table public.category
    owner to threepounds;



