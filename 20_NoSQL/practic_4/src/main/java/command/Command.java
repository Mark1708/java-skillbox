package command;

import java.util.Arrays;

public class Command {
    private final Opcode opcode;
    private final String[] args;

    public Command(Opcode opcode, String[] args) {
        this.opcode = opcode;
        this.args = args;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public String[] getArgs() {
        return args;
    }

    public static CommandBuilder newBuilder() {
        return new CommandBuilder();
    }

    @Override
    public String toString() {
        return "command.Command{" +
                "opcode=" + opcode +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
