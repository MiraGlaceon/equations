package mira.space.controller;

import mira.space.dto.EquationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EquationController {
    @GetMapping("/")
    public ResponseEntity<EquationDto> getEquationByCoefficients(@RequestParam Double a,
                                                                 @RequestParam Double b,
                                                                 @RequestParam Double c) {
        return null;
    }
}
