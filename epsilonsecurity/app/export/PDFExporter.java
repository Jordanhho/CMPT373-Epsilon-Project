package export;

import java.io.File;
import java.io.IOException;

public class PDFExporter {

    // TODO: remove throws
    public File export(PDFExportable object) throws IOException {
        // Construct LaTeX document
        // Call LaTeX as required
        // throw error or return construction PDF
        return File.createTempFile(object.uniqueIdentifier(), ".tex");
    }

}
