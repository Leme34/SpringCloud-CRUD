DROP TABLE IF EXISTS components;

CREATE TABLE IF NOT EXISTS components
(
    id         varchar(32) unsigned not null primary key,
    name       varchar(32),
    category   int,
    version    varchar(32),
    status     int,
    tenant_id  varchar(32),
    created_by varchar(32),
    updated_by varchar(32),
    created_at date,
    updated_at date,
    dr         int(1)
);

INSERT INTO components(id, name, category, version, status, tenant_id, created_by, updated_by, created_at, updated_at,
                       dr)
VALUES ('1', 'edas-config', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('2', 'sonar', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('3', 'RocketMQ', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('4', 'Zookeeper', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('5', 'TiDB', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('6', 'Redis', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('7', 'kafka', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('8', 'MongoDB', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('9', 'MySQL', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('10', '营销中心', 1, '1.0.0', 0, null, null, null, null, null, 0),
       ('11', 'ElasticSearch', 0, '1.0.0', 0, null, null, null, null, null, 0),
       ('12', 'RabbitMQ', 0, '1.0.0', 0, null, null, null, null, null, 0);
