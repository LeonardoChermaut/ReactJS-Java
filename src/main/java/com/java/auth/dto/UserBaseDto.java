package com.java.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserBaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 20, message = "Nome inválido.")
    protected String nome;

    @NotNull
    @Size(min = 3, max = 30, message = "Sobrenome inválido.")
    protected String sobrenome;

    @NotNull
    @Email(message = "Email inválido")
    protected String email;

    @NotNull
    private String senha;
}