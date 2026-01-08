# MICROSERVICES

A Spring Boot microservices project.

## Modules

- **EMPLOYEE** - Employee management service

## Tech Stack

- Java
- Spring Boot
- Maven
- MySQL

## Setup

1. Clone the repository:
```bash
git clone https://github.com/Ankitkumar341/MICROSERVICES.git
```

2. Configure database:
   - Copy `applicationDummy.properties` to `application.properties`
   - Update database credentials in `application.properties`

3. Build and run:
```bash
cd EMPLOYEE
mvn clean install
mvn spring-boot:run
```

## Configuration

Application properties are managed via:
- `application.properties` - Actual configuration (git-ignored for security)
- `applicationDummy.properties` - Template file with dummy values

## Notes

Never commit `application.properties` to version control. Use `applicationDummy.properties` as a template.
