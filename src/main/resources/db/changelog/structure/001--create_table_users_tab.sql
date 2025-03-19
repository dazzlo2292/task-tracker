 create table if not exists users_tab (
    id varchar(36) PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    login VARCHAR(50) NOT NULL UNIQUE,
    role VARCHAR(50),
    block_fl VARCHAR(1) default 'N',
    created_at timestamp default now(),
    updated_at timestamp default now()
 );
