package export.latex.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Environment implements Node {

    private String name;
    // Children are executed lazily
    private List<Node> children = new ArrayList<>();
    private List<OptionalArgument> optionalArguments = new ArrayList<>();
    private List<RequiredArgument> requiredArguments = new ArrayList<>();

    Environment() { } // default constructors are the dumbest language "feature"

    Environment(String name) {
        this.name = name;
    }

    public static Environment begin(String name) {
        return new Environment(name);
    }

    Environment settingName(String name) {
        this.name = name;
        return this;
    }

    public Environment addingChildren(Node... subnodes) {
        children.addAll(Arrays.asList(subnodes));
        return this;
    }

    public Environment addingChild(Node subnode) {
        children.add(subnode);
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
        Command begin = Command.command("begin")
                .addingRequiredArgument(RequiredArgument.of(name));
        requiredArguments.forEach(begin::addingRequiredArgument);
        optionalArguments.forEach(begin::addingOptionalArgument);
        Command end = Command.command("end")
                .addingRequiredArgument(RequiredArgument.of(name));
        begin.laTeXRepresentation(builder);
        children.forEach(child -> child.laTeXRepresentation(builder));
        end.laTeXRepresentation(builder);
    }
}
