
/*
 * Teachers: WARNING - this file is generated automatically, please do not
 * make changes directly in this file, instead communicate needed changes
 * to the person responsable for the Tester.
 *
 * Students: you are welcome to make changes to this file if it helps
 * you to better debug your programs. Just REMEMBER that any changes made
 * by you will not change the Tester program used for the 'entrega system'.
 *
 */

//////////////////////////////////////////////////////////////////////
//
// File generated at: 2021/10/20 -- 17:45:26
// Seed: {1634,744690,520103}
//
//////////////////////////////////////////////////////////////////////



package aed.recursion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.lang.reflect.*;
import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.graph.*;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.*;
import java.util.function.Function;
import java.util.stream.Stream;



/**
 * The main tester class which contains JUnit5 tests.
 */
@SuppressWarnings({"unused","deprecation"})
public class TesterLab3 {

  
    

  @BeforeAll
  public static void init () 
  {
    TestUtils.reportPid() ;
    ResultsHandler.init() ;
    ResultsHandler.setNumTestsRemaining(80,60) ;
    TestData.setTesterType(true) ;
  }
  
  @AfterAll
  public static void reportResults () 
  {
    ResultsHandler.report_results() ;
  }
  
  @Test
  public void test_01 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_01") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_01")) ;
if (ok_sofar)
  ok_sofar = new RecursionCheck(new String("aed.recursion"),new String("Explorador"),new String("explora"),new Integer(1)).doCall().checkResult(new Boolean(true)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_01")) ;
    ResultsHandler.add_result(new String("recursionCheck"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_02 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_02") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_02")) ;
if (ok_sofar)
  ok_sofar = new RecursionCheck(new String("aed.recursion"),new String("Utils"),new String("findBottom"),new Integer(1)).doCall().checkResult(new Boolean(true)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_02")) ;
    ResultsHandler.add_result(new String("recursionCheck"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_03 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_03") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_03")) ;
if (ok_sofar)
  ok_sofar = new RecursionCheck(new String("aed.recursion"),new String("Utils"),new String("joinMultiSets"),new Integer(2)).doCall().checkResult(new Boolean(true)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_03")) ;
    ResultsHandler.add_result(new String("recursionCheck"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_04 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_04") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_04")) ;
if (ok_sofar)
  ok_sofar = new RecursionCheck(new String("aed.recursion"),new String("Utils"),new String("multiply"),new Integer(2)).doCall().checkResult(new Boolean(true)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_04")) ;
    ResultsHandler.add_result(new String("recursionCheck"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_05 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_05") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_05")) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_05")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_06 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_06") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_06")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(3),new Integer(287)).doCall().checkResult(new Integer(861)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_06")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_07 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_07") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_07")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(365),new Integer(-202)).doCall().checkResult(new Integer(-73730)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_07")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_08 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_08") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_08")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-205),new Integer(-363)).doCall().checkResult(new Integer(74415)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_08")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_09 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_09") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_09")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(540),new Integer(51)).doCall().checkResult(new Integer(27540)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_09")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_10 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_10") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_10")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-66),new Integer(-682)).doCall().checkResult(new Integer(45012)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_10")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_11 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_11") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_11")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-114),new Integer(-689)).doCall().checkResult(new Integer(78546)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_11")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_12 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_12") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_12")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-661),new Integer(-323)).doCall().checkResult(new Integer(213503)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_12")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_13 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_13") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_13")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(756),new Integer(-290)).doCall().checkResult(new Integer(-219240)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_13")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_14 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_14") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_14")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-729),new Integer(-331)).doCall().checkResult(new Integer(241299)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_14")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_15 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_15") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_15")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-188),new Integer(-929)).doCall().checkResult(new Integer(174652)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_15")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_16 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_16") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_16")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(862),new Integer(288)).doCall().checkResult(new Integer(248256)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_16")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_17 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_17") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_17")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-538),new Integer(-614)).doCall().checkResult(new Integer(330332)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_17")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_18 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_18") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_18")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(885),new Integer(332)).doCall().checkResult(new Integer(293820)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_18")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_19 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_19") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_19")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-627),new Integer(-632)).doCall().checkResult(new Integer(396264)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_19")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_20 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_20") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_20")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(903),new Integer(356)).doCall().checkResult(new Integer(321468)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_20")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_21 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_21") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_21")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-413),new Integer(-882)).doCall().checkResult(new Integer(364266)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_21")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_22 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_22") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_22")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(749),new Integer(605)).doCall().checkResult(new Integer(453145)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_22")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_23 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_23") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_23")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-556),new Integer(-817)).doCall().checkResult(new Integer(454252)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_23")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_24 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_24") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_24")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(-638),new Integer(-809)).doCall().checkResult(new Integer(516142)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_24")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_25 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_25") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_25")) ;
