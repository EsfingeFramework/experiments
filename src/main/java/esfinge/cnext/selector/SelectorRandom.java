package esfinge.cnext.selector;

import esfinge.cnext.factories.Selector;

import java.lang.reflect.Method;
import java.util.Random;

public class SelectorRandom implements Selector {

    @Override
    public Class select(Class[] implementations, Object[] args, Method m) {
        int random = (new Random()).nextInt(implementations.length * 100_000);
        return implementations[random / 100_000];
    }

}
