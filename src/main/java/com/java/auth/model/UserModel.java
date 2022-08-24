
package com.java.auth.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 30)
    private String nome;

    @Column(nullable = false)
    @Size(max = 30)
    private String sobrenome;

    @Column(unique = true, nullable = false)
    @Email(message = "Tipo de email incompatível")
    private String email;

    @Column(nullable = false)
    private String senha;

}

