/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:41:54 GMT 2018
 */

package esfinge.cnext;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import esfinge.cnext.ABTestBuilder;
import esfinge.cnext.metric.MetricRecorderLogger;
import esfinge.cnext.selector.SelectorWithPersistence;
import java.lang.reflect.Array;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ABTestBuilder_ESTest extends ABTestBuilder_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ABTestBuilder<Object, Object> aBTestBuilder0 = new ABTestBuilder<Object, Object>();
      Class<Object> class0 = Object.class;
      aBTestBuilder0.createFor(class0);
      try { 
        aBTestBuilder0.build();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // java.lang.Object is not an interface
         //
         verifyException("java.lang.reflect.Proxy$ProxyClassFactory", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ABTestBuilder<String, Object> aBTestBuilder0 = new ABTestBuilder<String, Object>();
      MetricRecorderLogger metricRecorderLogger0 = new MetricRecorderLogger();
      aBTestBuilder0.withMetricRecorder(metricRecorderLogger0);
      try { 
        aBTestBuilder0.build();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.util.ReflectionUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SelectorWithPersistence selectorWithPersistence0 = new SelectorWithPersistence();
      ABTestBuilder<Object, Object> aBTestBuilder0 = new ABTestBuilder<Object, Object>();
      aBTestBuilder0.withSelector(selectorWithPersistence0);
      try { 
        aBTestBuilder0.build();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.util.ReflectionUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ABTestBuilder<Object, Object> aBTestBuilder0 = new ABTestBuilder<Object, Object>();
      Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 7);
      ABTestBuilder aBTestBuilder1 = aBTestBuilder0.withImplementations(classArray0);
      assertNotNull(aBTestBuilder1);
  }
}
