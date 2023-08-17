/*
 * Aisha Lalli
 * CST 338 - Java Design - CSUMB
 * Final Project / Module 8
 * Created: 4/23/2021
 * Title: Modulo Calculator
 * Description:
 *    A calculator that calculates the modulo of expressions
 *    gives the multiples of the expression using a thread that increments. 
 *    The user can look through the multiples generated using a history search.
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/* This class will create a calculator that calculates modulo expressions
 * and their multiples.
 */
public class ModuloCalculator extends JFrame implements ActionListener
{
   public static final int WIDTH = 800;
   public static final int HEIGHT = 600;
   public String inputStringNumber = "";
   public String input= "";
   public JTextField dividendField; 
   public JTextField divisorField;
   public JTextField answerField;
   public boolean dividendTurn = true;
   public int dividendFinalNumber;
   public int divisorFinalNumber;
   public int answerFinalNumber;
   private boolean equalPressed;
   private boolean moduloPressedOnce = false;
   public JLabel moduloLabelThread; 
   int A = 0, B = 0, R = 0;
   int i = 1;
   public MultiplesOfModulo options;
   public boolean userButton = false;
   public static final int PAUSE = 1000;
   public int newA;
   boolean noPrint = false;
   public JLabel moduloLabelHistory;
   public static final int MAX_HISTORY_LOG = 200;
   public String[] historyLogArray = new String[MAX_HISTORY_LOG];
   public int saveIndex = 0, showIndex = 0;

   /*
    * This constructor will create the JFrame and all it's components
    * Adding action listeners to buttons. Some JLabels and JTextField 
    * were declared as global to allow changes in the other methods.
    */
   public ModuloCalculator()
   {
      // Create the frame of the calculator using GridLayout
      super("Modulo Calculator");
      setSize(WIDTH, HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(5,4));
      getContentPane().setBackground(Color.DARK_GRAY);

      // dividentField and divisorField will be used to show user input
      JLabel dividendLabel = new JLabel("Dividend",JLabel.CENTER);
      JPanel dividendPanel = new JPanel();
      dividendField = new JTextField();
      dividendField.setEditable(false);
      dividendPanel.setLayout(new GridLayout(2,1));
      dividendPanel.add(dividendLabel);
      dividendPanel.add(dividendField);
      add(dividendPanel);

      JLabel moduloLabel = new JLabel("Modulo", JLabel.CENTER);
      JLabel moduloSignLabel = new JLabel("%", JLabel.CENTER);
      JPanel  moduloPanel = new JPanel();
      moduloPanel.setLayout(new GridLayout(2,1));
      moduloPanel.add(moduloLabel);
      moduloPanel.add(moduloSignLabel);
      add(moduloPanel);

      JLabel divisorLabel = new JLabel("Divisor",JLabel.CENTER);
      JPanel divisorPanel = new JPanel();
      divisorField = new JTextField();
      divisorField.setEditable(false);
      divisorPanel.setLayout(new GridLayout(2,1));
      divisorPanel.add(divisorLabel);
      divisorPanel.add(divisorField);
      add(divisorPanel);

      //calculation will appear here
      JLabel answerLabel = new JLabel("Answer", JLabel.CENTER);
      JPanel answerPanel = new JPanel();
      answerField = new JTextField();
      answerField.setEditable(false);
      answerPanel.setLayout(new GridLayout(2,1));
      answerPanel.add(answerLabel);
      answerPanel.add(answerField);      
      add(answerPanel);

      // Create all the number and useful buttons using the JFrame's gridlayout
      JButton bOne = new JButton("1");
      bOne.addActionListener(this);
      add(bOne);

      JButton bTwo = new JButton("2");
      bTwo.addActionListener(this);
      add(bTwo);

      JButton bThree = new JButton("3");
      bThree.addActionListener(this);
      add(bThree);

      /* moduloPanelThread is used to calculate all multiples of the divisor +
       * the divident. The user can turn it on or off with a single button
       */
      JPanel moduloPanelThread = new JPanel();
      moduloPanelThread.setLayout(new GridLayout(2,1));
      moduloLabelThread = new JLabel("A mod B = R", JLabel.CENTER);
      moduloPanelThread.add(moduloLabelThread);
      JButton moduloButtonThread = new JButton("On/Off");
      moduloButtonThread.addActionListener(this);
      moduloPanelThread.add(moduloButtonThread);
      add(moduloPanelThread);

      JButton bFour = new JButton("4");
      bFour.addActionListener(this);
      add(bFour);

      JButton bFive = new JButton("5");
      bFive.addActionListener(this);
      add(bFive);

      JButton bSix = new JButton("6");
      bSix.addActionListener(this);
      add(bSix);

      /*
       * This panel will show the generated multiples of the expression first 
       * shown in the moduloPanelThread. There are two buttons, one is to start
       *  or go next and one if for going back in the search log.
       */
      JPanel moduloPanelHistory = new JPanel();
      moduloPanelHistory.setLayout(new GridLayout(2,1));
      moduloLabelHistory = new JLabel("Search History", JLabel.CENTER);
      moduloPanelHistory.add(moduloLabelHistory);
      JButton backHistoryButton = new JButton("Back");
      JButton nextHistoryButton = new JButton("Start/Next");
      backHistoryButton.addActionListener(this);
      nextHistoryButton.addActionListener(this);
      JPanel historyButtonsPanel = new JPanel();
      historyButtonsPanel.setLayout(new FlowLayout());
      historyButtonsPanel.add(backHistoryButton);
      historyButtonsPanel.add(nextHistoryButton);
      moduloPanelHistory.add(historyButtonsPanel);
      add(moduloPanelHistory);

      JButton bSeven = new JButton("7");
      bSeven.addActionListener(this);
      add(bSeven);

      JButton bEight = new JButton("8");
      bEight.addActionListener(this);
      add(bEight);

      JButton bNine = new JButton("9");
      bNine.addActionListener(this);
      add(bNine);

      /* This panel could have been left empty, but I chose it to include 
       * my name and other information.
       */
      JPanel emptyPanel3 = new JPanel();
      JTextArea infoTextArea = new JTextArea(4, 15);
      infoTextArea.setText("Made by Aisha Lalli \nCSUMB CSIT Online \n"
            + "Java Design \nCST338");
      infoTextArea.setEditable(false);
      emptyPanel3.add(infoTextArea);
      add(emptyPanel3);

      JButton bClear = new JButton("Clear");
      bClear.addActionListener(this);
      add(bClear);

      JButton bZero = new JButton("0");
      bZero.addActionListener(this);
      add(bZero);

      JButton bModulo = new JButton("%");
      bModulo.addActionListener(this);
      add(bModulo);

      JButton bEqual = new JButton("=");
      bEqual.addActionListener(this);
      add(bEqual);
   }

