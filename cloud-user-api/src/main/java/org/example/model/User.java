package org.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;

    private String userId;

    private String username;

    private String password;

    private String roleId;

}
