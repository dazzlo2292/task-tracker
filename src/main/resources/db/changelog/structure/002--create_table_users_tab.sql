 create table if not exists users_tab (
    id varchar(36) PRIMARY KEY DEFAULT nextval('users_id_seq'),
    name VARCHAR(50),
    login VARCHAR(50) NOT NULL UNIQUE,
    role VARCHAR(50) DEFAULT 'user',
    block_fl VARCHAR(1) default 'N',
    created_at timestamp default now(),
    updated_at timestamp default now()
 );
