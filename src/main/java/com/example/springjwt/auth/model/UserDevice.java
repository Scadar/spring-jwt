
package com.example.springjwt.auth.model;

import com.example.springjwt.auth.model.audit.DateAudit;
import com.example.springjwt.auth.model.token.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "USER_DEVICE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDevice extends DateAudit {

    @Id
    @Column(name = "USER_DEVICE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_device_seq")
    @SequenceGenerator(name = "user_device_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "DEVICE_TYPE")
    @Enumerated(value = EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "NOTIFICATION_TOKEN")
    private String notificationToken;

    @Column(name = "DEVICE_ID", nullable = false)
    private String deviceId;

    @OneToOne(optional = false, mappedBy = "userDevice")
    private RefreshToken refreshToken;

    @Column(name = "IS_REFRESH_ACTIVE")
    private Boolean isRefreshActive;

}
