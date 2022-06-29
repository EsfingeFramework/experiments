package user.app;

import esfinge.cnext.annotations.MetricsGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@MetricsGenerator(OrderValueMetricsGenerator.class)
public @interface OrderValue {
}