if (ok_sofar)
  ok_sofar = new Multiply(new Integer(970),new Integer(-986)).doCall().checkResult(new Integer(-956420)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_25")) ;
    ResultsHandler.add_result(new String("multiply"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_26 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_26") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_26")) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_26")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_27 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_27") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_27")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(10149),new MyInteger(42437),new MyInteger(15041),new MyInteger(35350),new MyInteger(87947),new MyInteger(13813),new MyInteger(63338),new MyInteger(21415),new MyInteger(17347),new MyInteger(68489),new MyInteger(28953),new MyInteger(5787),new MyInteger(9045),new MyInteger(19767),new MyInteger(26907),new MyInteger(38232),new MyInteger(21800),new MyInteger(84656),new MyInteger(73622),new MyInteger(80198),new MyInteger(62371),new MyInteger(17427),new MyInteger(93995),new MyInteger(27474),new MyInteger(92934),new MyInteger(5164),new MyInteger(99346),new MyInteger(71762),new MyInteger(35979),new MyInteger(18183),new MyInteger(40434),new MyInteger(44505),new MyInteger(22741),new MyInteger(52192),new MyInteger(53988),new MyInteger(7167),new MyInteger(68936),new MyInteger(28391),new MyInteger(51882),new MyInteger(15973),new MyInteger(99784),new MyInteger(2614),new MyInteger(80212),new MyInteger(35707),new MyInteger(70012),new MyInteger(80538),new MyInteger(18885),new MyInteger(95709),new MyInteger(37808),new MyInteger(2028),new MyInteger(36682),new MyInteger(76536),new MyInteger(2999),new MyInteger(98855),new MyInteger(8980),new MyInteger(87832),new MyInteger(95608),new MyInteger(61227) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(2),new MyInteger(5),new MyInteger(8),new MyInteger(11),new MyInteger(16),new MyInteger(18),new MyInteger(21),new MyInteger(23),new MyInteger(25),new MyInteger(29),new MyInteger(32),new MyInteger(35),new MyInteger(37),new MyInteger(39),new MyInteger(41),new MyInteger(43),new MyInteger(46),new MyInteger(49),new MyInteger(52),new MyInteger(54),new MyInteger(57) }),new Integer(58))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_27")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_28 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_28") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_28")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(12722),new MyInteger(12530),new MyInteger(33086),new MyInteger(81853),new MyInteger(48302),new MyInteger(38312),new MyInteger(68929),new MyInteger(53210),new MyInteger(67405),new MyInteger(72857),new MyInteger(7268),new MyInteger(64263),new MyInteger(80798),new MyInteger(82423),new MyInteger(64764),new MyInteger(36034),new MyInteger(97097),new MyInteger(98847),new MyInteger(90844),new MyInteger(69441),new MyInteger(66172),new MyInteger(82489),new MyInteger(13317),new MyInteger(2355),new MyInteger(41172),new MyInteger(53626),new MyInteger(54873) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(5),new MyInteger(7),new MyInteger(10),new MyInteger(15),new MyInteger(20),new MyInteger(23) }),new Integer(27))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_28")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_29 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_29") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_29")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(14339),new MyInteger(87621),new MyInteger(50683),new MyInteger(98916),new MyInteger(1496),new MyInteger(1348),new MyInteger(71602),new MyInteger(67997),new MyInteger(6023),new MyInteger(69217),new MyInteger(53227),new MyInteger(54077),new MyInteger(90732),new MyInteger(92606),new MyInteger(62539),new MyInteger(85313),new MyInteger(19890),new MyInteger(470),new MyInteger(14563),new MyInteger(68346),new MyInteger(9741),new MyInteger(5223),new MyInteger(47219),new MyInteger(20587) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(2),new MyInteger(5),new MyInteger(8),new MyInteger(10),new MyInteger(14),new MyInteger(17),new MyInteger(21),new MyInteger(23) }),new Integer(24))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_29")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_30 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_30") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_30")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(17033),new MyInteger(84094),new MyInteger(85919),new MyInteger(24428),new MyInteger(63331),new MyInteger(36271),new MyInteger(25706),new MyInteger(20346),new MyInteger(5118),new MyInteger(65546),new MyInteger(8682),new MyInteger(52411),new MyInteger(44846),new MyInteger(92031),new MyInteger(26293),new MyInteger(6653),new MyInteger(34421),new MyInteger(66333),new MyInteger(13673),new MyInteger(38371),new MyInteger(34589),new MyInteger(74737),new MyInteger(23781),new MyInteger(35653),new MyInteger(38147),new MyInteger(74501),new MyInteger(23368),new MyInteger(76521),new MyInteger(25490),new MyInteger(56811),new MyInteger(74168),new MyInteger(97406),new MyInteger(26425),new MyInteger(5548),new MyInteger(92499),new MyInteger(43136),new MyInteger(75850),new MyInteger(1540),new MyInteger(72221),new MyInteger(70840),new MyInteger(17710),new MyInteger(58545),new MyInteger(36147),new MyInteger(97775),new MyInteger(56609),new MyInteger(3539),new MyInteger(849),new MyInteger(31136),new MyInteger(93007),new MyInteger(4329),new MyInteger(85202),new MyInteger(65382),new MyInteger(96130),new MyInteger(58985),new MyInteger(29070),new MyInteger(18695),new MyInteger(2260),new MyInteger(51549),new MyInteger(17360),new MyInteger(82745),new MyInteger(21305),new MyInteger(5560),new MyInteger(79310),new MyInteger(58822) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(3),new MyInteger(8),new MyInteger(10),new MyInteger(12),new MyInteger(15),new MyInteger(18),new MyInteger(20),new MyInteger(22),new MyInteger(26),new MyInteger(28),new MyInteger(33),new MyInteger(35),new MyInteger(37),new MyInteger(40),new MyInteger(42),new MyInteger(46),new MyInteger(49),new MyInteger(51),new MyInteger(56),new MyInteger(58),new MyInteger(61),new MyInteger(63) }),new Integer(64))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_30")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_31 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_31") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_31")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(18727),new MyInteger(11751),new MyInteger(34809),new MyInteger(78403),new MyInteger(12151),new MyInteger(48758),new MyInteger(60107),new MyInteger(73965),new MyInteger(66517),new MyInteger(80892),new MyInteger(81863),new MyInteger(67809),new MyInteger(83927),new MyInteger(38759),new MyInteger(7000),new MyInteger(8213),new MyInteger(40753),new MyInteger(79823),new MyInteger(73521),new MyInteger(61756),new MyInteger(38712),new MyInteger(95177),new MyInteger(82535),new MyInteger(28653),new MyInteger(22211),new MyInteger(74869),new MyInteger(36916),new MyInteger(62806),new MyInteger(31433),new MyInteger(24388),new MyInteger(77542),new MyInteger(17910),new MyInteger(33477),new MyInteger(12613),new MyInteger(22139),new MyInteger(19526),new MyInteger(10692),new MyInteger(1665),new MyInteger(34188),new MyInteger(72835),new MyInteger(93955),new MyInteger(35134),new MyInteger(45456),new MyInteger(60454),new MyInteger(5960),new MyInteger(80956),new MyInteger(48886),new MyInteger(91111),new MyInteger(77870),new MyInteger(21488),new MyInteger(4191),new MyInteger(74943),new MyInteger(11900),new MyInteger(43091),new MyInteger(60325),new MyInteger(9966),new MyInteger(89483),new MyInteger(14509),new MyInteger(24972),new MyInteger(59234),new MyInteger(83774),new MyInteger(74368),new MyInteger(92316),new MyInteger(63087),new MyInteger(96821),new MyInteger(70244),new MyInteger(76497),new MyInteger(710),new MyInteger(12686),new MyInteger(31887) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(4),new MyInteger(8),new MyInteger(11),new MyInteger(14),new MyInteger(20),new MyInteger(24),new MyInteger(26),new MyInteger(29),new MyInteger(31),new MyInteger(33),new MyInteger(37),new MyInteger(41),new MyInteger(44),new MyInteger(46),new MyInteger(50),new MyInteger(52),new MyInteger(55),new MyInteger(57),new MyInteger(61),new MyInteger(63),new MyInteger(65),new MyInteger(67) }),new Integer(70))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_31")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_32 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_32") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_32")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(22949),new MyInteger(66724),new MyInteger(15078),new MyInteger(98194),new MyInteger(68502),new MyInteger(79913),new MyInteger(62497),new MyInteger(86779),new MyInteger(76569),new MyInteger(80789),new MyInteger(52096),new MyInteger(17218),new MyInteger(64566) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(2),new MyInteger(4),new MyInteger(6),new MyInteger(8),new MyInteger(11) }),new Integer(13))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_32")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_33 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_33") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_33")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(25691),new MyInteger(11369),new MyInteger(46717),new MyInteger(35562),new MyInteger(43357),new MyInteger(1779),new MyInteger(58790),new MyInteger(71155),new MyInteger(86931),new MyInteger(37600),new MyInteger(62164),new MyInteger(82122),new MyInteger(79705),new MyInteger(85954),new MyInteger(93739),new MyInteger(51152),new MyInteger(66821),new MyInteger(65485),new MyInteger(82665),new MyInteger(79656),new MyInteger(83345),new MyInteger(84742),new MyInteger(2416),new MyInteger(76537),new MyInteger(93784),new MyInteger(71242),new MyInteger(64100),new MyInteger(64775),new MyInteger(70940),new MyInteger(62658),new MyInteger(39873),new MyInteger(80946),new MyInteger(21666),new MyInteger(99246),new MyInteger(30207),new MyInteger(85116),new MyInteger(94000),new MyInteger(27775),new MyInteger(84790),new MyInteger(27779),new MyInteger(54876),new MyInteger(76052),new MyInteger(70790),new MyInteger(86467),new MyInteger(17386),new MyInteger(87671),new MyInteger(64091),new MyInteger(31406),new MyInteger(77919),new MyInteger(35211),new MyInteger(17265),new MyInteger(77568),new MyInteger(43033),new MyInteger(95333),new MyInteger(55957),new MyInteger(14394),new MyInteger(67790),new MyInteger(83117),new MyInteger(24343),new MyInteger(78520),new MyInteger(45789),new MyInteger(17406),new MyInteger(3458),new MyInteger(76621),new MyInteger(36844),new MyInteger(58349),new MyInteger(98181),new MyInteger(61140),new MyInteger(20528),new MyInteger(65430),new MyInteger(85880),new MyInteger(58047),new MyInteger(85246),new MyInteger(38615),new MyInteger(2740),new MyInteger(47572),new MyInteger(5942),new MyInteger(46035),new MyInteger(83465),new MyInteger(47696) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(3),new MyInteger(5),new MyInteger(9),new MyInteger(12),new MyInteger(15),new MyInteger(17),new MyInteger(19),new MyInteger(22),new MyInteger(26),new MyInteger(30),new MyInteger(32),new MyInteger(34),new MyInteger(37),new MyInteger(39),new MyInteger(42),new MyInteger(44),new MyInteger(47),new MyInteger(50),new MyInteger(52),new MyInteger(55),new MyInteger(58),new MyInteger(62),new MyInteger(64),new MyInteger(68),new MyInteger(71),new MyInteger(74),new MyInteger(76),new MyInteger(79) }),new Integer(80))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_33")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_34 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_34") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_34")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(27096),new MyInteger(11821),new MyInteger(78861),new MyInteger(60593),new MyInteger(50823),new MyInteger(44769),new MyInteger(37243),new MyInteger(52587),new MyInteger(72655),new MyInteger(13726),new MyInteger(78845),new MyInteger(40728),new MyInteger(96827),new MyInteger(56429),new MyInteger(44818),new MyInteger(15133),new MyInteger(19965),new MyInteger(27098),new MyInteger(86906),new MyInteger(28689),new MyInteger(68049),new MyInteger(74134),new MyInteger(50058),new MyInteger(2898),new MyInteger(37685),new MyInteger(51570),new MyInteger(45726),new MyInteger(69597),new MyInteger(20675),new MyInteger(45323),new MyInteger(17020),new MyInteger(13624),new MyInteger(24026),new MyInteger(60436),new MyInteger(63891),new MyInteger(84322),new MyInteger(3551),new MyInteger(10885),new MyInteger(20415),new MyInteger(52555),new MyInteger(58994),new MyInteger(73080),new MyInteger(14645),new MyInteger(85104),new MyInteger(39839),new MyInteger(44894),new MyInteger(95188),new MyInteger(54104),new MyInteger(91132),new MyInteger(22160),new MyInteger(70993),new MyInteger(22173),new MyInteger(88482),new MyInteger(34075),new MyInteger(95619),new MyInteger(75798),new MyInteger(81262),new MyInteger(8803),new MyInteger(72657),new MyInteger(12808),new MyInteger(12664),new MyInteger(56414),new MyInteger(44775),new MyInteger(28020),new MyInteger(2395),new MyInteger(73933),new MyInteger(11196),new MyInteger(76436),new MyInteger(27875),new MyInteger(45605),new MyInteger(73668),new MyInteger(70019) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(6),new MyInteger(9),new MyInteger(11),new MyInteger(15),new MyInteger(19),new MyInteger(23),new MyInteger(26),new MyInteger(28),new MyInteger(31),new MyInteger(36),new MyInteger(42),new MyInteger(44),new MyInteger(47),new MyInteger(49),new MyInteger(51),new MyInteger(53),new MyInteger(55),new MyInteger(57),new MyInteger(60),new MyInteger(64),new MyInteger(66),new MyInteger(68),new MyInteger(71) }),new Integer(72))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_34")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_35 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_35") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_35")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(27955),new MyInteger(26134),new MyInteger(4712),new MyInteger(60907),new MyInteger(87197),new MyInteger(67055),new MyInteger(63776),new MyInteger(37349),new MyInteger(46095),new MyInteger(20106),new MyInteger(96966),new MyInteger(7660),new MyInteger(9767),new MyInteger(69453),new MyInteger(99057),new MyInteger(779),new MyInteger(35615),new MyInteger(17569),new MyInteger(85024),new MyInteger(80711),new MyInteger(34010),new MyInteger(40084),new MyInteger(30762),new MyInteger(69572),new MyInteger(73646),new MyInteger(87623),new MyInteger(99219),new MyInteger(33397),new MyInteger(45200),new MyInteger(37060),new MyInteger(94567),new MyInteger(33339),new MyInteger(68018),new MyInteger(8838),new MyInteger(87682),new MyInteger(28261),new MyInteger(38505),new MyInteger(59749),new MyInteger(71679),new MyInteger(9936),new MyInteger(50659),new MyInteger(27568),new MyInteger(58134),new MyInteger(8148),new MyInteger(14741),new MyInteger(56666),new MyInteger(62319),new MyInteger(49664),new MyInteger(81722),new MyInteger(75447),new MyInteger(24092),new MyInteger(26831),new MyInteger(35103),new MyInteger(50926),new MyInteger(60435),new MyInteger(9527),new MyInteger(95447),new MyInteger(43532),new MyInteger(52319),new MyInteger(5327),new MyInteger(25313),new MyInteger(98326),new MyInteger(87837),new MyInteger(22223),new MyInteger(102),new MyInteger(4756),new MyInteger(33074),new MyInteger(66220),new MyInteger(83081),new MyInteger(93940),new MyInteger(72908),new MyInteger(34625),new MyInteger(14584),new MyInteger(25127),new MyInteger(50884),new MyInteger(93260) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(2),new MyInteger(7),new MyInteger(9),new MyInteger(11),new MyInteger(15),new MyInteger(17),new MyInteger(20),new MyInteger(22),new MyInteger(27),new MyInteger(29),new MyInteger(31),new MyInteger(33),new MyInteger(35),new MyInteger(39),new MyInteger(41),new MyInteger(43),new MyInteger(47),new MyInteger(50),new MyInteger(55),new MyInteger(57),new MyInteger(59),new MyInteger(64),new MyInteger(72) }),new Integer(76))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_35")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_36 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_36") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_36")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(32883),new MyInteger(37618),new MyInteger(57788),new MyInteger(94519),new MyInteger(1046),new MyInteger(56150),new MyInteger(35948),new MyInteger(12806),new MyInteger(2274),new MyInteger(34940),new MyInteger(92210),new MyInteger(12597),new MyInteger(77732),new MyInteger(34049),new MyInteger(81515),new MyInteger(28279),new MyInteger(80870),new MyInteger(44443),new MyInteger(92729),new MyInteger(36380),new MyInteger(52413),new MyInteger(76634),new MyInteger(60758),new MyInteger(6113),new MyInteger(97703),new MyInteger(8932),new MyInteger(65353),new MyInteger(45024),new MyInteger(59614),new MyInteger(87862),new MyInteger(93821),new MyInteger(51656),new MyInteger(86710),new MyInteger(32623),new MyInteger(53469),new MyInteger(29781),new MyInteger(15490),new MyInteger(76962),new MyInteger(56379),new MyInteger(59227),new MyInteger(84889),new MyInteger(54348),new MyInteger(52488),new MyInteger(65204),new MyInteger(25093),new MyInteger(29197),new MyInteger(78110),new MyInteger(81329),new MyInteger(47231),new MyInteger(50769),new MyInteger(76361),new MyInteger(82934),new MyInteger(49630),new MyInteger(80023),new MyInteger(60938),new MyInteger(75145),new MyInteger(79496),new MyInteger(23013),new MyInteger(64894),new MyInteger(64502),new MyInteger(18351),new MyInteger(97222),new MyInteger(28231),new MyInteger(13851),new MyInteger(30258),new MyInteger(89531),new MyInteger(58433),new MyInteger(21069),new MyInteger(69715),new MyInteger(49412),new MyInteger(13057),new MyInteger(7162),new MyInteger(35220),new MyInteger(12359),new MyInteger(3724),new MyInteger(2601),new MyInteger(35822),new MyInteger(82292),new MyInteger(29203),new MyInteger(54700),new MyInteger(84281) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(4),new MyInteger(8),new MyInteger(11),new MyInteger(13),new MyInteger(15),new MyInteger(17),new MyInteger(19),new MyInteger(23),new MyInteger(25),new MyInteger(27),new MyInteger(31),new MyInteger(33),new MyInteger(36),new MyInteger(38),new MyInteger(42),new MyInteger(44),new MyInteger(48),new MyInteger(52),new MyInteger(54),new MyInteger(57),new MyInteger(60),new MyInteger(63),new MyInteger(67),new MyInteger(71),new MyInteger(75),new MyInteger(78) }),new Integer(81))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_36")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_37 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_37") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_37")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(51688),new MyInteger(95209),new MyInteger(30034),new MyInteger(58750),new MyInteger(3040),new MyInteger(71132),new MyInteger(74280),new MyInteger(7538),new MyInteger(39151),new MyInteger(70071),new MyInteger(93568),new MyInteger(80475),new MyInteger(13478),new MyInteger(29413),new MyInteger(24824),new MyInteger(13255),new MyInteger(43607),new MyInteger(53488),new MyInteger(19452),new MyInteger(3768),new MyInteger(65173),new MyInteger(77796),new MyInteger(14660),new MyInteger(70895),new MyInteger(5085),new MyInteger(62200),new MyInteger(97278),new MyInteger(27989),new MyInteger(4228),new MyInteger(86543),new MyInteger(60534),new MyInteger(38708),new MyInteger(64809),new MyInteger(42153),new MyInteger(66659),new MyInteger(26168),new MyInteger(51603),new MyInteger(26179),new MyInteger(79417),new MyInteger(35797),new MyInteger(69780),new MyInteger(37476),new MyInteger(35543),new MyInteger(75628),new MyInteger(37348),new MyInteger(35948),new MyInteger(60569),new MyInteger(22989),new MyInteger(22184),new MyInteger(32210),new MyInteger(52503),new MyInteger(38653),new MyInteger(76593),new MyInteger(29952),new MyInteger(57407),new MyInteger(28051),new MyInteger(44401),new MyInteger(54262),new MyInteger(54309),new MyInteger(46806),new MyInteger(81981),new MyInteger(40824),new MyInteger(36302),new MyInteger(92103),new MyInteger(34966),new MyInteger(65071),new MyInteger(95133),new MyInteger(67878),new MyInteger(6301),new MyInteger(76541),new MyInteger(45755),new MyInteger(4539),new MyInteger(46518),new MyInteger(48490),new MyInteger(46779),new MyInteger(47859),new MyInteger(23050),new MyInteger(72697),new MyInteger(53116),new MyInteger(39630),new MyInteger(68827),new MyInteger(82874),new MyInteger(40006),new MyInteger(14627),new MyInteger(22002),new MyInteger(97540),new MyInteger(35674),new MyInteger(7027),new MyInteger(8107),new MyInteger(22195),new MyInteger(1546),new MyInteger(9713) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(2),new MyInteger(4),new MyInteger(7),new MyInteger(12),new MyInteger(15),new MyInteger(19),new MyInteger(22),new MyInteger(24),new MyInteger(28),new MyInteger(31),new MyInteger(33),new MyInteger(35),new MyInteger(37),new MyInteger(39),new MyInteger(42),new MyInteger(45),new MyInteger(48),new MyInteger(51),new MyInteger(53),new MyInteger(55),new MyInteger(59),new MyInteger(62),new MyInteger(64),new MyInteger(68),new MyInteger(71),new MyInteger(74),new MyInteger(76),new MyInteger(79),new MyInteger(83),new MyInteger(87),new MyInteger(90) }),new Integer(92))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_37")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_38 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_38") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_38")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(54602),new MyInteger(23514),new MyInteger(99996),new MyInteger(1901),new MyInteger(34986),new MyInteger(30992),new MyInteger(25533),new MyInteger(96114),new MyInteger(50333),new MyInteger(3723),new MyInteger(74691),new MyInteger(82534),new MyInteger(4567),new MyInteger(30502),new MyInteger(31338),new MyInteger(38041),new MyInteger(21814),new MyInteger(21635),new MyInteger(56343),new MyInteger(24526),new MyInteger(56157),new MyInteger(1164),new MyInteger(59081),new MyInteger(88740),new MyInteger(8198),new MyInteger(67305),new MyInteger(21257),new MyInteger(80752),new MyInteger(72981),new MyInteger(98935),new MyInteger(56615),new MyInteger(32192),new MyInteger(41506),new MyInteger(71200),new MyInteger(23715),new MyInteger(21379),new MyInteger(19349),new MyInteger(81918),new MyInteger(63637),new MyInteger(57336),new MyInteger(9932),new MyInteger(60775),new MyInteger(65),new MyInteger(82760),new MyInteger(22069),new MyInteger(97962),new MyInteger(51620),new MyInteger(4755),new MyInteger(67658),new MyInteger(58167),new MyInteger(19997),new MyInteger(23147),new MyInteger(57352),new MyInteger(4221),new MyInteger(39567),new MyInteger(92457),new MyInteger(178),new MyInteger(90911),new MyInteger(30667),new MyInteger(8382),new MyInteger(94144),new MyInteger(96605),new MyInteger(60849),new MyInteger(25120),new MyInteger(34095),new MyInteger(73071),new MyInteger(98797),new MyInteger(66229),new MyInteger(51872),new MyInteger(16199),new MyInteger(24856),new MyInteger(50149),new MyInteger(23260),new MyInteger(38055),new MyInteger(86577),new MyInteger(31640),new MyInteger(74938),new MyInteger(92170),new MyInteger(96189),new MyInteger(73949) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(3),new MyInteger(6),new MyInteger(9),new MyInteger(12),new MyInteger(17),new MyInteger(19),new MyInteger(21),new MyInteger(24),new MyInteger(26),new MyInteger(28),new MyInteger(31),new MyInteger(36),new MyInteger(40),new MyInteger(42),new MyInteger(44),new MyInteger(47),new MyInteger(50),new MyInteger(53),new MyInteger(56),new MyInteger(59),new MyInteger(63),new MyInteger(69),new MyInteger(72),new MyInteger(75),new MyInteger(79) }),new Integer(80))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_38")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_39 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_39") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_39")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(63409),new MyInteger(76191),new MyInteger(12337),new MyInteger(84810),new MyInteger(49814),new MyInteger(42736),new MyInteger(81651),new MyInteger(62560),new MyInteger(40403),new MyInteger(94196),new MyInteger(35023),new MyInteger(95941),new MyInteger(8254),new MyInteger(82216),new MyInteger(92863),new MyInteger(87279),new MyInteger(88895),new MyInteger(25410),new MyInteger(50736),new MyInteger(47366),new MyInteger(72100),new MyInteger(16691),new MyInteger(99991),new MyInteger(55372),new MyInteger(98835),new MyInteger(95971),new MyInteger(10672),new MyInteger(83788),new MyInteger(5922),new MyInteger(71895),new MyInteger(43542),new MyInteger(98492),new MyInteger(39768),new MyInteger(58827),new MyInteger(56700) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(2),new MyInteger(5),new MyInteger(8),new MyInteger(10),new MyInteger(12),new MyInteger(15),new MyInteger(17),new MyInteger(19),new MyInteger(21),new MyInteger(23),new MyInteger(26),new MyInteger(28),new MyInteger(30),new MyInteger(32),new MyInteger(34) }),new Integer(35))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_39")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_40 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_40") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_40")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(65984),new MyInteger(28985),new MyInteger(37032),new MyInteger(35019),new MyInteger(78415),new MyInteger(62287),new MyInteger(52431),new MyInteger(63002),new MyInteger(90212),new MyInteger(97277),new MyInteger(21335),new MyInteger(77998),new MyInteger(16269),new MyInteger(8015),new MyInteger(90371),new MyInteger(75190),new MyInteger(38298),new MyInteger(90900),new MyInteger(66858),new MyInteger(18207),new MyInteger(68161),new MyInteger(3749),new MyInteger(71199),new MyInteger(54147),new MyInteger(83876),new MyInteger(53332),new MyInteger(5371),new MyInteger(32280),new MyInteger(40227),new MyInteger(44082),new MyInteger(96433),new MyInteger(93263),new MyInteger(98763) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(3),new MyInteger(6),new MyInteger(10),new MyInteger(13),new MyInteger(16),new MyInteger(19),new MyInteger(21),new MyInteger(23),new MyInteger(26),new MyInteger(31) }),new Integer(33))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_40")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_41 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_41") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_41")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(74466),new MyInteger(8863),new MyInteger(35567),new MyInteger(83874),new MyInteger(1500),new MyInteger(15501),new MyInteger(92752),new MyInteger(34913),new MyInteger(42971),new MyInteger(89332),new MyInteger(18490),new MyInteger(55602),new MyInteger(80029),new MyInteger(61146),new MyInteger(99580),new MyInteger(73592),new MyInteger(16936),new MyInteger(95022),new MyInteger(77104),new MyInteger(28081),new MyInteger(85337),new MyInteger(62108),new MyInteger(55406),new MyInteger(73652),new MyInteger(1946),new MyInteger(69998),new MyInteger(33843),new MyInteger(9830),new MyInteger(10967),new MyInteger(56515),new MyInteger(14012),new MyInteger(75313),new MyInteger(20291),new MyInteger(74911),new MyInteger(91128),new MyInteger(3873),new MyInteger(42371),new MyInteger(40961),new MyInteger(91784),new MyInteger(46620),new MyInteger(25685),new MyInteger(57193),new MyInteger(88076),new MyInteger(94149),new MyInteger(20220),new MyInteger(51396),new MyInteger(98399),new MyInteger(90878),new MyInteger(79317),new MyInteger(5077),new MyInteger(84986),new MyInteger(42433),new MyInteger(84050),new MyInteger(90749),new MyInteger(5903),new MyInteger(7040),new MyInteger(36283),new MyInteger(49684),new MyInteger(78676),new MyInteger(28324),new MyInteger(2275) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(4),new MyInteger(7),new MyInteger(10),new MyInteger(13),new MyInteger(16),new MyInteger(19),new MyInteger(22),new MyInteger(24),new MyInteger(27),new MyInteger(30),new MyInteger(32),new MyInteger(35),new MyInteger(37),new MyInteger(40),new MyInteger(44),new MyInteger(49),new MyInteger(51),new MyInteger(54),new MyInteger(60) }),new Integer(61))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_41")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_42 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_42") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_42")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(76373),new MyInteger(36005),new MyInteger(8066),new MyInteger(43043),new MyInteger(10986),new MyInteger(56393),new MyInteger(81536),new MyInteger(76437),new MyInteger(76401),new MyInteger(36591),new MyInteger(12405),new MyInteger(9770),new MyInteger(78103),new MyInteger(56107),new MyInteger(24579),new MyInteger(15632),new MyInteger(78903),new MyInteger(73562),new MyInteger(28670),new MyInteger(18226),new MyInteger(40016),new MyInteger(44814),new MyInteger(27895),new MyInteger(64333),new MyInteger(53898),new MyInteger(39215),new MyInteger(69201),new MyInteger(51642),new MyInteger(41138),new MyInteger(87158),new MyInteger(47954),new MyInteger(6873),new MyInteger(62966),new MyInteger(94014),new MyInteger(96940),new MyInteger(9155),new MyInteger(33951),new MyInteger(1019),new MyInteger(2070),new MyInteger(26458),new MyInteger(59768),new MyInteger(13435),new MyInteger(25848),new MyInteger(86695),new MyInteger(55485),new MyInteger(6452),new MyInteger(41413),new MyInteger(9284),new MyInteger(40490),new MyInteger(8629),new MyInteger(29113),new MyInteger(40941),new MyInteger(86035),new MyInteger(78984),new MyInteger(23036),new MyInteger(65297),new MyInteger(45361),new MyInteger(76924),new MyInteger(18735),new MyInteger(73505),new MyInteger(78803),new MyInteger(55920),new MyInteger(72922),new MyInteger(53586),new MyInteger(45310),new MyInteger(44877),new MyInteger(58351),new MyInteger(56056),new MyInteger(70982),new MyInteger(10699),new MyInteger(4443),new MyInteger(66205),new MyInteger(50689),new MyInteger(75241),new MyInteger(81422),new MyInteger(21865),new MyInteger(63911),new MyInteger(36821),new MyInteger(94440),new MyInteger(47),new MyInteger(42004),new MyInteger(34588),new MyInteger(23593),new MyInteger(19555),new MyInteger(71195),new MyInteger(88394),new MyInteger(87377),new MyInteger(43676),new MyInteger(73341),new MyInteger(56220),new MyInteger(31090),new MyInteger(1192) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(2),new MyInteger(4),new MyInteger(11),new MyInteger(15),new MyInteger(19),new MyInteger(22),new MyInteger(25),new MyInteger(28),new MyInteger(31),new MyInteger(35),new MyInteger(37),new MyInteger(41),new MyInteger(45),new MyInteger(47),new MyInteger(49),new MyInteger(54),new MyInteger(56),new MyInteger(58),new MyInteger(61),new MyInteger(65),new MyInteger(67),new MyInteger(70),new MyInteger(72),new MyInteger(75),new MyInteger(77),new MyInteger(79),new MyInteger(83),new MyInteger(87),new MyInteger(91) }),new Integer(92))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_42")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_43 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_43") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_43")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(79383),new MyInteger(76748),new MyInteger(6818),new MyInteger(22387),new MyInteger(15033),new MyInteger(49267),new MyInteger(4834),new MyInteger(17224),new MyInteger(90030),new MyInteger(33361),new MyInteger(48070),new MyInteger(29295),new MyInteger(8078),new MyInteger(48157),new MyInteger(82041),new MyInteger(10793),new MyInteger(20644),new MyInteger(54543),new MyInteger(88291),new MyInteger(31151),new MyInteger(79044),new MyInteger(27957),new MyInteger(45941),new MyInteger(18119),new MyInteger(34631),new MyInteger(48953),new MyInteger(76227),new MyInteger(46474),new MyInteger(20376),new MyInteger(69957),new MyInteger(40111),new MyInteger(20390),new MyInteger(65315),new MyInteger(20845),new MyInteger(97152),new MyInteger(58887),new MyInteger(83571),new MyInteger(29124),new MyInteger(18177),new MyInteger(24508),new MyInteger(85043),new MyInteger(41191),new MyInteger(31235),new MyInteger(39901),new MyInteger(39623),new MyInteger(87373),new MyInteger(45793),new MyInteger(23734),new MyInteger(78751),new MyInteger(1972),new MyInteger(8101) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(2),new MyInteger(4),new MyInteger(6),new MyInteger(9),new MyInteger(12),new MyInteger(15),new MyInteger(19),new MyInteger(21),new MyInteger(23),new MyInteger(28),new MyInteger(31),new MyInteger(33),new MyInteger(35),new MyInteger(38),new MyInteger(42),new MyInteger(44),new MyInteger(47),new MyInteger(49) }),new Integer(51))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_43")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_44 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_44") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_44")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(93352),new MyInteger(90708),new MyInteger(65142),new MyInteger(65970),new MyInteger(20541),new MyInteger(87005),new MyInteger(51908),new MyInteger(52119),new MyInteger(30612),new MyInteger(5297),new MyInteger(81711),new MyInteger(1920),new MyInteger(18430),new MyInteger(83585),new MyInteger(36609),new MyInteger(18470),new MyInteger(95829),new MyInteger(1635),new MyInteger(35345),new MyInteger(54415) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(2),new MyInteger(4),new MyInteger(6),new MyInteger(9),new MyInteger(11),new MyInteger(15),new MyInteger(17) }),new Integer(20))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_44")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_45 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_45") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_45")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(94696),new MyInteger(35230),new MyInteger(66677),new MyInteger(23076),new MyInteger(22244),new MyInteger(80906),new MyInteger(86348),new MyInteger(15158),new MyInteger(2673),new MyInteger(47861),new MyInteger(9279),new MyInteger(14285),new MyInteger(73790),new MyInteger(48716),new MyInteger(29234),new MyInteger(97113),new MyInteger(1360),new MyInteger(72687),new MyInteger(18262),new MyInteger(50560),new MyInteger(76175),new MyInteger(63783),new MyInteger(77259),new MyInteger(97468),new MyInteger(7027),new MyInteger(64471),new MyInteger(90014),new MyInteger(5884),new MyInteger(7829),new MyInteger(46495),new MyInteger(24964),new MyInteger(65036),new MyInteger(54780),new MyInteger(65003),new MyInteger(22347),new MyInteger(84268),new MyInteger(11092),new MyInteger(37439),new MyInteger(59978),new MyInteger(39174),new MyInteger(81193),new MyInteger(4357),new MyInteger(69461),new MyInteger(17914),new MyInteger(9039),new MyInteger(58766),new MyInteger(88322),new MyInteger(76860),new MyInteger(83984),new MyInteger(38745),new MyInteger(45014),new MyInteger(85830),new MyInteger(34216),new MyInteger(98952),new MyInteger(17907),new MyInteger(76591),new MyInteger(21785),new MyInteger(64102),new MyInteger(40551),new MyInteger(38546),new MyInteger(88561),new MyInteger(15262),new MyInteger(74159),new MyInteger(82042),new MyInteger(18976),new MyInteger(94943),new MyInteger(58217),new MyInteger(68029),new MyInteger(42690),new MyInteger(67589),new MyInteger(60605),new MyInteger(4825),new MyInteger(1595),new MyInteger(2481),new MyInteger(89331),new MyInteger(97970),new MyInteger(41429),new MyInteger(19223),new MyInteger(8518),new MyInteger(97561),new MyInteger(16564),new MyInteger(97432),new MyInteger(85949),new MyInteger(93303),new MyInteger(3659),new MyInteger(45534),new MyInteger(65895),new MyInteger(8460),new MyInteger(24118),new MyInteger(63692) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(1),new MyInteger(4),new MyInteger(8),new MyInteger(10),new MyInteger(14),new MyInteger(16),new MyInteger(18),new MyInteger(21),new MyInteger(24),new MyInteger(27),new MyInteger(30),new MyInteger(32),new MyInteger(34),new MyInteger(36),new MyInteger(39),new MyInteger(41),new MyInteger(44),new MyInteger(47),new MyInteger(49),new MyInteger(52),new MyInteger(54),new MyInteger(56),new MyInteger(59),new MyInteger(61),new MyInteger(64),new MyInteger(66),new MyInteger(68),new MyInteger(72),new MyInteger(78),new MyInteger(80),new MyInteger(82),new MyInteger(84),new MyInteger(87) }),new Integer(90))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_45")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_46 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_46") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_46")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(95910),new MyInteger(67412),new MyInteger(51609),new MyInteger(2362),new MyInteger(64403),new MyInteger(89397),new MyInteger(99384),new MyInteger(20884),new MyInteger(78407),new MyInteger(30405),new MyInteger(54900),new MyInteger(42420),new MyInteger(56036),new MyInteger(11945),new MyInteger(94106),new MyInteger(81333),new MyInteger(74005),new MyInteger(19797),new MyInteger(25419),new MyInteger(28020),new MyInteger(6371),new MyInteger(13580),new MyInteger(95462),new MyInteger(23183),new MyInteger(37324),new MyInteger(68860),new MyInteger(23791),new MyInteger(58552),new MyInteger(27850),new MyInteger(11164),new MyInteger(42797),new MyInteger(37786) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(3),new MyInteger(7),new MyInteger(9),new MyInteger(11),new MyInteger(13),new MyInteger(17),new MyInteger(20),new MyInteger(23),new MyInteger(26),new MyInteger(29),new MyInteger(31) }),new Integer(32))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_46")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_47 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_47") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_47")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0),new MyInteger(1),new MyInteger(2),new MyInteger(4),new MyInteger(5),new MyInteger(6),new MyInteger(7),new MyInteger(8),new MyInteger(9),new MyInteger(10),new MyInteger(11),new MyInteger(12),new MyInteger(13),new MyInteger(14),new MyInteger(15),new MyInteger(16),new MyInteger(17),new MyInteger(18),new MyInteger(19),new MyInteger(20),new MyInteger(21),new MyInteger(22),new MyInteger(23),new MyInteger(24),new MyInteger(25),new MyInteger(26),new MyInteger(27),new MyInteger(28),new MyInteger(29),new MyInteger(30) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(0) }),new Integer(30))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_47")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_48 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_48") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_48")) ;
