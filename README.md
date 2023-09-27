# Instrucciones de compilación y ejecución en Windows

## Pre requisitos

* Tener instalado un JDK versión 1.8 o mayor ([Instrucciones](https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#A1097936))
* Tener configurada la variable de entorno del sistema JAVA_HOME apuntando correctamente al directorio del JDK instalado
* Tener instalado la última versión binaria de Apache Maven ([Instrucciones](https://maven.apache.org/install.html))
* Tener configurado en la variable de entorno del sistema Path al directorio **bin** de la instalación de Maven

## Instrucciones

1. Descargar ZIP del proyecto desde el repositorio y extraer los archivos
2. Abrir una nueva terminal de símbolo de sistema
3. Desde la terminal, ingresar al directorio raíz del proyecto
4. Ejecutar el comando: `mvn clean package`
5. Ejecutar el comando: `java -jar .\target\EjercicioProcesadorPlantillas-1.0-SNAPSHOT.jar`