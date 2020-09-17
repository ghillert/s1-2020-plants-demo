
create sequence hibernate_sequence start with 1 increment by 1;

create table accessions (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    accession_date timestamp,
    accession_identifier varchar(255),
    accession_material_type integer,
    accession_specimen_numbers integer,
    accession_status integer,
    acquisition_id bigint,
    garden_id bigint,
    taxon_id bigint,
    primary key (id)
);

create table acquisitions (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    comment varchar(255),
    date timestamp,
    location varchar(255),
    name varchar(255),
    primary key (id)
);

create table families (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    name varchar(255),
    primary key (id)
);

create table gardens (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    name varchar(255),
    primary key (id)
);

create table genera (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    intergeneric_hybrid_flag integer,
    version integer,
    name varchar(255),
    family_id bigint,
    primary key (id)
);

create table images (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    image blob(255),
    name varchar(255),
    plant_id bigint,
    primary key (id)
);

create table plants (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    accuracy integer,
    latitude numeric(19,2),
    location_name varchar(255),
    longitude numeric(19,2),
    reusable boolean not null,
    accession_id bigint,
    date_planted timestamp,
    primary key (id)
);

create table taxa (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    common_name varchar(255),
    cultivar varchar(255),
    cultivar_group varchar(255),
    hybrid_flag integer,
    infraspecific_epithet varchar(255),
    infraspecific_rank integer,
    species varchar(255),
    unknown_species_name boolean not null,
    genus_id bigint,
    primary key (id)
);

alter table accessions
   add constraint FK8hp3gt0h6eft0u0jt4evnfwat
   foreign key (acquisition_id)
   references acquisitions;

alter table accessions
   add constraint FKa7gvu6b84g0fhs7k0dqtefwta
   foreign key (garden_id)
   references gardens;

alter table accessions
   add constraint FKgm8g1ilxivsonl8ww6pq5qimd
   foreign key (taxon_id)
   references taxa;

alter table genera
   add constraint FKhmevha4pgk7egle36l1lf8yxy
   foreign key (family_id)
   references families;

alter table images
   add constraint FKfnup6eq7s5h0ko7tn36hd6d0m
   foreign key (plant_id)
   references plants;

alter table plants
   add constraint FKaly5igotn348ebdujctwatx6p
   foreign key (accession_id)
   references accessions;

alter table taxa
   add constraint FKqb6ltgql8awobdp0v2hwjtkft
   foreign key (genus_id)
   references genera;
