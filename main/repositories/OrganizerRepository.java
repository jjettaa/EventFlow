package repositories;

import models.Organizer;
import java.util.ArrayList;
import java.util.List;

public class OrganizerRepository {

    private static OrganizerRepository instance;
    private List<Organizer> organizers = new ArrayList<>();
    private int nextId = 1;

    private OrganizerRepository() {}

    public static OrganizerRepository getInstance() {
        if (instance == null) instance = new OrganizerRepository();
        return instance;
    }

    public Organizer save(Organizer organizer) {
        if (organizer.getOrganizerId() == 0) {
            organizer.setOrganizerId(nextId++);
            organizers.add(organizer);
        }
        return organizer;
    }

    public List<Organizer> findAll() {
        return organizers;
    }

    public Organizer findById(int id) {
        for (Organizer o : organizers) {
            if (o.getOrganizerId() == id) return o;
        }
        return null;
    }
}

