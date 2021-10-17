# Liferay-task-manager
Liferay Task manager para versión 7.4.1-ga2

## Onboarding

Para el entorno de desarrollo se necesitan los siguientes requisitos mínimos:

* JDK 8
* Maven 3.6

### Compilación del portal

La compilación del portal se lleva a cabo con Maven. Para inicializar el portal, acudir al directorio raiz y ejecutar el comando: ```mvn bundle-support:init```.

### Ejecución del portal

La ejecución del portal se lleva a cabo inicializando el servidor Tomcat. Una vez llevados a cabo los dos pasos anteriores, ya estaría todo listo. Se puede arrancar de las siguientes formas:

* Con las herramientas de desarrollo de Eclipse, agregando el servidor como Tomcat 9.
* Mediante linea de comandos: ```tomcat-X.X.X/bin/startup.(bat|sh) -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n```

### Inicialización base de datos

Por defecto durante la ejecución del portal se arranca una base de datos Hypersonic.
