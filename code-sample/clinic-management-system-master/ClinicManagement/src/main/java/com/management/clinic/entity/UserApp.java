package com.management.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApp implements Serializable {

    private Long id;
    private String cardId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String avatar;
    private String username;
    private String password;
    private String gender;
    private String phoneNumber;
    private Boolean status;
    private Date createdStamp;
    private Date modifiedStamp;
    private List<RoleApp> roleApps = new ArrayList<>();
}
