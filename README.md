# Tech Challenge

This project was generated with Spring Boot [Spring Initializr](https://start.spring.io/) version 2.7.1

## Prerequisites

- JDK version >= 11
- Maven

## Project Structure

This project currently has three RESTful APIs, declared in the Airport Controller.

- Get all airports
- Get Standard Instrument Departure Top 2 Waypoints with the airport ICAO
- Get Standard Terminal Arrival Route Top 2 Waypoints with the airport ICAO

The data provided to the contoller are retrieved from calling the OPEN ATMS API in the Airport Service. Then, with the data, the controller would call the Airport Service again for the calculation of the top waypoints and return the result as the response.

## Configuration

The OPEN ATMS API configuration is declared in the <em>application.properties</em>

- `ATMS_URL`
- `ATMS_HEADER_KEY`
- `ATMS_HEADER_VALUE`

The values are saved in GitHub Repository Secrets and will be replaced by GitHub Actions workflow when it is deploying to Google Kubernetes Engine.

### CI/CD Pipeline

The general pipeline workflow is as follows:

- Build with Maven
- Login to Docker Hub
- Build and push container image to docker hub repository
- Replace the OPEN ATMS API configuration values in k8s <em>deploy.yaml</em> from GitHub Actions Secrets
- Apply to image and deploy to Google Kubernetes Engine