package mira.space.controller.exception;

public class RootsNotFoundException extends RuntimeException {
    public RootsNotFoundException(double a, double b, double c) {
        super("Корни уравнения не найдены. Коэффициенты: a=" + a + ", b=" + b + ", c=" + c);
    }
}
