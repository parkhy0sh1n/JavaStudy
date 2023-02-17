drop table contact_tbl;
create table contact_tbl (
    contact_no number not null,
    name       varchar2(20 byte) not null,
    tel        varchar2(20 byte),
    email      varchar2(99 byte),
    address    varchar2(99 byte)
);

alter table  contact_tbl add constraint pk_contact primary key(contact_no);

drop sequence contact_seq;
create sequence contact_seq nocache;