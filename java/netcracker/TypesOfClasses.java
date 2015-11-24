class Outer{

    private static int field = 123;
    private int anotherField = 321;

    public static class InnerNested{

        public void printField(){
            System.out.println(field);
        }
    }

    public class InnerInner{

        public void printField(){
            System.out.println(anotherField);
        }
    }

    public double getDistance(int x1, int y1, int x2, int y2){

        abstract class Local{

            int x1;
            int y1;
            int x2;
            int y2;

            public Local(int x1, int y1, int x2, int y2) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }

            public abstract double getDistance();
        }

        return new Local(x1, y1, x2, y2) {
            @Override
            public double getDistance() {
                return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
            }
        }.getDistance();
    }
}
