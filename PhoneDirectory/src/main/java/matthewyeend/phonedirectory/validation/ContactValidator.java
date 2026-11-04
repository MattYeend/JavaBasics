/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.validation;

import java.util.regex.Pattern;
import matthewyeend.phonedirectory.model.Contact;

/**
 * Allow list validation applied before any data reaches the database layer.
 *
 * @author matthewyeend
 */
public final class ContactValidator {

    private static final Pattern NAME = Pattern.compile("^\\p{L}[\\p{L} '\\-]{0,49}$");
    private static final Pattern PHONE = Pattern.compile("^\\+?[0-9][0-9 \\-]{5,17}[0-9]$");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$");
    private static final int EMAIL_MAX_LENGTH = 254;

    /**
     * Prevents instantiation, as this is a utility class.
     */
    private ContactValidator() {
    }

    /**
     * Validates and normalises the supplied contact details.
     *
     * @param id        the contact identifier, or {@code null} for a new contact
     * @param firstName the first name to validate
     * @param lastName  the last name to validate
     * @param phone     the phone number to validate
     * @param email     the optional email address to validate
     * @return a validated, white space trimmed {@link Contact}
     * @throws ValidationException if any field fails validation
     */
    public static Contact validate(Long id, String firstName, String lastName, String phone, String email)
            throws ValidationException {
        String first = strip(firstName);
        String last = strip(lastName);
        String number = strip(phone);
        String mail = strip(email);

        if (!NAME.matcher(first).matches()) {
            throw new ValidationException("First name must be 1 to 50 letters, spaces, apostrophes or hyphens.");
        }
        if (!NAME.matcher(last).matches()) {
            throw new ValidationException("Last name must be 1 to 50 letters, spaces, apostrophes or hyphens.");
        }
        if (!PHONE.matcher(number).matches()) {
            throw new ValidationException("Phone number must be 7 to 20 digits, optionally starting with + and containing spaces or hyphens.");
        }
        if (!mail.isEmpty() && (mail.length() > EMAIL_MAX_LENGTH || !EMAIL.matcher(mail).matches())) {
            throw new ValidationException("Email address is not valid.");
        }
        return new Contact(id, first, last, number, mail.isEmpty() ? null : mail);
    }

    /**
     * Trims a value, treating {@code null} as an empty string.
     *
     * @param value the value to trim
     * @return the trimmed value, never {@code null}
     */
    private static String strip(String value) {
        return value == null ? "" : value.strip();
    }
}