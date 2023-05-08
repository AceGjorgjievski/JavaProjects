package mk.ukim.finki.sqt.auds.aud_4_graph_coverage;

// JUnit tests for PatternIndex.java

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.*;

@RunWith(Parameterized.class)
public class DataDrivenPatternIndexTest {
    private String subject;
    private String pattern;
    private int expected;

    public DataDrivenPatternIndexTest(String subject, String pattern, int
            expected) {
        this.subject = subject;
        this.pattern = pattern;
        this.expected = expected;
    }

    // The following values are from the 1st edition, Table 2.4, page 59
    // Need an update to the new edition table number and page number.
    @Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"a", "bc", -1},
                {"ab", "a", 0},
                {"ab", "ab", 0},
                {"ab", "ac", -1},
                {"ab", "b", 1},
                {"ab", "c", -1},
                {"abc", "abc", 0},
                {"abc", "abd", -1},
                {"abc", "ac", -1},
                {"abc", "ba", -1},
                {"abc", "bc", 1}
        });
    }

    @Test
    public void dataFlowTests() {
        assertEquals("Data flow PatternIndex test",
                PatternIndex.patternIndex(subject, pattern), expected);
    }
}