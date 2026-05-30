# 🌐 Microservicios con Spring Boot WebFlux

## 📌 Digrama de alto nivel
<img width="981" height="772" alt="Image" src="https://github.com/user-attachments/assets/66284f9d-8396-4bae-9766-de5715c3f59b" />

## 📌 Requisitos
- **Java 25**
- **Gradle 9.3.0**
- **Spring Boot 3.5.14**
- **Spring Cloud 2025.0.0**

## ⚙️ Módulos incluidos
Este proyecto está compuesto por varios subproyectos:

| Módulo               | Descripción |
|----------------------|-------------|
| `ms-client`          | Servicio de clientes |
| `ms-product`         | Servicio de productos |
| `ms-bff`             | Backend For Frontend (API Composite) |
| `ms-discovery-server`| Eureka Server para descubrimiento de servicios |
| `ms-config-server`   | Configuración centralizada con Spring Cloud Config |
| `ms-api-gateway`     | Puerta de entrada con Spring Cloud Gateway |

## 📂 Estructura del proyecto

microservicios-webflux/

 ├── ms-client/              
 ├── ms-product/             
 ├── ms-bff/                 
 ├── ms-discovery-server/    
 ├── ms-config-server/       
 ├── ms-api-gateway/         
 └── build.gradle            

## ▶️ Cómo ejecutar

### 1. Clonar el repositorio
Primero descarga el proyecto en tu máquina local:
```bash
git clone https://github.com/usuario/microservicios-webflux.git
cd microservicios-webflux
```

### 2. Levantar el Config Server
✅ms-config-server


### 3. Levantar el Discovery Server (Eureka)
✅ms-discovery-server

### 4. Levantar el Clientes, Products, BFF, Api-Gateway
✅ms-client              
✅ms-product            
✅ms-bff                
✅ms-api-gateway/    