if (ok_sofar)
  ok_sofar = new FindBottom(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(30),new MyInteger(29),new MyInteger(28),new MyInteger(27),new MyInteger(26),new MyInteger(25),new MyInteger(24),new MyInteger(23),new MyInteger(22),new MyInteger(21),new MyInteger(20),new MyInteger(19),new MyInteger(18),new MyInteger(17),new MyInteger(16),new MyInteger(15),new MyInteger(14),new MyInteger(13),new MyInteger(12),new MyInteger(11),new MyInteger(10),new MyInteger(9),new MyInteger(8),new MyInteger(7),new MyInteger(6),new MyInteger(5),new MyInteger(4),new MyInteger(3),new MyInteger(2),new MyInteger(1) })).doCall().checkResult(new es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>(new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(new MyInteger[] { new MyInteger(29) }),new Integer(30))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_48")) ;
    ResultsHandler.add_result(new String("findBottom"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_49 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_49") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_49")) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_49")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_50 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_50") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_50")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_50")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_51 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_51") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_51")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_51")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_52 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_52") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_52")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(6))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_52")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_53 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_53") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_53")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_53")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_54 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_54") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_54")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_54")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_55 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_55") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_55")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(4))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_55")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_56 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_56") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_56")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(5))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_56")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_57 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_57") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_57")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(5))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_57")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_58 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_58") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_58")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(6))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_58")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_59 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_59") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_59")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(5))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_59")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_60 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_60") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_60")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(4))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_60")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_61 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_61") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_61")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_61")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_62 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_62") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_62")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(5)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_62")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_63 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_63") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_63")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_63")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_64 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_64") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_64")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_64")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_65 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_65") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_65")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_65")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_66 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_66") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_66")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(3))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_66")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_67 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_67") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_67")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(6)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(4))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_67")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_68 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_68") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_68")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(2)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 97),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(3)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 99),new Integer(4)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 100),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_68")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_69 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_69") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_69")) ;
