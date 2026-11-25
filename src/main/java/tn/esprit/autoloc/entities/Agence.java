package tn.esprit.autoloc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;

    // Relation OneToMany vers Employe
    @OneToMany(mappedBy = "agence")
    @JsonIgnoreProperties("agence")
    private List<Employe> employes;

    // Relation OneToMany vers Vehicule
    @OneToMany(mappedBy = "agence")
    @JsonIgnoreProperties("agence")
    private List<Vehicule> vehicules;
}