   /*
    * This method will be called to save the multiples of the expressions 
    * generated in an array, which can be searched in showHistory()
    * It takes in the expression generated and inserts it into the array 
    * which is controlled by a counter called saveIndex. saveIndex is global and
    * is reset to 0 when the user enters "clear"
    */
   public void saveHistory(String dataText)
   {
      if(saveIndex < MAX_HISTORY_LOG)
      {
         historyLogArray[saveIndex] = dataText;
         saveIndex++;
      }
   }

   /*
    * This method is used by both the back or the start/next options. It is 
    * called after the global counter showIndex is either increased or 
    * decreased by one. The showIndex counter resets in "clear" . Only
    * the label is changed to portray the relevant text
    */
   public void showHistory() 
   {
      moduloLabelHistory.setText(historyLogArray[showIndex]);
   }

   /*
    * This method will clear either the dividentField or the divisorField
    * based on which one is the argument. In general, numbers as strings are
    * saved from the user input into input which is appended to inputStringNumber
    * that gets inserted into the relevant field. When reseting these field both
    * these string variables are set to "". This method returns true when used.
    */
   public boolean clearField(JTextField field) 
   {
      inputStringNumber = "";
      input = "";
      field.setText(inputStringNumber);
      return true;
   }

   /*
    * This method receives the relevant field and shows the string of number in
    * the passes field.
    */
   public void inputField(JTextField field)
   {
      inputStringNumber = inputStringNumber + input;
      field.setText(inputStringNumber); 
   }

   /*
    * This method will generate a new A the divident based on the counter i
    * The formula is (A+i.B) mod B for any int i.
    * the displayed expression will also be saved in an array to be used to 
    * retrieve the history.
    * Use  method doNothing() which will pause as i increments 
    * this method will be placed in a while loop that loops so lonf that the 
    * button is on. 
    */
   public synchronized void increment()
   {
      newA = A + (i*B);
      moduloLabelThread.setText(newA +" mod " + B + " = " + R);
      String text = (newA +" mod " + B + " = " + R);
      saveHistory(text);
      i++;
      doNothing(PAUSE);
   }

