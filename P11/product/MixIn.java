package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MixIn {
    public MixIn(MixInFlavor flavor, MixInAmount amount) {
        this.flavor = flavor;
        this.amount = amount;
    }
    public MixIn(BufferedReader br) throws IOException {
        amount = MixInAmount.valueOf(br.readLine());
        flavor = new MixInFlavor(br);
    }
    
    public void save(BufferedWriter bw) throws IOException {
        bw.write(amount.toString() + '\n');
        flavor.save(bw);
    }
    public int price() {
        switch(amount) {
            case Light:    return flavor.price() * 4 / 5;
            case Normal:   return flavor.price();
            case Extra:    return flavor.price() * 6 / 5;
            case Drenched: return flavor.price() * 2;
            default: throw new RuntimeException("Invalid MixInAmount: " + amount);
        }
    }
    @Override
    public String toString() {
//        return flavor.toString() + ((amount != MixInAmount.Normal)
        return flavor.toString() + (!amount.equals(MixInAmount.Normal)
                                 ? " (" + amount + ")"
                                 : "");
    }
    private MixInFlavor flavor;
    private MixInAmount amount;
}
