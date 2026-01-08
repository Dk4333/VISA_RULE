package app;

import enums.Country;
import enums.TravelPurpose;
import evaluator.VisaRuleEvaluator;
import loader.RuleLoader;
import model.VisaDecision;
import model.VisaRule;
import repository.RuleRepository;

import java.util.List;

public class VisaRuleTestApp {

    public static void main(String[] args) {

        RuleLoader loader = new RuleLoader();
        List<VisaRule> rules = loader.loadRules("config/visa-rules.json");
        RuleRepository repository = new RuleRepository(rules);
        VisaRuleEvaluator evaluator = new VisaRuleEvaluator(repository);

        testValidRule(evaluator);
        testNoRuleFound(evaluator);
        testConflict(evaluator);
        testInvalidInput(evaluator);
    }

    // 1️⃣ VALID RULE MATCH
    private static void testValidRule(VisaRuleEvaluator evaluator) {
        System.out.println("\nTEST 1: Valid Rule Match");

        VisaDecision decision = evaluator.evaluate(
                Country.UAE,
                Country.INDIA,
                TravelPurpose.TOURISM,
                10
        );

        System.out.println("Visa Required: " + decision.isVisaRequired());
        System.out.println("Visa Type: " + decision.getVisaType());
        System.out.println("Warnings: " + decision.getWarnings());
    }

    // 2️⃣ NO RULE FOUND
    private static void testNoRuleFound(VisaRuleEvaluator evaluator) {
        System.out.println("\nTEST 2: No Rule Found");

        VisaDecision decision = evaluator.evaluate(
                Country.UAE,
                Country.GERMANY,
                TravelPurpose.TOURISM,
                10
        );

        System.out.println("Warnings: " + decision.getWarnings());
    }

    // 3️⃣ MULTIPLE RULE CONFLICT
    private static void testConflict(VisaRuleEvaluator evaluator) {
        System.out.println("\nTEST 3: Rule Conflict");

        VisaDecision decision = evaluator.evaluate(
                Country.GERMANY,
                Country.INDIA,
                TravelPurpose.STUDY,
                200
        );

        System.out.println("Warnings: " + decision.getWarnings());
    }

    // 4️⃣ INVALID INPUT
    private static void testInvalidInput(VisaRuleEvaluator evaluator) {
        System.out.println("\nTEST 4: Invalid Input");

        try {
            evaluator.evaluate(
                    Country.UAE,
                    Country.INDIA,
                    TravelPurpose.TOURISM,
                    -5
            );
        } catch (Exception e) {
            System.out.println("Caught error: " + e.getMessage());
        }
    }
}

