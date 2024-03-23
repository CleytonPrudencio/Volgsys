package com.volgsys.api.Controller;

import com.volgsys.api.AuthenticateApi;
import com.volgsys.api.SwaggerConfig.JwtTokenUtil;
import com.volgsys.api.model.dto.JwtResponse;
import com.volgsys.api.service.JwtUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"Authentication"}, value = "Controller", description = "Login")
public class JwtAuthenticationController implements AuthenticateApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<ResponseEntity<String>> createAuthenticationToken(String user, String senha, ServerWebExchange exchange) {
        return Mono.fromCallable(() -> {
            //authenticate(user, senha);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user);
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token).getToken());
        }).onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().body("Invalid credentials")));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
