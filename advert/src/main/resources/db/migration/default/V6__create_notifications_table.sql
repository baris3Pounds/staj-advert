create table public.notifications
(
    sent              boolean,
    created_timestamp timestamp(6),
    updated_timestamp timestamp(6),
    id                uuid         not null
        primary key,
    message           varchar(255),
    notification_type varchar(255),
    title             varchar(255) not null,
    to_user           varchar(255)
);

alter table public.notifications
    owner to threepounds;

