
package com.example.springjwt.auth.model.payload;

import com.example.springjwt.auth.validation.annotation.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Registration Request", description = "The registration request payload")
@Data
public class RegistrationRequest {

    @NullOrNotBlank(message = "Registration username can be null but not blank")
    @ApiModelProperty(value = "A valid username", allowableValues = "NonEmpty String")
    private String username;

    @NullOrNotBlank(message = "Registration email can be null but not blank")
    @ApiModelProperty(value = "A valid email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotNull(message = "Registration password cannot be null")
    @ApiModelProperty(value = "A valid password string", required = true, allowableValues = "NonEmpty String")
    private String password;

}
