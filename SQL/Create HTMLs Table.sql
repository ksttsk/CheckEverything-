Create Table "HTMLs"
(
"ID" serial primary key,
"Level" integer,
"URL" varchar(1000),
"Parent" integer,
"Checked" boolean
);