Create Table "HTMLs"
(
"ID" serial primary key,
"Level" integer,
"URL" varchar(1000),
"Parent" integer,
"Checked" boolean
);



Create Table "HTMLs"
(
"ID" integer primary key,
"Level" integer,
"URL" varchar(1000),
"ParentLevel" integer,
"Downloaded" boolean,
"Extracted" boolean
);