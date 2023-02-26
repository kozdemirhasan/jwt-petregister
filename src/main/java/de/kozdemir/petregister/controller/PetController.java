package de.kozdemir.petregister.controller;

import de.kozdemir.petregister.model.Pet;
import de.kozdemir.petregister.repository.PetRepository;
import de.kozdemir.petregister.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/pets")
@RequiredArgsConstructor //alternative gegen @Autowired aber nicht ganz gleich
public class PetController {

    private final PetRepository petRepository; // bei final Feldern ist eine Constructor-Injection nötig
    private final JwtService jwtService;


    @GetMapping
    public List<Pet> index() {

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority("USER"));
            }

            @Override
            public String getPassword() {
                return "geheim#123";
            }

            @Override
            public String getUsername() {
                return "p.parker@schield.org";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        System.out.println(jwtService.generateToken(new HashMap<>(), userDetails));

        return petRepository.findAll();
    }

}
