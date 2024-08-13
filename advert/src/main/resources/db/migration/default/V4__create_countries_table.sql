create table public.countries
(
    active     boolean,
    phone_code integer,
    id         uuid not null
        primary key,
    iso_code3  varchar(255),
    name       varchar(255)
);

alter table public.countries
    owner to threepounds;

