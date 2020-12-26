CREATE TABLE IF NOT EXISTS city(
   id int NOT NULL  PRIMARY KEY,
   name varchar(50) NOT NULL
);
insert into City (id, name) values
        (703448, 'Kyiv' ),
        (2643743, 'London'),
        (524901, 'Moscow');

CREATE TABLE IF NOT EXISTS temperature (
     id int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     dt int NOT NULL,
     temp real NOT NULL,
     city_id int NOT NULL,
     FOREIGN KEY (city_id) REFERENCES city(id)
);