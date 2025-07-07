package com.shop.cleaning.dev.infra.security;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shop.cleaning.dev.entities.Customer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenService {

    //Eu tô colocando isso, pq n to conseguindo passar a secret key pelo application.properties por algum motivo... Então vou deixar aqui, dps vocÊs alterem

    String secret = "mysecret";


    public String generateToken(Customer customer) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("com.shop.cleaning")
                    .withSubject(customer.getUserName())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

        } catch(JWTCreationException exception){
            throw new RuntimeException("ERROR WHILE AUTHENTICATING TOKEN");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("com.shop.cleaning")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plus(Duration.ofHours(24)).toInstant(ZoneOffset.of("-03:00"));
    }

}