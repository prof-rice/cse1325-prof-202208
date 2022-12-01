package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;

import product.Item;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Container;
import product.Scoop;
import product.Serving;
import product.Order;

import person.Customer;

import emporium.Emporium;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainWin extends JFrame {

    private final String NAME = "MICE";
    private final String EXTENSION = "mice";
    private final String VERSION = "0.4";
    private final String FILE_VERSION = "2.0";
    private final String MAGIC_COOKIE = "Mïċẹ🍦🍨";

    public MainWin() {
        super("Mavs Ice Cream Emporium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file            = new JMenu("File");
        JMenuItem open            = new JMenuItem("Open");
                  save            = new JMenuItem("Save");
                  saveAs          = new JMenuItem("Save As");
        JMenuItem quit            = new JMenuItem("Quit");
        JMenu     view            = new JMenu("View");
        JMenuItem viewCustomer    = new JMenuItem("Customers");
        JMenuItem viewContainer   = new JMenuItem("Containers");
        JMenuItem viewICF         = new JMenuItem("Ice Cream Flavors");
        JMenuItem viewMIF         = new JMenuItem("Mix In Flavors");
        JMenuItem viewOrder       = new JMenuItem("Orders");
        JMenu     create          = new JMenu("Create");
        JMenuItem createCustomer  = new JMenuItem("Customer");
        JMenuItem createContainer = new JMenuItem("Container");
        JMenuItem createICF       = new JMenuItem("Ice Cream Flavor");
        JMenuItem createMIF       = new JMenuItem("Mix In Flavor");
                  createOrder     = new JMenuItem("Order");
        JMenu     help            = new JMenu("Help");
        JMenuItem about           = new JMenuItem("About");
        
        open           .addActionListener(event -> onOpenClick());
        save           .addActionListener(event -> onSaveClick());
        saveAs         .addActionListener(event -> onSaveAsClick());
        quit           .addActionListener(event -> onQuitClick());
        viewCustomer   .addActionListener(event -> view(Screen.CUSTOMERS));
        viewContainer  .addActionListener(event -> view(Screen.CONTAINERS));
        viewICF        .addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));
        viewMIF        .addActionListener(event -> view(Screen.MIX_IN_FLAVORS));
        viewOrder      .addActionListener(event -> view(Screen.ORDERS));
        createCustomer .addActionListener(event -> onCreateCustomerClick());
        createContainer.addActionListener(event -> onCreateContainerClick());
        createICF      .addActionListener(event -> onCreateIceCreamFlavorClick());
        createMIF      .addActionListener(event -> onCreateMixInFlavorClick());
        createOrder    .addActionListener(event -> onCreateOrderClick());
        about          .addActionListener(event -> onAboutClick());

        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        view.add(viewCustomer);
        view.add(viewContainer);
        view.add(viewICF);
        view.add(viewMIF);
        view.add(viewOrder);
        create.add(createCustomer);
        create.add(createContainer);
        create.add(createICF);
        create.add(createMIF);
        create.add(createOrder);
        help.add(about);
        
        menubar.add(file);
        menubar.add(view);
        menubar.add(create);
        menubar.add(help);
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Mice Controls");

       // Add a New Emporium icon
        saveButton  = new JButton(new ImageIcon("gui/save.png"));
          saveButton.setActionCommand("Save data to default file");
          saveButton.setToolTipText("Save data to default file");
          saveButton.setEnabled(false);
          saveButton.addActionListener(event -> onSaveClick());
          toolbar.add(saveButton);
        
        saveAsButton  = new JButton(new ImageIcon("gui/save_as.png"));
          saveAsButton.setActionCommand("Save data to selected file");
          saveAsButton.setToolTipText("Save data to selected file");
          saveAsButton.addActionListener(event -> onSaveAsClick());
          toolbar.add(saveAsButton);
        
        JButton openButton  = new JButton(new ImageIcon("gui/open.png"));
          openButton.setActionCommand("Load data from selected file");
          openButton.setToolTipText("Load data from selected file");
          openButton.addActionListener(event -> onOpenClick());
          toolbar.add(openButton);
        
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));


        // Create the product buttons using the icons provided
        JButton createCustomerButton  = new JButton(new ImageIcon("gui/createCustomerButton.png"));
          createCustomerButton.setActionCommand("New customer");
          createCustomerButton.setToolTipText("Create new customer");
          toolbar.add(createCustomerButton);
          createCustomerButton.addActionListener(event -> onCreateCustomerClick());

        JButton createContainerButton  = new JButton(new ImageIcon("gui/createContainerButton.png"));
          createContainerButton.setActionCommand("New container");
          createContainerButton.setToolTipText("Create new container");
          toolbar.add(createContainerButton);
          createContainerButton.addActionListener(event -> onCreateContainerClick());

        JButton createIceCreamFlavorButton  = new JButton(new ImageIcon("gui/createIceCreamFlavorButton.png"));
          createIceCreamFlavorButton.setActionCommand("New ice cream flavor");
          createIceCreamFlavorButton.setToolTipText("Create new ice cream flavor");
          toolbar.add(createIceCreamFlavorButton);
          createIceCreamFlavorButton.addActionListener(event -> onCreateIceCreamFlavorClick());

        JButton createMixInFlavorButton  = new JButton(new ImageIcon("gui/createMixInFlavorButton.png"));
          createMixInFlavorButton.setActionCommand("New mix in / topping flavor");
          createMixInFlavorButton.setToolTipText("Create new mix in / topping flavor");
          toolbar.add(createMixInFlavorButton);
          createMixInFlavorButton.addActionListener(event -> onCreateMixInFlavorClick());

        createOrderButton  = new JButton(new ImageIcon("gui/createOrdersButton.png"));
          createOrderButton.setActionCommand("New order");
          createOrderButton.setToolTipText("Create new order");
          toolbar.add(createOrderButton);
          createOrderButton.addActionListener(event -> onCreateOrderClick());
          createOrderButton.setEnabled(false);

        toolbar.add(Box.createHorizontalStrut(25));
        
        JButton viewCustomersButton  = new JButton(new ImageIcon("gui/viewCustomersButton.png"));
          viewCustomersButton.setActionCommand("View customers");
          viewCustomersButton.setToolTipText("View customers");
          toolbar.add(viewCustomersButton);
          viewCustomersButton.addActionListener(event -> view(Screen.CUSTOMERS));

        JButton viewContainersButton  = new JButton(new ImageIcon("gui/viewContainersButton.png"));
          viewContainersButton.setActionCommand("View containers");
          viewContainersButton.setToolTipText("View containers");
          toolbar.add(viewContainersButton);
          viewContainersButton.addActionListener(event -> view(Screen.CONTAINERS));

        JButton viewIceCreamFlavorsButton  = new JButton(new ImageIcon("gui/viewIceCreamFlavorsButton.png"));
          viewIceCreamFlavorsButton.setActionCommand("View ice cream flavors");
          viewIceCreamFlavorsButton.setToolTipText("View ice cream flavors");
          toolbar.add(viewIceCreamFlavorsButton);
          viewIceCreamFlavorsButton.addActionListener(event -> view(Screen.ICE_CREAM_FLAVORS));

        JButton viewMixInFlavorsButton  = new JButton(new ImageIcon("gui/viewMixInFlavorsButton.png"));
          viewMixInFlavorsButton.setActionCommand("View mix in / topping flavors");
          viewMixInFlavorsButton.setToolTipText("View mix in / topping flavors");
          toolbar.add(viewMixInFlavorsButton);
          viewMixInFlavorsButton.addActionListener(event -> view(Screen.MIX_IN_FLAVORS));

         JButton viewOrdersButton  = new JButton(new ImageIcon("gui/viewOrdersButton.png"));
          viewOrdersButton.setActionCommand("View orders");
          viewOrdersButton.setToolTipText("View orders");
          toolbar.add(viewOrdersButton);
          viewOrdersButton.addActionListener(event -> view(Screen.ORDERS));

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        // /////////////////////////// ////////////////////////////////////////////
        // M A I N   D I S P L A Y
        // Provide a text entry box to show data
        display = new JLabel();
        display.setVerticalAlignment(JLabel.TOP);
        display.setFont(new Font("Courier New", Font.BOLD, 18));
        add(display, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        // msg = new JLabel();
        // add(msg, BorderLayout.PAGE_END);
        
        // Make everything in the JFrame visible
        setVisible(true);
        
        // Start a new emporium
        emporium = new Emporium();
        filename = new File("untitled." + EXTENSION);
        setDirty(false);     // disables Save when no new data exists
        setOrderAvailable(); // disables new Scoop when no ice cream flavors exist
        view(Screen.ICE_CREAM_FLAVORS);   
    }
    
    // Listeners
    protected void onQuitClick() {System.exit(0);}   // Exit the game
    
    protected void onCreateCustomerClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel phone = new JLabel("<html><br/>Phone</html>");
            JTextField phones = new JTextField(20);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                phone,  phones
            };
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Customer", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/customer_icon.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addCustomer(new Customer(names.getText(), phones.getText()));
                setDirty(true);
                view(Screen.CUSTOMERS);         
           }
        } catch(Exception e) {
            System.err.println("onCreateContainerClick exception: " + e);
        }    
    }

    protected void onCreateContainerClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel scoop = new JLabel("<html><br/>Max Scoops</html>");
            SpinnerModel range = new SpinnerNumberModel(1, 0, 15, 1);
            JSpinner scoops = new JSpinner(range);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                scoop,  scoops};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Container", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/container_icon.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addContainer(new Container(
                    names.getText(), descs.getText(), (Integer) scoops.getValue()));
                setDirty(true);
                view(Screen.CONTAINERS);         
           }
            // emporium.addContainer(new Container(
            //     JOptionPane.showInputDialog(this, "Name?", "Create Container", JOptionPane.QUESTION_MESSAGE),
            //     JOptionPane.showInputDialog(this, "Description?", "Create Container", JOptionPane.QUESTION_MESSAGE),
            //     Integer.parseInt(JOptionPane.showInputDialog(this, "Max Scoops?", "Create Container", JOptionPane.QUESTION_MESSAGE))
            // ));
        } catch(Exception e) {
            System.err.println("onCreateContainerClick exception: " + e);
        }
    }
    protected void onCreateIceCreamFlavorClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel price = new JLabel("<html><br/>Price</html>");
            SpinnerModel priceRange = new SpinnerNumberModel(1, 0, 1000, 10);
            JSpinner prices = new JSpinner(priceRange);
            JLabel cost = new JLabel("<html><br/>Cost</html>");
            SpinnerModel costRange = new SpinnerNumberModel(1, 0, 1000, 10);
            JSpinner costs = new JSpinner(costRange);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                price,  prices,
                cost,   costs};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New Ice Cream Flavor", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/iceCreamFlavor_logo.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addIceCreamFlavor(new IceCreamFlavor(
                    names.getText(), descs.getText(), 
                    (Integer) prices.getValue(), (Integer) costs.getValue()));
                setDirty(true);
                view(Screen.ICE_CREAM_FLAVORS);         
           }
        
