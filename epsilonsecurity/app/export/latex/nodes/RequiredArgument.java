package export.latex.nodes;

public class RequiredArgument implements Node {
    private String argument;

    private RequiredArgument(String argument) {
        this.argument = argument;
    }

    public static RequiredArgument of(String argument) {
        return new RequiredArgument(argument);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append('{');
        builder.append(argument);
        builder.append('}');
    }
}
