
package com.example.springjwt.auth.model.payload;

import com.example.springjwt.auth.validation.annotation.MatchPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@MatchPassword
@ApiModel(value = "Password reset Request", description = "The password reset request payload")
@Data
public class PasswordResetRequest {

    @NotBlank(message = "The email for which the password needs to be reset can not be empty")
    @ApiModelProperty(value = "The user email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotBlank(message = "New password cannot be blank")
    @ApiModelProperty(value = "New user password", required = true, allowableValues = "NonEmpty String")
    private String password;

    @NotBlank(message = "Confirm Password cannot be blank")
    @ApiModelProperty(value = "Must match the new user password. Else exception will be thrown", required = true,
            allowableValues = "NonEmpty String matching the password")
    private String confirmPassword;

    @NotBlank(message = "Password reset token for the specified email has to be supplied")
    @ApiModelProperty(value = "Reset token received in mail", required = true, allowableValues = "NonEmpty String")
    private String token;

}
