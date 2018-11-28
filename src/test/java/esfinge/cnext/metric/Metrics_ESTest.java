/*
 * This file was automatically generated by EvoSuite
 * Wed Nov 28 17:32:18 GMT 2018
 */

package esfinge.cnext.metric;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import esfinge.cnext.metric.MemoryMetricsGenerator;
import esfinge.cnext.metric.MetricRecorder;
import esfinge.cnext.metric.MetricRecorderFile;
import esfinge.cnext.metric.MetricRecorderLogger;
import esfinge.cnext.metric.TimeMetricsGenerator;
import java.lang.reflect.Method;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Metrics_ESTest extends Metrics_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      MemoryMetricsGenerator memoryMetricsGenerator0 = new MemoryMetricsGenerator();
      try { 
        memoryMetricsGenerator0.finishRecording((Method) null, "bk3Jy,I,PSY4@mm", " seconds");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("esfinge.cnext.metric.Metrics", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      MemoryMetricsGenerator memoryMetricsGenerator0 = new MemoryMetricsGenerator();
      memoryMetricsGenerator0.startRecording((Method) null);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      TimeMetricsGenerator timeMetricsGenerator0 = new TimeMetricsGenerator();
      MetricRecorderLogger metricRecorderLogger0 = new MetricRecorderLogger();
      timeMetricsGenerator0.setMetricRecorder(metricRecorderLogger0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      TimeMetricsGenerator timeMetricsGenerator0 = new TimeMetricsGenerator();
      MetricRecorder metricRecorder0 = timeMetricsGenerator0.getMetricRecorder();
      assertNull(metricRecorder0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      TimeMetricsGenerator timeMetricsGenerator0 = new TimeMetricsGenerator();
      MetricRecorderFile metricRecorderFile0 = new MetricRecorderFile("");
      timeMetricsGenerator0.setMetricRecorder(metricRecorderFile0);
      MetricRecorder metricRecorder0 = timeMetricsGenerator0.getMetricRecorder();
      assertSame(metricRecorder0, metricRecorderFile0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      TimeMetricsGenerator timeMetricsGenerator0 = new TimeMetricsGenerator();
      // Undeclared exception!
      try { 
        timeMetricsGenerator0.extractMetricResult((Method) null, "", "", "");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("esfinge.cnext.metric.Metrics", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      MemoryMetricsGenerator memoryMetricsGenerator0 = new MemoryMetricsGenerator();
      try { 
        memoryMetricsGenerator0.finishRecording((Method) null, "V$x", "V$x");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("esfinge.cnext.metric.Metrics", e);
      }
  }
}
