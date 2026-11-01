/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.population;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Open input files
        Scanner in1 = new Scanner(new File("worldpop.txt"));
        Scanner in2 = new Scanner(new File("worldarea.txt"));
        
        // Open output file
        PrintWriter out = new PrintWriter("world_pop_desity.txt");
        
        // Read lines from each file
        while (in1.hasNextLine() && in2.hasNextLine()) {
            CountryValue population = new CountryValue(in1.nextLine());
            CountryValue area = new CountryValue(in2.nextLine());
            
            // Compute and print the population density
            double density = 0;
            if (area.getValue() != 0) // Protect against division of 0
            {
                density = population.getValue() / area.getValue();
            }
            out.printf("%-40s%15.2f\n", population.getCountry(), density);
        }
        
        in1.close();
        in2.close();
        out.close();
    }
}
