/*
 * Class for  CommandButton
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 31.07.17
 * @version 1.0
 */

import javax.swing.JButton;

enum CommandType {
    CLEAR, ADD, SUB, EQUAL, MULTY, DIV
}

public class CommandButton extends JButton {

    private static final long serialVersionUID = 1L;

    CommandType commandType;

    CommandButton(CommandType type, String text) {
        this.commandType = type;
        this.setText(text);
    }

    CommandType getCommandType() {
        return this.commandType;
    }
}
