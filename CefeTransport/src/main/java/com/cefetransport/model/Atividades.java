package com.cefetransport.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_atividades_ct")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atividades {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtividade;

    @OneToOne(cascade = CascadeType.PERSIST)
    // @NotBlank
    private Funcionario funcionario;

    @OneToOne(cascade = CascadeType.PERSIST)
    // @NotBlank
    private Modal modal;

    // @NotBlank
    private String atividade;

    @Setter(AccessLevel.NONE)
    private String dataHora;

    @PrePersist
    public void prePersist() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dataHora = now.format(formatter);

    }

}