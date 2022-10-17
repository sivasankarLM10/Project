package factorial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class factorial extends JPanel
{
         private int factorial; 
         private JLabel inputLabel,resultLabel;
         private JTextField factorialText;
         private JButton factButton;
          //Constructor: sets up main GUI components
 
          public void FactorialPanel(int factorial)
          {
               this.factorial=factorial;
               inputLabel= new JLabel ("Please enter an integer:");
               factButton = new JButton("Compute");
               TempListener listener=new TempListener();
               factButton.addActionListener(listener);
               factorialText = new JTextField();
               factorialText.addActionListener (new TempListener());
               add(inputLabel);
               add(factorialText);
               add(factButton);
               add(resultLabel);
          }
            private int computeFactorial ()
         {
           factorial=Integer.parseInt(factorialText.getText());
       int f =1;
       for(int i=factorial;i>=1;i--)
       {       
           f = f*i;
       }   
        resultLabel.setText(Integer.toString(f));
        return f;
                      }        
           //represents a listener for the button
           private class TempListener implements ActionListener
           {
            //performs factorial operation when the 'Compute' button is  pressed
            public void actionPerformed(ActionEvent event)
            {
                computeFactorial();
                 }  
            }
      }
