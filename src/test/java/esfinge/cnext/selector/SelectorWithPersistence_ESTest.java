/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:42:06 GMT 2018
 */

package esfinge.cnext.selector;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import esfinge.cnext.selector.SelectorRandom;
import esfinge.cnext.selector.SelectorWithPersistence;
import java.lang.reflect.Array;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.json.simple.JSONArray;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SelectorWithPersistence_ESTest extends SelectorWithPersistence_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SelectorRandom selectorRandom0 = new SelectorRandom();
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence("SI2g", selectorRandom0);
      Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 4);
      // Undeclared exception!
      try { 
        selectorWithPersistence0.select(classArray0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("esfinge.cnext.selector.SelectorWithPersistence", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence();
      Class<JSONArray>[] classArray0 = (Class<JSONArray>[]) Array.newInstance(Class.class, 4);
      FileSystemHandling.shouldAllThrowIOExceptions();
      Class<JSONArray> class0 = JSONArray.class;
      classArray0[0] = class0;
      Class class1 = selectorWithPersistence0.select(classArray0);
      Class class2 = selectorWithPersistence0.select(classArray0);
      assertSame(class2, class1);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence();
      Class<JSONArray>[] classArray0 = (Class<JSONArray>[]) Array.newInstance(Class.class, 1);
      Class<JSONArray> class0 = JSONArray.class;
      classArray0[0] = class0;
      selectorWithPersistence0.select(classArray0);
      Class class1 = selectorWithPersistence0.select(classArray0);
      assertFalse(class1.isEnum());
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence("Ka+|\"");
      SelectorWithPersistence selectorWithPersistence1 = new SelectorWithPersistence("Ka+|\"", selectorWithPersistence0);
      assertFalse(selectorWithPersistence1.equals((Object)selectorWithPersistence0));
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence();
      SelectorWithPersistence selectorWithPersistence1 = new SelectorWithPersistence(selectorWithPersistence0);
      assertFalse(selectorWithPersistence1.equals((Object)selectorWithPersistence0));
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence("=Y0g1");
      Class<Integer>[] classArray0 = (Class<Integer>[]) Array.newInstance(Class.class, 0);
      // Undeclared exception!
      try { 
        selectorWithPersistence0.select(classArray0);
        fail("Expecting exception: ArithmeticException");
      
      } catch(ArithmeticException e) {
         //
         // / by zero
         //
         verifyException("org.evosuite.runtime.Random", e);
      }
  }
}
