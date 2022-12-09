package mira.space.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Equation")
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double a;
    private double b;
    private double c;
    private double x1;
    private double x2;

    public Equation(double a, double b, double c, double x1, double x2) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.x1 = x1;
        this.x2 = x2;
    }
}
