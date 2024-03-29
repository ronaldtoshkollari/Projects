toc.dat                                                                                             0000600 0004000 0002000 00000005370 13665715055 0014461 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP           (                x           Movies    12.2    12.2     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                    0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                    0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                    1262    60765    Movies    DATABASE     �   CREATE DATABASE "Movies" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE "Movies";
                postgres    false         �            1259    60847 	   bookmarks    TABLE     T   CREATE TABLE public.bookmarks (
    email text NOT NULL,
    movie text NOT NULL
);
    DROP TABLE public.bookmarks;
       public         heap    postgres    false         �            1259    60839    users    TABLE     �   CREATE TABLE public.users (
    email text NOT NULL,
    password text,
    username character varying(100),
    name character varying(100),
    surname character varying(100)
);
    DROP TABLE public.users;
       public         heap    postgres    false                   0    60847 	   bookmarks 
   TABLE DATA           1   COPY public.bookmarks (email, movie) FROM stdin;
    public          postgres    false    203       2823.dat           0    60839    users 
   TABLE DATA           I   COPY public.users (email, password, username, name, surname) FROM stdin;
    public          postgres    false    202       2822.dat �
           2606    60854    bookmarks bookmars_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmars_pkey PRIMARY KEY (email, movie);
 A   ALTER TABLE ONLY public.bookmarks DROP CONSTRAINT bookmars_pkey;
       public            postgres    false    203    203         �
           2606    60846    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    202         �
           2606    60855    bookmarks bookmars_email_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmars_email_fkey FOREIGN KEY (email) REFERENCES public.users(email);
 G   ALTER TABLE ONLY public.bookmarks DROP CONSTRAINT bookmars_email_fkey;
       public          postgres    false    2692    202    203                                                                                                                                                                                                                                                                                2823.dat                                                                                            0000600 0004000 0002000 00000001637 13665715055 0014274 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        dimitris@hotmail.com	The Godfather
dimitris@hotmail.com	Shark
dimitris@hotmail.com	Mark Zuckerberg: Inside Facebook
dimitris@hotmail.com	WALL·E
dimitris@hotmail.com	Master
dimitris@hotmail.com	Home Alone
dimitris@hotmail.com	The Dictator
dimitris@hotmail.com	Aladin
dimitris@hotmail.com	Gold
dimitris@hotmail.com	The Mummy
dimitris@hotmail.com	The Story of Maths
dimitris@hotmail.com	Green Book
a@a.a	WALL·E
a@a.a	The Hangover
a@a.a	Snowden
a@a.a	Power Rangers
a@a.a	Q
a@a.a	21
a@a.a	Kung Fu Panda
a@a.a	Star Wars: Episode IV - A New Hope
a@a.a	Inter Star Wars 2. The Last Jehi
a@a.a	The Green Mile
dimitris@hotmail.com	Star Wars: Episode VIII - The Last Jedi
dimitris@hotmail.com	Algorithms
dimitris@hotmail.com	Google and the World Brain
lol@lol.lol	Happy Feet
a@a.a	SpongeBob SquarePants
roni@hotmail.com	Batman
roni@hotmail.com	The Avengers
roni@hotmail.com	The Mask
roni@hotmail.com	The Green Mile
a@a.a	Happy Feet
\.


                                                                                                 2822.dat                                                                                            0000600 0004000 0002000 00000000263 13665715055 0014265 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        dimitris@hotmail.com	bek	dimitris	Dimitris	Bekiarhs
a@a.a	a	a	a	a
lol@lol.lol	lol	lol	lol	lol
error@error.error	error	error	error	error
roni@hotmail.com	roni	roni	Roni	Tosko
\.


                                                                                                                                                                                                                                                                                                                                             restore.sql                                                                                         0000600 0004000 0002000 00000005522 13665715055 0015405 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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

DROP DATABASE "Movies";
--
-- Name: Movies; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Movies" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE "Movies" OWNER TO postgres;

\connect "Movies"

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
-- Name: bookmarks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookmarks (
    email text NOT NULL,
    movie text NOT NULL
);


ALTER TABLE public.bookmarks OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    email text NOT NULL,
    password text,
    username character varying(100),
    name character varying(100),
    surname character varying(100)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: bookmarks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookmarks (email, movie) FROM stdin;
\.
COPY public.bookmarks (email, movie) FROM '$$PATH$$/2823.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (email, password, username, name, surname) FROM stdin;
\.
COPY public.users (email, password, username, name, surname) FROM '$$PATH$$/2822.dat';

--
-- Name: bookmarks bookmars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmars_pkey PRIMARY KEY (email, movie);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);


--
-- Name: bookmarks bookmars_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookmarks
    ADD CONSTRAINT bookmars_email_fkey FOREIGN KEY (email) REFERENCES public.users(email);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              