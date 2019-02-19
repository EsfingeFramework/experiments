/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:47:49 GMT 2018
 */

package esfinge.cnext.selector;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import esfinge.cnext.selector.SelectorRandom;
import java.lang.reflect.Array;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.Random;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SelectorRandom_ESTest extends SelectorRandom_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SelectorRandom selectorRandom0 = new SelectorRandom();
      Class<Integer>[] classArray0 = (Class<Integer>[]) Array.newInstance(Class.class, 3);
      Random.setNextRandom((-154));
      Class class0 = selectorRandom0.select(classArray0);
      assertNull(class0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SelectorRandom selectorRandom0 = new SelectorRandom();
      Class<Integer>[] classArray0 = (Class<Integer>[]) Array.newInstance(Class.class, 3);
      Class<Integer> class0 = Integer.class;
      classArray0[0] = class0;
      Class class1 = selectorRandom0.select(classArray0);
      assertFalse(class1.isArray());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SelectorRandom selectorRandom0 = new SelectorRandom();
      // Undeclared exception!
      try { 
        selectorRandom0.select((Class[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("esfinge.cnext.selector.SelectorRandom", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      SelectorRandom selectorRandom0 = new SelectorRandom();
      Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 0);
      // Undeclared exception!
      try { 
        selectorRandom0.select(classArray0);
        fail("Expecting exception: ArithmeticException");
      
      } catch(ArithmeticException e) {
         //
         // / by zero
         //
         verifyException("org.evosuite.runtime.Random", e);
      }
  }
}