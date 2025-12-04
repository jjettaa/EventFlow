package services;

import models.Organizer;
import repositories.OrganizerRepository;

import java.util.List;

public class OrganizerService {

    private OrganizerRepository organizerRepository = OrganizerRepository.getInstance();

    public Organizer createOrganizer(String name, String email, String password) {
        Organizer organizer = new Organizer(0, name, email, password);
        return organizerRepository.save(organizer);
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Organizer getById(int id) {
        return organizerRepository.findById(id);
    }
}
