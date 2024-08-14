create table public.location
(
    lat            double precision,
    lon            double precision,
    id             uuid not null
        primary key,
    as_description varchar(255),
    city           varchar(255),
    country        varchar(255),
    country_code   varchar(255),
    isp            varchar(255),
    org            varchar(255),
    query          varchar(255),
    region         varchar(255),
    status         varchar(255),
    timezone       varchar(255)
);

alter table public.location
    owner to threepounds;

