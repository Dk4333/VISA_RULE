# Config-Driven Visa Rule Evaluation System

##  Assignment Overview
This project is a Java-based, config-driven rule engine that evaluates whether a traveler requires a visa based on:
- Destination country
- Passport country
- Travel purpose
- Stay duration

The system is designed without frameworks, databases, or hardcoded country logic, as per the assignment requirements.

---

##  Key Features
- Pure Java (Java 8+)
- Config-driven business rules using JSON
- No hardcoded visa logic
- Clean OOP design
- Enum-based input validation
- Defensive coding & edge-case handling

---

##  Project Architecture
JSON Config
↓
RuleLoader
↓
RuleRepository
↓
VisaRuleEvaluator
↓
VisaDecision


---

## 📂 Package Structure

| Package | Responsibility |
|-------|----------------|
| app | Application entry point & testing |
| enums | Controlled input values |
| model | Immutable data models |
| loader | JSON rule loading |
| repository | Rule storage |
| evaluator | Core rule evaluation logic |

---

##  Technologies Used
- Java 8+
- Jackson (JSON parsing)
- Java Collections
- Enums

---

##  How to Run

### Compile

javac -cp "lib/*:src" src/**/*.java

### Test case senario
java -cp "lib/*:src" app.VisaRuleTestApp



