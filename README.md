# Api Carrito

Descargar el Proyecto y ejecutar el comando para inicializar la aplicacion

./mvnw spring-boot:run

El servicio queda expuesto en el endpoint: http://localhost:8081/api/store/order

y espera un Json de entrada como este

 {
  "id": 10,
  "productName": "Manzanas",
  "totalAmtt": 10000,
  "paymentMethod": "PSE",
  "quantity": 7,
  "shipDate": "2025-08-14T20:49:56.445Z",
  "complete": true
} 

El Json de salida seria como 

{
    "code": "200",
    "message": "Pago validado"
}

Si el proyecto se ejecuta solo, se debe desarcar el archivo https://github.com/idgualtero/Taller1/blob/main/Taller2/Diplomado.postman_collection.json montarlo en un Postman para que se active el mockque esta configurado (en este mismo proyecto esta para enviar la peticion a esta API), si quiere conectar con la siguiente API se debe descargar el proyecto de:

y modificar la linea 42 del ApiController.java para cambiar la url de consumo de la segunda API.