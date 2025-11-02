package com.prooflift.login.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChangePasswordRequest {

    String oldPassword;
    String newPassword;

}
