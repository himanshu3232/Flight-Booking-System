package com.capgemini.gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * @param source jwt token
     * @return list of roles
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        //Get the object inside the jwt json with the name realm_access
        Map<String, Object> realmAccess = (Map<String, Object>) source
                .getClaims()
                .get("realm_access");

        if(realmAccess == null || realmAccess.isEmpty()){
            return new ArrayList<>();
        }

        return ((List<String>) realmAccess.get("roles"))
                .stream()
                //Add ROLE_ prefix to each role so Spring Security can use it
                .map(roleName -> "ROLE_" + roleName)
                //An implementation of GrantedAuthority
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
