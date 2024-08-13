create table public.users
(
    active   boolean,
    age      integer,
    id       uuid not null
        primary key,
    gender   varchar(255)
        constraint users_gender_check
            check ((gender)::text = ANY ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying])::text[])),
    name     varchar(255),
    password varchar(255),
    username varchar(255)
);

alter table public.users
    owner to threepounds;

create table public.roles
(
    id   uuid not null
        primary key,
    code varchar(255),
    name varchar(255)
);

alter table public.roles
    owner to threepounds;


create table public.permissions
(
    id   uuid not null
        primary key,
    code varchar(255),
    name varchar(255)
);

alter table public.permissions
    owner to threepounds;

create table public.role_permissions
(
    permission_id uuid not null,
    role_id       uuid not null
);

alter table public.role_permissions
    owner to threepounds;


create table public.user_roles
(
    role_id uuid not null,
    user_id uuid not null
);

alter table public.user_roles
    owner to threepounds;

