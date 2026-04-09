class Table {
    // Synchronized method locks the object for the current thread
    synchronized void printTable(int n) {
        for (int i = 1; i <= 5; i++) {
            System.out.print((n * i) + " ");
            try {
                // Using sleep to demonstrate that even if a thread pauses, 
                // it still holds the lock and prevents the other thread from entering.
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println(); // New line after each table
    }
}

class MyThread1 extends Thread {
    Table t;
    MyThread1(Table t) {
        this.t = t;
    }
    public void run() {
        t.printTable(5);
    }
}

class MyThread2 extends Thread {
    Table t;
    MyThread2(Table t) {
        this.t = t;
    }
    public void run() {
        t.printTable(100);
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) {
        // Shared resource: Only one Table object is created
        Table obj = new Table();

        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);

        // Starting both threads
        t1.start();
        t2.start();
    }
}
