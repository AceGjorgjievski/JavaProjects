package mk.ukim.finki.sqt.auds.aud_6_logic_coverage;// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 8; page ??
// See mk.ukim.finki.sqt.auds.aud_6_logic_coverage.GoodFastCheapRACC.java, GoodFastCheapMUMCUT.java for JUnit tests
// See also GoodFastCheapRefactored.java

import java.util.*;

// mk.ukim.finki.sqt.auds.aud_6_logic_coverage.GoodFastCheap: Investigating clause testing with an old engineering joke

public final class GoodFastCheap {

    boolean good  = false;
    boolean fast  = false;
    boolean cheap = false;

    public void makeGood () {
       good = true;
       if (fast && cheap) { cheap = false; }
    }

    public void makeFast () {
       fast = true; 
       if (good && cheap) { good = false; }
    }

    public void makeCheap () {
       cheap = true;
       if (fast && good) { fast = false; }
    }

    public void makeBad ()       { good = false; }
    public void makeSlow ()      { fast = false; }
    public void makeExpensive () { cheap = false; }

    public boolean isSatisfactory() {
       if ((good && fast) || (good && cheap) || (fast && cheap)) {
          return true;
       }
       return false;
    }
}