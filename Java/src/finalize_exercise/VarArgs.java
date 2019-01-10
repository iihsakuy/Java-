package finalize_exercise;

class A {
}

public class VarArgs {
    static void printArrays(Object[] args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArrays(new Object[]{
                new Integer(12), new Float(3.14), new Double(6.1800)
        });
        printArrays(new Object[]{"one", "two", "three"});
        printArrays(new Object[]{new A(),new A(),new A()});

    }
}
