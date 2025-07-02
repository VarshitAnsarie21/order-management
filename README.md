# Order Management System

A Spring Boot application for managing customers, products, and orders, with reporting and RESTful APIs.

## Features

- **Customer Management**: Add, update, and fetch customers.
- **Product Management**: Add, update, and fetch products.
- **Order Management**: Place orders, validate stock, and fetch orders by customer.
- **Reporting**: Get total orders per customer and top 5 customers.

## Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 (test database)
- Maven

## API Endpoints
- Customer APIs (/api/customers)
 ```
- POST /api/customers — Add a new customer.
- PUT /api/customers/{id} — Update customer by ID.
- GET /api/customers — List all customers.
- GET /api/customers/{id} — Get customer by ID.
```
- Product APIs (/api/products)
 ```
- POST /api/products — Add a new product.
- PUT /api/products/{id} — Update product by ID.
- GET /api/products — List all products.
- GET /api/products/{id} — Get product by ID.
 ```
- Order APIs (/api/orders) 
 ```
- POST /api/orders — Place a new order.
- GET /api/orders/customer/{customerId} — Get orders by customer.
 ```

- Reporting APIs (/api/reports)
 ```
- GET /api/reports/total-orders — Total orders per customer.
- GET /api/reports/top-customers — Top 5 customers by order count.
 ```

## How to Run 
1. Clone the repository

2. Build:
``` 
mvn clean install 
```

3. Run:
```
 mvn spring-boot:run 
```

## Sample API Payloads

- Add Customer

```
{
"name": "Alice Smith",
"email": "alice.smith@example.com",
"phone": "9876543210"
}
```

- Add Customer
```
{
"name": "Laptop",
"price": 1200.50,
"stock": 15
}
```

- Place Order
```
{
"customerId": 1,
"products": [
   { "productId": 1, 
   "quantity": 2 }
   ]
}
```
- Replace IDs with actual values from your database.

<hr></hr>

## Exception Handling
- **ResourceNotFoundException**: Entity not found.
- **InsufficientStockException**: Not enough product stock.
- **GlobalExceptionHandler**: Maps exceptions to HTTP responses.
<hr></hr>

## Project Structure
- **controller** — REST API endpoints
- **service** — Business logic
- **repository** — Data access
- **entity** — JPA entities
- **dto** — Data transfer objects
- **exception** — Custom exceptions and handlers

## License
- This project is for educational/demo purposes.
