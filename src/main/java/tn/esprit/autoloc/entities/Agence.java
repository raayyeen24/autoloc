package tn.esprit.autoloc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Agence {

    @JsonCreator
    public Agence() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    // Relation OneToMany vers Employe
    @OneToMany(mappedBy = "agence")
    @JsonIgnore
    private List<Employe> employes;

    // Relation OneToMany vers Vehicule
    @OneToMany(mappedBy = "agence")
    @JsonIgnore
    private List<Vehicule> vehicules;
}
