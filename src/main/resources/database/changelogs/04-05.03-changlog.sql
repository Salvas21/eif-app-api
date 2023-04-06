alter table "Choice"
add column selected boolean default false;

alter table "Activity"
add column places integer default 0;