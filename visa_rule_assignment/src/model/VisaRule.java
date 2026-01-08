package model;

import enums.Country;
import enums.TravelPurpose;
import enums.VisaType;
import enums.DocumentType;

import java.util.List;

public class VisaRule {

    private Country destinationCountry;
    private Country passportCountry;
    private TravelPurpose purpose;
    private int maxStayDays;

    private boolean visaRequired;
    private VisaType visaType;
    private List<DocumentType> documents;
    private int processingDays;

    // Required by Jackson (JSON parser)
    public VisaRule() {
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public Country getPassportCountry() {
        return passportCountry;
    }

    public TravelPurpose getPurpose() {
        return purpose;
    }

    public int getMaxStayDays() {
        return maxStayDays;
    }

    public boolean isVisaRequired() {
        return visaRequired;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    public List<DocumentType> getDocuments() {
        return documents;
    }

    public int getProcessingDays() {
        return processingDays;
    }
}
