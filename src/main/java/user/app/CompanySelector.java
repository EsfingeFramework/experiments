package user.app;

import esfinge.cnext.factories.Selector;
import esfinge.cnext.selector.SelectorRandom;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class CompanySelector implements Selector {
    @Override
    public Class select(Class[] implementations, Object[] args,Method m) {

        Parameter[] parameters = m.getParameters();
        int targetParameterPosition=0 ;
        for(int i =0; i< parameters.length; i++){
            if (parameters[i].isAnnotationPresent(targetParameter.class)){
                targetParameterPosition= i;
            }
        }

        if(((User) args[targetParameterPosition]).getCompanyName().equals("Loacker")){
            return implementations[0];
            //for the company Loacker only the first implementation will be selected
        }else{
            return new SelectorRandom().select(implementations,args,m);
        }
    }
}
