package com.prooflift.login.Profile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewPasswordRequest {

    @NotNull
    String newPassword;
    String resetCode;

}
