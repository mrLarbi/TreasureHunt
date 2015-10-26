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

CREATE TABLE coordinates (
    id integer DEFAULT nextval('coord_id_seq'::regclass) NOT NULL,
    latitude text,
    longitude text,
    image text,
    name text
);


ALTER TABLE coordinates OWNER TO postgres;

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
    createdAt timestamp with time zone,
    name text
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
    userfrom integer NOT NULL,
    userto integer NOT NULL,
    content text,
    sentdate timestamp with time zone
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
    remember text,
    UNIQUE(remember, email, username)
);


ALTER TABLE users OWNER TO postgres;

CREATE SEQUENCE friend_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE friend_id_seq OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE friends (
    id integer DEFAULT nextval('friend_id_seq'::regclass) NOT NULL,
    follower integer NOT NULL,
    agent integer NOT NULL,
    created timestamp with time zone,
    PRIMARY KEY(id)
);


ALTER TABLE friends OWNER TO postgres;



CREATE SEQUENCE hunt_coord_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hunt_coord_id_seq OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE hunt_coord (
    id integer DEFAULT nextval('hunt_coord_id_seq'::regclass) NOT NULL,
    hunt_id integer NOT NULL,
    coord_id integer NOT NULL,
    PRIMARY KEY(id)
);


ALTER TABLE hunt_coord OWNER TO postgres;


CREATE SEQUENCE hunter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hunter_id_seq OWNER TO postgres;

--
-- Name: hunts; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE hunters (
    id integer DEFAULT nextval('hunter_id_seq'::regclass) NOT NULL,
    finished boolean,
    hunter_id integer NOT NULL,
    hunt_id integer NOT NULL,
    coord_id integer NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE hunters OWNER TO postgres;

--
-- Name: coord_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('coord_id_seq', 1, false);

SELECT pg_catalog.setval('friend_id_seq', 1, false);
--
-- Data for Name: coords; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY coordinates (id, latitude, longitude, image, name) FROM stdin;
\.


--
-- Name: hunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hunt_id_seq', 1, false);


--
-- Data for Name: hunts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hunts (id, creator, createdAt, name) FROM stdin;
\.

--
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('message_id_seq', 1, false);


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY messages (id, userfrom, userto, content, sentdate) FROM stdin;
\.


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, false);

SELECT pg_catalog.setval('hunt_coord_id_seq', 1, false);

SELECT pg_catalog.setval('hunter_id_seq', 1, false);
--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, username, password, email, avatar, name, gender, postalcode, phone, created,remember) FROM stdin;
\.

COPY friends (id, follower, agent,created) FROM stdin;
\.


COPY hunt_coord (id, hunt_id, coord_id) FROM stdin;
\.

COPY hunters (id, hunter_id, hunt_id, coord_id, finished) FROM stdin;
\.
--
-- Name: coords_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--
ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY coordinates
    ADD CONSTRAINT coords_pkey PRIMARY KEY (id);



--
-- Name: hunts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY hunts
    ADD CONSTRAINT hunts_pkey PRIMARY KEY (id);

ALTER TABLE ONLY hunts
    ADD CONSTRAINT hunts_fkey FOREIGN KEY (creator)
	REFERENCES users(id);



  ALTER TABLE ONLY hunters
      ADD CONSTRAINT hunts_fkey FOREIGN KEY (hunter_id)
          REFERENCES users(id);

  ALTER TABLE ONLY hunters
      ADD CONSTRAINT hunters_fkey2 FOREIGN KEY (hunt_id)
  	       REFERENCES hunts(id);

  ALTER TABLE ONLY hunters
      ADD CONSTRAINT hunters_fkey3 FOREIGN KEY (coord_id)
  	       REFERENCES coordinates(id);

--
-- Name: messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);

ALTER TABLE ONLY messages
            ADD CONSTRAINT messages_fkey FOREIGN KEY (userto)
              REFERENCES users(id);

ALTER TABLE ONLY messages
      ADD CONSTRAINT messages_fkey2 FOREIGN KEY (userfrom)
          REFERENCES users(id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--


ALTER TABLE ONLY friends
        ADD CONSTRAINT friends_fkey FOREIGN KEY (follower)
          REFERENCES users(id);

ALTER TABLE ONLY friends
      ADD CONSTRAINT friends_fkey2 FOREIGN KEY (agent)
          REFERENCES users(id);


ALTER TABLE ONLY hunt_coord
      ADD CONSTRAINT hunt_coord_fkey FOREIGN KEY (hunt_id)
          REFERENCES hunts(id);

ALTER TABLE ONLY hunt_coord
      ADD CONSTRAINT hunt_coord_fkey2 FOREIGN KEY (coord_id)
          REFERENCES coordinates(id);


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