/*            emporium.addIceCreamFlavor(new IceCreamFlavor(
                JOptionPane.showInputDialog(this, "Name?", "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Description?", "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Price?", "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Cost?", "Create Ice Cream Flavor", JOptionPane.QUESTION_MESSAGE))
            ));*/
        } catch(Exception e) {
        }
        try {
            setOrderAvailable();
            setDirty(true);
            view(Screen.ICE_CREAM_FLAVORS);         
        } catch(Exception e) {
            System.err.println("onCreateIceCreamFlavorClick exception: " + e);
        }
    }
    protected void onCreateMixInFlavorClick() {
        try {
            JLabel name = new JLabel("<html>Name</html>");
            JTextField names = new JTextField(20);
            JLabel desc = new JLabel("<html><br/>Description</html>");
            JTextField descs = new JTextField(20);
            JLabel price = new JLabel("<html><br/>Price</html>");
            SpinnerModel priceRange = new SpinnerNumberModel(1, 0, 1000, 10);
            JSpinner prices = new JSpinner(priceRange);
            JLabel cost = new JLabel("<html><br/>Cost</html>");
            SpinnerModel costRange = new SpinnerNumberModel(1, 0, 1000, 10);
            JSpinner costs = new JSpinner(costRange);
            
            Object[] objects = { // Array of widgets to display
                name,   names,
                desc,   descs,
                price,  prices,
                cost,   costs};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "Create Mix In Flavor", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/mixInFlavor_logo.png"));
            if(button == JOptionPane.OK_OPTION) {
                emporium.addMixInFlavor(new MixInFlavor(
                    names.getText(), descs.getText(), 
                    (Integer) prices.getValue(), (Integer) costs.getValue()));
                setDirty(true);
                view(Screen.MIX_IN_FLAVORS);         
           }
/*        
            emporium.addMixInFlavor(new MixInFlavor(
                JOptionPane.showInputDialog(this, "Name?", "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Description?", "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Price?", "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(this, "Cost?", "Create Mix In Flavor", JOptionPane.QUESTION_MESSAGE))
            ));   */
            setDirty(true);
            view(Screen.MIX_IN_FLAVORS);         
        } catch(Exception e) {
            System.err.println("onCreateMixInFlavorClick exception: " + e);
        }
    }
    protected void onCreateOrderClick() {
        Customer customer = (Customer) JOptionPane.showInputDialog(this, null, "Select Customer", 
            JOptionPane.OK_CANCEL_OPTION, new ImageIcon("customer_icon.png"), emporium.customers(), null);
        if(customer == null) return;
        Order order = null;
        try {
            Serving serving = null;
            while((serving = onCreateServing(customer)) != null) {
                if(order == null) order = new Order(customer);
                order.addServing(serving);
                int result = JOptionPane.showConfirmDialog(
                    this, "<html>" + order + "</html>", "Add Another Serving?", JOptionPane.YES_NO_CANCEL_OPTION);
                if(result == JOptionPane.CANCEL_OPTION) return;
                if(result == JOptionPane.NO_OPTION) break;
            }
            if(order != null) emporium.addOrder(order);
            setDirty(true);
            view(Screen.ORDERS);
        } catch(Exception e) {
            System.err.println("onCreateScoop exception: " + e);
        }
    }
    protected Serving onCreateServing(Customer customer) {
        Serving serving = null;
        try {
            Object[] favorites = emporium.favoriteServings(customer);
            if(favorites.length > 0) {
                JComboBox<Object> servings = new JComboBox<>(favorites);
                int response = JOptionPane.showConfirmDialog(this, servings, "Select favorite serving?", 
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("createOrdersButton.png"));
                if(response == JOptionPane.CANCEL_OPTION) return null;
                if(response == JOptionPane.YES_OPTION) return (Serving) servings.getSelectedItem();
            }
            Container container = (Container) JOptionPane.showInputDialog(this, "Container?", "New Container", 
                JOptionPane.QUESTION_MESSAGE, null, emporium.containers(), null);
            if(container != null) {
                serving = new Serving(container);
                Scoop scoop = null;
                boolean noScoop = true;
                //String prompt = "<html>" + serving + "<br/>Start with which scoop?</html>";
                while(((scoop = onCreateScoop()) != null) && (serving.numScoops() <= container.maxScoops())) {
                    serving.addScoop(scoop);
                    noScoop = false;
                    int result = JOptionPane.showConfirmDialog(
                        this, serving, "Add another scoop?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result == JOptionPane.CANCEL_OPTION) return null;
                    if(result == JOptionPane.NO_OPTION) break;
                }
                if(noScoop) return null; 
                if(emporium.mixInFlavors().length > 0) {
                    String prompt = "<html>" + serving + "<br/>Add a topping?</html>";
                    MixIn topping = null;
                    while((topping = onCreateMixIn(prompt)) != null) {
                        serving.addTopping(topping);
                        prompt = "<html>" + serving + "<br/>Add another topping?</html>";
                    }
                }  
            }
        } catch(Exception e) {
            System.err.println("onCreateScoop exception: " + e);
            return null;
        }
        return serving;
    }

    protected Scoop onCreateScoop() {
        Scoop scoop = null;
        try {
            IceCreamFlavor icf = (IceCreamFlavor) JOptionPane.showInputDialog(this, "Ice Cream Flavor?", "New Scoop", JOptionPane.QUESTION_MESSAGE, null, emporium.iceCreamFlavors(), null);
            if(icf != null) {
                scoop = new Scoop(icf);
                if(emporium.mixInFlavors().length > 0) {
                    String prompt = "<html>" + scoop + "<br/>Add a mix in?</html>";
                    MixIn mixin = null;
                    while((mixin = onCreateMixIn(prompt)) != null) {
                        scoop.addMixIn(mixin);
                        prompt = "<html>" + scoop + "<br/>Add another mix in?</html>";
                    }
                }  
            }
        } catch(Exception e) {
            System.err.println("onCreateScoopClick exception: " + e);
            scoop = null;
        }
        return scoop;
    }
    
    
    protected MixIn onCreateMixIn(String prompt) {
        MixIn mixin = null;
        try {
            JLabel status = new JLabel(prompt);
            JLabel flavor = new JLabel("<html><br/>MixIn Flavor</html>");
            JComboBox<Object> flavors = new JComboBox<>(emporium.mixInFlavors());
            JLabel amount = new JLabel("<html><br/>MixIn Amount</html>");
            JComboBox<MixInAmount> amounts = new JComboBox<>(MixInAmount.values());
            amounts.setSelectedItem(MixInAmount.Normal); // default value
          
            Object[] objects = { // Array of widgets to display
                status,
                flavor, flavors,
                amount, amounts};
            int button = JOptionPane.showConfirmDialog( // Show the dialog
                this, objects, "New MixIn", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/MixInIcon.png"));
            if(button == JOptionPane.YES_OPTION) 
                mixin = new MixIn((MixInFlavor) flavors.getSelectedItem(), 
                                  (MixInAmount) amounts.getSelectedItem());         
        } catch(Exception e) {
            System.err.println("onCreateMixIn exception: " + e);
        }
        return mixin;
    }
    // File I/O Methods
    
   protected void onOpenClick() { 
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", EXTENSION);
        fc.addChoosableFileFilter(miceFiles);         // Add "Mice file" filter
        fc.setFileFilter(miceFiles);                  // Show Mice files only by default
        
        int result = fc.showOpenDialog(this);        // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();        // Obtain the selected File object
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a MICE file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible MICE file format");
                
                emporium = new Emporium(br);                   // Open a new emporium
                view(Screen.ICE_CREAM_FLAVORS);                           // Update the user interface
                setOrderAvailable();
                setDirty(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to load " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             }
        }
    }

    protected void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            emporium.save(bw);
            setDirty(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to write " + filename + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }

    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);  // Create a file chooser dialog
        FileFilter miceFiles = new FileNameExtensionFilter("MICE files", EXTENSION);
        fc.addChoosableFileFilter(miceFiles);         // Add "MICE file" filter
        fc.setFileFilter(miceFiles);                  // Show MICE files only by default
        
        int result = fc.showSaveDialog(this);         // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) {  // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile();          // Obtain the selected File object
            if(!filename.getAbsolutePath().endsWith("." + EXTENSION))  // Ensure it ends with ".mice"
                filename = new File(filename.getAbsolutePath() + "." + EXTENSION);
            onSaveClick();                           // Delegate to Save method
        }
    }

    
    // About and miscellaneous
    
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog(this, "Mavs Ice Cream Emporium");
        about.setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
