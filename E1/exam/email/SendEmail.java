public class SendEmail {
    public static void main(String[] args) {
        String line;
        try {
            Email email = new Email(args[0], args[1]);
            while((line = System.console().readLine()) != null)
                email.addParagraph(line);
            email.send();
        } catch(Exception e) {
            System.out.println("usage: java SendEmail address subject"); // not required
            System.err.println(e);
            System.exit(-1);
        }        
    }
}
