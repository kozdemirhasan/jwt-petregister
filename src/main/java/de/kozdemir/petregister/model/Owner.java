package de.kozdemir.petregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    @JsonIgnore //
    private String password;

    // Alles was mit Owner passiert, wird weiter an dazugehörige Pet-Entities weitergegeben
    // orphanRemoval : beim löschen des Owners werden auch Pet-Datensätze aus der DB gelöscht
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() { // Anmeldename
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // ist der Account NICHT
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // ist der Account NICHT gesperrt?
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // sind die Zugangsdaten noch aktuell?
    }

    @Override
    public boolean isEnabled() {
        return true; // ist der Account aktiv?
    }


}
