/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.basics;

import java.util.Objects;

/**
 *
 * @author matthewyeend
 */
public record Customer(String name, String email) {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_EMAIL_LENGTH = 254;

    public Customer {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(email, "email must not be null");

        name = name.strip();
        email = email.strip();

        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be 1 to " + MAX_NAME_LENGTH + " characters");
        }
        if (email.length() > MAX_EMAIL_LENGTH || !email.contains("@")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}