   /*
    * This inner class extends thread and the method run() is overridden to set the
    * variables of the expression based on the numbers in the fields divident, 
    * divisor and answer.
    * a while loop will loop so long the button of this thread is on, increment
    * will do the calculations and display the thread.
    * join() to avoid thread confusion. join's calling object is this class type
    * (created in actionPerformed())
    * The variables dividentFinalNumber and divisorFinalNumber were transformed
    * from string to int using the wrapper class Integer in ActionPerformed().
    */
   private class MultiplesOfModulo extends Thread
   {
      public void run()
      {
         A = dividendFinalNumber; 
         B = divisorFinalNumber ;
         R = answerFinalNumber;

         while(userButton)
         {
            increment();
         }
         try
         {
            options.join();
         } catch (InterruptedException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      } 
   }

   /*
    * This method will pause for a second = 1000 milliseconds
    * and throws an exception that exits the program.
    */
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      {
         System.out.println("Unexpected Interupt");
         System.exit(0);
      }
   }
   /*
    * This method will be called after the user button command is chosen. There 
    * are two major conditions. This will change the divident and divisor fields.
    * Condition 1:  "=" was pressed. Condition 2: "=" was not pressed and the 
    * buttons for the thread and history were not pressed ie !noPrint
    * If the second condition passes, either the filling will be in the divident 
    * or the divisor field.
    *  
    */
   public void fillIn()
   {
      // output into fields divident and divisor
      if(equalPressed)
      {
         // the fields are not empty all the way to the answer field
         if(!(dividendField.getText().equals("")) &&
               !(divisorField.getText().equals(""))) 
         {
            inputStringNumber = ""; // reset the variable that hold input
            input = "";
            dividendTurn = true; // reset to the first/initial field
         }
         // "=" was pressed accidently while still on the dividend field
         else if(dividendTurn)
            equalPressed = false; // remove the flag that equal was pressed
      }
      // "=" was not pressed, but the dividend and divisor field should be filled
      else if (!noPrint) // if true means that the thread and history buttons were used
      {
         if(dividendTurn)
         {
            inputField(dividendField);
         }
         else if(!dividendTurn)//divisor turn
         {
            if(moduloPressedOnce) //1st time pressed %
            {
               inputField(divisorField);
            }
            else
               // when the button pressed was %, there is no input in fields
               // this flag will constrict what field clear will reset.
               //because if % is not pressed the clear is for dividend. If % is 
               //pressed then it means divisor could be cleared if answer is
               //still empty.
               moduloPressedOnce = true; 
         }
      }
   }
   
   // this method is called when user presses "=" correctly to get answer
   public void string2Int()
   {
      dividendFinalNumber = Integer.parseInt(dividendField.getText());
      divisorFinalNumber = Integer.parseInt(divisorField.getText());
      answerFinalNumber = dividendFinalNumber % divisorFinalNumber;
      answerField.setText(Integer.toString(answerFinalNumber));
   }

