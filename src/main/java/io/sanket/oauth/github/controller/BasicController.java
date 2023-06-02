package io.sanket.oauth.github.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

@RestController
public class BasicController {

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public Map<String,Object> processLogin(@AuthenticationPrincipal OAuth2User principal){
        System.out.println(principal.getAttributes());
       return Collections.singletonMap("name", principal.getAttribute("login"));
    }
}
