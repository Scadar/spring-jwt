package com.example.springjwt.auth.model.payload;

import com.example.springjwt.auth.model.DeviceType;
import com.example.springjwt.auth.validation.annotation.NullOrNotBlank;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeviceInfo {

    @NotBlank(message = "Device id cannot be blank")
    @ApiModelProperty(value = "Device Id", required = true, dataType = "string", allowableValues = "Non empty string")
    private String deviceId;

    @NotNull(message = "Device type cannot be null")
    @ApiModelProperty(value = "Device type Android/iOS", required = true, dataType = "string", allowableValues =
            "DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS")
    private DeviceType deviceType;

    @NullOrNotBlank(message = "Device notification token can be null but not blank")
    @ApiModelProperty(value = "Device notification id", dataType = "string", allowableValues = "Non empty string")
    private String notificationToken;

}
