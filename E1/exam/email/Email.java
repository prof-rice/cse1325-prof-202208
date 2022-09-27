import java.util.ArrayList;

public class Email {
    public Email(String toAddress, String subject) throws BadEmailAddr {
        if(toAddress.indexOf("@") < 0) throw new BadEmailAddr();
        this.toAddress = toAddress;
        this.subject = subject;
        this.priority = Priority.NORMAL;
        body = new ArrayList<>();
    }
    public void addParagraph(String paragraph) {
        body.add(paragraph);
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public void send() {
        System.out.println("\n\nSending...\n" + this);
    }
    
    @Override
    public String toString() {
        String result = "To:      " + toAddress + "\nSubject: " + subject;
        for(String s : body) result += "\n  " + s;
        return result;
    }
    
    public class BadEmailAddr extends Exception { }
    
    private String toAddress;
    private String subject;
    private Priority priority;
    private ArrayList<String> body;
}
