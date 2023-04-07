package mk.ukim.finki.sqt.labs.lab_1;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.*;


class LabOneChecker {

    public static Set<String> notInBothTeams(Set<String> team1, Set<String> team2) {
        if (team1.isEmpty() || team1 == null || team2.isEmpty() || team2 == null) {
            return null;
        }
        //t1 -> 1 3 5 7
        //t2 -> 2 4 6 8

        Set<String> finalSet = new HashSet<>();

        for (String s1 : team1) {
            int number = Collections.frequency(team2, s1) + Collections.frequency(team1, s1);
            if (number > 1) return Collections.emptySet();
            else if (!team2.contains(s1)) {
                finalSet.add(s1);
            }
        }


        for (String s2 : team2) {
            int number = Collections.frequency(team2, s2) + Collections.frequency(team1, s2);
            if (number > 1) return Collections.emptySet();
            else if (!team1.contains(s2)) {
                finalSet.add(s2);
            }
        }


        return finalSet;
    }

}


public class LabOneTest {

    /**
     * Sets are empty - Interfaced-based approach
     * 3 tests for checking the parameters in
     * method LabOneChecker.notInBothTeams(Set<String> team1, Set<String> team2);
     * <p>
     * C1 - F, C2 - F - Base Case
     */
    @Test
    public void testEmptySets_BaseCase() {
        Set<String> team1 = new HashSet<>();
        Set<String> team2 = new HashSet<>();
        Set<String> finalIds = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                team2.add("" + i);
            } else {
                team1.add("" + i);
            }
            finalIds.add("" + i);
        }

        assertNotNull(LabOneChecker.notInBothTeams(team1, team2));
    }

    /**
     * C1 - F, C2 - T
     */
    @Test
    public void testTeam2Empty() {
        Set<String> team1 = new HashSet<>();
        Set<String> team2 = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 1) {
                team1.add("" + i);
            }
        }

        assertNull(null, LabOneChecker.notInBothTeams(team1, team2));
    }

    /**
     * C1 - T, C2 - F
     */
    @Test
    public void testTeam1Empty() {
        Set<String> team1 = new HashSet<>();
        Set<String> team2 = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                team2.add("" + i);
            }
        }

        assertNull(null, LabOneChecker.notInBothTeams(team1, team2));
    }


    /**
     * 2 test for checking duplicates of the Ids - Functionality-based approach.
     * <p>
     * C1 - T - Base Case
     */
    @Test
    public void testNoDuplicatesInBothTeams() {
        Set<String> team1Ids = new HashSet<>();
        Set<String> team2Ids = new HashSet<>();
        Set<String> finalIds = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                team2Ids.add("" + i);
            } else {
                team1Ids.add("" + i);
            }
            finalIds.add("" + i);
        }
        assertEquals(finalIds, LabOneChecker.notInBothTeams(team1Ids, team2Ids));

    }

    /**
     * C1 - F
     */
    @Test
    public void testDuplicatesInBothTeams() {
        Set<String> team1Ids = new HashSet<>();
        Set<String> team2Ids = new HashSet<>();
        Set<String> finalIds = new HashSet<>();

        team1Ids.add("1");
        team1Ids.add("3");
        team1Ids.add("3");
        team1Ids.add("5");
        team1Ids.add("6");

        team2Ids.add("2");
        team2Ids.add("4");
        team2Ids.add("4");
        team2Ids.add("6");

        finalIds.add("1");
        finalIds.add("2");
        finalIds.add("3");
        finalIds.add("4");
        finalIds.add("5");
        finalIds.add("6");

        assertEquals(Collections.emptySet(), LabOneChecker.notInBothTeams(team1Ids, team2Ids));
    }
}
