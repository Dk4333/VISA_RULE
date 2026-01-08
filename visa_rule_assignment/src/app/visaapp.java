package app;

import enums.Country;
import enums.TravelPurpose;
import evaluator.VisaRuleEvaluator;
import loader.RuleLoader;
import model.VisaDecision;
import model.VisaRule;
import repository.RuleRepository;

import java.util.List;
import java.util.Scanner;

public class visaapp {

    public static void main(String[] args) {

        RuleLoader loader = new RuleLoader();
        List<VisaRule> rules = loader.loadRules("config/visa-rules.json");

        RuleRepository repository = new RuleRepository(rules);
        VisaRuleEvaluator evaluator = new VisaRuleEvaluator(repository);

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter destination country: ");
            Country destination = Country.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter passport country: ");
            Country passport = Country.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter travel purpose: ");
            TravelPurpose purpose = TravelPurpose.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Enter stay duration (days): ");
            int stayDays = Integer.parseInt(sc.nextLine());

            VisaDecision decision = evaluator.evaluate(
                    destination, passport, purpose, stayDays
            );

            System.out.println("\n--- VISA DECISION ---");
            System.out.println("Visa Required: " + decision.isVisaRequired());
            System.out.println("Visa Type: " + decision.getVisaType());
            System.out.println("Documents: " + decision.getRequiredDocuments());
            System.out.println("Processing Days: " + decision.getEstimatedProcessingDays());
            System.out.println("Warnings: " + decision.getWarnings());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please check values.");
        }
    }
}
