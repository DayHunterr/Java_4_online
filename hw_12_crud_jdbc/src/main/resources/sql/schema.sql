create table cars
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    car_brand varchar(255) null,
    car_owner_name varchar(255) null,
    car_serial_number varchar(255) null
);
create table garages
(
    id bigint auto_increment primary key,
    created datetime(6) null,
    updated datetime(6) null,
    address varchar(255) null
);
create table cars_in_garages
(
    car_id bigint not null,
    garage_id bigint not null,
    primary key (car_id, garage_id),
    foreign key (car_id) references cars(id),
    foreign key (garage_id) references garages(id)
);
