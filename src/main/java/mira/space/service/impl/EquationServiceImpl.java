package mira.space.service.impl;

import mira.space.model.Equation;
import mira.space.model.repo.EquationRepository;
import mira.space.service.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquationServiceImpl implements EquationService {

    @Autowired
    EquationRepository equationRepository;

    @Override
    public Equation solveEquation(double a, double b, double c) {
        Optional<Equation> loadedEquation = equationRepository.findByCoefficients(a, b, c);
        if (loadedEquation.isPresent()) {
            return loadedEquation.get();
        }

        double x1;
        double x2;
        double d = b * b - 4 * a * c;
        if (d >= 0) {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
        } else {
            return null;
        }
        return new Equation(a, b, c, x1, x2);
    }
}
