package repository;

import model.VisaRule;

import java.util.Collections;
import java.util.List;

public class RuleRepository {

    private final List<VisaRule> rules;

    public RuleRepository(List<VisaRule> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("Visa rules cannot be empty");
        }
        this.rules = Collections.unmodifiableList(rules);
    }

    public List<VisaRule> getAllRules() {
        return rules;
    }
}
