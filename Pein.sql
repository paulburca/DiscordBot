-- Table: public.feed

-- DROP TABLE public.feed;

CREATE TABLE public.feed
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(32) COLLATE pg_catalog."default" NOT NULL,
    link character varying(256) COLLATE pg_catalog."default" NOT NULL,
    category character varying(32) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Feed_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.feed
    OWNER to postgres;
	
	
INSERT INTO public.feed( name, link, category)
	VALUES( 'News', 'https://www.news.ro/rss', 'any');