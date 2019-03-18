import java.lang.reflect.*;

public class MainClass
{
    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,ClassNotFoundException {
        /*Object obj=new Square(2);
        Class classToInvest=obj.getClass();
        Constructor[] conts=classToInvest.getConstructors();
        Field[] allFields=classToInvest.getDeclaredFields();
        int numOfConsField=0;
        for (Field field:allFields){
            if (Modifier.isFinal(field.getModifiers())){
                System.out.println(field.toString());
                ++numOfConsField;
            }
        }

        Method func=classToInvest.getMethod("getSideLen",new Class[] {});
        //Square sq=new Square(5);
        System.out.println(func.invoke(obj));*/
        /*
        int x=6;
        Square sq=new Square(x);
        ReflectClass refClassSquare=new ReflectClass(sq);
        System.out.println("Total number of methods: "+refClassSquare.getTotalNumberOfMethods());
        System.out.println("Total number of constructors: "+refClassSquare.getTotalNumberOfConstructors());
        System.out.println("Total number of fields: "+refClassSquare.getTotalNumberOfFields());
        System.out.println("Implemented interfaces: "+refClassSquare.getAllImplementedInterfaces());
        System.out.println("Total number of const fields: "+refClassSquare.getCountOfConstantFields());
        System.out.println("Total number of static methods: "+refClassSquare.getCountOfStaticMethods());
        System.out.println("Is extending: "+refClassSquare.isExtending());
        System.out.println("Parent class name: "+refClassSquare.getParentClassSimpleName());
        System.out.println("Is parent class abstract: "+refClassSquare.isParentClassAbstract());
        System.out.println("All fields,including inheritance: "+refClassSquare.getNamesOfAllFieldsIncludingInheritanceChain());
        System.out.println("Invoking 'int return' function: "+refClassSquare.invokeMethodThatReturnsInt("getArea"));
        Square obj=(Square)refClassSquare.createInstance(1,9);
        System.out.println(obj.getSideLen());
        System.out.println(sq.getSquareId());
        System.out.println(refClassSquare.elevateMethodAndInvoke("setSquareId",new Class[] {Integer.class},20));
        ReflectClass reReflect=new ReflectClass(refClassSquare);
        System.out.println(reReflect.getAllImplementedInterfaces().toString());
        System.out.println(refClassSquare.getInheritanceChain("->"));
        System.out.println(reReflect.getInheritanceChain("->"));
        */
        int x=6;
        Square sq=new Square(x);
        ReflectClass refClassSquare=new ReflectClass();
        refClassSquare.load(sq);
        Square sq2=(Square)refClassSquare.createInstance(1,3);
//        System.out.println(sq2.getArea());
        System.out.println(refClassSquare.getInheritanceChain("->>"));
        System.out.println(refClassSquare.getNamesOfAllFieldsIncludingInheritanceChain());
        System.out.println(refClassSquare.invokeMethodThatReturnsInt("getAreaFloat"));
    }
}
