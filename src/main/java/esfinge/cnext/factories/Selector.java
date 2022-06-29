package esfinge.cnext.factories;

import java.lang.reflect.Method;

public interface Selector {

    Class select(Class[] implementations, Object[] args, Method m);

}
