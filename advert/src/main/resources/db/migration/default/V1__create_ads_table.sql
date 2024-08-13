create table public.ads
(
    active      boolean          not null,
    latitude    double precision not null,
    longitude   double precision not null,
    price       numeric(38, 2)   not null,
    view_count  integer          not null,
    category_id uuid,
    city_id     uuid,
    country_id  uuid,
    id          uuid             not null
        primary key,
    user_id     uuid,
    title       varchar(100)     not null,
    description varchar(500)
);

alter table public.ads
    owner to threepounds;


