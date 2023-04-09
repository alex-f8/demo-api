-- we don't know how to generate root <with-no-name> (class Root) :(
create table products.products
(
    id          bigserial
        constraint key_name
            primary key,
    title       varchar           not null,
    description varchar,
    amount      integer default 0 not null,
    type        varchar           not null,
    param_1     varchar
);

alter table products.products
    owner to postgres;

create table products.clients
(
    id        bigserial
        constraint pk
            primary key,
    full_name varchar not null
);

alter table products.clients
    owner to postgres;

create table products.accounting
(
    id         bigserial
        constraint accounting_pk
            primary key,
    product_id bigint                              not null
        constraint acc_prod_fk
            references products.products,
    client_id  bigserial
        constraint acc_client_fk
            references products.clients,
    amount     integer                             not null,
    created_at timestamp default CURRENT_TIMESTAMP not null
);

alter table products.accounting
    owner to postgres;

