# Definiciones de comunicación

El sistema propuesto se desarrollará utilizando Java como lenguaje de programación y MySQL como motor de base de datos.

La comunicación entre la aplicación y la base de datos se realizará mediante JDBC, que permite conectar una aplicación Java con una base de datos relacional y ejecutar sentencias SQL.

En una primera versión, el sistema funcionará en una computadora local o dentro de una red interna de la institución.

La aplicación Java actuará como cliente y la base de datos MySQL funcionará como repositorio central de la información.

La conexión se realizará utilizando el protocolo TCP/IP, con el puerto predeterminado de MySQL, habitualmente el 3306.

La infraestructura mínima necesaria será:

- Una computadora con sistema operativo Windows.
- MySQL instalado.
- Una IDE de desarrollo Java.
- Conector JDBC para MySQL.
- Usuario y contraseña para acceder a la base de datos.

Como medida de seguridad, el acceso a la base de datos deberá estar restringido mediante credenciales. Además, se recomienda limitar los permisos de los usuarios para evitar accesos no autorizados.