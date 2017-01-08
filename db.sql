create database bekti;use bekti;
create table guru(
kode varchar(20)primary key,
nama varchar(40)not null,
alamat text not null,
masuk date not null
);create table akutansi(
kode varchar(20)primary key,
tgl date not null,
hal text,
ket text not null,
duwik bigint not null,
tipe varchar(6)not null
);create table gaji(
guru varchar(20)not null,
trans varchar(20)not null,
bulan varchar(20)not null
);alter table gaji add foreign key(guru)references guru(kode)on update cascade on delete cascade;
alter table gaji add foreign key(trans)references akutansi(kode)on update cascade on delete cascade;
