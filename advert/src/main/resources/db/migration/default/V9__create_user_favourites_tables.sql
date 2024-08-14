create table public.user_favourites
(
    ad_id   uuid not null,
    user_id uuid not null
);

alter table public.user_favourites
    owner to threepounds;

