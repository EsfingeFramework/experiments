/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:31:57 GMT 2018
 */

package esfinge.cnext.metric;

import org.junit.Test;
import static org.junit.Assert.*;
import esfinge.cnext.metric.MetricResult;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class MetricResult_ESTest extends MetricResult_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult((String) null, (String) null, "esfinge.cnext.metric.MetricResult", "", "`Z%6rci-,}|cu1");
      String string0 = metricResult0.getSelector();
      assertEquals("esfinge.cnext.metric.MetricResult", metricResult0.getImplementation());
      assertNull(string0);
      assertEquals("`Z%6rci-,}|cu1", metricResult0.getResult());
      assertEquals("", metricResult0.getMethod());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult(", selector=", "", "", ", selector=", "");
      String string0 = metricResult0.getSelector();
      assertEquals("", metricResult0.getResult());
      assertEquals(", selector=", metricResult0.getMetric());
      assertEquals(", selector=", metricResult0.getMethod());
      assertEquals("", metricResult0.getImplementation());
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult((String) null, (String) null, (String) null, (String) null, (String) null);
      String string0 = metricResult0.getResult();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult(", selector=", "", "", ", selector=", "");
      String string0 = metricResult0.getResult();
      assertEquals("", metricResult0.getSelector());
      assertEquals("", string0);
      assertEquals(", selector=", metricResult0.getMetric());
      assertEquals(", selector=", metricResult0.getMethod());
      assertEquals("", metricResult0.getImplementation());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult((String) null, (String) null, (String) null, (String) null, (String) null);
      String string0 = metricResult0.getMetric();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("", "", "", "", "<kbf=La,Ix8e<BZ");
      String string0 = metricResult0.getMetric();
      assertEquals("<kbf=La,Ix8e<BZ", metricResult0.getResult());
      assertEquals("", string0);
      assertEquals("", metricResult0.getImplementation());
      assertEquals("", metricResult0.getMethod());
      assertEquals("", metricResult0.getSelector());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult((String) null, (String) null, (String) null, (String) null, (String) null);
      String string0 = metricResult0.getMethod();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("", "", "", "", "");
      String string0 = metricResult0.getMethod();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("J*6zm", "J*6zm", "J*6zm", "", "");
      assertEquals("J*6zm", metricResult0.getImplementation());
      
      metricResult0.setImplementation((String) null);
      metricResult0.getImplementation();
      assertEquals("", metricResult0.getMethod());
      assertEquals("J*6zm", metricResult0.getMetric());
      assertEquals("", metricResult0.getResult());
      assertEquals("J*6zm", metricResult0.getSelector());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("0TE:M1F{8", "0TE:M1F{8", "0TE:M1F{8", "0TE:M1F{8", "");
      assertEquals("0TE:M1F{8", metricResult0.getImplementation());
      
      metricResult0.setImplementation("");
      metricResult0.getImplementation();
      assertEquals("0TE:M1F{8", metricResult0.getMetric());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.getMetric();
      assertEquals("1", string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      metricResult0.setSelector("MetricResult{metric=1, selector=1, implementation=1, method=1, result=1}");
      assertEquals("1", metricResult0.getMethod());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.toString();
      assertEquals("MetricResult{metric=1, selector=1, implementation=1, method=1, result=1}", string0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.getImplementation();
      assertEquals("1", string0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.getSelector();
      assertEquals("1", string0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.getResult();
      assertEquals("1", string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      metricResult0.setMethod("1");
      assertEquals("1", metricResult0.getMethod());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      metricResult0.setMetric("MetricResult{metric=1, selector=1, implementation=1, method=1, result=1}");
      assertEquals("MetricResult{metric=1, selector=1, implementation=1, method=1, result=1}", metricResult0.getMetric());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      String string0 = metricResult0.getMethod();
      assertEquals("1", string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      MetricResult metricResult0 = new MetricResult("1", "1", "1", "1", "1");
      metricResult0.setResult("MetricResult{metric=1, selector=1, implementation=1, method=1, result=1}");
      assertEquals("1", metricResult0.getSelector());
  }
}
