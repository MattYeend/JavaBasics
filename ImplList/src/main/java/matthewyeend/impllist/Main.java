/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.impllist;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        ListIterator it = list.listIterator();

        it.add("A");
        it.add("B");
        it.add("C");

        it = list.listIterator();
        it.next();            // A
        it.set("A2");
        it.next();            // B
        it.remove();

        try {
            it.set("X");      // should now throw
        } catch (IllegalStateException e) {
            System.out.println("set after remove correctly rejected");
        }

        it = list.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next()); // A2, C
        }
    }
}
