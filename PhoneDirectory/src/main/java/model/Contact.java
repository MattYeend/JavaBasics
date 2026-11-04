/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Immutable representation of a single phone directory entry.
 *
 * @param id          the database identifier, or {@code null} for a contact not yet saved
 * @param firstName   the contact's first name
 * @param lastName    the contact's last name
 * @param phoneNumber the contact's phone number
 * @param email       the contact's email address, or {@code null} if none was given
 *
 * @author matthewyeend
 */
public record Contact(Long id, String firstName, String lastName, String phoneNumber, String email) {
}