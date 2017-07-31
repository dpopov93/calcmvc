/*
 * Class for  Calculator
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 31.07.17
 * @version 1.0
 */

import java.util.Observable;

public class Calculator extends Observable {
    private int buffer;
    private int result;
    private OperatorType operatorType;

    public Calculator() {
        this.clear();
    }

    public void clear() {
        this.result = this.buffer = 0;
        this.operatorType = OperatorType.ADD;
        this.setChanged();
        this.notifyObservers(this.buffer);
    }

    public void appendDigit(int digit) {
        this.buffer = this.buffer * 10 + digit;
        this.setChanged();
        this.notifyObservers(this.buffer);
    }

    public void setOperator(OperatorType type) {
        if (this.buffer != 0) {
            this.calculate();
        }

        this.operatorType = type;
    }

    public void calculate() {
        switch (this.operatorType) {
            case ADD:
                this.result += this.buffer;
                break;
            case SUB:
                this.result -= this.buffer;
                break;
            case MULTY:
                this.result *= this.buffer;
                break;
            case DIV:
                this.result /= this.buffer;
                break;
            default:
                break;
        }
        this.buffer = this.result;
        this.setChanged();
        this.notifyObservers(this.buffer);

        this.buffer = 0;
    }
}