# Price Service (Spring Boot, Java 21)

Small REST service that formats prices and returns a VAT breakdown.

- **Java:** 21
- **Spring Boot:** 3.5.5
- **Formats:** JSON and XML (content negotiation)
- **VAT:** 19%
- **Currencies:** `USD` (symbol `$` right), `ILS` (symbol `₪` left)

---

## Quick start

```bash
# from repo root
./mvnw clean test
./mvnw spring-boot:run
# app runs on http://localhost:8080
