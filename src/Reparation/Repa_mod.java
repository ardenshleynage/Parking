package Reparation;

public class Repa_mod {
    private int id;
    private String problem;
    private String solution;
    private double price;
    private String add_date;

    public Repa_mod() {
    }

    public Repa_mod(int id, String problem, String solution, double price, String add_date) {
        this.id = id;
        this.problem = problem;
        this.solution = solution;
        this.price = price;
        this.add_date = add_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String toString() {
        return String.format(
                "PROBLEME: %s\nSOLUTION: %s\nPRIX: %s G\nDATE D'AJOUT: %s",
                problem.toUpperCase(), solution.toUpperCase(), price, add_date);
    }

}
