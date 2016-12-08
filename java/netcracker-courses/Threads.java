public class Solution {
    static int a = 1;

    static void registerThreads() throws InterruptedException {

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                a++;
            }
        });
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                a++;
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();
    }


    public static void main(String... args) {

        try {
            registerThreads();
        } catch (InterruptedException error) {
            System.out.println(error.getMessage());
        }

        System.out.println(a);

    }

}
