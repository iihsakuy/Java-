package finalize_exercise;


class Window {
    Window(int i) {
        System.out.println("Window(" + i +")");
    }
}

class House{
    Window w1 = new Window(1);

    House(){
        System.out.println("House()");
        w3 = new Window(4);
    }

    Window w2 = new Window(2);

    void f(){
        System.out.println("f()");
    }

    Window w3 = new Window(3);
}

public class OrderOfInitialization {
    public static void main(String[] args) {
        House house = new House();
        house.f();
    }
}
