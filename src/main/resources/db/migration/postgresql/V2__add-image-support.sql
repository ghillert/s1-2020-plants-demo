create table IMAGES (
    id bigint not null,
    created_by varchar(255),
    created_on timestamp,
    updated_by varchar(255),
    updated_on timestamp,
    version integer,
    image oid,
    name varchar(255),
    plant_id bigint,
    primary key (id)
);

ALTER TABLE PLANTS
    ADD COLUMN plant_sign_missing boolean NOT NULL DEFAULT FALSE;