if (ok_sofar)
  ok_sofar = new JoinMultiSets(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2)))),new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1))))).doCall().checkResult(new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(TestUtils.toGenericArray(new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 98),new Integer(1)),new es.upm.aedlib.Pair<Character,Integer>(new Character((char) 101),new Integer(2))))) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_69")) ;
    ResultsHandler.add_result(new String("joinMultiSets"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_70 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_70") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_70")) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_70")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_71 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_71") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_71")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,true,true,false,true),new Punto(null,true,true,true,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,false),new Punto(null,true,true,false,false) }, { new Punto(null,false,true,false,false),new Punto(null,false,true,false,true),new Punto(null,false,false,true,false),new Punto(41,false,false,false,true),new Punto(null,false,true,true,false) } },0,0)).doCall().checkResult(new Integer(41)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_71")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_72 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_72") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_72")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,true),new Punto(92,true,false,true,false),new Punto(null,true,false,false,false) }, { new Punto(null,true,true,false,true),new Punto(null,false,true,true,false),new Punto(null,true,true,false,true),new Punto(null,false,true,true,false),new Punto(null,true,true,false,true),new Punto(null,true,true,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false) } },0,0)).doCall().checkResult(new Integer(92)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_72")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_73 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_73") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_73")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,false) }, { new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),new Punto(null,true,true,true,true),new Punto(null,false,true,true,false) }, { new Punto(null,true,false,false,true),new Punto(null,true,false,true,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,true),new Punto(null,true,true,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,false,true,true,true),new Punto(87,false,true,true,false),new Punto(null,false,true,false,false) } },0,0)).doCall().checkResult(new Integer(87)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_73")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_74 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_74") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_74")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,true,false,false,true),new Punto(null,true,true,true,true),new Punto(null,true,true,true,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false) }, { new Punto(null,false,true,false,true),new Punto(null,true,true,true,true),new Punto(null,false,false,true,false) }, { new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,false,false,false) }, { new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false) }, { new Punto(70,false,true,false,true),new Punto(null,true,false,true,true),new Punto(null,false,true,true,false) }, { new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,false) } },0,0)).doCall().checkResult(new Integer(70)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_74")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_75 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_75") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_75")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,false,true,true,false),new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),new Punto(null,true,true,true,false) }, { new Punto(null,true,false,false,true),new Punto(null,false,false,true,false),new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,false,true,true,false) }, { new Punto(60,true,true,false,false),new Punto(null,false,false,false,true),new Punto(null,true,true,true,true),new Punto(null,false,true,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false) } },0,0)).doCall().checkResult(new Integer(60)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_75")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_76 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_76") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_76")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,false,false,true),new Punto(null,false,false,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,false),new Punto(20,true,false,false,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,false),new Punto(null,true,true,false,false) }, { new Punto(null,false,false,false,true),new Punto(null,true,false,true,false),new Punto(null,true,true,false,false),new Punto(null,true,true,false,false) }, { new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,false),new Punto(null,true,true,false,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,false,true,true,false) }, { new Punto(null,false,true,false,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,false) }, { new Punto(null,false,false,false,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,false),new Punto(null,false,false,false,false) } },0,0)).doCall().checkResult(new Integer(20)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_76")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_77 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_77") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_77")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,true,false,true,false),new Punto(null,false,false,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,true,false,false),new Punto(null,true,true,false,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,false,true,true,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,true),new Punto(null,true,true,true,false),new Punto(56,true,false,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,true),new Punto(null,true,true,true,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,false,false,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false),new Punto(null,false,true,false,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,false) } },0,0)).doCall().checkResult(new Integer(56)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_77")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_78 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_78") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_78")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,true,false,false,false),new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,true),new Punto(null,true,true,true,true),new Punto(null,false,true,true,false),new Punto(null,true,false,false,false),new Punto(null,true,false,false,false),new Punto(null,true,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,false) }, { new Punto(null,true,false,false,true),new Punto(null,false,true,true,false),new Punto(null,false,false,false,false),new Punto(null,true,true,false,false),new Punto(null,true,true,false,true),new Punto(null,true,true,true,false),new Punto(null,false,true,false,true),new Punto(null,false,false,true,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,true),new Punto(null,true,true,true,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,false) }, { new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,false,true,true,true),new Punto(null,true,false,true,false),new Punto(null,false,true,false,false),new Punto(50,true,false,false,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,false,false,true),new Punto(null,true,false,true,false),new Punto(null,true,true,false,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false) }, { new Punto(null,true,true,false,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,false),new Punto(null,false,false,false,false),new Punto(null,true,false,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,true),new Punto(null,true,true,true,false),new Punto(null,true,true,false,false),new Punto(null,true,true,false,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false),new Punto(null,false,false,false,true),new Punto(null,false,true,true,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false) } },0,0)).doCall().checkResult(new Integer(50)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_78")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_79 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_79") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_79")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,false),new Punto(null,false,false,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,false) }, { new Punto(null,false,false,false,false),new Punto(null,true,false,false,false),new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,true,false,false),new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,true,false,false,false),new Punto(null,true,true,false,true),new Punto(null,false,true,true,false),new Punto(null,true,true,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,false),new Punto(null,true,false,false,true),new Punto(null,false,true,true,false) }, { new Punto(null,true,true,false,false),new Punto(null,true,true,false,false),new Punto(null,true,false,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false),new Punto(null,false,false,false,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,true,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false),new Punto(null,false,false,false,true),new Punto(null,false,true,true,false),new Punto(null,true,false,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false) }, { new Punto(73,false,false,false,true),new Punto(null,true,true,true,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false),new Punto(null,true,true,false,false),new Punto(null,false,false,false,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,true),new Punto(null,true,false,true,false),new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,false,false,false),new Punto(null,false,false,false,true),new Punto(null,false,true,true,false),new Punto(null,false,true,false,true),new Punto(null,false,false,true,false),new Punto(null,false,false,false,true),new Punto(null,false,false,true,false),new Punto(null,false,true,false,false) } },0,0)).doCall().checkResult(new Integer(73)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_79")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  
  @Test
  public void test_80 () 
  {
    if (ResultsHandler.isTimedout())
    {
      Assertions.assertTrue(false,new String("Testing stopped due to global timeout -- too slow execution")) ;
    }
    boolean resultIs = true ;
    try
    {
      resultIs = Assertions.assertTimeoutPreemptively(Duration.ofSeconds(10), ()  -> {
TesterCode.resetPrinter() ;
TestData.initTrace() ;
TestData.testName = new String("test_80") ;
boolean ok_sofar = true ;
ResultsHandler.startTest(new String("test_80")) ;
if (ok_sofar)
  ok_sofar = new Explora(new Lugar(new Punto[][] { { new Punto(null,false,false,false,true),new Punto(null,true,false,true,false),new Punto(null,false,false,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,false) }, { new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,false,false,false),new Punto(null,true,true,false,false),new Punto(null,false,false,false,false),new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,false,false,false) }, { new Punto(null,false,true,false,true),new Punto(null,true,true,true,true),new Punto(null,false,false,true,true),new Punto(null,false,true,true,false),new Punto(null,true,false,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,false) }, { new Punto(null,false,false,false,false),new Punto(null,false,true,false,false),new Punto(null,true,false,false,true),new Punto(null,false,false,true,true),new Punto(null,true,true,true,true),new Punto(null,true,true,true,true),new Punto(null,false,false,true,true),new Punto(null,true,true,true,false) }, { new Punto(null,false,false,false,true),new Punto(null,true,false,true,false),new Punto(null,true,true,false,true),new Punto(null,true,false,true,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,true),new Punto(null,false,false,true,false),new Punto(null,true,true,false,false) }, { new Punto(null,true,false,false,false),new Punto(null,true,true,false,false),new Punto(null,false,true,false,true),new Punto(null,true,true,true,true),new Punto(null,true,false,true,true),new Punto(null,false,false,true,true),new Punto(null,true,false,true,true),new Punto(null,true,true,true,false) }, { new Punto(null,true,true,false,true),new Punto(null,false,true,true,false),new Punto(null,true,false,false,true),new Punto(null,true,true,true,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,true),new Punto(null,false,true,true,false),new Punto(null,true,true,false,false) }, { new Punto(null,false,true,false,false),new Punto(null,false,false,false,true),new Punto(null,true,true,true,true),new Punto(null,true,true,true,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,true),new Punto(null,false,true,true,false) }, { new Punto(null,false,false,false,true),new Punto(null,true,false,true,false),new Punto(null,true,true,false,true),new Punto(null,true,true,true,true),new Punto(null,true,true,true,false),new Punto(null,true,false,false,false),new Punto(null,false,true,false,true),new Punto(null,true,false,true,false) }, { new Punto(null,false,false,false,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,true),new Punto(null,false,true,true,false),new Punto(null,false,true,false,false),new Punto(null,false,true,false,false),new Punto(45,false,false,false,true),new Punto(null,false,true,true,false) } },0,0)).doCall().checkResult(new Integer(45)) ;
return ok_sofar ;
}
) ;
    }
    catch ( org.opentest4j.AssertionFailedError exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.DURING,new String("*** TIMEOUT! *** Very slow or non-terminating execution"),exc) ;
      resultIs = false ;
    }
    catch ( Throwable exc )
    {
      TestUtils.printCallException(TestUtils.ExecutionTime.UNRELATED,new String("Internal testing error; please report this to teachers"),exc) ;
      resultIs = false ;
    }
    String messages = TestData.getMessages() ;
    ResultsHandler.stopTest(new String("test_80")) ;
    ResultsHandler.add_result(new String("explora"),resultIs) ;
    if (messages.length() > 0)
      System.out.println(messages) ;
    if (!resultIs)
      Assertions.assertTrue(resultIs,messages) ;
  }
  


static class Tests {
  static String tester = "TesterLab3";
}




static class MazeChecker {

  static boolean checkExplore(Explora call,
                              TestResult<Pair<Object,PositionList<Lugar>>,Integer> result,
                              Integer expected)
  {
    boolean ok_sofar = true;
    
    if (!call.noException() || !call.nonNull())
      ok_sofar = false;

    if (ok_sofar) {
      Pair<Object,PositionList<Lugar>> pair =
	result.getValue();

      if (pair == null)
	ok_sofar = (expected == null);

      if (!ok_sofar) {
	TestUtils.printError
	  (TestUtils.ExecutionTime.UNRELATED,
	   "the call "+call+" returned null"+
	   " but should have returned a pair with a tesoro "+expected);
      }
      
      if (pair != null) {
	Object treasure = pair.getLeft();
	PositionList<Lugar> path = pair.getRight();
	
	if (treasure != null)
	  ok_sofar = ok_sofar && treasure.equals(expected);
	else
	  ok_sofar = false;
	
	if (!ok_sofar) {
	  TestUtils.printError
	    (TestUtils.ExecutionTime.UNRELATED,
	     "the call "+call+" returned the tesoro "+treasure+
	     " but should have returned "+expected);
	}
      
	if (ok_sofar && path == null) {
	  TestUtils.printError
	    (TestUtils.ExecutionTime.UNRELATED,
	     "the call "+call+" returned the path null");
	  ok_sofar = false;
	}
	
	if (ok_sofar) {
	  ok_sofar =
	    checkPCPath(path, treasure, call.x_1.cueva, call.toString());
	}
      }
    }
    
    if (!ok_sofar) {
      TestData.message("\nLaberinto inicial: (x marca la posicion inicial, $ el tesoro) ");
      TestData.message(Punto.printPuntos(call.x_1_orig.cueva,0,0));
      TestData.message("\nLaberinto despues de la exploracion ($ el tesoro, y con simbol de tiza \".\"):");
      TestData.message(Punto.printPuntos(call.x_1.cueva));
      return false;
    }
    return ok_sofar;
  }


