package finalize_exercise;

class Book {
    boolean checkOut = false;

    Book(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public void checkIn() {
        checkOut = false;
    }

    protected void finalize()  {
        if (checkOut) {
            System.out.println("Error : check out");
        }
    }
}

public class TerminationCondition{
        public static void main(String[] args) {
            Book book = new Book(true);
            book.checkIn();
            new Book(true);
            System.gc();
        }
    }
