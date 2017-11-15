package export;

import com.google.common.collect.Lists;
import export.latex.Document;
import export.latex.nodes.*;
import models.schedule.Schedule;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static export.latex.nodes.Command.command;
import static export.latex.nodes.RequiredArgument.of;
import static export.latex.nodes.SimpleNode.just;

public class PDFExportableSchedule implements PDFExportable {

    private Instant initTime;
    private Schedule schedule;

    private Optional<Document> document = Optional.empty();

    public PDFExportableSchedule(Schedule schedule) {
        this.initTime = Instant.now();
        this.schedule = schedule;
    }

    @Override
    public String uniqueIdentifier() {
        return initTime.toString();
    }

    @Override
    public StringBuilder constructLaTeXRepresentation() {
        // constructs document lazily
        StringBuilder builder = new StringBuilder();
        Document document = this.document.orElse(constructDocument());
        document.laTeXRepresentation(builder);
        return builder;
    }

    private Document constructDocument() {
        Document document = new Document();
        document.setPreamble(constructPreamble());
        document.setBody(constructDocumentBody());
        return document;
    }

    private Preamble constructPreamble() {
        // write pre-amble
        Preamble preamble = new Preamble();
        preamble.setFontSize(12);
        requiredLibraries()
                .forEach(preamble::addLibrary);
        commandDefinitions()
            .forEach(preamble::addCommandDefinition);
        // maybe color definitions depending on shifts? Maybe not
        return preamble;
    }

    private DocumentBody constructDocumentBody() {
        DocumentBody body = new DocumentBody();
        Environment initialEnvironment = constructInitialEnvironments();
        return body;
    }

    private Environment constructInitialEnvironments() {
        Environment landscape = Environment.begin("landscape");
        return landscape.addingChildren(
            command("thispagestyle") .addingRequiredArgument(of(just("empty"))),
            command("noindent"),
            command("label") .addingRequiredArgument(of(just("tab:daypack")))
        );
    }

    private Environment initializeTableEnvironment() {
        Environment tableEnvironment = Environment.begin("tabularx")
            .addingRequiredArgument(of(command("columnwidth")))
            .addingRequiredArgument(of(just("|RRRRRRR|")));
        MultiNode landscapeTableWorkaround = MultiNode.of(
            command("caption*")
                .addingRequiredArgument(of(
                    command("textbf")
                        .addingRequiredArgument(of(just("SFU Security Schedule")))
                )),
            just("//")
        );
        tableEnvironment.addingChild(landscapeTableWorkaround);
        return tableEnvironment;
    }

    private static List<Library> requiredLibraries() {
        return Lists.newArrayList(
            Library.named("inputenc").addingOptional(OptionalArgument.of("utf8")),
            Library.named("tabularx"),
            Library.named("pdflscape"),
            Library.named("xcolor").addingOptional(OptionalArgument.of("dvipsnames")),
            Library.named("colortbl"),
            Library.named("geometry").addingOptional(OptionalArgument.of("margin=0.5in")),
            Library.named("ltablex")
        );
    }

    private static List<CommandDefinition> commandDefinitions() {
        return Lists.newArrayList(
            CommandDefinition.of("DayColor", 0, "Cyan!20"),
            CommandDefinition.of("DateColor", 0, "Goldenrod!70"),
            CommandDefinition.of("TrafficColor", 0, "SpringGreen!20"),
            CommandDefinition.of("TimeColor", 0, "Cerulean!90"),
            CommandDefinition.of("AssigneeColor", 0, "ForestGreen!65"),
            CommandDefinition.of("SpecialShiftColor", 0, "Lavender!50"),
            CommandDefinition.of("SpecialShiftSubColor", 0, "Dandelion!50"),
            CommandDefinition.of("TeamLeadersColor", 0, "Salmon!50"),
            CommandDefinition.of("VolunteerColor", 0, "YellowOrange!70"),
            CommandDefinition.of("SupervisorColor", 0, "Thistle!20"),
            CommandDefinition.of("BlankRow", 0,
                                 command("multicolumn")
                                     .addingRequiredArgument(of("7"))
                                     .addingRequiredArgument(of("|c|"))
                                     .addingRequiredArgument(of(""))
            ),
            CommandDefinition.of("TitleRow", 2, MultiNode.of(
                command("hline"),
                command("rowcolor")
                    .addingRequiredArgument(of("#1")),
                command("multicolumn")
                    .addingRequiredArgument(of(((Integer) 7).toString()))
                    .addingRequiredArgument(of("|c|"))
                    .addingRequiredArgument(of(command("textsc")
                        .addingRequiredArgument(of("#2"))
                    )),
                command("hline")

            )),
            CommandDefinition.of("SpecialShift", 1, MultiNode.of(
                command("cellcolor")
                    .addingRequiredArgument(of(command("SpecialShiftSubColor"))),
                command("textsc")
                    .addingRequiredArgument(of("#1")))
                .setOneLine()
            ),
            CommandDefinition.of("TimeCell", 1, MultiNode.of(
                command("cellcolor")
                    .addingRequiredArgument(of(command("TimeColor"))),
                just("#1"))
                .setOneLine()
            ),
            CommandDefinition.of("AssigneeCell", 1, MultiNode.of(
                command("cellcolor")
                    .addingRequiredArgument(of(command("AssigneeColor"))),
                just("#1"))
                .setOneLine()
            )
        );
    }
}
