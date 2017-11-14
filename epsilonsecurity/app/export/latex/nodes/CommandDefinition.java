package export.latex.nodes;

import com.google.common.collect.Lists;

public class CommandDefinition implements Node {

    private String name;
    private Node definition;
    private Integer argCount;

    private CommandDefinition(String name, Node definition, Integer argCount) {
        this.name = name;
        this.definition = definition;
        this.argCount = argCount;
    }

    public static CommandDefinition of(String name, Integer argCount, String definition) {
        return new CommandDefinition(name,
                                     SimpleNode.of(definition),
                                     argCount);
    }

    public static CommandDefinition of(String name, Integer argCount, Node definition) {
        return new CommandDefinition(name, definition, argCount);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        Command.named("newcommand")
            .laTeXRepresentation(builder);
        Lists.newArrayList(
            RequiredArgument.of(Command.named(name)),
            OptionalArgument.of(argCount.toString()),
            RequiredArgument.of(definition)
        )
            .forEach( argument -> argument.laTeXRepresentation(builder));

    }

    // TODO: Command Export, once we know how we want to store them.
}
