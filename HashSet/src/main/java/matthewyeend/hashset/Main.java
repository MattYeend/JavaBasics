/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.hashset;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        Set<String> names = new HashSet<>(101); // 101 is a prime
        
        names.add("Harry");
        names.add("Sue");
        names.add("Nina");
        names.add("Susannah");
        names.add("Larry");
        names.add("Eve");
        names.add("Sarah");
        names.add("Adam");
        names.add("Tony");
        names.add("Katherine");
        names.add("Juliet");
        names.add("Romeo");
        names.remove("Romeo");
        names.remove("George");
        
        Iterator<String> iter = names.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
