--drop table complaint;
--drop table customer;
CREATE TABLE  "CUSTOMER"
   (  "ID" integer NOT NULL ,
  "CODE" integer NOT NULL ,
  "FIRST_NAME" VARCHAR(100) NOT NULL ,
  "LAST_NAME" VARCHAR(100) NOT NULL ,
  "BIRTHDATE" DATE, 
  "DISCOUNT" integer,
  "IS_ACTIVE" integer NOT NULL ,
  "PARENT_CUSTOMER_ID" integer,
   CONSTRAINT "CUSTOMER_PK" PRIMARY KEY ("ID")
   )
;
insert into CUSTOMER values(1, 101,'Tooth','Fairy','0003-02-18',5,1,NULL);
insert into CUSTOMER values(2, 666, 'The','Devil','0666-06-06',80,1,NULL);
insert into CUSTOMER values(3, 888, 'Sandy','Claws','1888-08-08',30,1,NULL);
insert into CUSTOMER values(4, 757, 'North','Elf','1757-05-07',20,1,3);

CREATE TABLE  "COMPLAINT"
   (  "ID" integer NOT NULL ,
  "CUSTOMER_ID" integer NOT NULL ,
  "PERCENT_SOLVED" integer,
  "RESOLVED_DATE" DATE, 
  "REFOUND_SUM" NUMERIC(10,2), 
  "TITLE" VARCHAR(255),
   "COMMENTS" VARCHAR(1000),
   CONSTRAINT "COMPLAINT_PK" PRIMARY KEY ("ID") ,
   CONSTRAINT "FK_COMP_CUSTOMER" FOREIGN KEY ("CUSTOMER_ID")
    REFERENCES  "CUSTOMER" ("ID")
   )
;

Insert into COMPLAINT values (1, 1, 15, null, 155, 'Teeth quality decline',null);
Insert into COMPLAINT values (2, 1, 100, '2008-01-12', 544, 'False tooth returned',null);
Insert into COMPLAINT values (3, 2, 10, null, 1000000, 'Hell is freezing over',null);
Insert into COMPLAINT values (4, 2, 75, null, 10, 'Hitler complains of heat',null);
Insert into COMPLAINT values (5, 3, 85, null, 333, 'Naughty boy didn''t get coal',null);
Insert into COMPLAINT values (6, 4, 55, null, 50000, 'Sandy Claws refuses payment',null);