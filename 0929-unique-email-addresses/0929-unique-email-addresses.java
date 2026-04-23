import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numUniqueEmails(String[] emails) {
        // Set to store the unique formatted emails
        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            // Split into local and domain parts
            int atIndex = email.indexOf('@');
            String local = email.substring(0, atIndex);
            String domain = email.substring(atIndex);

            // 1. Handle the '+' rule: ignore everything after '+'
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }

            // 2. Handle the '.' rule: remove all dots
            local = local.replace(".", "");

            // Recombine and add to the set
            uniqueEmails.add(local + domain);
        }

        return uniqueEmails.size();
    }
}