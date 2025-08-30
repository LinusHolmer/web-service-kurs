package com.linusholmer.lektion_4.model;

public record CustomUser(
        String username,
        String password,
        boolean accountEnabled,
        int id
) {


}
