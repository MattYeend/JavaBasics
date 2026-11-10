/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.priorityqueue;

/**
 *
 * @author matthewyeend
 */
public class WorkOrder implements Comparable {
    private final int priority;
    private final String description;
    
    /**
     * Constructs a work order with a given priority and description.
     * @param aPriority the priority of this work order
     * @param aDescription the description of this work order
     */
    public WorkOrder(int aPriority, String aDescription) {
        priority = aPriority;
        description = aDescription;
    }
    
    /**
     * Returns a string representation of this work order, containing its
     * priority and description.
     *
     * @return a string in the form
     *         {@code "Priority = <priority>, description = <description>"}
     */
    @Override
    public String toString() {
        return "Priority = " + priority + ", description = " + description;
    }
    
    /**
     * Compares this work order with another work order by priority. A work
     * order with a lower priority value is ordered before one with a higher
     * priority value.
     *
     * @param otherObject the work order to compare against; must be an
     *                    instance of {@code WorkOrder}
     * @return a negative integer, zero, or a positive integer as the priority
     *         of this work order is less than, equal to, or greater than that
     *         of the other work order
     * @throws ClassCastException if {@code otherObject} is not a
     *                            {@code WorkOrder}
     * @throws NullPointerException if {@code otherObject} is {@code null}
     */
    @Override
    public int compareTo(Object otherObject) {
        WorkOrder other = (WorkOrder) otherObject;
        if (priority < other.priority) return -1;
        if (priority > other.priority) return 1;
        return 0;
    }
}