/*              
        try {
            BufferedImage myPicture = ImageIO.read(new File("gui/logo.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
*/
        Canvas logo = new Canvas("gui/logo.png", "gui/spiropic.txt");
        logo.setAlignmentX(Canvas.LEFT_ALIGNMENT);
        about.add(logo);
        
        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("<html>"
          + "<br/><p><font size=+4>MICE</font></p>"
          + "</html>", JLabel.CENTER);
        text.add(title);

        JLabel subTitle = new JLabel("<html>"
          + "<br/><p><font size=+1>Mavs Ice Cream Emporium</font></p>"
          + "</html>", JLabel.CENTER);
        text.add(subTitle);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Version " + VERSION + "</p>"
          + "<p>Copyright 2022 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "Logo by Schmidsi, per the Pixabay License"
          + "<p><font size=-2>https://pixabay.com/en/ice-ice-cream-cone-ice-ball-pink-1429596/</font></p>"
          + "<p>Ice cream icons created by Freepik - Flaticon</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/ice-cream-cone</font></p"
          + "<p>File icons created by Pixel perfect - Flaticon</p>"  
          + "<p><font size=-2>https://www.flaticon.com/free-icons/direct-download</font></p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/file-upload</font></p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/ui</font></p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icons/</font></p>"
          + "<br/></html>", JLabel.CENTER);
        text.add(artists);
        about.add(text);
        
        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        panel.add(ok);
        about.add(panel);
        
        about.add(Box.createVerticalStrut(10));
        
        about.pack();
        about.setVisible(true);
     }
     
    private enum Screen {CUSTOMERS, ICE_CREAM_FLAVORS, MIX_IN_FLAVORS, CONTAINERS, ORDERS}
    private void view(Screen screen) {
        String title = "";
        StringBuilder s = new StringBuilder();
        switch(screen) {
             case CUSTOMERS:
                 title = "Customers";
                 for(var t : emporium.customers()) s.append(t.toString() + "<br/>");
                 break;
             case ICE_CREAM_FLAVORS: 
                 title = "Ice Cream Flavors";
                 for(var t : emporium.iceCreamFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case MIX_IN_FLAVORS: 
                 title = "Mix In Flavors";
                 for(var t : emporium.mixInFlavors()) s.append(t.toString() + "<br/>");
                 break;
             case CONTAINERS: 
                 title = "Containers";
                 for(var t : emporium.containers()) s.append(t.toString() + "<br/>");
                 break;
             case ORDERS: 
                 title = "Orders";
                 int orderNumber = 1;
                 for(var t : emporium.orders()) {
                     int price = ((Order) t).price();
                     s.append("Order " + orderNumber++ + String.format("  $%d.%02d", price/100, price%100) + "  " + t.toString() + "<br/>");
                 }
                 break;
             default:
                 display.setText("PANIC: Invalid Displays type: " + display);
        }
        display.setText("<html><font size=+4>" + title + "<br/></font><font size=+2>" + s.toString() + "</font></html>");
    }
     
    private void setDirty(boolean isDirty) {
        save.setEnabled(isDirty);
        saveAs.setEnabled(isDirty);
        saveButton.setEnabled(isDirty);
        saveAsButton.setEnabled(isDirty);
    };
     
    private void setOrderAvailable() {
        boolean orderIsAvailable = (emporium.iceCreamFlavors().length > 0)
                                && (emporium.customers().length > 0);
        createOrder.setEnabled(orderIsAvailable); // until an ice cream flavor and a customer are defined
        createOrderButton.setEnabled(orderIsAvailable);
    }
    
    private Emporium emporium;
    private File filename;

    private JLabel display;         // Main data display

    private JMenuItem save;         // defined here so we can disable and enable it
    private JMenuItem saveAs;
    private JButton saveButton;
    private JButton saveAsButton;

    private JMenuItem createOrder;  // defined here so we can disable and enable it
    private JButton createOrderButton;

    // private JLabel msg;                     // Status message display
}
