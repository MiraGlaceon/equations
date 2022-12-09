package mira.space.controller;

import mira.space.controller.exception.RootsNotFoundException;
import mira.space.dto.EquationDto;
import mira.space.model.Equation;
import mira.space.model.repo.EquationRepository;
import mira.space.service.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EquationController {

    @Autowired
    EquationRepository equationRepository;
    @Autowired
    EquationService equationService;

    @GetMapping("/equations")
    public ResponseEntity<EquationDto> getEquationByCoefficients(@RequestParam double a,
                                                                 @RequestParam double b,
                                                                 @RequestParam double c) {
        Optional<Equation> optionalEquation = equationRepository.findByCoefficients(a, b, c);
        if (optionalEquation.isPresent()) {
            Equation equation = optionalEquation.get();
            return ResponseEntity.ok(new EquationDto(equation.getX1(), equation.getX2()));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/equations")
    public ResponseEntity<Equation> postEquationWithCoefficients(@RequestParam double a,
                                                                 @RequestParam double b,
                                                                 @RequestParam double c) {
        Equation equation = equationService.solveEquation(a, b, c);
        if (equation == null) {
            throw new RootsNotFoundException(a, b, c);
        }
        return ResponseEntity.ok(equationRepository.save(equation));
    }
}
