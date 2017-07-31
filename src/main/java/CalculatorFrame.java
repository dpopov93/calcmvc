/*
 * Class for  CalculatorFrame
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 31.07.17
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class CalculatorFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    private Calculator calculator;
    private JLabel buffer;

    public CalculatorFrame(Calculator calculator) {
        this.calculator = calculator;
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.buffer = this.createBuffer();
        JPanel digitButtons = this.createDigitButtonPanel();
        JPanel operatorButtons = this.createOperatorButtonPanel();

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.buffer, BorderLayout.NORTH);
        contentPane.add(digitButtons, BorderLayout.CENTER);
        contentPane.add(operatorButtons, BorderLayout.EAST);

        this.setSize(200, 200);
    }

    private JLabel createBuffer() {
        JLabel buffer = new JLabel("0", JLabel.RIGHT);
        buffer.setFont(new Font("Sans", 0, 16));

        return buffer;
    }

    private JPanel createDigitButtonPanel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {
            System.out.println("Error: " + e.toString());
        }
        int[] digits = {
                7, 8, 9,
                4, 5, 6,
                1, 2, 3,
                0
        };
        CalculatorListener listener = new DigitButtonListener(this.calculator);

        JPanel digitPanel = new JPanel(new GridLayout(4, 3));
        for (int digit : digits) {
            JButton button = new DigitButton(digit);
            button.addActionListener(listener);
            digitPanel.add(button);
        }

        return digitPanel;
    }

    private JPanel createOperatorButtonPanel() {
        final String[] buttonTexts = {"+", "-", "*", "/", "C", "="};
        final CommandType[] commands = {CommandType.ADD, CommandType.SUB,
                CommandType.MULTY, CommandType.DIV, CommandType.CLEAR, CommandType.EQUAL};

        CalculatorListener listener = new CommandButtonListener(this.calculator);

        JPanel commandPanel = new JPanel(new GridLayout(4, 1));
        for(int i = 0; i < buttonTexts.length; i++) {
            JButton button = new CommandButton(commands[i], buttonTexts[i]);
            button.addActionListener(listener);
            commandPanel.add(button);
        }

        return commandPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.buffer.setText(arg.toString());
    }
}

abstract class CalculatorListener implements ActionListener {

    Calculator calculator;

    CalculatorListener(Calculator calculator) {
        this.calculator = calculator;
    }
}

class DigitButtonListener extends CalculatorListener {

    DigitButtonListener(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int input = ((DigitButton)e.getSource()).digit;
        this.calculator.appendDigit(input);
    }
}

class CommandButtonListener extends CalculatorListener {

    CommandButtonListener(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandType command = ((CommandButton)e.getSource()).commandType;
        switch (command) {
            case ADD:
                this.calculator.setOperator(OperatorType.ADD);
                break;
            case SUB:
                this.calculator.setOperator(OperatorType.SUB);
                break;
            case MULTY:
                this.calculator.setOperator(OperatorType.MULTY);
                break;
            case DIV:
                this.calculator.setOperator(OperatorType.DIV);
                break;
            case CLEAR:
                this.calculator.clear();
                break;
            case EQUAL:
                this.calculator.calculate();
                break;
        }
    }
}