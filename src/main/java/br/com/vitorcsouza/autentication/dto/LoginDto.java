package br.com.vitorcsouza.autentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private Long id;
    @NotBlank
    private String usuario;
    @NotBlank
    private String senha;
}
