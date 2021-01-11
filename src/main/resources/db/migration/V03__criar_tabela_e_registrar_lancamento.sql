CREATE TABLE entry (
    id SERIAL PRIMARY KEY,
    description varchar NOT NULL,
    due_date DATE NOT NULL,
    payment_date date, 
    value decimal(10,2) NOT NULL,
    note varchar(100),
    entry_type varchar NOT NULL,
    category_id int NOT NULL,
    person_id int NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);

INSERT INTO entry (description, due_date, value, entry_type, category_id, person_id) 
       VALUES('Despesas Teste', '2021-01-25', 150.20, 'EXPENSE', 1,1),
             ('Despesas Teste 2', '2021-01-22', 150.20, 'EXPENSE', 2,1),
             ('Receita Teste', '2021-01-22', 1500.20, 'INCOME', 2,1),
             ('Receita Teste 2', '2021-01-22', 2150.20, 'INCOME', 2,1);