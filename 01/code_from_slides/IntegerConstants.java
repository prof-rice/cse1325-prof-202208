public class IntegerConstants {
  public static void main(String[] args) {
    for(int i = -1000; i <= 1000; ++i) {
        Integer x = i;
        Integer y = i;
        if(x == y)
          System.out.printf("%4d, " + ((x%10 == 0) ? "\n" : ""), x);
     }
     System.out.println();
  }
}
