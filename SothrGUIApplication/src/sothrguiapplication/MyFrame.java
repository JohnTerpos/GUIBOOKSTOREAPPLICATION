package sothrguiapplication;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;



public class MyFrame extends JFrame 
{
    BookDao bookdao=new BookDao();
    
    //Components for GUI Applications
    private String[] categories={"Κατηχητικό","Ιστορικά","Παιδικά","Ημερολόγια","Θεολογικά","Αγία Γραφή"};
    private String[] options={"Όλα","Διαθέσιμα","Κατηγορία","Συγγραφέας"};
    private String[] methodsPayment={"Μετρητά","Κάρτα"};
    private JButton submitbtn;
    private JLabel namelabel,surnamelabel,emaillabel,phonelabel,citylabel,addresslabel,paymentlabel,book_idlabel,titlelabel,authorlabel,pricelabel,pageslabel,publisherlabel,categorylabel,contain_pictureslabel,contain_cdlabel,number_available_label;
    private JTextArea results=new JTextArea(20,40);
    private JTextArea orders=new JTextArea(20,40);
    private JScrollPane scrollPane = new JScrollPane(results); 
    private JTextField NAME,SURNAME,EMAIL,PHONE,CITY,ADDRESS,ID,TITLE,AUTHOR,PRICE,PAGES,PUBLISHER,NUMBER_AVAILABLE;
    private JList CATEGORY,PAYMENT;
    private JCheckBox picturesYes,cdYes;
    private JPanel pl1,pl2;
    private JMenu FileMenu,SearchMenu,EditMenu,OrderMenu;
    private JMenuBar ApplicationBar;
    private JMenuItem AllOrdersItem,OrderBookItem,CancelOrderItem,NewBookItem,ExitItem,LoadFromFileItem,SaveItem,AllBooksItem,AvailableBooksItem,CategoryItem,AuthorItem,BookIdItem,NumberAvailableBooksItem,PriceItem,DeleteBookItem;
    private JFileChooser selectFile=new JFileChooser();
    
