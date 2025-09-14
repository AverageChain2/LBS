package staffs.common.domain;



import staffs.common.events.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends AssertionConcern {
   protected final Identity id;
    private final List<Event> domainEvents = new ArrayList<>();


    protected Entity(Identity id) {
        this.id = id;
    }

    public Identity id() {
        return id;
    }

    protected void addDomainEvent(Event event) {
        domainEvents.add(event);
    }

    public List<Event> listOfDomainEvents() {
        return domainEvents;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        Entity another = (Entity) o;

        return another.id == this.id;
    }
}


