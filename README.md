# Parranderos con MongoDB (Demo)

Este proyecto busca representar de manera muy simple una aplicacion RESTful que permita hacer transacciones tipo CRUD. La base de datos es creada con MongoDB Atlas, la cual maneja clusters que proveen el servicio a los usuarios, en este caso, los estudiantes.

## Contenido

1. [Requisitos](#requisitos)
2. [Conexion del proyecto a la base de datos](#conexion-del-proyecto-a-la-base-de-datos)
3. [Colecciones](#colecciones)
4. [Tecnologias usadas](#tecnologias-usadas)
5. [Colaboración](#colaboración)


## Requisitos

- Java 8 o 7
- IDE de su preferencia
- Usuario en MongoDB
- Base de datos MongoDB en Atlas
- Extensiones para java en caso de usar Visual Studio Code (recomendado) 

## Conexion del proyecto a la base de datos

En el archivo del proyecto ubicado en src/main/resources/application.yml, donde dice "uri" ingresar el enlace de conexion a la base de datos de mongo creada por el estudiante en Atlas. El uri de conexion debe lucir como el siguiente ejemplo: mongodb+srv://admin:mongo@cluster0.ejemplo.mongodb.net/

## Colecciones

Cuando se crea una base de datos en Atlas MongoDB por lo general se pide una coleccion base para comenzar, por ello, a continuacion adjuntamos las colecciones usadas en este proyecto para usar alguna de estas como coleccion base (puede seleccionar cualquiera). La base de datos MongoDB usada para este proyecto, tiene estas colecciones creadas:

- bebidas:
```json
{
  "_id": {
    "$oid": "6555905537bd8a4de1a8ca08"
  },
  "nombre": "Blue Label",
  "gradoAlcohol": 12,
  "_class": "com.example.demo.modelo.Bebida"
}
```

- bebidas_tipos:
```json
{
  "_id": {
    "$oid": "6553edd71af8a72f0d64d54c"
  },
  "nombre": "Cerveza",
  "Bebidas": [
    {
      "nombre": "Poker",
      "gradoAlcohol": 0.2
    }
  ],
  "_class": "com.example.demo.modelo.BebidaTipos"
}
```

- tipo_bebidas:
```json
{
  "_id": {
    "$oid": "655590ebc466e02e51a4f2e0"
  },
  "nombre": "Wisky",
  "bebidas": [
    {
      "$ref": "bebidas",
      "$id": {
        "$oid": "655590eac466e02e51a4f2df"
      }
    }
  ],
  "_class": "com.example.demo.modelo.TipoBebida"
}
```


## Tecnologias usadas
                     
Este proyecto fue desarrollado haciendo uso de Spring, particularmente Spring Boot para el backend, con entorno de ejecucion node.js y thymeleaf para la conexion de un frontend local con los endpoints definidos. Se recomienda hacer lectura de la documentacion de dichas tecnologias.

## Colaboración
Proyecto desarrollado por los monitores del curso Sistemas Transaccionales, especificamente, de las secciones 2 y 3. (2023-02)
- Version inicial (inicializacion de Spring, entidades iniciales y creacion de la base de datos MongoDB en Atlas): [Nathalia Quiroga, Laura Martinez]
- MVP Demo v0.1 (Front-end, Back-end, CRUD, implementacion patron MVC, modelos, controladores, templates, conexion, documentacion): [Juan Coronel @JuanCoronel70] 
- Revisión: ...

