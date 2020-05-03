CREATE TABLE "public"."idee"
(
   id int PRIMARY KEY NOT NULL,
   nom varchar(100) NOT NULL,
   description varchar(1000) DEFAULT NULL::character varying
)
;
CREATE UNIQUE INDEX idee_pkey ON "public"."idee"(id)
;

CREATE TABLE "public"."type_grappe"
(
   id int PRIMARY KEY NOT NULL,
   nom varchar(100) NOT NULL
)
;
CREATE UNIQUE INDEX type_grappe_pkey ON "public"."type_grappe"(id)
;


CREATE TABLE "public"."grappe"
(
   id int PRIMARY KEY NOT NULL,
   nom varchar(100) NOT NULL,
   description varchar(1000) DEFAULT NULL::character varying,
   grappe_id bigint,
   type_id bigint,
   ordre int DEFAULT 99
)
;
CREATE UNIQUE INDEX grappe_pkey ON "public"."grappe"(id)
;


CREATE TABLE "public"."asso_grappe"
(
   id int PRIMARY KEY NOT NULL,
   idee_id bigint NOT NULL,
   grappe_id bigint NOT NULL,
   type_id bigint
)
;
CREATE UNIQUE INDEX asso_grappe_pkey ON "public"."asso_grappe"(id)
;
CREATE TABLE "public"."asso_idee"
(
   id int PRIMARY KEY NOT NULL,
   liaison varchar(100) DEFAULT NULL::character varying,
   maitre_id bigint NOT NULL,
   esclave_id bigint NOT NULL,
   grappe_id bigint
)
;
CREATE UNIQUE INDEX asso_idee_pkey ON "public"."asso_idee"(id)
;

