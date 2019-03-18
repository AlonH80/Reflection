import reflection.api.Investigator;
import java.lang.reflect.*;
import java.util.*;

public class ReflectClass implements Investigator {
    private Class classToInvestigate;
    private Object realObj;

    @Override
    public void load(Object anInstanceOfSomething){
        realObj=anInstanceOfSomething;
        classToInvestigate=anInstanceOfSomething.getClass();
    }

    @Override
    public int getTotalNumberOfMethods() {
        return classToInvestigate.getDeclaredMethods().length;
    }

    @Override
    public int getTotalNumberOfConstructors() {
        return classToInvestigate.getConstructors().length;
    }

    @Override
    public int getTotalNumberOfFields() {
        return classToInvestigate.getDeclaredFields().length;
    }

    @Override
    public Set<String> getAllImplementedInterfaces() {
        Set<String> implementedInterfacesNames=new HashSet<>();
        Class[] interfaces=classToInvestigate.getInterfaces();
        for (Class interf:interfaces){
            implementedInterfacesNames.add(interf.getSimpleName());
        }
        return implementedInterfacesNames;
    }

    @Override
    public int getCountOfConstantFields() {
        Field[] allFields=classToInvestigate.getDeclaredFields();
        int numOfConsField=0;
        for (Field field:allFields){
            if (Modifier.isFinal(field.getModifiers())){
                ++numOfConsField;
            }
        }
        return numOfConsField;
    }

    @Override
    public int getCountOfStaticMethods() {
        Method[] allMethods=classToInvestigate.getDeclaredMethods();
        int numOfStaticMethods=0;
        for (Method meth:allMethods){
            if (Modifier.isStatic(meth.getModifiers())){
                ++numOfStaticMethods;
            }
        }
        return numOfStaticMethods;
    }

    @Override
    public boolean isExtending() {
        return classToInvestigate.getSuperclass()!=Object.class;
    }

    @Override
    public String getParentClassSimpleName() {
        if (this.isExtending()) {
            return classToInvestigate.getSuperclass().getSimpleName();
        }
        return null;
    }

    @Override
    public boolean isParentClassAbstract() {
        if (this.isExtending()) {
            return Modifier.isAbstract(classToInvestigate.getSuperclass().getModifiers());
        }
        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        Set<String> allFieldsNames= new HashSet<>();
        Field[] classFields=classToInvestigate.getDeclaredFields();
        if (this.isExtending()) {
            Field[] superClassFields = classToInvestigate.getSuperclass().getDeclaredFields();
            for (Field fld:superClassFields){
                allFieldsNames.add(fld.getName());
            }
        }
        for (Field fld:classFields){
            allFieldsNames.add(fld.getName());
        }
        return allFieldsNames;
    }

    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        Class[] funcArgs=new Class[args.length];
        int returnVal;
        Method func;
        for (int i=0;i<args.length;++i){
            funcArgs[i]=args[i].getClass();
        }
        try {
            func = classToInvestigate.getMethod(methodName, funcArgs);
            returnVal = (int) func.invoke(realObj, args);
        } catch (IllegalAccessException|NoSuchMethodException|InvocationTargetException|ClassCastException e) {
            returnVal=-1;
        }
        return returnVal;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) {
        Constructor[] conts=classToInvestigate.getConstructors();
        for (Constructor con:conts){
            if (con.getParameterCount()==numberOfArgs){
                try {
                    return con.newInstance(args);
                }
                catch(InstantiationException|IllegalAccessException|InvocationTargetException e){

                }
            }
        }
        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        try {
            Method funcToElevate = classToInvestigate.getDeclaredMethod(name, parametersTypes);
            funcToElevate.setAccessible(true);
            return funcToElevate.invoke(realObj,args);
        }
        catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
            return -1;
        }
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        Class currClass=classToInvestigate;
        Stack<Class> classChain=new Stack<>();
        classChain.push(currClass);
        while(currClass!=Object.class) {
            currClass=currClass.getSuperclass();
            classChain.push(currClass);
        }
        String inheritChain=new String();
        inheritChain+=classChain.pop().getSimpleName();
        while(!classChain.isEmpty()){
            inheritChain+=(delimiter+classChain.pop().getSimpleName());
        }
        return inheritChain;
    }
}
