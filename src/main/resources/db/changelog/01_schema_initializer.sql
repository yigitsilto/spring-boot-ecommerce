CREATE TABLE public.users
(
    id         uuid                  DEFAULT uuid_generate_v4(),
    created_at timestamp NULL,
    updated_at timestamp NULL,
    email      varchar(255) NULL,
    "password" varchar(255) NOT NULL,
    "role"     varchar(255) NOT NULL DEFAULT 'USER',
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
INSERT INTO public.users (email, password, role)
VALUES ('yigit@mail.com', '$2a$10$8TJ0YMzRzoZjQJKIoTDHdeqIkON8BRDolXt8dPY92J7sHEm1oGBr6', 'ADMIN');


CREATE TABLE public.categories
(
    id         uuid    DEFAULT uuid_generate_v4(),
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    name       varchar(255) NOT NULL,
    slug       varchar(255) NOT NULL,
    parent_id  uuid NULL,
    is_active  boolean DEFAULT false,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
);

CREATE TABLE public.brands
(
    id         uuid    DEFAULT uuid_generate_v4(),
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    name       varchar(255) NOT NULL,
    image_path uuid NULL,
    is_active  boolean DEFAULT false,
    CONSTRAINT brands_pkey PRIMARY KEY (id)
);


