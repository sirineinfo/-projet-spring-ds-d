package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, String> {
}