   /* This method contains the majority of the logic in the ModuloCalculator 
    *class
    */
   public void actionPerformed(ActionEvent e)
   {
      noPrint = false; // set to false so that fillIn() would fill fields needed
      String actionCommand = e.getActionCommand();

      if(actionCommand.equals("On/Off")) // For the expression generator
      {
         noPrint = true; // this will not print in fields
         // cannot run if there is no answer in answer field
         if(!(answerField.getText().equals(""))) 
         {
            if(!userButton) // was off and turns on
            {
               userButton = true; // flag showing button is on
               options = new MultiplesOfModulo(); //object of inner class
               options.start(); // run increment and pauses with join()
            }
            else // user want to turn off
            {
               userButton = false;
            }
         } 
      }
      // button for going back in history, cannot execute if array is empty
      // can only execute if showIndex is not 0, since index will decrement.
      else if(actionCommand.equals("Back"))
      {
         noPrint = true;
         if(saveIndex > 0)
         {
            if(showIndex > 0)
            {
               showIndex--;
               showHistory(); // run method to show in label the array content
            }
         } 
      }
      // used to go up in the search of array, will not display anything in fields
      // array should contain an element and the showIndex cannot be equal to 
      //the array counter used to fill array bc ++ will make it pass the num
      // of elements available
      else if(actionCommand.equals("Start/Next"))
      {
         noPrint = true;
         if(saveIndex >0 && showIndex < saveIndex)
         {
            showIndex++;
            showHistory();
         }
      }
      // buttons will fill input with string of number, will be appended
      //to larger string variable for output use and calculations
      else if(actionCommand.equals("1"))
         input = "1";
      else if(actionCommand.equals("2"))
         input = "2";
      else if(actionCommand.equals("3"))
         input = "3";
      else if(actionCommand.equals("4"))
         input = "4";
      else if(actionCommand.equals("5"))
         input = "5";
      else if(actionCommand.equals("6"))
         input = "6";
      else if(actionCommand.equals("7"))
         input = "7";
      else if(actionCommand.equals("8"))
         input = "8";
      else if(actionCommand.equals("9"))
         input = "9";
      // 0 can be a dividend and the second number in a divisor
      // 0 cannot be a divisor 
      else if(actionCommand.equals("0"))
      {
         try
         {
            if(dividendTurn) // everything good in dividend, 0 inserted
            {
               input = "0"; 
            }
            // 0 is second number in the divisor, we can inserted only then
            else if((!(divisorField.getText().equals("")))) 
            {
               input ="0";
            }
            else // 0 is in the divisor as 1st number
            {
               noPrint = true; //divisorField will show error by skipping fillIn()
               throw new ArithmeticException();
            }
         }
         catch (ArithmeticException e2)
         {
            divisorField.setText("Error: Division By Zero");// field displays

         }
      }
      // clear can be for all fields or only dividend or divisor.
      else if(actionCommand.equals("Clear")) 
      {
         // A clear for all fields and buttons like thread expression and history
         if(equalPressed) //reset
         {
            clearField(answerField);
            clearField(divisorField);
            clearField(dividendField);
            dividendTurn = true; // 1st field
            equalPressed = false; 
            moduloPressedOnce = false;
            i=1; // resets for "On/Off" thread
            userButton = false; // turn the button off 
            moduloLabelThread.setText("A mod B = R");
            saveIndex = 0;
            showIndex = 0;
            historyLogArray = new String[MAX_HISTORY_LOG];
            moduloLabelHistory.setText("Search History");

         }
         // clear pressed in divisor's turn 
         else if(!dividendTurn) // divisor turn, clear divisor field
         {
            if(divisorField.getText().equals("")) // divisor is empty already
            {
               clearField(dividendField); // so empty the dividend
               dividendTurn = true; // dividend empty so turn is with it
            }
            else
            {
               // divisor not empty so clear divisor and remember modulo pressed
               //already keeping divisor with turn
               clearField(divisorField);
               moduloPressedOnce = true; // if % pressed but then clear
            }
         }
         //if dividend turn, clear the dividend
         else if(dividendTurn)
            clearField(dividendField);

      }
      // can be pressed after the dividend turn to move to divisor, or accidental
      else if(actionCommand.equals("%"))
      {
         if(dividendTurn)
         {
            if(!(dividendField.getText().equals("")))
            {
               // correct use of % by user
               dividendTurn = false;
               moduloPressedOnce = true; // modulu pressed, so turn goes to divisor
               inputStringNumber = ""; // clear to put new input into divisor field
               input = "";
            }
         }
         // divisor turn  
         else if(!dividendTurn) // divisor turn
         {
            moduloPressedOnce = false; // need this to avoid inputing into field
         }
         else if(equalPressed)
         {
            moduloPressedOnce = false; // modulo won't change anything after answer
         }            
      }  
      else //"="
      {
         // case 1: need to calculate the answer 
         if(!(divisorField.getText().equals("")) &&
               !(divisorField.getText().equals("Error: Division By Zero"))) 
         {
            string2Int();
            equalPressed = true;
         }
         // case 2: pressed accidentally 
         else if(dividendTurn)
         {
            equalPressed = true;
         }
      }
      // fill in the fields 
      fillIn();
   }

   public static void main (String[] args)
   {
      // create an object of class ModuloCalculator
      ModuloCalculator myCalculator = new ModuloCalculator();
      myCalculator.setVisible(true);

   }
}
