package it.teammanager.futsalmontevarchi.dto;

public record LoginResponse(boolean success, String token, String message) {}