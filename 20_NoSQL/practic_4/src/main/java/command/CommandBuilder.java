package command;

import java.util.Arrays;
import java.util.Locale;

import static command.ExceptionsMessages.*;
import static command.Opcode.HELP;
import static command.Opcode.valueOf;

public class CommandBuilder {

    private Opcode opcode;
    private String[] args;

    public CommandBuilder() {
    }

    public CommandBuilder parseLine(String line) {
        String[] arr = line.split(" ");
        return this.setOpcode(arr[0]).setArgs(Arrays.copyOfRange(arr, 1, arr.length));
    }

    public CommandBuilder setOpcode(String opcode) {
        try {
            this.opcode = valueOf(opcode.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            System.err.printf(NOT_EXISTS_OPCODE, opcode);
            this.opcode = HELP;
        }
        return this;
    }

    public CommandBuilder setArgs(String[] args) {
        this.args = args;
        int expectedArgsCount = 0;
        switch (opcode) {
            case ADD_SHOP:
                expectedArgsCount = 1;
                break;
            case CREATE_PRODUCT:
                expectedArgsCount = 2;
                if (expectedArgsCount != args.length) break;
                try {
                    int value = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.printf(WRONG_ARGUMENT_TYPE, 1, "integer");
                    opcode = HELP;
                }
                break;
            case ADD_PRODUCT:
                expectedArgsCount = 2;
                break;
        }
        if (expectedArgsCount != args.length) {
            this.opcode = HELP;
            System.err.printf(WRONG_ARGUMENTS_LENGTH, expectedArgsCount, args.length);
        }
        return this;
    }

    public Command build() {
        return new Command(opcode, args);
    }
}

