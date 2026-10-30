/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.quake;

import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Quake 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter a magnitude on the Richter scale: ");
        double magnitude = in.nextDouble();
        Earthquake quake = new Earthquake(magnitude);
        System.out.println(quake.getDescription());
    }
}
