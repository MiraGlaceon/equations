package mira.space.controller.exception;

public class RootsNotFoundException extends RuntimeException {
    public RootsNotFoundException(double a, double b, double c) {
        super("Can not find roots of equation with params: a=" + a + ", b=" + b + ", c=" + c);
    }
}