  static boolean checkPCPath(PositionList<Lugar> path, Object treasure, Punto[][] puntos, String callString) {
    Lugar currentLugar = new Lugar(puntos,0,0);
    Position<Lugar> cursor = path.first();

    if (cursor != null) {
      Lugar pathLugar = cursor.element();
      if (!currentLugar.equals(pathLugar)) {
        TestUtils.printError
          (TestUtils.ExecutionTime.UNRELATED,
           "the call "+callString+" returned a path "+path+" with the initial lugar "+pathLugar+"; should be (0,0)");
        return false;
      }
    }

    if (cursor == null) {
        TestUtils.printError
          (TestUtils.ExecutionTime.UNRELATED,
           "the call "+callString+" returned an empty path; at least one position is required");
        return false;
    }

    while (cursor != path.last()) {

      Lugar pathLugar = cursor.element();
      if (pathLugar == null) {
	TestUtils.printError
	  (TestUtils.ExecutionTime.UNRELATED,
	   "the call "+callString+" returned a path "+path+
	   " that contains a null element");
	return false;
      }

      cursor = path.next(cursor);
      Lugar nextPathLugar = cursor.element();
      boolean found = false;

      for (Lugar nextLugar : currentLugar.caminos()) {
	if (nextPathLugar.equals(nextLugar)) {
	  found = true;
          currentLugar = nextLugar;
        }
      }

      if (!found) {
	TestUtils.printError
	  (TestUtils.ExecutionTime.UNRELATED,
	   "the call "+callString+" returned a path "+path+
	   " which cannot be taken; cannot find a valid path between "+pathLugar+" and "+nextPathLugar);
	return false;
      }
    }

    if (!currentLugar.tieneTesoro()) {
	TestUtils.printError
	  (TestUtils.ExecutionTime.UNRELATED,
	   "the call "+callString+" returned a path "+path+
	   " which terminates in a lugar "+
	   " which does not contain any tesoro ");
	return false;
    }

    if (!currentLugar.getTesoro().equals(treasure)) {
	TestUtils.printError
	  (TestUtils.ExecutionTime.UNRELATED,
	   "the call "+callString+" returned a path "+path+
	   " which terminates in a lugar "+
	   " which has a tesoro "+currentLugar.getTesoro()+
	   " which is distinct from the tesoro "+
	   " returned by the call to "+callString);
	return false;
    }
    return true;
  }
}




static class CheckRecursion {

  // Try to determine whether the starterName method is recursive.
  // If the bcel library is not available, fail with a message
  // indicating that no analysis was done. 
    @SuppressWarnings({"unchecked","rawtypes"}) 
    static boolean recursionCheck(String packageName, String className, String starterName, int arity) {
    try {
      String fullClassName =
        packageName+"."+className;
      Class<?> cl =
        Class.forName(fullClassName).getClass();
      String path =
        new String("classes"+'/'+packageName+"."+className).replace('.','/')+".class";
    
      java.lang.Class classParserClass;
      try {
	classParserClass = java.lang.Class.forName("org.apache.bcel.classfile.ClassParser");
      } catch (Throwable exc) {
        return true;
      }

      java.lang.reflect.Constructor classParserConstructor =
	classParserClass.getConstructor(path.getClass());
      java.lang.reflect.Method parse =
	classParserClass.getMethod("parse");

      Object cp = null;
      Object jc = null;
    
      cp = classParserConstructor.newInstance(path);
      jc = parse.invoke(cp);

      Hashtable<Pair<String,Integer>,HashSet<Pair<String,Integer>>> calls =
        new Hashtable<Pair<String,Integer>,HashSet<Pair<String,Integer>>>();

      java.lang.Class ccfgClass =
	java.lang.Class.forName(packageName+".ClassCFG");
      java.lang.reflect.Constructor ccfgConstructor =
	ccfgClass.getConstructor(jc.getClass(), calls.getClass());

      Object ccfg = ccfgConstructor.newInstance(jc, calls);
      java.lang.reflect.Method start = ccfgClass.getMethod("start");
      start.invoke(ccfg);

      boolean result = 
        isRecursive
        (new Pair<String,Integer>
         (packageName+"."+className+"."+starterName,arity),
         new HashSet<Pair<String,Integer>>(),
         calls);
      
      if (!result)
	TestUtils.printWarning(TestUtils.ExecutionTime.UNRELATED,
	  "la implementacion de "+starterName+
	   " no parece recursivo aunque es OBLIGATORIO "+
	   "-- compruebalo manualmente!\n");

      try {
        java.lang.Class rhClass =
          java.lang.Class.forName(packageName+".ResultsHandler");
        java.lang.reflect.Field completedAnalysis =
          rhClass.getField("completedAnalysis");
        completedAnalysis.setBoolean(null,true);
      } catch (Throwable texc) { };

      return true;
    } catch (Throwable exc) {
      TestUtils.printWarning(TestUtils.ExecutionTime.UNRELATED,
                             "cannot analyze whether the method "+
                             starterName+" is recursive");
      exc.printStackTrace();
      return true;
    }
  }

  static boolean isRecursive(Pair<String,Integer> current,
			     HashSet<Pair<String,Integer>> seen,
			     Hashtable<Pair<String,Integer>,HashSet<Pair<String,Integer>>> calls) {
    if (seen.contains(current)) {
      return true;
    }
    
    HashSet<Pair<String,Integer>> nexts = calls.get(current);
    seen.add(current);
    
    if (nexts == null) return false;
    else {
      for (Pair<String,Integer> next : nexts)
	if (isRecursive(next,seen,calls)) return true;
    }
    
    return false;
  }

}

 

static class Recursividad2021Checker {

  public static boolean
    checkBottom(TestCall<Integer,Pair<ArrayIndexedList<MyInteger>,Integer>> call,
                TestResult<Integer,Pair<ArrayIndexedList<MyInteger>,Integer>> result,
                Pair<ArrayIndexedList<MyInteger>,Integer> expectedPair) {

    ArrayIndexedList<MyInteger> expected = expectedPair.getLeft();
    Integer arraySize = expectedPair.getRight();
    long opCounter = MyInteger.getCounter();
    MyInteger.setCounter(0);

    long log2size = Math.round(Math.log(arraySize) / Math.log(2));

    if (opCounter > log2size*4) {
      TestUtils.printWarning
        (TestUtils.ExecutionTime.DURING,
         "during the call "+call.toString()+" "+opCounter+
         " comparisons were made; the complexity of the algorithm does not appear to be logarithmic");
    }

    Integer resultValue = result.getValue();
    if (!TestUtils.memberElements(new MyInteger(resultValue),expected)) {
      TestUtils.printError
        (TestUtils.ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value "+TestUtils.print(result.getValue())+
         "\nwhich was not included among the expected values\n"+
         TestUtils.print(expected));
      return false;
    }
    return true;
  }
}



























static class TestUtils {
  enum ExecutionTime { AFTER, LAST, DURING, UNRELATED }

  static String print(Object obj) {
    return TesterCode.printer(obj);
  }

  static String standardPrinter(Object obj) {
    return printer(obj, x -> standardPrinter(x));
  }

  static String printer(Object obj, Function<Object,String> print) {
    if (obj == null)
      return "null";

    if (obj instanceof String) {
      return("\""+obj.toString()+"\"");
    } else if (obj instanceof Position<?>) {
      return obj.toString();
    } else if (obj instanceof Pair<?,?>) {
      Pair<?,?> p = (Pair<?,?>) obj;
      return "Pair("+print.apply(p.getLeft())+","+print.apply(p.getRight())+")";
    } else if (obj instanceof java.util.Set<?>) {
      Iterable<?> l = (Iterable<?>) obj;
      StringBuffer buf = new StringBuffer();
      buf.append("{");
      boolean first = true;
      for (Object lobj : l) {
        if (first) first=false;
        else buf.append(",");
        buf.append(print.apply(lobj));
      }
      buf.append("}");
      return buf.toString();
    } else if (obj instanceof Iterable<?>) {
      Iterable<?> l = (Iterable<?>) obj;
      StringBuffer buf = new StringBuffer();
      buf.append("[");
      boolean first = true;
      for (Object lobj : l) {
        if (first) first=false;
        else buf.append(",");
        buf.append(print.apply(lobj));
      }
      buf.append("]");
      return buf.toString();
    } else if (obj instanceof Map<?,?>) {
      Map<?,?> m = (Map<?,?>) obj;
      StringBuffer buf = new StringBuffer();
      buf.append("[");
      boolean first = true;
      for (Entry<?,?> lobj : m.entries()) {
        if (first) first=false;
        else buf.append(",");
        buf.append("("+print.apply(lobj.getKey())+","+print.apply(lobj.getValue())+")");
      }
      buf.append("]");
      return buf.toString();
    } else if (obj instanceof Object[]) {
      Object[] arr = (Object[]) obj;
      StringBuffer buf = new StringBuffer();
      buf.append("[");
      boolean first = true;
      for (int i=0; i<arr.length; i++) {
        Object aobj = arr[i];
        if (first) first=false;
        else buf.append(",");
        buf.append(print.apply(aobj));
      }
      buf.append("]");
      return buf.toString();
    } else if (obj instanceof int[]) {
      int[] arr = (int[]) obj;
      StringBuffer buf = new StringBuffer();
      buf.append("[");
      boolean first = true;
      for (int i=0; i<arr.length; i++) {
        int aobj = arr[i];
        if (first) first=false;
        else buf.append(",");
        buf.append(print.apply(aobj));
      }
      buf.append("]");
      return buf.toString();
    }
    else
      return obj.toString();
  }

  static void printWarning(String TestName) {
      TestData.message("\n*** Warning in "+(TestName==null?"":TestName)+":");
  }

  static void printError(String TestName) {
      TestData.message("\n\n***********************************************");
      TestData.message("*** Error in "+(TestName==null?"":TestName)+":");
  }

  static void terminateErrorPrint() {
      TestData.message("\n***********************************************\n\n");
  }

  static boolean compare(Object o1, Object o2) {
      if (o1==null) return o2==null;
      else return o1.equals(o2);
  }

  static void printCallException(ExecutionTime time, String msg, Throwable exc) {
    printError(TestData.testName);

      int traceLength = TestData.numCommands();

      if (traceLength > 0) {
        TestData.message("\n"+callSeqString(traceLength,time)+"\n");
        TestData.message(TestData.getTrace());
      }

      switch (time) {
      case AFTER:
	  TestData.message("the call to "+msg+" raised the exception "+exc+
			     " although it should not have");
	  break;
      default:
	  TestData.message(" -- the exception "+exc+" was raised although it "+
			     "should not have been\n"+msg);
	  break;
      }
      TestData.message(getStackTrace(exc));
      terminateErrorPrint();
    }

  static String getStackTrace(Throwable throwable) {
    filterStackTrace(throwable);
    StringWriter errors = new StringWriter();
    throwable.printStackTrace(new PrintWriter(errors));
    return errors.toString();
  }

  static void filterStackTrace(Throwable throwable) {
    StackTraceElement[] stackTrace = throwable.getStackTrace();
    ArrayList<StackTraceElement> l = new ArrayList<StackTraceElement>();
    for (int i=0; i<stackTrace.length; i++) {
      String className = stackTrace[i].getClassName();
      if (className.startsWith("jdk.internal")
          || className.startsWith("org.junit")) break;
      l.add(stackTrace[i]);
    }
    StackTraceElement[] cutStackTrace = new StackTraceElement[l.size()];
    throwable.setStackTrace(l.toArray(cutStackTrace));
  }

  static void printCallException(String msg, Throwable exc) {
    printCallException(ExecutionTime.AFTER,msg,exc);
  }

  static void printCallException(Throwable exc) {
    printCallException(ExecutionTime.LAST,"",exc);
  }

  static void printCallException(Throwable exc, Supplier<String> msg) {
    printCallException(ExecutionTime.LAST,msg.get(),exc);
  }

  static void printWarning(ExecutionTime time, String msg) {
    TestData.message("\n\n***********************************************");
    if (TestData.testName != "") {
      TestData.message("\n*** Warning for "+TestData.testName+": ");
    } else
      TestData.message("\n*** Warning:");

    if (time != TestUtils.ExecutionTime.UNRELATED) {
      int traceLength = TestData.numCommands();
    
      if (traceLength > 0) {
        TestData.message(callSeqString(traceLength,time));
        TestData.message(TestData.getTrace());
      }
    }
    TestData.message(msg);
    terminateErrorPrint();
  }

  static void printError(ExecutionTime time, String msg) {
    printError(TestData.testName);

    if (time != TestUtils.ExecutionTime.UNRELATED) {
      int traceLength = TestData.numCommands();
      
      if (traceLength > 0) {
        TestData.message("\n"+callSeqString(traceLength,time));
        TestData.message(TestData.getTrace());
      }
    }
      
    TestData.message(msg);
    terminateErrorPrint();
  }

  static String callSeqString(int traceLength, ExecutionTime time) {
    if (traceLength < 2)
      return "while executing the call ";
    else {
      switch(time) {
      case AFTER:
	  return "after executing the call sequence ";
      case LAST:
	  return "while executing the last statement of call sequence ";
      case DURING:
	  return "while executing the call sequence ";
      case UNRELATED:
	  return "";
      }
      return "";
    }
  }

