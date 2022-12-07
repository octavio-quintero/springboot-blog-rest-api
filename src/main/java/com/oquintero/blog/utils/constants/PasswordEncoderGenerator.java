package com.oquintero.blog.utils.constants;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Password Octavio: "+ passwordEncoder.encode("password"));
        System.out.println("Password Admin: "+ passwordEncoder.encode("admin"));

    }
}
