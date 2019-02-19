/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:16:43 GMT 2018
 */

package esfinge.cnext;

import org.junit.Test;
import static org.junit.Assert.*;
import esfinge.cnext.ABTest;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ABTest_ESTest extends ABTest_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ABTest<Integer, Integer> aBTest0 = new ABTest<Integer, Integer>();
      Integer integer0 = new Integer((-1280));
      aBTest0.setProxy(integer0);
      Integer integer1 = aBTest0.getProxy();
      assertEquals((-1280), (int)integer1);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ABTest<Integer, Integer> aBTest0 = new ABTest<Integer, Integer>();
      Integer integer0 = aBTest0.getProxy();
      assertNull(integer0);
  }
}