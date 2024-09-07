

# Coderhack Crio Week 1 Assignment

### Problem Statement
Develop a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.
Problem Description
While coding platforms usually host multiple contests while maintaining numerous leaderboards, this assignment requires you to design a service for managing the leaderboard of a specific contest. Assume the platform has only one contest with a single leaderboard. The platform also gives virtual awards to the users called Badges based on their score.

### Solution

Build a Spring Application by completing all requirements mention in problem statement

## How to Run the application

* Clone the git repository
* Make sure you have docker installed or run mongoDb server locally at port 27017 check the application.yaml file for Db config
* if using docker run **docker compose up** inside project directory
* run the server **./mvnw spring-boot:run**

## Test the server 
* There is test already define in app you can just run and check
* POST API [link](https://elements.getpostman.com/redirect?entityId=33264336-068c521b-e6df-4e3f-bd37-72e89e8fb110&entityType=collection)
* With Swagger {base_url}/swagger-ui/index.html



