create table public.cities
(
    id        uuid not null
        primary key,
    country_id uuid not null,
    name      varchar(255)
);

alter table public.category
    owner to threepounds;



