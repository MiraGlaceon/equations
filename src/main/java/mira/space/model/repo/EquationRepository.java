package mira.space.model.repo;

import mira.space.model.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {
    @Query("select e from Equation e where e.a = ?1 and e.b = ?2 and e.c = ?3")
    Optional<Equation> findByCoefficients(double a, double b, double c);
}
