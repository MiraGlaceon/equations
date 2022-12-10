package mira.space.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@OpenAPIDefinition(info = @Info(
        title = "Примерный API для Аксиоматики",
        description = "Решает квадратные уравнения вида ax^2 + bx + c = 0"))
public class EquationController {

    @Autowired
    private EquationRepository equationRepository;
    @Autowired
    private EquationService equationService;

    @Operation(summary = "Получить корни уравнения по коэффициентам из базы данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Корни успешно получены",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EquationDto.class))}),
            @ApiResponse(responseCode = "204", description = "Не удалось получить корни из БД",
                    content = @Content)
    })
    @GetMapping("/equation")
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

    @Operation(summary = "Решить уравнение и результат записать в базу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Уравнение решено и записано в базу",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equation.class))}),
            @ApiResponse(responseCode = "422", description = "Уравнение не имеет корней",
                    content = @Content)
    })
    @PostMapping("/equation")
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
