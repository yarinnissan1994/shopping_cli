# Shopping CLI 💻

This project is a console-based application for shopping.

[![Java Version](https://img.shields.io/badge/Java-20-blue.svg)](https://www.java.com/en/download/)
[![MySQL](https://img.shields.io/badge/MySQL-latest-blue.svg)](https://www.mysql.com/)

## 📚 Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Installation](#installation)
4. [Running Tests](#running-tests)
5. [Contributions](#contributions)
6. [License](#license)

## <a name="features"></a>🛠️Features
- **User authentication:** Users can securely log in to the application using their credentials. This ensures that only authorized users can access the shopping features and perform actions.
- **Command-line interface (CLI) support:** The application provides a user-friendly command-line interface, allowing users to interact with the shopping features through user response pattern. This makes it easy to navigate the application, browse products, add items to the cart, and complete purchases, all from the command line.
- **Cart management:** Users can add products to their cart, remove items, update quantities, and view the contents of their cart. This allows for easy management and organization of selected items before proceeding to checkout.
- **Order history and account management:** Users have access to their order history, which includes details of past purchases. They can also manage their account settings, update personal information, and modify their preferences.

## <a name="technologies-used"></a>🛠️ Technologies Used
- Frontend: CMD, SQLPAD
- Backend: Java jdk20 Maven
- Spring-boot:
  - data jpa
  - hibernite
  - spring session
  - spring containers
- Database: MySQL
- Deployment: Docker, Github Actions
- Testing: 

## <a name="installation"></a>🔧 Installation
You can install and run the Shopping CLI either natively or through Docker. Please choose the most suitable method for you.

## 🖥️ Native Installation
1. Clone the repository: `git clone https://github.com/yarinnissan1994/shopping_cli_client.git`
2. Navigate to the project directory: `cd shopping_cli_client`
3. Restore the required dependencies: `mvn clean install`
4. Build the app: `mvn package`
5. Navigate to the execution directory: `cd target`
6. Start the application: `java -jar shopping_cli.jar`

## 🐳 Docker Installation
1. Make sure Docker is up and running on your machine.
2. Clone the repository: `git clone https://github.com/yarinnissan1994/shopping_cli_client.git`
3. Navigate to the project directory: `cd shopping_cli_client`
4. Run the Docker Compose command: `docker-compose up -d`
5. Run the Docker command: `docker exec -it [ContainerId] bash`
6. Start the application: `dotnet shopping_cli.dll`

## <a name="running-tests"></a>🧪 Running Tests
We have a comprehensive suite of unit tests implemented via "add libary". Ensure your installation is working as expected by running these tests.

## <a name="contributions"></a>👥 Contributions
As of now, we are not accepting contributions to this project.

## <a name="license"></a>📄 License
There is no specific license associated with this project.
