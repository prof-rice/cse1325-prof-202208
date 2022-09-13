public class Demo {
    public class DemoException extends Exception {}
    public void boom() throws DemoException {
        throw new DemoException();  // inside Demo, use bare name
    }
    public static void main(String[] args) {
        try {
            (new Demo()).boom();
        } catch(Demo.DemoException e) { // outside Demo, use fully qualified name
            System.err.println("Boom!");
        }
    }
}
