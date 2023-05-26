package io.sanket.jwt.springsecurityjwt.controller;

import io.sanket.jwt.springsecurityjwt.model.AuthRequest ;
import io.sanket.jwt.springsecurityjwt.service.JwtUtil;
import io.sanket.jwt.springsecurityjwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class CheckController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetails;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getId(), authRequest.getPassword());
            authenticationManager.authenticate(authToken);
        }catch(BadCredentialsException bce){
            bce.printStackTrace();
        }

        final UserDetails userDetailsInstance = userDetails.loadUserByUsername(authRequest.getId());
        final String jwt = jwtUtil.generateToken(userDetailsInstance);
        return ResponseEntity.ok(jwt);
    }
}
