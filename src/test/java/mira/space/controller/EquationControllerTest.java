package mira.space.controller;

import mira.space.controller.exception.RootsNotFoundException;
import mira.space.dto.EquationCoefficientsDto;
import mira.space.dto.EquationDto;
import mira.space.model.Equation;
import mira.space.model.repo.EquationRepository;
import mira.space.service.EquationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EquationControllerTest {
    @Autowired
    private EquationController equationController;
    @Autowired
    private EquationRepository equationRepository;
    @Autowired
    private EquationService equationService;

    @Test
    void getEquationByCoefficientsTest() {
        double a = 2;
        double b = 8;
        double c = 3;
        Equation equation = equationService.solveEquation(a, b, c);
        equationRepository.save(equation);

        EquationCoefficientsDto equationCoefficientsDto = new EquationCoefficientsDto(a,b,c);
        ResponseEntity<EquationDto> expected = ResponseEntity.ok(new EquationDto(equation.getX1(), equation.getX2()));
        ResponseEntity<EquationDto> actual = equationController.getEquationByCoefficients(equationCoefficientsDto);
        Assertions.assertEquals(expected, actual);

        double different = 5;
        equationCoefficientsDto.setA(different);
        actual = equationController.getEquationByCoefficients(equationCoefficientsDto);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void postEquationWithCoefficientsTest() {
        double a = 4;
        double b = 5;
        double c = -3;

        ResponseEntity<Equation> output = equationController.postEquationWithCoefficients(a, b, c);
        Assertions.assertEquals(output.getStatusCode(), HttpStatus.OK);

        double different = 3;
        Assertions.assertThrows(RootsNotFoundException.class,
                () -> equationController.postEquationWithCoefficients(a, b, different));
    }
}
