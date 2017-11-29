package export.latex.nodes;

public final class OptionalArgument implements Node {
    private String argument;

    private OptionalArgument(String argument) {
        this.argument = argument;
    }

    public static OptionalArgument of(String argument) {
        return new OptionalArgument(argument);
    }


    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append('[');
        builder.append(argument);
        builder.append(']');
    }
}
