package mira.space.service;

import mira.space.model.Equation;

public interface EquationService {
    Equation solveEquation(double a, double b, double c);
}
