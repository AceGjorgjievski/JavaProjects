package mk.ukim.finki.sqt.auds.aud_6_logic_coverage;// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 8; page ??
// JUnit for mk.ukim.finki.sqt.auds.aud_6_logic_coverage.GoodFastCheap.java

import org.junit.*;
import static org.junit.Assert.*;

public class GoodFastCheapRACC {

   // This test set achieves RACC on the predicate in the isSatisfactory() method 
   // inside the mk.ukim.finki.sqt.auds.aud_6_logic_coverage.GoodFastCheap class.  All other predicates in mk.ukim.finki.sqt.auds.aud_6_logic_coverage.GoodFastCheap
   // are ignored by this test set

   // Comments show the test number and the values of good, fast, and cheap 
   // variables.  Test numbers are from the logic coverage tool on the book 
   // web site.   Using the logic coverage tool on the book website, one 
   // can determine that there are various ways to achieve RACC with exactly 
   // four tests.  The tests in this JUnit implement one of these possibilities, 
   // namely, tests 2, 3, 4, 6.   Hence, the tests here are named reach2(), 
   // reach3(), reach4(), and reach6().


   GoodFastCheap gfc;

   @Before public void setUp() { 
       gfc = new GoodFastCheap();    // 8:  F F F
   }

   @Test public void test2() throws Exception { 
      gfc.makeGood();         // 4: T F F
      gfc.makeFast();         // 2: T T F
      assertTrue(gfc.isSatisfactory());
   }

   @Test public void test3() throws Exception { 
      gfc.makeGood();         // 4: T F F
      gfc.makeCheap();        // 3: T F T
      assertTrue(gfc.isSatisfactory());
   }  
   
   
   @Test public void test4() throws Exception { 
      gfc.makeGood();         // 4: T F F
      assertFalse(gfc.isSatisfactory());
   }

   @Test public void test6() throws Exception { 
      gfc.makeFast();         // 6: F T F
      assertFalse(gfc.isSatisfactory());
   }
}
