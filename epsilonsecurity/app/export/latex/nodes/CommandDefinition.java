package export.latex.nodes;

import com.google.common.collect.Lists;

public class CommandDefinition implements Node {

    private String name;
    private String definition;
    private Integer argCount;

    private CommandDefinition(String name, String definition, Integer argCount) {
        this.name = name;
        this.definition = definition;
        this.argCount = argCount;
    }

    public static CommandDefinition of(String name, Integer argCount, String definition) {
        return new CommandDefinition(name, definition, argCount);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append("\\newcommand");
        Lists.newArrayList(
            RequiredArgument.of("\\" + name),
            OptionalArgument.of("\\" + argCount),
            RequiredArgument.of("\\" + definition)
        )
            .forEach( argument -> argument.laTeXRepresentation(builder));

    }

    // TODO: Command Export, once we know how we want to store them.
}
