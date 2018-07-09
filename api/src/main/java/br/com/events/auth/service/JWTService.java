package br.com.events.auth.service;

import java.util.Optional;

public interface JWTService {

    String generate(String id);

    Optional<String> valid(String token);

}
