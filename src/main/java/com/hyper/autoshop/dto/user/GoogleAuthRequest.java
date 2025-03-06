package com.hyper.autoshop.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GoogleAuthRequest {

    private String username;
    private String email;
    private String avatar;
    private Date created_date;
    private String authentication_method;
}
