package com.SQS_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateStudent {

    private String fname;
    private String lname;
    private String email;
    private String password;
}
