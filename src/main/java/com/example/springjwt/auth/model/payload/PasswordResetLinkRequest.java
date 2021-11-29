
package com.example.springjwt.auth.model.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Password reset link request", description = "The password reset link payload")
@Data
public class PasswordResetLinkRequest {

    @NotBlank(message = "Email cannot be blank")
    @ApiModelProperty(value = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

}
