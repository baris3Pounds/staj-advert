create table public.user_ad
(
    ad_id   uuid not null,
    user_id uuid not null
);

alter table public.user_ad
    owner to threepounds;

