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
public class EmailLoginRequest {
    private String email;
    private String password;
    private Date created_date;
}
