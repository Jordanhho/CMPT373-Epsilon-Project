package export.latex.nodes;

import java.util.List;
import java.util.ArrayList;

public class Library implements Node {

    private final String name;
    private List<OptionalArgument> optionalArguments = new ArrayList<>();
    private List<RequiredArgument> requiredArguments = new ArrayList<>();

    private Library(String name,

                    List<RequiredArgument> requiredArguments,
                    List<OptionalArgument> optionalArguments) {
        this.name = name;
        this.requiredArguments = requiredArguments;
        this.optionalArguments = optionalArguments;
    }

    public static Library named(String name) {
        return new Library(name, new ArrayList<>(), new ArrayList<>());
    }

    public Library addingOptional(OptionalArgument optionalArgument) {
        this.optionalArguments.add(optionalArgument);
        return this;
    }

    public Library addingRequired(RequiredArgument requiredArgument) {
        this.requiredArguments.add(requiredArgument);
        return this;
    }



    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append("\\usepackage");
        optionalArguments.forEach( arg -> arg.laTeXRepresentation(builder));
        requiredArguments.add(RequiredArgument.of(name));
        requiredArguments.forEach( arg -> arg.laTeXRepresentation(builder));
        Nodes.newLineOn(builder);
    }
}
