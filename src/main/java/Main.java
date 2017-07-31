/*
 * Class for  Main
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 30.07.17
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorFrame frame = new CalculatorFrame(calculator);
        calculator.addObserver(frame);
        frame.setVisible(true);
    }
}
