/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.priorityqueue;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        MinHeap q = new MinHeap();
        q.add(new WorkOrder(3, "Shampoo carpets"));
        q.add(new WorkOrder(7, "Empty bins"));
        q.add(new WorkOrder(8, "Water plants"));
        q.add(new WorkOrder(10, "Remove pencil sharpener shavings"));
        q.add(new WorkOrder(6, "Replace light bulb"));
        q.add(new WorkOrder(1, "Fix broken sink"));
        q.add(new WorkOrder(9, "Clean coffee maker"));
        q.add(new WorkOrder(2, "Order cleaning supplies"));
    }
}
