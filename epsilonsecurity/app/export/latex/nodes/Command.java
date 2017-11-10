package export.latex.nodes;

import java.util.ArrayList;
import java.util.List;

public class Command implements Node {
    private String name;
    private List<OptionalArgument> optionalArguments = new ArrayList<>();
    private List<RequiredArgument> requiredArguments = new ArrayList<>();

    private Command(String name) {
        this.name = name;
    }

    public static Command named(String name) {
        return new Command(name);
    }

    public Command addingOptionalArgument(OptionalArgument optionalArgument) {
        this.optionalArguments.add(optionalArgument);
        return this;
    }

    public Command addingRequiredArgument(RequiredArgument requiredArgument) {
        this.requiredArguments.add(requiredArgument);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append("\\");
        builder.append(name);
        optionalArguments.forEach( argument -> argument.laTeXRepresentation(builder));
        requiredArguments.forEach( argument -> argument.laTeXRepresentation(builder));
        Nodes.newLineOn(builder);
    }
}
