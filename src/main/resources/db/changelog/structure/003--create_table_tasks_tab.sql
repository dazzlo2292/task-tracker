 create table if not exists tasks_tab (
    id varchar(36) PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(1024),
    assignee VARCHAR(50) NOT NULL REFERENCES users_tab (id),
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    due_date timestamp,
    created_at timestamp default now(),
    updated_at timestamp default now()
 );
