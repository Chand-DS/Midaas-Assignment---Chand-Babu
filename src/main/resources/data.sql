INSERT INTO kitchen_resource
(id, resource_name, total_units, available_units)
VALUES
(1, 'OVEN', 2, 2);

INSERT INTO dish
(id, name, preparation_time_in_minutes, resource_id)
VALUES
(1, 'Pizza', 20, 1);

INSERT INTO dish
(id, name, preparation_time_in_minutes, resource_id)
VALUES
(2, 'Burger', 10, 1);