  // Checks that results, which may not be an exception nor null
  // (needs prior checks), which are iterables, return the same
  // set of elements.
  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...
  static <E extends Iterable<?>,F extends Iterable<?>>
    boolean sameSet(TestCall<E,F> call,
                    TestResult<E,F> result,
                    F expected) {
    return sameSet(call,result.getValue(),expected);
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...
  static <E extends Iterable<?>,F extends Iterable<?>>
    boolean sameSet(TestCall<?,?> call, E value, F expected) {

    HashSet<Object> s1 = new HashSet<Object>();
    HashSet<Object> s2 = new HashSet<Object>();
    for (Object e : value)
      s1.add(e);
    for (Object f : expected)
      s2.add(f);
    if (!s1.equals(s2)) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value which contained the elements\n  "+TestUtils.print(s1)+
         "\nwhich differs from the expected elements\n  "+TestUtils.print(s2));
      return false;
    }
    return true;
  }

  // Checks that results, which are basic arrays, are equal
  static <E,F> boolean eqArrays(TestCall<int[],int[]> call,
                                TestResult<int[],int[]> result,
                                int[] expected) {
    if (!call.noException())
      return false;
    if (!call.nonNull())
      return false;

    return eqArrays(call, result.getValue(), expected);
  }

  static boolean eqArrays(TestCall<int[],int[]> call, int[] value, int[] expected) {
    boolean ok_sofar = value.length == expected.length;

    if (ok_sofar) {
      for (int i=0; i<value.length && ok_sofar; i++) {
        ok_sofar = ok_sofar && (value[i] == expected[i]);
      }
    }

    if (!ok_sofar) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned an array "+printArray(value)+
         " which differs from the expected array "+printArray(expected));
    }
    return ok_sofar;
  }

  // Checks that the result is a member of the iterable
  static <E,F> boolean memberElements(TestCall<E,F> call,
				     TestResult<E,F> result,
				     F expected) {
    if (!call.noException())
      return false;
    if (!call.nonNull())
      return false;

    boolean checkResult = memberElements(call,result.getValue(),expected);
    if (!checkResult)
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value "+TestUtils.print(result.getValue())+
         "\nwhich was not included among the expected values\n"+
         TestUtils.print(expected));
    return checkResult;
  }

  static <E,F> boolean memberElements(TestCall<E,F> call,
                                    E value,
                                    F expected) {
    
    if (!memberElements(value,expected)) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value\n  "+TestUtils.print(value)+
         "\nwhich is not included among the expected elements\n  "+TestUtils.print(expected));
      return false;
    }
    return true;
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...
  public static <E,F>
    boolean memberElements(E value, F expectedP) {
    if (expectedP instanceof Iterable<?>) {
      Iterable<?> expected = (Iterable<?>) expectedP;
    
      HashSet<Object> s = new HashSet<Object>();
      for (Object e : expected) {
        s.add(e);
      }
      return s.contains(value);
    } else {
      System.out.println
	("*** Error: expected of type "+expectedP.getClass()+" does not implement Iterable");
      throw new RuntimeException();
    }
  }

  // Checks that results, which are iterables, return the same
  // elements, regardless of the order.
  static <E,F> boolean sameElements(TestCall<E,F> call,
                                    TestResult<E,F> result,
                                    F expected) {
    if (!call.noException())
      return false;
    if (!call.nonNull())
      return false;

    return sameElements(call,result.getValue(),expected);
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...

  static <E,F> boolean sameElements(TestCall<E,F> call,
                                    E value,
                                    F expected) {
    
    if (!sameElements(value,expected)) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value which contained the elements\n  "+TestUtils.print(value)+
         "\nwhich differs from the expected elements\n  "+TestUtils.print(expected));
      return false;
    }
    return true;
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...
  static <E,F>
    boolean sameElements(E valueP, F expectedP) {
    if ((valueP instanceof Iterable<?>) &&
        (expectedP instanceof Iterable<?>)) {
      Iterable<?> value = (Iterable<?>) valueP;
      Iterable<?> expected = (Iterable<?>) expectedP;
    
      HashMap<Object,Integer> s1 = new HashMap<Object,Integer>();
      HashMap<Object,Integer> s2 = new HashMap<Object,Integer>();
      for (Object e : value) {
        Integer si = s1.get(e);
        if (si == null) si = 0;
        s1.put(e,si+1);
      }
      for (Object f : expected) {
        Integer si = s2.get(f);
        if (si == null) si = 0;
        s2.put(f,si+1);
      }
      if (!s1.equals(s2)) 
        return false;
      else
        return true;
    } else {
      if (!(valueP instanceof Iterable<?>))
        System.out.println
          ("*** Error: result of type "+valueP.getClass()+" does not implement Iterable");
      if (!(expectedP instanceof Iterable<?>))
        System.out.println
          ("*** Error: expected of type "+expectedP.getClass()+" does not implement Iterable");
      throw new RuntimeException();
    }
  }



  // Checks that results, which are iterables, return the same
  // elements, regardless of the order.
  static <E,F> boolean sameElementsInOrder(TestCall<E,F> call,
                                    TestResult<E,F> result,
                                    F expected) {
    if (!call.noException())
      return false;
    if (!call.nonNull())
      return false;

    return sameElementsInOrder(call,result.getValue(),expected);
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...

  static <E,F> boolean sameElementsInOrder(TestCall<E,F> call,
                                    E value,
                                    F expected) {
    
    if (!sameElementsInOrder(value,expected)) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a value which contained the elements\n  "+TestUtils.print(value)+
         "\nwhich differs from the expected elements\n  "+TestUtils.print(expected));
      return false;
    }
    return true;
  }

  // *********************************************
  // WARNING: this relies on a sane definition of
  // hashCode and equals which does not fail upon finding
  // null values...
  static <E,F>
    boolean sameElementsInOrder(E valueP, F expectedP) {
    if ((valueP instanceof Iterable<?>) &&
        (expectedP instanceof Iterable<?>)) {
      Iterable<?> value = (Iterable<?>) valueP;
      Iterable<?> expected = (Iterable<?>) expectedP;
    
      Iterator<?> it1 = value.iterator();
      Iterator<?> it2 = expected.iterator();

      if (it1 == null) return false;

      while (it1.hasNext() && it2.hasNext()) {
        Object e2 = it2.next();
        Object e1 = null;
        try { e1 = it1.next(); }
        catch (Throwable exc) {
          return false;
        }
        if (e2 == null && e1 != null) return false;
        if (!e2.equals(e1)) return false;
      }

      if (it1.hasNext() != it2.hasNext())
        return false;
      return true;

    } else {
      if (!(valueP instanceof Iterable<?>))
        System.out.println
          ("*** Error: result of type "+valueP.getClass()+" does not implement Iterable");
      if (!(expectedP instanceof Iterable<?>))
        System.out.println
          ("*** Error: expected of type "+expectedP.getClass()+" does not implement Iterable");
      throw new RuntimeException();
    }
  }
  
  // *********************************************
  // WARNING: this relies on a sane definition of
  // equals which does not fail upon finding
  // null values...
  static <E> boolean wellSorted(TestCall<?,?> call,
                                IndexedList<E> value,
                                ArrayIndexedList<ArrayIndexedList<E>> expected) {
    
    int i = 0;
    IndexedList<E> eqClass = null;
    for (E e : value) {
      if (eqClass == null) {
        if (i < expected.size())
          eqClass = new ArrayIndexedList<E>(expected.get(i));
        else {
          printError
            (ExecutionTime.DURING,
             "the call "+call.toString()+
             " returned a list: "+value+
             " which isn't sorted correctly. The correct sort order is "+expected);
          return false;
        }
      }
      if (!eqClass.remove(e)) {
          printError
            (ExecutionTime.DURING,
             "the call "+call.toString()+
             " returned a list: "+value+
             " which isn't sorted correctly. The correct sort order is "+expected);
          return false;
      }
      if (eqClass.size() == 0) {
        eqClass = null;
        i++;
      }
    }
    if (eqClass != null || i < expected.size()) {
          printError
            (ExecutionTime.DURING,
             "the call "+call.toString()+
             " returned a list: "+value+
             " which isn't sorted correctly. The correct sort order is "+expected);
          return false;
    }
    return true;
  }


  static <E> boolean wellSorted(TestCall<?,?> call,
                                PositionList<E> value,
                                PositionList<E> expected,
                                Function<Pair<E,E>,Boolean> isSame) {

    PositionList<E> expectedCopy = new NodePositionList<E>(expected);

    if (value.size() != expected.size()) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned a list:\n  "+value+
         "\nwhich contains the wrong number of elements.\n"+
         "The correct list (modulo order of equal elements) is\n  "+
         expectedCopy);
      return false;
    }

    Position<E> valuePos = value.first();
    
    while (valuePos != null) {
      E valueE = valuePos.element();

      if (valueE == null) {
        if (value.size() != expected.size()) {
          printError
            (ExecutionTime.DURING,
             "the call "+call.toString()+
             " returned a list:\n  "+value+
             "\nwhich contains a null element.\n"+
             "The correct list (modulo order of equal elements) is\n  "+
             expectedCopy);
          return false;
        }
      }

      Position<E> expectedPos = expected.first();
      boolean found = false;
      boolean equals = true;

      while (expectedPos != null && !found && equals) {
        E expectedE = expectedPos.element();
        if (valueE.equals(expectedE)) {
          found = true;
          expected.remove(expectedPos);
        } else if (!isSame.apply(new Pair<E,E>(valueE,expectedE)))
          equals = false;

        if (!found && equals) {
          expectedPos = expected.next(expectedPos);
        }
      }

      if (!found) {
        printError
          (ExecutionTime.DURING,
           "the call "+call.toString()+
           " returned a list:\n  "+value+
           "\nwhich contains elements either incorrect elements or elements in the wrong order."+
           "\nThe correct list (modulo order of equal elements) is\n  "+
           expectedCopy);
        return false;
      }

      valuePos = value.next(valuePos);
    }
    return true;
  }


  static <E> PositionList<E> extractElementsFromIterable(String callString, Iterable<E> i) {
    if (i == null) {
      printError
        (ExecutionTime.DURING,
         "the call "+callString+
         " returned an null iterable");
      return null;
    }

    PositionList<E> l = null;

    try {
      java.util.Iterator<E> it = i.iterator();
      l = extractElementsFromIterator(it);
    } catch (Throwable exc) {
      printError
        (ExecutionTime.DURING,
         "the call "+callString+
         " returned an iterable which when used raised the exception "+
         exc+"\n"+getStackTrace(exc));
      return null;
    }
    return l;
  }

  static <E> PositionList<E> extractElementsFromIterator(java.util.Iterator<E> it) {
    PositionList<E> l = new NodePositionList<E>();
    while (it.hasNext()) {
      l.addLast(it.next());
    }
    return l;
  }

  static <E,F> boolean unorderedIterableCorrect
    (TestCall<Iterable<E>,F[]> call,
     TestResult<Iterable<E>,F[]> result,
     F[] expected) {
    java.util.Iterator<E> it = null;

    try {
      it = result.getValue().iterator();
      if (it == null) {
        printError
          (ExecutionTime.DURING,
           "the call "+call.toString()+
           " returned an iterable which returned a null iterator");
	return false;
      }
      else return unorderedIteratorCorrect(() -> call.toString(), it, expected);
    } catch (Throwable exc) {
      printError
        (ExecutionTime.DURING,
         "the call "+call.toString()+
         " returned an iterable which when used raised the exception "+
         exc+"\n"+getStackTrace(exc));
      return false;
    }
  }

  static <E,F> boolean unorderedIteratorCorrect
    (TestCall<java.util.Iterator<E>,F[]> call,
     TestResult<java.util.Iterator<E>,F[]> result,
     F[] expected) {
    java.util.Iterator<E> it = result.getValue();
    return unorderedIteratorCorrect(() -> call.toString(), it, expected);
  }

  static <E,F> boolean unorderedIteratorCorrect
    (Supplier<String> callString,
     java.util.Iterator<E> it,
     F[] expected) {

    PositionList<E> l = null;

    try { l = extractElementsFromIterator(it); }
    catch ( Throwable exc ) {
      TestUtils.printCallException(exc);
      return false;
    }

    if (l == null) return false;

    boolean correct = (expected.length == l.size());

    if (correct) {
      PositionList<F> expectedList = new NodePositionList<F>();
      for (int j=0; j<expected.length; j++) {
        expectedList.addLast(expected[j]);
      }
      HashSet<Object> s1 = new HashSet<Object>();
      HashSet<Object> s2 = new HashSet<Object>();
      for (Object e : l)
        s1.add(e);
      for (Object f : expectedList)
        s2.add(f);
      if (!s1.equals(s2)) {
        printError
          (ExecutionTime.DURING,
           "the call "+callString.get()+
           " returned an iterator which returned the elements "+TestUtils.print(s1)+
           " which differs from the expected elements "+TestUtils.print(s2));
        return false;
      }
    }
   
    if (!correct) {
      printError
        (ExecutionTime.DURING,
         "the call "+callString.get()+
         " returned an iterable with the elements "+TestUtils.print(l)+
         " but should have returned the elements "+TestUtils.print(expected));
      return false;
    } else return true;
  }

  static <E,F> boolean iterableCorrect(String callString, E[] original, Iterable<E> i) {
    PositionList<E> l = extractElementsFromIterable(callString, i);
    if (l == null) return false;

    boolean correct = (original.length == l.size());

    if (correct) {
      Position<E> cursor = l.first();
      for (int j=0; j<original.length; j++) {
        if (original[j] == null) {
          correct = correct && (cursor.element() == null);
        }
        correct = correct && original[j].equals(cursor.element());
        if (!correct) break;
        cursor = l.next(cursor);
      }
    }
   
    if (!correct) {
      printError
        (ExecutionTime.DURING,
         "the call "+callString+
         " returned an iterable with the elements "+TestUtils.print(l)+
         " but should have returned the elements "+TestUtils.print(original));
      return false;
    } else return true;
  }

  static <E> boolean unchanged(String callString, E[] original, PositionList<E> l) {
      boolean ok_sofar = true;
      
      if (l.size() != original.length) {
	  ok_sofar = false;
      }

      Position<E> lPos = l.first();
      int i=0;
      while (ok_sofar && lPos != null) {
	  E elem = lPos.element();

	  if (elem == null) ok_sofar = (original[i] == null);
	  else ok_sofar = elem.equals(original[i]);

	  lPos = l.next(lPos);
	  ++i;
      }

      if (!ok_sofar) {
	  printError
            (ExecutionTime.DURING,
	       "the call "+callString+
	       " has modified the input list "+TestUtils.print(original)+
	     "; it has now the elements "+TestUtils.print(l));
	  return ok_sofar;
      }
      return ok_sofar;
  }

  static <E> boolean unchanged(String callString, E[] original, E[] newer) {
      boolean ok_sofar = true;
      
      if (newer.length != original.length) {
	  ok_sofar = false;
      }

      for (int i=0; i<newer.length; i++) {
	  if (newer[i] == null) ok_sofar = (original[i] == null);
	  else ok_sofar = newer[i].equals(original[i]);
      }

      if (!ok_sofar) {
	  printError
	      (ExecutionTime.DURING,
	       "the call "+callString+
	       " has modified the input array "+TestUtils.print(original)+
	       "; it has now the elements "+TestUtils.print(newer));
	  return ok_sofar;
      }
      return ok_sofar;
  }

  static String printArray(Object[] arr) {
      if (arr == null) return "null";
      else {
	  StringBuilder arrString = new StringBuilder("[");
      
	  for (int i = 0; i < arr.length; i++) {
	      arrString.append(arr[i]);
	      if (i < arr.length-1)
		  arrString.append(", ");
	  }
	  arrString.append("]");
	  return arrString.toString();
      }
  }

  static String printArray(int[] arr) {
      if (arr == null) return "null";
      else {
	  StringBuilder arrString = new StringBuilder("[");
      
	  for (int i = 0; i < arr.length; i++) {
	      arrString.append(arr[i]);
	      if (i < arr.length-1)
		  arrString.append(", ");
	  }
	  arrString.append("]");
	  return arrString.toString();
      }
  }

  static String printArray(char[] arr) {
      if (arr == null) return "null";
      else {
	  StringBuilder arrString = new StringBuilder("[");
      
	  for (int i = 0; i < arr.length; i++) {
	      arrString.append(arr[i]);
	      if (i < arr.length-1)
		  arrString.append(", ");
	  }
	  arrString.append("]");
	  return arrString.toString();
      }
  }

  public static <T> PositionList<T> toPositionList(T[] arr) {
    NodePositionList<T> l = new NodePositionList<T>();
    for (int i=0; i<arr.length; i++)
      l.addLast(arr[i]);
    return l;
  }

  public static <T> IndexedList<T> toIndexedList(T[] arr) {
    ArrayIndexedList<T> l = new ArrayIndexedList<T>();
    for (int i=0; i<arr.length; i++)
      l.add(0,arr[i]);
    return l;
  }

  public static Object accessAttribute(String fieldName, Object obj) {
    try {
      Field field;
      field = obj.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(obj);
    } catch ( Throwable exc ) {
      TestUtils.printCallException
        (TestUtils.ExecutionTime.LAST,
         "cannot access the field "+fieldName+": ",
         exc);
      return null;
    }
  }

  public static void reportPid() {
    try {
      String[] ids = ManagementFactory.getRuntimeMXBean().getName().split("@");
      BufferedWriter bw = new BufferedWriter(new FileWriter("pid"));
      bw.write(ids[0]);
      bw.close();
    } catch (Exception e) {
      System.out.println("Avisa al profesor de fallo sacando el PID");
    }
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public static <T> T[] toGenericArray(T ... elems) {
    return elems;
  }

  public static boolean ensureAedlibVersion(int major, int minor, int patchlevel) {
    boolean isOk = true;
    int aedlibMajor = 0, aedlibMinor = 0, aedlibPatchlevel = 0;
    
    try {
      Class<?> aedlibVersion = Class.forName("es.upm.aedlib.Version");
      Method majorMethod = aedlibVersion.getDeclaredMethod("major");
      Method minorMethod = aedlibVersion.getDeclaredMethod("minor");
      Method patchlevelMethod = aedlibVersion.getDeclaredMethod("patchlevel");

      aedlibMajor =
	(Integer) majorMethod.invoke(null);
      aedlibMinor = 
	(Integer) minorMethod.invoke(null);
      aedlibPatchlevel = 
	(Integer) patchlevelMethod.invoke(null);

      isOk =
	((aedlibMajor > major)
	 || ((aedlibMajor == major) &&
	     ((aedlibMinor > minor)
	      || ((aedlibMinor == minor) &&
		  (aedlibPatchlevel >= patchlevel)))));
    } catch (Throwable exc) {
      System.out.println
	("*** WARNING: cannot check aedlib version number due to "+exc);
      exc.printStackTrace();
    }

    if (!isOk) {
      String errorMessage =
	"*** ERROR: aedlib is too old. Minimum version needed is "+
	major+"."+minor+"."+patchlevel+"; your version is "+
	aedlibMajor+"."+aedlibMinor+"."+aedlibPatchlevel+
	". Please download a new version of aedlib from moodle\n";
      System.out.println(errorMessage);
      throw new RuntimeException(errorMessage);
    } else return true; 	      
  }

  @SuppressWarnings("unchecked")
  public static es.upm.aedlib.Entry<es.upm.aedlib.graph.Vertex<Integer>,Integer> es_upm_aedlib_map_Entry_refl_cnstr(Object... params) {
    return (es.upm.aedlib.Entry<es.upm.aedlib.graph.Vertex<Integer>,Integer>) reflectionNew("es.upm.aedlib.map.HashEntry",params);
  }

  public static Object reflectionNew(String className, Object... params) {
    try {
      Class<?> cl = Class.forName(className);
      for (java.lang.reflect.Constructor<?> cnstr : cl.getConstructors()) {
        Class<?>[] parameterTypes = cnstr.getParameterTypes();
        if (parameterTypes.length == params.length) {
          boolean equals = true;
          for (int i=0; i<parameterTypes.length && equals; i++) {
            if (!parameterTypes[i].isInstance(params[i]))
              equals = false;
          }
          if (equals) {
            cnstr.setAccessible(true);
            return cnstr.newInstance(params);
          }
        }
      }
    } catch (ClassNotFoundException exc) {
      System.out.println
        ("*** Warning: could not access class "+className);
      throw new RuntimeException();
    } catch (InstantiationException exc) {
      System.out.println
        ("*** Warning: could not instantiate "+className);
      throw new RuntimeException();
    } catch (IllegalAccessException exc) {
      System.out.println
        ("*** Warning: could not access "+className);
      throw new RuntimeException();
    } catch (InvocationTargetException exc) {
      System.out.println
        ("*** Warning: constructor "+className+" raised an exception");
      throw new RuntimeException();
    }
    throw new RuntimeException();
  }
}

