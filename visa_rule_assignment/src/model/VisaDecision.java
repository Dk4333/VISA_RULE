package model;

import enums.VisaType;
import enums.DocumentType;

import java.util.Collections;
import java.util.List;

public final class VisaDecision {

    private final boolean visaRequired;
    private final VisaType visaType;
    private final List<DocumentType> requiredDocuments;
    private final int estimatedProcessingDays;
    private final List<String> warnings;

    private VisaDecision(
            boolean visaRequired,
            VisaType visaType,
            List<DocumentType> requiredDocuments,
            int estimatedProcessingDays,
            List<String> warnings
    ) {
        this.visaRequired = visaRequired;
        this.visaType = visaType;
        this.requiredDocuments = requiredDocuments;
        this.estimatedProcessingDays = estimatedProcessingDays;
        this.warnings = warnings;
    }

    public static VisaDecision fromRule(VisaRule rule) {
        return new VisaDecision(
                rule.isVisaRequired(),
                rule.getVisaType(),
                rule.getDocuments(),
                rule.getProcessingDays(),
                Collections.emptyList()
        );
    }

    public static VisaDecision noRuleFound() {
        return new VisaDecision(
                true,
                null,
                Collections.emptyList(),
                0,
                Collections.singletonList("No matching visa rule found")
        );
    }

    public static VisaDecision conflict(int count) {
        return new VisaDecision(
                true,
                null,
                Collections.emptyList(),
                0,
                Collections.singletonList("Multiple visa rules matched: " + count)
        );
    }

    // Getters only (immutability)
    public boolean isVisaRequired() { return visaRequired; }
    public VisaType getVisaType() { return visaType; }
    public List<DocumentType> getRequiredDocuments() { return requiredDocuments; }
    public int getEstimatedProcessingDays() { return estimatedProcessingDays; }
    public List<String> getWarnings() { return warnings; }
}
