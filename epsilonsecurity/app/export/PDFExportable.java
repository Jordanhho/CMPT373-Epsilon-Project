package export;

public interface PDFExportable {
    // TODO: do we want this to be String? or byte[]?
    StringBuilder constructLaTeXRepresentation();
    String uniqueIdentifier();
}
