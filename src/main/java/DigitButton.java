/*
 * Class for  DigitButton
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 31.07.17
 * @version 1.0
 */

import javax.swing.JButton;

public class DigitButton extends JButton {

    private static final long serialVersionUID = 1L;

    int digit;

    DigitButton(int digit) {
        this.digit = digit;
        this.setText(Integer.toString(digit));
    }

    int getDigit() {
        return this.digit;
    }
}