interface TestResult<E,F> {
  boolean isException();
  Throwable getException();
  E getValue();
  boolean checkResult(F expected);
}

static class Result<E,F> implements TestResult<E,F> {
  private boolean isException;
  private Throwable exception;
  private E value;
  Call<E,F> call;

  public static <E,F> Result<E,F> result(E e, Call<E,F> call) {
    Result<E,F> result = new Result<E,F>();
    result.isException = false;
    result.value = e;
    result.call = call;
    return result;
  }
  
  public static <E,F> Result<E,F> exception(Throwable exception, Call<E,F> call) {
    Result<E,F> result = new Result<E,F>();
    result.isException = true;
    result.exception = exception;
    result.call = call;
    return result;
  }
  
  public boolean isException() {
    return isException;
  }

  public Throwable getException() {
    if (!isException()) {
      TestData.message("*** Internal model error: calling getException() without exception");
      throw new RuntimeException();
    }
    return exception;
  }

  public E getValue() {
    if (isException())
      throw new RuntimeException();
    return value;
  }
  
  Call<E,F> getCall() {
    return call;
  }

  public boolean checkResult(F expected) {
    try {
      return getCall().checkResult(expected);
    } catch (Throwable exc) {
      System.out.println("\n\n\n*** Internal testing error: checkResult("+expected+") raised exception "+exc);
      exc.printStackTrace();
      TestData.message("\n\n\n*** Internal testing error: checkResult("+expected+") raised exception "+exc);
      TestUtils.printCallException(exc);
      TestData.message("\n");
      throw new RuntimeException();
    }
  }

  public String toString() {
    String callString = getCall().toString();
    if (!callString.equals("")) {
      if (isException())
	return callString + "  =>  " + getException();
      else
	{
          String valueString = TesterCode.printer(getValue());
          return callString + "  =>  " + format_value(valueString);
        }
    } else return callString;
  }

  private String format_value(String msg) {
    String[] lines = msg.split("\\r?\\n");
    if (lines.length == 1) return msg;
    else {
      StringBuffer resultString = new StringBuffer();
      for (String line : lines) {
        resultString.append("\n    ");
        resultString.append(line);
      }
      return resultString.toString();
    }
  }
}

interface Call<E,F> {
  TestResult<E,F> doCall();
  String toString();
  boolean checkResult(F expected);
}

static abstract class TestCall<E,F> implements Call<E,F> {
  TestResult<E,F> result;
  boolean hasCalled;
  boolean voidReturn = false;

  public TestResult<E,F> doCall() {
    if (hasCalled())
      throw new RuntimeException();

    hasCalled = true;
    TestData.addCallToTrace(this.toString());
    
    try {
      E e = call();
      result = Result.result(e,this);
    } catch (Throwable exception) {
      result = Result.exception(exception,this);
    }

    if (result.isException() || !voidReturn)
      TestData.modifyLastCallInTrace(TesterCode.printer(result));
    
    return result;
  }

  abstract E call() throws Exception;

  boolean hasCalled() {
    return hasCalled;
  }

  boolean noException() {
    if (result.isException()) {
      Throwable throwable = result.getException();
      TestUtils.printCallException(throwable);
      return false;
    }
    return true;
  }

  boolean noException(Supplier<String> msg) {
    if (result.isException()) {
      Throwable throwable = result.getException();
      TestUtils.printCallException(throwable,msg);
      return false;
    }
    return true;
  }

  boolean throwsException(String expectedName) {
    if (!result.isException()) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+" should have thrown an exception "+expectedName+
         " but did not.");
      return false;
    }

    Throwable exception = result.getException();
    if (!expectedName.equals(exception.getClass().getCanonicalName())) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+" should have thrown the exception "+
         expectedName+
         ", but throwed the exception "+exception+"\n"+
         TestUtils.getStackTrace(exception));
      return false;
    }

    return true;
  }

  boolean nonNull() {
    if (!noException())
      return false;
    if (result.getValue() == null) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+
         " returned null but it should not have\n");
      return false;
    } else return true;
  }

  boolean nonNull(Supplier<String> msg) {
    if (!noException())
      return false;
    if (result.getValue() == null) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+
         " returned null but it should not have\n"+
         msg.get());
      return false;
    } else return true;
  }

  boolean fresh(Object obj) {
    return fresh(result.getValue(),obj);
  }

  boolean fresh(Object obj1, Object obj2) {
    if (obj1 != null && obj1 == obj2) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+
         " returned an object\n  "+TestUtils.print(obj1)+
         "\nwhich is the same REFERENCE as an argument object; "+
         "a NEW object should have been returned\n");
      return false;
    } else return true;
  }

  boolean unchanged(Object obj1, Object obj2) {
    return report_unchanged(obj1.equals(obj2),obj1,obj2);
  }

  boolean report_unchanged(boolean result, Object obj1, Object obj2) {
    if (!result) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+
         "\nchanged a method parameter into\n  "+TestUtils.print(obj1)+
         "\nwhich used to be\n  "+TestUtils.print(obj2)+".\nIt should not have been changed.\n");
    }
    return result;
  }

  boolean unchanged_array(Object[] original, Object[] newer) {
      boolean ok_sofar = true;
      
      if (newer.length != original.length) {
	  ok_sofar = false;
      }

      for (int i=0; i<newer.length; i++) {
	  if (newer[i] == null) ok_sofar = (original[i] == null);
	  else ok_sofar = newer[i].equals(original[i]);
      }

      if (!ok_sofar) {
	  TestUtils.printError
	      (TestUtils.ExecutionTime.LAST,
	       "the call to "+this+
	       " has modified the input array "+TestUtils.printArray(original)+
	       "; it has now the elements "+TestUtils.printArray(newer));
	  return ok_sofar;
      }
      return ok_sofar;
  }

  boolean unchanged_array(int[] original, int[] newer) {
      boolean ok_sofar = true;
      
      if (newer.length != original.length) {
	  ok_sofar = false;
      }

      for (int i=0; i<newer.length; i++) {
	  ok_sofar = newer[i] == original[i];
      }

      if (!ok_sofar) {
	  TestUtils.printError
	      (TestUtils.ExecutionTime.LAST,
	       "the call to "+this+
	       " has modified the input array "+TestUtils.printArray(original)+
	       "; it has now the elements "+TestUtils.printArray(newer));
	  return ok_sofar;
      }
      return ok_sofar;
  }

  boolean unchanged_array(char[] original, char[] newer) {
      boolean ok_sofar = true;
      
      if (newer.length != original.length) {
	  ok_sofar = false;
      }

      for (int i=0; i<newer.length; i++) {
	  ok_sofar = newer[i] == original[i];
      }

      if (!ok_sofar) {
	  TestUtils.printError
	      (TestUtils.ExecutionTime.LAST,
	       "the call to "+this+
	       " has modified the input array "+TestUtils.printArray(original)+
	       "; it has now the elements "+TestUtils.printArray(newer));
	  return ok_sofar;
      }
      return ok_sofar;
  }

  boolean setsMatch(Object expected) {
    if (!noException())
      return false;

    if (!(expected instanceof Iterable<?>)) {
      TestData.message("Cannot iterate over expected value "+TestUtils.print(expected));
      throw new RuntimeException();
    }
    Iterable<?> ev = (Iterable<?>) expected;

    E value = result.getValue();
    if (!(value instanceof Iterable<?>)) {
      TestData.message("Cannot iterate over result value "+TestUtils.print(value));
      throw new RuntimeException();
    }
    Iterable<?> iv = (Iterable<?>) value;

    HashSet<Object> ts1 = new HashSet<Object>();
    HashSet<Object> ts2 = new HashSet<Object>();


    for (Object s1 : iv)
      ts1.add(s1);
    for (Object s2 : ev)
      ts2.add(s2);
    
    if (!ts1.equals(ts2)) {
      TestUtils.printError
        (TestUtils.ExecutionTime.LAST,
         "\nthe call to "+this+
         " returned a value\n"+TestUtils.print(value)+
         "\nwhich does not contain the same elements as the expected answer"+
         "\n"+expected);
      return false;
    }

    return true;
  }

  // For use in invariant checking code only -- does not report failure
  boolean silentCheckTrueResult() {
    if (result.isException()) {
      TestUtils.printError
	(TestUtils.ExecutionTime.LAST,
	 "\n*** INTERNAL ERROR in tester: an invariant raised an exception\n");
      Throwable throwable = result.getException();
      TestUtils.printCallException(throwable);
      return false;
    }

    E value = result.getValue();

    if (value == null) {
      TestUtils.printError
	(TestUtils.ExecutionTime.LAST,
	 "\n*** INTERNAL ERROR in tester: an invariant returned a null object\n");
      return false;
    }
    
    return value.equals(true);
  }

  boolean valuesMatch(Object expected) {
    if (result.isException()) {
      noException();
      return false;
    } else {
      E value = result.getValue();
      
      if (value == null) {
        if (expected != null) {
          TestUtils.printError
            (TestUtils.ExecutionTime.LAST,
             "\nthe call to "+this+" returned null, "+
             "but should have returned "+TestUtils.print(expected));
          return false;
        }
        else return true;
      }
      
      if (!value.equals(expected)) {
        TestUtils.printError
          (TestUtils.ExecutionTime.LAST,
           "\nthe call to "+this+" returned\n  "+TestUtils.print(value)+
           "\n\nbut should have returned\n  "+TestUtils.print(expected));
        return false;
      }
      return true;
    }
  }

  public boolean checkResult(F expected) {
    if (!hasCalled())
      throw new RuntimeException();

    return valuesMatch(expected);
  }
}