   //Constructor
    public MyFrame()
    {
       results.setEditable(false);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
       FileMenu=new JMenu("Αρχείο");
       SearchMenu=new JMenu("Αναζήτηση Βιβλίου");
       EditMenu= new JMenu("Επεξεργασία στοιχείων Βιβλίου");
       OrderMenu= new JMenu("Παραγγελία Βιβλίου"); 
       ApplicationBar=new JMenuBar();
       
       FileMenu.setMnemonic('A');
       SearchMenu.setMnemonic('B');
       EditMenu.setMnemonic('C');
       OrderMenu.setMnemonic('D');
       
       NewBookItem=FileMenu.add("Προσθήκη νέου βιβλίου");
       NewBookItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
       NewBookItem.addActionListener(new ActionListener()
       {
          @Override
          public void actionPerformed(ActionEvent e) {
              createFormBook();
         }  
       });
       FileMenu.addSeparator();
       
       LoadFromFileItem=FileMenu.add("Φόρτωση στοιχείων βιβλίων από αρχείο");
       LoadFromFileItem.setAccelerator(KeyStroke.getKeyStroke('L', Event.CTRL_MASK));
       LoadFromFileItem.addActionListener(new ActionListener()
       {
          @Override
          public void actionPerformed(ActionEvent e) {
              loadFile();
         }  
       });
       FileMenu.addSeparator();
       
       SaveItem=FileMenu.add("Αποθήκευση στοιχείων όλων των βιβλίων σε αρχείο");
       SaveItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
       SaveItem.addActionListener(new ActionListener()
       {
          @Override
          public void actionPerformed(ActionEvent e) {
              saveFile();
         }  
       });
       FileMenu.addSeparator();
       
       ExitItem=FileMenu.add("Έξοδος");
       ExitItem.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));
       ExitItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
              exit();
         }  
       });
       
       OrderBookItem=OrderMenu.add("Παραγγελία");
       OrderBookItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
              order();
         }  
       });
       OrderMenu.addSeparator();
       
       AllOrdersItem=OrderMenu.add("Εμφάνιση παραγγελιών");
       AllOrdersItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
              allOrders();
         }  
       });
       OrderMenu.addSeparator();
       
       CancelOrderItem=OrderMenu.add("Ακύρωση Παραγγελίας");
       CancelOrderItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
              cancelOrder();
         }  
       });
       
       AllBooksItem=SearchMenu.add("Όλα τα Βιβλία");
       AllBooksItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             searchAllBooks();
         }  
       });
       
       SearchMenu.addSeparator();
       AvailableBooksItem=SearchMenu.add("Διαθέσιμα Βιβλία");
       AvailableBooksItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             searchAvailableBooks();
         }  
       });
       
       SearchMenu.addSeparator();
       CategoryItem=SearchMenu.add("Κατηγορία Βιβλίων");
       CategoryItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             searchBooksByCategory();
         }  
       });
       SearchMenu.addSeparator();
       
       AuthorItem=SearchMenu.add("Συγγραφέας Βιβλίων");
       AuthorItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             searchBooksByAuthor();
         }  
       });
       SearchMenu.addSeparator();
       
       BookIdItem=SearchMenu.add("Κωδικός Βιβλίων");
       BookIdItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             searchBookByID();
         }  
       });
       SearchMenu.addSeparator();
       
       NumberAvailableBooksItem=EditMenu.add("Αριθμός διαθέσιμων αντίτυπων");
       NumberAvailableBooksItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             updateNumberAvailable();
         }  
       });
       EditMenu.addSeparator();
       
       PriceItem=EditMenu.add("Τιμή Βιβλίου");
       PriceItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             updateBookPrice();
         }  
       });
       EditMenu.addSeparator();
       
       DeleteBookItem=EditMenu.add("Διαγραφή Βιβλίου");
       DeleteBookItem.addActionListener(new ActionListener()
       {
         @Override
         public void actionPerformed(ActionEvent e) {
             deleteBook();
         }  
       });
               
       this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       this.addWindowListener(new WindowAdapter() 
       {
          @Override
          public void windowClosing(WindowEvent e)
          {
            exit();
          }            
       });
        
       this.setSize(500, 500);
       this.setTitle("SothrBookApplication");
       this.setLocationRelativeTo(null);
       this.setVisible(true);
       
       ApplicationBar.add(FileMenu);
       ApplicationBar.add(EditMenu);
       ApplicationBar.add(SearchMenu);
       ApplicationBar.add(OrderMenu);
       this.setJMenuBar(ApplicationBar);
    }
    
    private void createFormBook()
    {
      pl1=new JPanel();
      pl2=new JPanel();
      
      book_idlabel=new JLabel("Κωδικός βιβλίου: ");
      titlelabel=new JLabel("Τίτλος: ");
      authorlabel=new JLabel("Συγγραφέας: ");
      pricelabel=new JLabel("Τιμή: ");
      pageslabel=new JLabel("Αριθμός σελίδων: ");
      publisherlabel=new JLabel("Εκδόσεις: ");
      categorylabel=new JLabel("Κατηγορία: ");
      contain_pictureslabel=new JLabel("Εικονογράφηση: ");
      contain_cdlabel=new JLabel("CD: ");
      number_available_label=new JLabel("Αριθμός Τεμαχίων: ");
      
      submitbtn=new JButton("Υποβολή");
      picturesYes=new JCheckBox("Ναι");
      cdYes=new JCheckBox("Ναι");
      
      ID=new JTextField(5);
      ID.setEditable(false);
      TITLE=new JTextField(30);
      AUTHOR=new JTextField(30);
      PRICE=new JTextField(6);
      PUBLISHER=new JTextField(30);
      PAGES=new JTextField(4);
      NUMBER_AVAILABLE=new JTextField(4);
      
      CATEGORY=new JList(categories);
      CATEGORY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane scrollPane = new JScrollPane(CATEGORY);
      scrollPane.setBounds(0, 0, 100, 100);
      
      pl1.setLayout(new BoxLayout(pl1,BoxLayout.Y_AXIS));
      pl1.add(book_idlabel);
      pl1.add(ID);
      pl1.add(titlelabel);
      pl1.add(TITLE);
      pl1.add(authorlabel);
      pl1.add(AUTHOR);
      pl1.add(pricelabel);
      pl1.add(PRICE);
      pl1.add(publisherlabel);
      pl1.add(PUBLISHER);
      pl1.add(categorylabel);
      pl1.add(scrollPane);
      pl1.add(pageslabel);
      pl1.add(PAGES);
      pl1.add(contain_pictureslabel);
      pl1.add(picturesYes);
      pl1.add(contain_cdlabel);
      pl1.add(cdYes);
      pl1.add(number_available_label);
      pl1.add(NUMBER_AVAILABLE);
      pl2.setLayout(new FlowLayout(FlowLayout.RIGHT));
      pl2.add(submitbtn);
      
      this.add(pl1,BorderLayout.CENTER);
      this.add(pl2,BorderLayout.SOUTH);
      
      submitbtn.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) 
         {   
             submitBook();
         } 
      });
    }
    
    private void loadFile()
    {
       int result = selectFile.showOpenDialog(this);
       if (result == JFileChooser.APPROVE_OPTION) 
       {
          File selectedFile = selectFile.getSelectedFile();
          
          try 
          {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
            boolean eof=false;
            int status=0;
            
            while(!eof)
            {
               String line=reader.readLine();
               if(line==null)
                   eof=true;
               
               String[] lines=line.split("(\\s|\\t)+|#|,");
               Book b=new Book();
               b.setTitle(lines[0]);
               b.setAuthor(lines[1]);
               b.setPrice(Float.parseFloat(lines[2]));
               b.setPages(Integer.parseInt(lines[3]));
               b.setPublisher(lines[4]);
               b.setCategory(lines[5]);
               b.setContain_pictures(lines[6]);
               b.setContain_cd(lines[7]);
               b.setNumber_available(Integer.parseInt(lines[8]));
               
               status=BookDao.addBook(b);
            }
            
            reader.close();
            if(status!=0)
                 JOptionPane.showMessageDialog(null, "Το/α βιβλίο/α σας καταχωρήθηκε/αν επιτυχώς στο σύστημα!");
            else
                 JOptionPane.showMessageDialog(null, "Υπήρξε πρόβλημα με τα στοιχεία που δώσατε σε κάποιο βιβλίο!");
          } 
          catch (FileNotFoundException ex)
          {
              JOptionPane.showMessageDialog(null,"Δε βρέθηκε το αρχείο που ζητήσατε!");
          }
          catch(IOException e)
          {
              JOptionPane.showMessageDialog(null, e.toString());
          }

       }
    }
    
    private void saveFile()
    {
        int result = selectFile.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) 
        {
           File selectedFile = selectFile.getSelectedFile();
            try
            {
               BufferedWriter bw=new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()));
               List<Book> blist=new ArrayList();
               String choice=(String)JOptionPane.showInputDialog(this,
               "Επιλέξτε το περιεχόμενο του αρχείου", 
               null,            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               options, 
               options[0] 
               );
               
               if(choice.equals(options[0]))
                   blist=bookdao.getAllBooks();
               else if(choice.equals(options[1]))
                   blist=bookdao.getAvailableBooks();
               else if(choice.equals(options[2]))
               {
                   String ct=(String)JOptionPane.showInputDialog(this,
                   "Επιλέξτε μία από τις παρακάτω κατηγορίες", 
                   null,            
                   JOptionPane.PLAIN_MESSAGE,
                   null,            
                   categories, 
                   categories[0] 
                   );
                   blist=bookdao.getBooksByCategory(ct);
               }
               else
               {
                  String author=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον συγγραφέα: ");
                  blist=bookdao.getBooksByAuthor(author);
               }
               
               for(Book b: blist)
               {
                  bw.write(b.getId()+" "+b.getTitle()+" "+b.getAuthor()+" "+b.getPrice()+" "+b.getPublisher()+" "+b.getPages()+" "+b.getCategory()+" "+b.getContain_pictures()+" "+b.getContain_cd()+" "+b.getNumber_available()+"\n");
               }
               
               bw.close();
               JOptionPane.showMessageDialog(null,"Τα στοιχεία των βιβλίων του συστήματος καταχωρήθηκαν επιτυχώς στο αρχείο "+selectedFile.getName());
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
    }
    
    private void deleteBook()
    {
       String sid=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον κωδικό βιβλίου: ");
       int id=Integer.parseInt(sid);
       int status=bookdao.deleteBook(id);
       
       if(status!=0)
           JOptionPane.showMessageDialog(null,"Επιτυχής διαγραφή του βιβλίου!");
       else
           JOptionPane.showMessageDialog(null,"Δεν υπάρχει βιβλίο με κωδικό "+id+" στο σύστημα!");
    }
    
    private void allOrders()
    {
        orders.setEditable(false);
        orders.setText("");
        try 
        {
          File in=new File("orders.txt");
          BufferedReader input = new BufferedReader(new FileReader(in));
          String line;
          
          while((line=input.readLine())!=null)
          {
              orders.append(line);
          }
          
          input.close();
          this.add(orders);
        } 
        catch (FileNotFoundException ex) 
        {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
        catch(IOException ex)
        {
           JOptionPane.showMessageDialog(null, ex.toString());
        }
    }  
    
    private void order()
    {
       this.remove(results);
       this.remove(orders);
       pl1=new JPanel();
       pl2=new JPanel();
      
       book_idlabel=new JLabel("Κωδικός βιβλίου: ");
       namelabel=new JLabel("Όνομα: ");
       surnamelabel=new JLabel("Επώνυμο: ");
       emaillabel=new JLabel("Email: ");
       phonelabel=new JLabel("Τηλέφωνο: ");
       citylabel=new JLabel("Πόλη: ");
       addresslabel=new JLabel("Διεύθυνση: ");
       paymentlabel=new JLabel("Τρόπος πληρωμής: ");
       
       ID=new JTextField(5);
       NAME=new JTextField(30);
       SURNAME=new JTextField(30);
       EMAIL=new JTextField(20);
       PHONE=new JTextField(30);
       CITY=new JTextField(20);
       ADDRESS=new JTextField(4);
       submitbtn=new JButton("Υποβολή");
       
       PAYMENT=new JList(methodsPayment);
       PAYMENT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       JScrollPane scrollPane = new JScrollPane(PAYMENT);
       scrollPane.setBounds(0, 0, 100, 100);
       
       pl1.setLayout(new BoxLayout(pl1,BoxLayout.Y_AXIS));
       pl1.add(book_idlabel);
       pl1.add(ID);
       pl1.add(namelabel);
       pl1.add(NAME);
       pl1.add(surnamelabel);
       pl1.add(SURNAME);
       pl1.add(emaillabel);
       pl1.add(EMAIL);
       pl1.add(phonelabel);
       pl1.add(PHONE);
       pl1.add(citylabel);
       pl1.add(CITY);
       pl1.add(addresslabel);
       pl1.add(ADDRESS);
       pl1.add(paymentlabel);
       pl1.add(PAYMENT);
       pl2.setLayout(new FlowLayout(FlowLayout.RIGHT));
       pl2.add(submitbtn);
      
       this.remove(results);
       this.add(pl1,BorderLayout.CENTER);
       this.add(pl2,BorderLayout.SOUTH);
      
       submitbtn.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) 
         {   
             submitOrder();
         } 
      });
    }
    
    private void cancelOrder()
    {
        this.remove(orders);
        this.remove(results);
        String em=(String)JOptionPane.showInputDialog("Πληκτρολογήστε το email σας!");
        String sid=(String)JOptionPane.showInputDialog("Πληκτρολογήστε το κωδικό βιβλίου!");
        int id=Integer.parseInt(sid);
        try 
        {
            File in=new File("orders.txt");
            File out=new File("new_orders.txt");
            BufferedReader input=new BufferedReader(new FileReader(in));
            BufferedWriter output=new BufferedWriter(new FileWriter(out,true));
            String line;
            int status=0;
            
            while((line=input.readLine())!=null)
            {
              String[] lines=line.split("#");
              if(lines[3].equals(em)&& Integer.parseInt(lines[0])==id)
              {
                status=bookdao.cancelOrder(Integer.parseInt(lines[0]));
                continue;
              }
              
              output.write(line);
            }
            
            input.close();
            output.close();
            out.renameTo(in);
            
            if(status!=0)
              JOptionPane.showMessageDialog(null,"Η παραγγελία σας ακυρώθηκε επιτυχώς!");
        } 
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } 
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
      
    }
    
    
    private void searchAllBooks()
    {
        List<Book> bl=new ArrayList();
        bl=bookdao.getAllBooks();
        results.setText("");
        results.append("Κωδικός#Τίτλος#Συγγραφέας#Τιμή#Εκδόσεις#ΑριθμόςΣελιδών#Κατηγορία#Εικόνες#CD#Αριθμός τεμαχίων\n");
        
        for(Book b: bl)
        {
            results.append(b.getId()+"#"+b.getTitle()+"#"+b.getAuthor()+"#"+b.getPrice()+"#"+b.getPublisher()+"#"+b.getPages()+"#"+b.getCategory()+"#"+b.getContain_pictures()+"#"+b.getContain_cd()+"#"+b.getNumber_available()+"\n");
        }
        
        this.remove(pl1);
        this.remove(pl2);
        this.add(scrollPane);
    }
    
    private void searchAvailableBooks()
    {
        this.remove(orders);
        List<Book> bl=new ArrayList();
        bl=bookdao.getAvailableBooks();
        results.setText("");
        results.append("Κωδικός#Τίτλος#Συγγραφέας#Τιμή#Εκδόσεις#ΑριθμόςΣελιδών#Κατηγορία#Εικόνες#CD#Αριθμός τεμαχίων\n");
        
        for(Book b: bl)
        {
            results.append(b.getId()+"#"+b.getTitle()+"#"+b.getAuthor()+"#"+b.getPrice()+"#"+b.getPublisher()+"#"+b.getPages()+"#"+b.getCategory()+"#"+b.getContain_pictures()+"#"+b.getContain_cd()+"#"+b.getNumber_available()+"\n");
        }
        
        this.remove(pl1);
        this.remove(pl2);
        this.add(scrollPane);
    }
    
    private void searchBooksByCategory()
    {
        this.remove(orders);
        String ct=(String)JOptionPane.showInputDialog(this,
               "Επιλέξτε μία από τις παρακάτω κατηγορίες", 
               "Αναζήτηση βιβλίων",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               categories, 
               categories[0] 
            );
        List<Book> bl=new ArrayList();
        bl=bookdao.getBooksByCategory(ct);
        results.setText("");
        results.append("Κωδικός#Τίτλος#Συγγραφέας#Τιμή#Εκδόσεις#ΑριθμόςΣελιδών#Κατηγορία#Εικόνες#CD#Αριθμός τεμαχίων\n");
        
        for(Book b: bl)
        {
            results.append(b.getId()+"#"+b.getTitle()+"#"+b.getAuthor()+"#"+b.getPrice()+"#"+b.getPublisher()+"#"+b.getPages()+"#"+b.getCategory()+"#"+b.getContain_pictures()+"#"+b.getContain_cd()+"#"+b.getNumber_available()+"\n");
        }
        
        this.remove(pl1);
        this.remove(pl2);
        this.add(scrollPane);
    }
    
    private void searchBooksByAuthor()
    {
       String author=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον συγγραφέα: ");
       List<Book> bl=new ArrayList();
       bl=bookdao.getBooksByAuthor(author);
       results.setText("");
       results.append("Κωδικός#Τίτλος#Συγγραφέας#Τιμή#Εκδόσεις#ΑριθμόςΣελιδών#Κατηγορία#Εικόνες#CD#Αριθμός τεμαχίων\n");
        
       for(Book b: bl)
       {
          results.append(b.getId()+"#"+b.getTitle()+"#"+b.getAuthor()+"#"+b.getPrice()+"#"+b.getPublisher()+"#"+b.getPages()+"#"+b.getCategory()+"#"+b.getContain_pictures()+"#"+b.getContain_cd()+"#"+b.getNumber_available()+"\n");
       }
       
       this.remove(pl1);
       this.remove(pl2);
       this.add(scrollPane);
    }
    
    private void searchBookByID()
    {
       this.remove(orders);
       String sid=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον κωδικό βιβλίου: ");
       int id=Integer.parseInt(sid);
       Book b=new Book();
       b=bookdao.getBookByBookID(id);
       
       results.setText("");
       results.append("Κωδικός#Τίτλος#Συγγραφέας#Τιμή#Εκδόσεις#ΑριθμόςΣελιδών#Κατηγορία#Εικόνες#CD#Αριθμός τεμαχίων\n");
       results.append(b.getId()+"#"+b.getTitle()+"#"+b.getAuthor()+"#"+b.getPrice()+"#"+b.getPublisher()+"#"+b.getPages()+"#"+b.getCategory()+"#"+b.getContain_pictures()+"#"+b.getContain_cd()+"#"+b.getNumber_available()+"\n");
       
       this.remove(pl1);
       this.remove(pl2);
       this.add(scrollPane);
    }
    
    private void submitBook()
    {
        Book b=new Book();
        float pr=Float.parseFloat(PRICE.getText());
        int pg,num_available;
        pg=Integer.parseInt(PAGES.getText());
        num_available=Integer.parseInt(NUMBER_AVAILABLE.getText());
             
        b.setTitle(TITLE.getText());
        b.setAuthor(AUTHOR.getText());
        b.setPrice(pr);
        b.setPages(pg);
        b.setNumber_available(num_available);
        b.setPublisher(PUBLISHER.getText());
        b.setCategory(String.valueOf(CATEGORY.getSelectedValue()));
             
        if(picturesYes.isSelected())
            b.setContain_pictures("Ναι");
        else
            b.setContain_pictures("Όχι");
             
        if(cdYes.isSelected())
            b.setContain_cd("Ναι");
        else
            b.setContain_cd("Όχι");
             
        int status=BookDao.addBook(b);
        if(status!=0)
            JOptionPane.showMessageDialog(null, "Το βιβλίο σας καταχωρήθηκε επιτυχώς στο σύστημα!");
        else
            JOptionPane.showMessageDialog(null, "Υπήρξε πρόβλημα με τα στοιχεία που δώσατε");
        
        this.remove(pl1);
        this.remove(pl2);
    }
    
    private void submitOrder()
    {
        int status=bookdao.orderBook((Integer.parseInt(ID.getText())));
        if(status!=0)
        {
            try
            {
                BufferedWriter out=new BufferedWriter(new FileWriter("orders.txt",true));
                out.write(ID.getText()+"#"+NAME.getText()+"#"+SURNAME.getText()+"#"+EMAIL.getText()+"#"+PHONE.getText()+"#"+CITY.getText()+"#"+ADDRESS.getText()+"#"+PAYMENT.getSelectedValue()+"\n");
                out.close();
                JOptionPane.showMessageDialog(null,"Επιτυχής ολοκλήρωση παραγγελίας!");
            } 
            catch (IOException ex) 
            {
               JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        
        else
            JOptionPane.showMessageDialog(null,"Δεν υπάρχουν διάθεσιμα τεμάχια βιβλίου με κωδικό "+ID.getText());
        
        this.remove(pl1);
        this.remove(pl2);
    }
        
    private void updateNumberAvailable()
    {
       this.remove(pl1);
       this.remove(pl2);
       String sid=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον κωδικό βιβλίου: ");
       int id=Integer.parseInt(sid);
       String snum_available=(String)JOptionPane.showInputDialog("Πληκτρολογήστε το νέο αριθμό αντίτυπων του βιβλίου: ");
       int num_available=Integer.parseInt(snum_available);
       
       int status=BookDao.updateNumberAvailableBooks(id, num_available);
       if(status!=0)
           JOptionPane.showMessageDialog(null, "Ο αριθμός αντίτυπων του βιβλίου με κωδικό "+id+" ενημερώθηκε επιτυχώς!");
       else
           JOptionPane.showMessageDialog(null, "Δε βρέθηκε βιβλίο με κωδικό "+id+" στο σύστημα!");
    }
    
     private void updateBookPrice()
    {
       this.remove(pl1);
       this.remove(pl2);
       String sid=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τον κωδικό βιβλίου: ");
       int id=Integer.parseInt(sid);
       String sprice=(String)JOptionPane.showInputDialog("Πληκτρολογήστε τη νέα τιμή του βιβλίου: ");
       float pr=Float.parseFloat(sprice);
       
       int status=BookDao.updatePrice(id, pr);
        if(status!=0)
           JOptionPane.showMessageDialog(null, "Η νέα τιμή του βιβλίου με κωδικό "+id+" ενημερώθηκε επιτυχώς!");
       else
           JOptionPane.showMessageDialog(null, "Δε βρέθηκε βιβλίο με κωδικό "+id+" στο σύστημα!");
    }
         
    private void exit() 
    {
        int i = JOptionPane.showConfirmDialog(null,
                "Είστε Σίγουροι ότι θέλετε να κλείσετε την εφαρμογή?",
                "Έξοδος",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
