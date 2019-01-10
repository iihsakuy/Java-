package finalize_exercise;

public class NewVarArgs {
    static void printArrays(Object...args){
        for (Object object : args){
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArrays(new Integer(12),new Float(3.14),new Double(6.180));
        printArrays(47,3.14,6.180);
        printArrays("one","two","three");
        printArrays(new A(),new A(),new A());
        printArrays((Object[])new Integer[]{1,2,3,4});
        printArrays();
    }
}
