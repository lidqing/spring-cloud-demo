package org.example.model;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String userId;

    private String username;

    private String password;

    private String roleId;

}
