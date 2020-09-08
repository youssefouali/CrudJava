package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Optional<Departement> findById(Long id);
    List<Departement> findAll();
}
