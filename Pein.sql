-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(32) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.category
    OWNER to postgres;
	
	
-- Table: public.feed

-- DROP TABLE public.feed;

CREATE TABLE public.feed
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(32) COLLATE pg_catalog."default" NOT NULL,
    link character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Feed_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.feed
    OWNER to postgres;
	
	
-- Table: public.feedcategory

-- DROP TABLE public.feedcategory;

CREATE TABLE public.feedcategory
(
    id_feed bigint NOT NULL,
    id_category bigint NOT NULL,
    CONSTRAINT feedcategory_pkey PRIMARY KEY (id_category, id_feed)
)

TABLESPACE pg_default;

ALTER TABLE public.feedcategory
    OWNER to postgres;