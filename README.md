# product-order

This project is a web application designed to manage products, orders (pedidos), and suppliers (fornecedores). It features a Spring Boot backend providing RESTful APIs and an Angular.js frontend for user interaction.

## Stack

*   **Backend**: Java 11, Spring Boot (Web, Data JPA), H2 Database, Maven.
*   **Frontend**: Angular.js.

## How to Run

To get this application up and running on your local machine, follow these steps:

### Prerequisites

*   Java Development Kit (JDK) 11 or higher
*   Apache Maven 3.6.0 or higher

### Steps

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd product-order
    ```
    *(Note: Replace `<repository-url>` with the actual URL of your repository.)*

2.  **Build the application**:
    Navigate to the project's root directory and execute the Maven build command:
    ```bash
    mvn clean install
    ```

3.  **Run the application**:
    You can run the Spring Boot application using Maven:
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, after building, you can run the generated JAR file:
    ```bash
    java -jar target/product-order-0.0.1-SNAPSHOT.jar
    ```

4.  **Access the application**:
    Once the application starts, it will be accessible in your web browser, typically at:
    ```
    http://localhost:8080
    ```