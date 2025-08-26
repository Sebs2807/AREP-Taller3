# Proyecto MiniSpringBoot en java

Servidor HTTP en Java puro que muestra archivos estáticos y provee un servicio básico mediante un endpoint REST.

## 🏗️ Arquitectura del prototipo

El prototipo implementa un **servidor HTTP básico** en Java con la siguiente arquitectura:

- **Servidor HTTP (HttpServer.java)**  
  Maneja las conexiones entrantes mediante `ServerSocket` y procesa las peticiones de los clientes.

- **Módulo de servicios (/app)**  
  Expone endpoints simples (ejemplo: `/app/hello`) que responden en formato JSON.

- **Módulo de archivos estáticos (/www)**  
  Contiene los recursos que el servidor puede entregar directamente (HTML, CSS, JS, imágenes).  
  - `index.html` → Página principal  
  - `404.html` → Página de error cuando no se encuentra un recurso
    
---

## 🚀 Comenzando

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

### 📋 Prerrequisitos

Necesitas tener instalado:

- Java 8 o superior

- Maven

---

### 🔧 Instalación

Pasos para ejecutar el servidor en tu entorno local:

1. Clonar el repositorio

    git clone https://github.com/Sebs2807/AREP-Taller1.git

    cd AREP-Taller1
   
    cd arep

3. Compilar el proyecto
    
    mvn clean install

4. Ejecutar el servidor

    mvn exec:java

    java -cp target/classes eci.escuelaing.edu.co.MicroSpringBoot eci.escuelaing.edu.co.controllers.HelloController

El servidor quedará corriendo en el puerto **36000**


### ⚙️ Ejecución de pruebas

mvn test

### 📂 Estructura del proyecto

```text
│   pom.xml
│
├───src
│   ├───main
│   │   └───java
│   │       └───eci
│   │           └───escuelaing
│   │               └───edu
│   │                   └───co
│   │                           HttpServer.class
│   │                           HttpServer.java
│   │
│   └───test
│       └───java
│           └───eci
│               └───escuelaing
│                   └───edu
│                       └───co
│                               HttpServerTest.java
│
└───www
    │   404.html
    │   index.html
    │   script.js
    │   style.css
    │
    └───images
            cielo.jpg
            footer.png
```

### 📡 Endpoints disponibles

- http://localhost:36000/ (Sirve index.html desde la carpeta www).

- http://localhost:36000/app/hello?name=TuNombre (Devuelve un JSON con un saludo).
  
- http://localhost:36000/[archivo] (Sirve cualquier recurso dentro de www).

- http://localhost:36000/app/pi (Devuelve el valor de PI como String).

- http://localhost:36000/hello (Devuelve un String Hola Mundo).

- http://localhost:36000/helloQuery?name=TuNombre (Devuelve un String con un saludo).

### ✅ Comprobación de resultado esperado de los test
<img width="1211" height="445" alt="image" src="https://github.com/user-attachments/assets/494693c4-79f7-42b1-8c9e-f9b9687fc813" />

### 🛠️ Construido con

- Java - Lenguaje de programación

- Maven - Gestión de dependencias y ejecución

### 📝 Ejemplo de uso del framework
<img width="1398" height="1014" alt="code" src="https://github.com/user-attachments/assets/41c7e743-0701-4358-a7f4-95546fc1dfc8" />

### Ejemplos de uso con curl

```bash
curl http://localhost:36000/
```

```bash
curl http://localhost:36000/style.css

```

```bash
curl http://localhost:36000/app/hello?name=Sebastian
```

```bash
curl http://localhost:36000/noexiste.html
```
### ✒️ Autores

Sebastian Velasquez - Sebs2807
