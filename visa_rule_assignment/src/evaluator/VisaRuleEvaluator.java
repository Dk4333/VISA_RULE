package evaluator;

import enums.Country;
import enums.TravelPurpose;
import model.VisaDecision;
import model.VisaRule;
import repository.RuleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VisaRuleEvaluator {

    private final RuleRepository ruleRepository;

    public VisaRuleEvaluator(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public VisaDecision evaluate(
            Country destination,
            Country passportCountry,
            TravelPurpose purpose,
            int stayDays
    ) {

        if (stayDays <= 0) {
            throw new IllegalArgumentException("Stay duration must be positive");
        }

        // 1️⃣ Find matching rules
        List<VisaRule> matchedRules = ruleRepository.getAllRules()
                .stream()
                .filter(rule ->
                        rule.getDestinationCountry() == destination &&
                        rule.getPassportCountry() == passportCountry &&
                        rule.getPurpose() == purpose &&
                        stayDays <= rule.getMaxStayDays()
                )
                .collect(Collectors.toList());

        // 2️⃣ No rule found
        if (matchedRules.isEmpty()) {
            return VisaDecision.noRuleFound();
        }

        // 3️⃣ Multiple rule conflict
        if (matchedRules.size() > 1) {
            return VisaDecision.conflict(matchedRules.size());
        }

        // 4️⃣ Single valid rule
        VisaRule rule = matchedRules.get(0);

        return VisaDecision.fromRule(rule);
    }
}
