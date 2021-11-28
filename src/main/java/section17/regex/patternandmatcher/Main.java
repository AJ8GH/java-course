package section17.regex.patternandmatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        StringBuilder htmlText = new StringBuilder("<h1>My Heading Text</h1>")
                .append("<h2>Sub-heading</h2>")
                .append("<p>paragraph 1</p>")
                .append("<Paragraph 2</p>")
                .append("<h2>Summary</h2>")
                .append("<p>Here is the summary</p>");

        // match any number of any chars before and after the ".*"
        // below will return true for any string which contains any instance of "<h2>"
        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        // resets the matcher so it can be used again
        matcher.reset();

        // find looks for the next occurrence of the pattern and allows start and end to get the first and lst index of match
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + ": " +
                    matcher.start() + " to " + matcher.end());
        }

        // make the * quantifier a lazy quantifier - e.g. match zero or one occurrences
        // e.g. below will match first occurrence of <h2> ... </h2> and everything in between the tags.
        // (<h2>.+</h2>) matches one or more occurrences
        // (<h2>.*</h2>) matches zero or more occurrences
        String groupPatternString = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(groupPatternString);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while (h2TextMatcher.find()) {
            System.out.println("Occurrence:" + h2TextMatcher.group(2));
        }

        // Operators
        // and operator is implicit in regex - "abc" means "a" and (followed by) "b" and (followed by ) "c"
        // or operator is pipe char |
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

        // not operator is the carat ^
        // below matches any t with any character after it other than v
        // (does not match t's with v after them or t's with no chars after them)
        String tvTest = "tstvtkt";
        String tNotVRegex = "t[^v]";
        Pattern tNotVPattern = Pattern.compile(tNotVRegex);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;
        while (tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + ": " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }
        // ! is the not operator
        // (? ...) is a look ahead
        // look aheads do not include the look ahead chars in the matched text,
        // returns up to index after the match.

        String tNotVRegex2 = "t(?!v)";
        Pattern tNotVPattern2 = Pattern.compile(tNotVRegex2);
        Matcher tNotVMatcher2 = tNotVPattern2.matcher(tvTest);

        count = 0;
        while (tNotVMatcher2.find()) {
            count++;
            System.out.println("Occurrence " + count + ": " + tNotVMatcher2.start() + " to " + tNotVMatcher2.end());
        }

        // positive look ahead: t(?=v)
        // find all occurrences of t followed by v, but don't include v in the match

        // Regex to validate US phone number
        String usPhoneRegex = "^([(][0-9]{3}[)][ ][0-9]{3}[-][0-9]{4})$";

        String phone1 = "1234567890";
        String phone2 = "(123) 456-7890";
        String phone3 = "123 456-7890";
        String phone4 = "(123)456-7890";

        System.out.println("Phone 1 = " + phone1.matches(usPhoneRegex));
        System.out.println("Phone 2 = " + phone2.matches(usPhoneRegex));
        System.out.println("Phone 3 = " + phone3.matches(usPhoneRegex));
        System.out.println("Phone 4 = " + phone4.matches(usPhoneRegex));
    }
}
