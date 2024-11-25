# 🍋 Welcome to CitrusTrack! 🍋

## Hello, Agriculture Enthusiasts! 👋

Welcome to **CitrusTrack**, a comprehensive REST API designed to manage a citrus farm efficiently. **CitrusTrack** leverages modern technologies like **Jav 21, Spring Boot**, and **Hibernate** to provide a robust and scalable platform for handling fields, farms, and harvest details.

## 🚀 About CitrusTrack

**CitrusTrack** enables administrators to manage fields, farms, and harvest details through an intuitive API interface. With **CRUD** functionality for fields, farms, and harvest details, **CitrusTrack** ensures a seamless experience for managing agricultural content and user data.

## 📁 Project Structure

Here's an overview of the project structure for **CitrusTrack**:

- `config`: Contains configuration classes for **Spring Boot** and **Exceptions**.
- `controllers`: Handles HTTP requests for managing fields, farms, and harvest details.
- `dto`: Data Transfer Objects facilitating data transfer between layers.
- `entities`: Classes representing core entities: `Field`, `Farm`, `Harvest`, `HarvestDetail`, `Tree`, `Sale`,  and associated enums.
- `mappers`: Classes for mapping entities to DTOs and vice versa.
- `repositories`: Managing database queries.
- `services`: Business logic layer for managing fields, farms, and harvest details.
- `resources`: Contains configuration files such as `application.properties` and database setup scripts.

## 🧩 Key Features

- **Field Management**: Create, update, and delete fields.
- **Farm Management**: Add, update, and remove farms.
- **Harvest Detail Management**: Manage harvest details by adding, updating, and deleting them.
- **Secure Data Handling**: Ensures data integrity and security.
- **Unit Tests**: Using **JUnit** and **Mockito** to test business and data access components.

## 🌐 API Endpoints

Here are the key API endpoints for **CitrusTrack**:

- **Fields**:
    - `GET /api/v1/field`: Get all fields.
    - `GET /api/v1/field/{id}`: Get a field by ID.
    - `POST /api/v1/field`: Add a new field.
    - `PUT /api/v1/field`: Update an existing field.
    - `DELETE /api/v1/field/{id}`: Delete a field by ID.
    - `GET /api/v1/field/farm/{id}`: Get all fields by farm ID.

- **Farms**:
    - `GET /api/v1/farm`: Get all farms.
    - `GET /api/v1/farm/{id}`: Get a farm by ID.
    - `POST /api/v1/farm`: Add a new farm.
    - `PUT /api/v1/farm`: Update an existing farm.
    - `DELETE /api/v1/farm/{id}`: Delete a farm by ID.
    - `GET /api/v1/farm/search`: Search farms by name, location or area.

- **Harvests**:
    - `GET /api/v1/harvest`: Get all harvests.
    - `GET /api/v1/harvest/{id}`: Get a harvest by ID.
    - `POST /api/v1/harvest`: Add a new harvest.
    - `PUT /api/v1/harvest`: Update an existing harvest.
    - `DELETE /api/v1/harvest/{id}`: Delete a harvest by ID.
    
- **Harvest Details**:
    - `GET /api/v1/harvest-detail`: Get all harvest details.
    - `GET /api/v1/harvest-detail/{id}`: Get a harvest detail by ID.
    - `POST /api/v1/harvest-detail`: Add a new harvest detail.
    - `PUT /api/v1/harvest-detail`: Update an existing harvest detail.
    - `DELETE /api/v1/harvest-detail/{id}`: Delete a harvest detail by ID.

- **Trees**:
    - `GET /api/v1/tree`: Get all trees.
    - `GET /api/v1/tree/{id}`: Get a tree by ID.
    - `POST /api/v1/tree`: Add a new tree.
    - `PUT /api/v1/tree`: Update an existing tree.
    - `DELETE /api/v1/tree/{id}`: Delete a tree by ID.

- **Sales**:
    - `GET /api/v1/sale`: Get all sales.
    - `GET /api/v1/sale/{id}`: Get a sale by ID.
    - `POST /api/v1/sale`: Add a new sale.
    - `PUT /api/v1/sale`: Update an existing sale.
    - `DELETE /api/v1/sale/{id}`: Delete a sale by ID.


## 🎯 Project Objectives

- Develop a robust system for managing citrus farms.
- Implement CRUD operations for managing fields, farms, and harvest details.****
- Use **JUnit** and **Mockito** for unit testing.
- Maintain clear separation of concerns with an MVC architecture.

## 🌐 SWAGGER UI
- **Swagger UI** is a tool that visually presents the API documentation. It provides a user-friendly interface for exploring the API endpoints and their functionalities.
- To access the **Swagger UI** for **CitrusTrack**, run the application and navigate to `http://localhost:8085/swagger-ui/index.html` in your browser.

## 🧩 Postman Collection

- **Postman** is a popular API client that allows you to test API endpoints and monitor responses.
- To access the **Postman Collection** for **CitrusTrack**, click [here](https://swiftride.postman.co/workspace/My-Workspace~5068411f-ba84-490f-827d-09a1db076e70/collection/33286297-882e01b6-82c6-48e6-a38d-5ae29bfd17dd).

## 🛠️ How to Use CitrusTrack

### Prerequisites

Before running **CitrusTrack**, ensure you have the following installed:

- **Java 8** or later
- **Maven** for project build and dependency management
- **Spring Boot** for developing Restful APIs
- **PostgreSQL** or any other relational database
- **Postman** for testing API endpoints
- **Swagger UI** for API documentation

### Running the Application

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/zinebMachrouh/CitrusTrack.git
   cd CitrusTrack
   ```

2. Update the `application.properties` file in the `resources` directory with your database connection details.

3. Build and run the application using Maven:
   ```bash
   mvn clean install
   ```

## 🎉 Get Started with CitrusTrack Today!

For any questions, feedback, or suggestions, feel free to reach out to us. 📧