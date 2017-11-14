package export.latex.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static export.latex.nodes.OptionalArgument.*;

public class Environment implements Node {

    private String name;
    // Children are executed lazily
    private List<Consumer<StringBuilder>> children = new ArrayList<>();
    private List<OptionalArgument> optionalArguments = new ArrayList<>();
    private List<RequiredArgument> requiredArguments = new ArrayList<>();

    private Environment(String name) {
        this.name = name;
    }

    public static Environment begin(String name) {
        return new Environment(name);
    }

    public Environment addingChild(Consumer<StringBuilder> childClosure) {
        children.add(childClosure);
        return this;
    }

    public Environment addingOptionalArgument(OptionalArgument argument) {
        optionalArguments.add(argument);
        return this;
    }

    public Environment addingRequiredArgument(RequiredArgument argument) {
        requiredArguments.add(argument);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        Command begin = Command.named("begin")
                .addingRequiredArgument(RequiredArgument.of(name));
        requiredArguments.forEach(begin::addingRequiredArgument);
        optionalArguments.forEach(begin::addingOptionalArgument);
        Command end = Command.named("end")
                .addingRequiredArgument(RequiredArgument.of(name));
        begin.laTeXRepresentation(builder);
        children.forEach(child -> child.accept(builder));
        end.laTeXRepresentation(builder);
    }
}
