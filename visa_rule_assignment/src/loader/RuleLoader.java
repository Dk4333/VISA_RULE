package loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.VisaRule;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class RuleLoader {

    /**
     * Loads visa rules from a JSON configuration file
     */
    public List<VisaRule> loadRules(String filePath) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON array and convert to Java array
            VisaRule[] rulesArray = objectMapper.readValue(
                    new File(filePath),
                    VisaRule[].class
            );

            // Convert array to List
            return Arrays.asList(rulesArray);

        } catch (Exception e) {
            // Fail fast if config is invalid
            throw new RuntimeException("Failed to load visa rules from config", e);
    }
}

}
