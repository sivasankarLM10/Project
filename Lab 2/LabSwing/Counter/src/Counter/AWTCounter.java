package Counter;
import java.awt.*;         // Using AWT's containers and components
import java.awt.event.*;   // Using AWT's event classes and listener interfaces
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class AWTCounter extends Frame implements ActionListener {
   private Label lblCount;     // Declare component Label
   private TextField tfCount;  // Declare component TextField
   private Button btnCount;    // Declare component Button
   private int count = 88;      // counter's value
 
   // Constructor to setup UI components and event handlers
   public AWTCounter () {
      setLayout(new FlowLayout());
         // "super" Frame sets layout to FlowLayout, which arranges
         //  Components from left-to-right, then top-to-bottom.
 
      lblCount = new Label("Counter"); // Construct component Label
      add(lblCount);                   // "super" Frame adds Label
 
      tfCount = new TextField(count + "", 10); // Construct component TextField
      tfCount.setEditable(false);       // read-only
      add(tfCount);                     // "super" Frame adds TextField
 
      btnCount = new Button("Count Down");   // Construct component Button
      add(btnCount);                    // "super" Frame adds Button
      btnCount.addActionListener(this);
         // btnCount is the source object that fires ActionEvent when clicked.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Clicking btnCount invokes actionPerformed().
 
      setSize(250, 100);       // "super" Frame sets initial size
      setTitle("AWT Counter"); // "super" Frame sets title
      setVisible(true);        // show "super" Frame
   }
 
   // ActionEvent handler - Called back when the button is clicked.
   @Override
   public void actionPerformed(ActionEvent evt) {
      --count;                     // Incrase the counter value
      tfCount.setText(count + ""); // Display on the TextField
                                   // setText() takes a String
   }
 
   // The entry main() method
   public static void main(String[] args) {
      // Invoke the constructor by allocating an anonymous instance
      new AWTCounter();
   }
}