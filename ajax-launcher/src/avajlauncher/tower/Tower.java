package avajlauncher.tower;

import java.util.List;
import java.util.ArrayList;

import avajlauncher.flyable.Flyable;

public class Tower {

    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        observers.add(flyable); //Add Flyable objects to observers
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable); //Remove Flyable objects from observers
    }

    protected void conditionsChanged() throws Exception {
        for (int i = 0; i < observers.size(); i++) { //turns all flyable objects
            observers.get(i).updateConditions(); //call objects's updateConditions functions where in the observers list.
        }
    }

}