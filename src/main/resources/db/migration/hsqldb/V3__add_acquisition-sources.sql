create table acquisition_sources (
       id bigint not null,
        created_by varchar(255),
        created_on timestamp,
        updated_by varchar(255),
        updated_on timestamp,
        version integer,
        location varchar(255),
        name varchar(255),
        primary key (id)
    );

alter table acquisitions add column acquisition_source_id bigint;
