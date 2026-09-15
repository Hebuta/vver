# VVER-1000 Simulator

Simulation nuclear reactor VVER-1000 in java + Spring Boot.

## Stack

- Java 17+
- Spring Boot (Web, JPA)
- Lombok

## What already exists

REST API: '/api/vver/data' (Сollecting data)
REST API: '/api/vver/az5' (Emergency protection)
REST API: '/api/vver/rods' (Rods control)
Service with logic 'VverCoreService'
Model 'VverState' with JPA
