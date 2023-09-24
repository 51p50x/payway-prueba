--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    id bigint NOT NULL,
    fechadenacimiento timestamp without time zone,
    nombre character varying(255),
    pais character varying(255)
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- Name: autor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autor_seq OWNER TO postgres;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id bigint NOT NULL,
    archivo character varying(255),
    nombre character varying(255)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_seq OWNER TO postgres;

--
-- Name: libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro (
    id bigint NOT NULL,
    estado character varying(255),
    precio numeric(19,2),
    titulo character varying(255),
    autor_id bigint
);


ALTER TABLE public.libro OWNER TO postgres;

--
-- Name: libro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.libro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libro_seq OWNER TO postgres;

--
-- Name: librocategoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.librocategoria (
    id bigint NOT NULL,
    categoria_id bigint,
    libro_id bigint
);


ALTER TABLE public.librocategoria OWNER TO postgres;

--
-- Name: librocategoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.librocategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.librocategoria_seq OWNER TO postgres;

--
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autor (id, fechadenacimiento, nombre, pais) FROM stdin;
2	1947-09-21 00:00:00	Stephen King	Estados Unidos
3	1965-07-31 00:00:00	J.K. Rowling	Reino Unido
4	1892-01-03 00:00:00	J.R.R. Tolkien	Reino Unido
5	1920-08-22 00:00:00	Ray Bradbury	Estados Unidos
6	1818-12-16 00:00:00	Mary Shelley	Reino Unido
7	1882-02-02 00:00:00	James Joyce	Irlanda
8	1928-08-28 00:00:00	Maya Angelou	Estados Unidos
9	1899-09-24 00:00:00	William Faulkner	Estados Unidos
10	1835-11-30 00:00:00	Mark Twain	Estados Unidos
11	1948-07-12 00:00:00	Paulo Coelho	Brasil
\.


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, archivo, nombre) FROM stdin;
4	archivo_1	Ciencia Ficción
5	archivo_2	Fantasía
6	archivo_3	Misterio
7	archivo_4	Terror
8	archivo_5	Historia
9	archivo_6	Biografía
10	archivo_7	Autoayuda
11	archivo_8	Tecnología
12	archivo_9	Ciencia
13	archivo_10	Poesía
\.


--
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.libro (id, estado, precio, titulo, autor_id) FROM stdin;
16	activo	20.15	Harry Potter	3
15	activo	20.99	El resplandor	2
17	activo	30.50	El Señor de los Anillos	4
18	activo	12.50	Fahrenheit 451	5
19	activo	20.43	Frankenstein	6
20	activo	15.99	Ulises	7
21	activo	21.89	Yo sé por qué canta el pájaro enjaulado	8
22	activo	14.33	El ruido y la furia	9
23	activo	14.44	Las aventuras de Huckleberry Finn	10
24	activo	10.99	El Alquimista	11
\.


--
-- Data for Name: librocategoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.librocategoria (id, categoria_id, libro_id) FROM stdin;
40	4	16
41	5	16
42	4	15
43	7	15
44	5	17
45	4	18
46	7	19
47	12	20
48	9	21
49	4	22
50	5	23
51	10	24
\.


--
-- Name: autor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autor_seq', 1, false);


--
-- Name: categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_seq', 1, false);


--
-- Name: libro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.libro_seq', 24, true);


--
-- Name: librocategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.librocategoria_seq', 51, true);


--
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (id);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: libro libro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id);


--
-- Name: librocategoria librocategoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.librocategoria
    ADD CONSTRAINT librocategoria_pkey PRIMARY KEY (id);


--
-- Name: librocategoria fkfkxr5d9jljy24fu1mtanp2jml; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.librocategoria
    ADD CONSTRAINT fkfkxr5d9jljy24fu1mtanp2jml FOREIGN KEY (libro_id) REFERENCES public.libro(id);


--
-- Name: librocategoria fkmto026cqsq1ttl79nvtfawjtp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.librocategoria
    ADD CONSTRAINT fkmto026cqsq1ttl79nvtfawjtp FOREIGN KEY (categoria_id) REFERENCES public.categoria(id);


--
-- Name: libro fkp5u4s28y83m4vc5qw0xdlslwo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT fkp5u4s28y83m4vc5qw0xdlslwo FOREIGN KEY (autor_id) REFERENCES public.autor(id);


--
-- PostgreSQL database dump complete
--

