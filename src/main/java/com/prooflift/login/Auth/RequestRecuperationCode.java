package com.prooflift.login.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestRecuperationCode {
    @NotBlank(message = "El código no puede estar vacío")
    String code;

}
