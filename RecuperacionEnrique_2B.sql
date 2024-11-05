create table tbUsuario(
ID nvarchar2(36) primary key,
Nombre nvarchar2(100) not null,
Apellidos nvarchar2(100) not null,
Correo nvarchar2(100) not null,
Contrasena nvarchar2(64) not null,
Edad int not null
);

create table tbTaller(
ID nvarchar2(36) primary key,
Nombre nvarchar2(100) not null,
Telefono nvarchar2(100) not null,
Marca nvarchar2(100) not null,
Modelo nvarchar2(100) not null,
Ano nvarchar2(100) not null,
Problema nvarchar2(100) not null,
Estado nvarchar2(100) not null
);

select * from tbUsuario;
select * from tbTaller;
commit