static class Explora extends TestCall<es.upm.aedlib.Pair<Object,es.upm.aedlib.positionlist.PositionList<Lugar>>,Integer>
{
  Lugar x_1;
  Lugar x_1_orig;
  
  public Explora (Lugar x_1)
  {
    this.x_1 = x_1 ;
    if (x_1 == null)
      x_1_orig = null ;
    else
      x_1_orig = new Lugar(x_1) ;
  }
  
  public String toString () 
  {
    StringBuilder output = new StringBuilder() ;
    output.append(new String("Explorador.explora")) ;
    output.append(new String("(")) ;
    output.append(TesterCode.printer(x_1_orig)) ;
    output.append(new String(")")) ;
    return output.toString() ;
  }
  
  public es.upm.aedlib.Pair<Object,es.upm.aedlib.positionlist.PositionList<Lugar>> call () 
  {
    return Explorador.explora(x_1) ;
  }
  
  public boolean checkResult (Integer expected) 
  {
    if (!(result.isException() || fresh(result.getValue(),x_1)))
      return false ;
    if (!unchanged(x_1,x_1_orig))
      return false ;
    if (!MazeChecker.checkExplore(this,result,expected))
      return false ;
    return true ;
  }
  
}


static class JoinMultiSets extends TestCall<es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>,es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>>
{
  es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_1;
  es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_1_orig;
  es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_2;
  es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_2_orig;
  
  public JoinMultiSets (es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_1,es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> x_2)
  {
    this.x_1 = x_1 ;
    if (x_1 == null)
      x_1_orig = null ;
    else
      x_1_orig = new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(x_1) ;
    this.x_2 = x_2 ;
    if (x_2 == null)
      x_2_orig = null ;
    else
      x_2_orig = new es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>>(x_2) ;
  }
  
  public String toString () 
  {
    StringBuilder output = new StringBuilder() ;
    output.append(new String("Utils.joinMultiSets")) ;
    output.append(new String("(")) ;
    output.append(TesterCode.printer(x_1_orig)) ;
    output.append(new String(", ")) ;
    output.append(TesterCode.printer(x_2_orig)) ;
    output.append(new String(")")) ;
    return output.toString() ;
  }
  
  public es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> call () 
  {
    return Utils.joinMultiSets(x_1,x_2) ;
  }
  
  public boolean checkResult (es.upm.aedlib.positionlist.NodePositionList<es.upm.aedlib.Pair<Character,Integer>> expected) 
  {
    if (!noException())
      return false ;
    if (!nonNull())
      return false ;
    if (!(result.isException() || fresh(result.getValue(),x_1)))
      return false ;
    if (!(result.isException() || fresh(result.getValue(),x_2)))
      return false ;
    if (!unchanged(x_1,x_1_orig))
      return false ;
    if (!unchanged(x_2,x_2_orig))
      return false ;
    if (!valuesMatch(expected))
      return false ;
    return true ;
  }
  
}


static class Multiply extends TestCall<Integer,Integer>
{
  Integer x_1;
  Integer x_2;
  
  public Multiply (Integer x_1,Integer x_2)
  {
    this.x_1 = x_1 ;
    this.x_2 = x_2 ;
  }
  
  public String toString () 
  {
    StringBuilder output = new StringBuilder() ;
    output.append(new String("Utils.multiply")) ;
    output.append(new String("(")) ;
    output.append(TesterCode.printer(x_1)) ;
    output.append(new String(", ")) ;
    output.append(TesterCode.printer(x_2)) ;
    output.append(new String(")")) ;
    return output.toString() ;
  }
  
  public Integer call () 
  {
    return Utils.multiply(x_1,x_2) ;
  }
  
  public boolean checkResult (Integer expected) 
  {
    if (!noException())
      return false ;
    if (!nonNull())
      return false ;
    if (!valuesMatch(expected))
      return false ;
    return true ;
  }
  
}


static class RecursionCheck extends TestCall<Boolean,Boolean>
{
  String x_1;
  String x_2;
  String x_3;
  Integer x_4;
  
  public RecursionCheck (String x_1,String x_2,String x_3,Integer x_4)
  {
    this.x_1 = x_1 ;
    this.x_2 = x_2 ;
    this.x_3 = x_3 ;
    this.x_4 = x_4 ;
  }
  
  public String toString () 
  {
    StringBuilder output = new StringBuilder() ;
    output.append(new String("CheckRecursion.recursionCheck")) ;
    output.append(new String("(")) ;
    output.append(TesterCode.printer(x_1)) ;
    output.append(new String(", ")) ;
    output.append(TesterCode.printer(x_2)) ;
    output.append(new String(", ")) ;
    output.append(TesterCode.printer(x_3)) ;
    output.append(new String(", ")) ;
    output.append(TesterCode.printer(x_4)) ;
    output.append(new String(")")) ;
    return output.toString() ;
  }
  
  public Boolean call () 
  {
    return CheckRecursion.recursionCheck(x_1,x_2,x_3,x_4) ;
  }
  
  public boolean checkResult (Boolean expected) 
  {
    if (!noException())
      return false ;
    if (!nonNull())
      return false ;
    if (!valuesMatch(expected))
      return false ;
    return true ;
  }
  
}


static class FindBottom extends TestCall<Integer,es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer>>
{
  es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger> x_1;
  es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger> x_1_orig;
  
  public FindBottom (es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger> x_1)
  {
    this.x_1 = x_1 ;
    if (x_1 == null)
      x_1_orig = null ;
    else
      x_1_orig = new es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>(x_1) ;
  }
  
  public String toString () 
  {
    StringBuilder output = new StringBuilder() ;
    output.append(new String("Utils.findBottom")) ;
    output.append(new String("(")) ;
    output.append(TesterCode.printer(x_1_orig)) ;
    output.append(new String(")")) ;
    return output.toString() ;
  }
  
  public Integer call () 
  {
    return Utils.findBottom(x_1) ;
  }
  
  public boolean checkResult (es.upm.aedlib.Pair<es.upm.aedlib.indexedlist.ArrayIndexedList<MyInteger>,Integer> expected) 
  {
    if (!noException())
      return false ;
    if (!nonNull())
      return false ;
    if (!(result.isException() || fresh(result.getValue(),x_1)))
      return false ;
    if (!unchanged(x_1,x_1_orig))
      return false ;
    if (!Recursividad2021Checker.checkBottom(this,result,expected))
      return false ;
    return true ;
  }
  
}












 
static class BaseResultsHandler {
  public static volatile CountDownLatch waitForThreads = null;
  static LinkedHashMap<String,Boolean> results_sofar;
  static HashSet<String> runningTests;
  static int numTestsRemaining = 1000;
  static long endTime = 0;

  static void init() {
    results_sofar = new LinkedHashMap<String,Boolean>();
    runningTests = new HashSet<String>();
  }

  static void setNumTestsRemaining(int numTestsToRun, int GlobalTimeoutInSeconds) {
    numTestsRemaining = numTestsToRun;
    endTime = System.currentTimeMillis() + GlobalTimeoutInSeconds*1000;
  }

  static boolean isTimedout() {
    return System.currentTimeMillis() > endTime;
  }

  static void startTest(String testName) {
    runningTests.add(testName);
  }

  static void stopTest(String testName) {
    runningTests.remove(testName);
    --numTestsRemaining;
  }

  static boolean results_ok_sofar() {
    for (String key : results_sofar.keySet())
      if (!results_sofar.get(key))
        return false;
    return true;
  }

  static boolean local_results_ok_sofar(String test_type) {
    Boolean result = results_sofar.get(test_type);
    return (result==null) || result;
  }

  static void add_result(String test_type, boolean result) {
    Boolean old_result = results_sofar.get(test_type);
    if ((old_result == null) || old_result)
      results_sofar.put(test_type,result);
  }

  static boolean results_ok() {
    if (results_sofar.size() == 0) {
      System.out.println("\n*** Error: no tests were run");
      return false;
    }

    if (runningTests.size() > 0) {
      System.out.println("\n*** Error: some tests ("+runningTests.size()+") are still running\n");
      return false;
    }

    for (String key : results_sofar.keySet())
      if (!results_sofar.get(key))
        return false;

    if (numTestsRemaining > 0) {
      System.out.println("\n*** Error: some tests ("+numTestsRemaining+") did not run\n");
      return false;
    }

    return true;
  }

  static int get_nota() {
    if (results_ok()) return 10;
    else return 0;
  }

  static void report_results() {
    System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++\n");

    for (String testName : results_sofar.keySet()) {
      System.out.print("Testing results for "+testName+": ");
      if (results_sofar.get(testName)) {
        System.out.println("succeeded");
      } else {
        System.out.println("failed");
      }
    }

    System.out.println("\n------------------------------------------");
    if (results_ok()) {
      System.out.println("\n"+Tests.tester+": Test finalizado correctamente.\n");
      System.out.println(readFile("aed_success_message.txt"));
    } else {
      System.out.println("\n"+Tests.tester+": errores detectados.\n\n");
    }
  }

  private static String readFile(String filePath) 
  {
    StringBuilder sb = new StringBuilder();
    Path path = Paths.get(filePath);
 
    if (Files.isReadable(path)) {
      try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) 
        {
          stream.forEach(s -> sb.append(s).append("\n"));
        }
      catch (IOException e) {};
    }
 
    return sb.toString();
  }
  
}





public static class TestData {
  static String testName = "";
  static ArrayList<String> trace;
  static ArrayList<String> messages;
  static boolean isJunitTester;

  public static void initTrace() {
    trace = new ArrayList<String>();
    messages = new ArrayList<String>();
  }

  public static void setTesterType(boolean junitTesting) {
    isJunitTester = junitTesting;
  }

  public static boolean isJunitTester() {
    return isJunitTester;
  }

  public static void addCallToTrace(String callString) {
    if (!callString.equals(""))
      trace.add("  "+callString);
    else
      trace.add(callString);
  }

  public static void modifyLastCallInTrace(String callString) {
    if (trace.size() > 0) {
      trace.remove(trace.size()-1);
    if (!callString.equals(""))
      trace.add("  "+callString);
    else
      trace.add(callString);
    }
  }

  public static void message(String message) {
    messages.add(message);
  }

  public static int numCommands() {
    return trace.size();
  }

  private static String arrayToString(ArrayList<String> arr) {
    StringBuffer sb = new StringBuffer();

    for (int i=0; i<arr.size(); i++) {
      String item = arr.get(i);
      if (!item.equals("")) {
	sb.append(item+"\n");
      }
    }
    return sb.toString();
  }

  public static String getTrace() {
    return arrayToString(trace);
  }

  public static String getMessages() {
    return arrayToString(messages);
  }

  public static String getTestName() {
    return testName;
  }

  public static ArrayList<String> getRawTrace() {
    return trace;
  }

  public static void setTrace(ArrayList<String> setTrace) {
    trace = setTrace;
  }

  public static void setTestName(String setTestName) {
    testName = setTestName;
  }

  public static void setContext(String setTestName, ArrayList<String> setTrace) {
    setTestName(setTestName);
    setTrace(setTrace);
  }
}

static class ResultsHandler extends BaseResultsHandler {

  static boolean results_ok() {
    if (results_sofar.size() == 0) {
      System.out.println("\n*** Error: no tests were run");
      return false;
    }

    if (runningTests.size() > 0) {
      System.out.println("\n*** Error: some tests ("+runningTests.size()+") are still running\n");
      return false;
    }

    int numSuccesses = 0;
    
    for (String key : results_sofar.keySet()) {
      if (!key.equals("recursionCheck") && results_sofar.get(key)) ++numSuccesses;
    }

    if (numTestsRemaining > 0) {
      System.out.println("\n*** Error: some tests ("+numTestsRemaining+") did not run\n");
      return false;
    }

    return numSuccesses >= 3;
  }

  static void report_results() {
    System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++\n");

    for (String testName : results_sofar.keySet()) {
      if (!testName.equals("recursionCheck")) {
        System.out.print("Testing results for "+testName+": ");
        if (results_sofar.get(testName))
          System.out.println("succeeded");
        else
          System.out.println("failed");
      }
    }

    System.out.println("\n------------------------------------------");
    if (results_ok())
      System.out.println("\n"+Tests.tester+": Test finalizado correctamente.\n");
    else
      System.out.println("\n"+Tests.tester+": errores detectados.\n\n");
  }
}

static class TesterCode
{
  
  public static void resetPrinter () 
  {
  }
  
  public static String printer (Object obj) 
  {
    return TestUtils.standardPrinter(obj) ;
  }
  
}


}
