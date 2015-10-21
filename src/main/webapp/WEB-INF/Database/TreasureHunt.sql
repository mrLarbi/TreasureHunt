--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: coord_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE coord_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE coord_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: coords; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE coords (
    id integer DEFAULT nextval('coord_id_seq'::regclass) NOT NULL,
    latitude text,
    longitude text,
    image text,
    name text
);


ALTER TABLE coords OWNER TO postgres;

--
-- Name: hunt_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hunt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hunt_id_seq OWNER TO postgres;

--
-- Name: hunts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hunts (
    id integer DEFAULT nextval('hunt_id_seq'::regclass) NOT NULL,
    creator integer,
    creation timestamp with time zone,
    name text,
    points integer[],
    usersfinished integer[],
    userscurrent integer[]
);


ALTER TABLE hunts OWNER TO postgres;

--
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE message_id_seq OWNER TO postgres;

--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE messages (
    id integer DEFAULT nextval('message_id_seq'::regclass) NOT NULL,
    userfrom integer,
    userto integer,
    content text,
    date timestamp with time zone
);


ALTER TABLE messages OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id integer DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    avatar text NOT NULL,
    name text,
    gender character(1),
    postalcode integer,
    phone text,
    created timestamp with time zone,
    friends integer[],
    messages integer[],
    createdhunts integer[],
    currenthunts integer[],
    finishedhunts integer[],
    remember text
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: coord_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('coord_id_seq', 1, false);


--
-- Data for Name: coords; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY coords (id, latitude, longitude, image, name) FROM stdin;
\.


--
-- Name: hunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hunt_id_seq', 1, false);


--
-- Data for Name: hunts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hunts (id, creator, creation, name, points, usersfinished, userscurrent) FROM stdin;
\.


--
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('message_id_seq', 1, false);


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY messages (id, userfrom, userto, content, date) FROM stdin;
\.


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, false);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, username, password, email, avatar, name, gender, postalcode, phone, created, friends, messages, createdhunts, currenthunts, finishedhunts, remember) FROM stdin;
\.


--
-- Name: coords_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coords
    ADD CONSTRAINT coords_pkey PRIMARY KEY (id);


--
-- Name: hunts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hunts
    ADD CONSTRAINT hunts_pkey PRIMARY KEY (id);


--
-- Name: messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

