Microservicios PasteleriaMilSabores
Este proyecto busca implementar una arquitectura de microservicios deonde cada microservicio cumplira con su funcion especifica para la e-commerce de PasteleriaMilSabores

Evaluación Parcial 2 JVY0101 

Grupo 10 :
Agustin Caceres
Matias Turra


Microservicios : 

microservicio-usuario
Gestion de usuarios y tipos de usuario

carro-microservicio
operaciones del carrito de compra

core-microservicio
administracion de productos y categorias

Instalacion

1.-Clonacion de reposotitorio : git clone https://github.com/agustilin/Equipo10_Caceres-Turra/tree/features
2.-Importar los proyectos : importar cada carpeta como proyectos Maven existentes
3.-Configuracion de la base de datos: activar y modificar MySQL dentro de application.properties
jdbc:mysql://localhost:3306/[db]
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
server.port=8180  
3.-Ejecutar: mnv clean install
mvn spring-boot:run

EndPoints principales : 
MicroUsuario:
GET	/usuarios	Listar usuarios
GET	/usuarios/{id}	Obtener usuario por ID
POST	/usuarios	Crear usuario
PUT	/usuarios/{id}	Actualizar usuario
DELETE	/usuarios/{id}	Eliminar usuario

MicroCore:
GET	/categorias	Listar categorías
GET	/productos	Listar productos
POST	/productos	Crear producto
PUT	/productos/{id}	Actualizar producto
DELETE	/productos/{id}	Eliminar producto

MicroCarrito : 
GET	/api/carrito	Listar carritos
GET	/api/carrito/{id}	Ver carrito
POST	/api/carrito	Crear carrito
PUT	/api/carrito/{id}	Modificar carrito
DELETE	/api/carrito/{id}	Eliminar carrito



Tecnologias necesarias : 
java 17 
maven
MySQL
Maven
Postman
Github
JPA
Spring boot
