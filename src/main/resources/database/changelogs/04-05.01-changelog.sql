create table "Level" (
    id serial primary key,
    name varchar not null
);

create table "Student" (
    id serial primary key,
    email varchar not null,
    password varchar not null,
    cp varchar not null,
    first_name varchar not null,
    last_name varchar not null,
    level_id integer not null,
    foreign key (level_id) references "Level"(id) on delete cascade
);

create table "Admin" (
    id serial primary key,
    email varchar not null,
    password varchar not null,
    ce varchar not null,
    first_name varchar not null,
    last_name varchar not null
);

create table "Activity" (
    id serial primary key,
    code varchar not null,
    name varchar not null,
    description text not null,
    level_id integer not null,
    foreign key (level_id) references "Level"(id) on delete cascade
);

create table "Choice" (
    id serial primary key,
    preference integer not null,
    date_submitted timestamp without time zone default now() not null,
    activity_id integer not null,
    student_id integer not null,
    foreign key (activity_id) references "Activity"(id) on delete cascade,
    foreign key (student_id) references "Student"(id) on delete cascade
);

create table "Attendance" (
    id serial primary key,
    finished boolean default false not null,
    session varchar not null,
    activity_id integer not null,
    student_id integer not null,
    foreign key (activity_id) references "Activity"(id) on delete cascade,
    foreign key (student_id) references "Student"(id) on delete cascade
);