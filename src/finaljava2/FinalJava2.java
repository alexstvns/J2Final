/*
Alex Stevens
5/21/2017
Final Project
Csis 123B-3183
0495503
 */
package finaljava2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Alex
 */
public class FinalJava2 extends Application 
{
    

    private TextArea avgHigh = new TextArea("");
    private TextArea avgLow = new TextArea("");
    
    private Stage stage;
    private String fname;
    
    private Label status = new Label("");
    

    private int lineCount=1;  // variable used to track # lines read from file.
    

    
    
    
    @Override
    public void start(Stage primaryStage)
    {
        
        avgHigh.setEditable(false);
        //avgHigh.setMouseTransparent(true);
        avgHigh.setFocusTraversable(false);
        
        avgLow.setEditable(false);
        //avgLow.setMouseTransparent(true);
        avgLow.setFocusTraversable(false);

        fname = "Untitled";
        stage = primaryStage;
        status.setText("Please Select a File to Find Averages");
        
        
       
           GridPane menu = new GridPane();
       
        menu.add(menuSetup(), 0, 0);
        menu.add(new Label(), 0, 1);
        
        
        GridPane txtCent = new GridPane();
        BorderPane root = new BorderPane();
        
       Label High = new Label("     Highest Average ");
       
       Label Low = new Label("     Lowest Average ");
       
       
       
        txtCent.add(High, 0, 0);
        txtCent.add(Low,1,0);
        txtCent.add(avgHigh,0,1);
        txtCent.add(avgLow,1,1);
        
        
        root.setCenter(txtCent);
        root.setTop(menu);
        root.setBottom(status);
        Scene scene = new Scene(root, 400,300);
        
        primaryStage.setTitle("JavaII Final");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
  
        
    }
    
    
    
    
    
    private MenuBar menuSetup()
    {
       Menu fileMenu = new Menu("File"); 
       Menu openFile = new Menu("Open");
       Menu exitMenu = new Menu("Exit");
       Menu newItem = new Menu("New");
        
        fileMenu.getItems().addAll(openFile, newItem, new SeparatorMenuItem(), exitMenu);
        
       openFile.setOnAction(menuHandler);
       newItem.setOnAction(menuHandler); 
       
        exitMenu.setOnAction(actionEvent->Platform.exit());
        
        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        
        return bar;
    }
              EventHandler<ActionEvent> menuHandler = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) 
        {
        
        MenuItem mi =(MenuItem) event.getSource();
        
        String command = mi.getText();
        
        if(command.equals("Open"))
        {
          openFile();
        }
        else if(command.equals("New"))
        {
            newPage();
        }
    
        }
      };
              
   private void openFile()
     {
         
        status.setText("File Loading");  
        FileChooser fc = new FileChooser(); 

         FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"); 
         fc.getExtensionFilters().add(filter);
         
         File file = fc.showOpenDialog(stage);
         
         
         if(file==null)
         {
             
           
           status.setText("No File Selected");
           return;
         }
         else
         {
           

          fname = file.getPath();
          stage.setTitle(fname);
           readFile();
             
         }
     }
              
      private void readFile()
      {
          FileReader fis=null;
          BufferedReader bis=null;
          
          
          
          try
          {
              fis = new FileReader(fname);
              bis = new BufferedReader(fis);
              String s;
              
              Pattern p = Pattern.compile("(.*?)(.[0-9]{1,})");
       
              /*
              Wrote a regex to pull out the first word  into capture group 1
              and any 1 + numbers follow a decimal point . into capture group 2
              
              I made the decimal capture be able to capture decimal numbers 
              longer than just 3 places, just in case there are more accurate decimal 
              numbers being used. could have also done (.[0-9]{3}) since the
              data in out avgs.txt are all 3 spaces in legnth
              */
              
              
              while((s=bis.readLine())!=null)
              {
               Matcher m = p.matcher(s);
               
               while(m.find())
               {
                
                Double av = Double.parseDouble(m.group(2));
                 Player player = new Player(m.group(1),av); 
                 
                 
                 
                 
                 if(lineCount==1)
                 {
                    Averages.firstLine(player);
                    
                     // used for tracing file read --> player object --> Averages
                    System.out.println("===HIGH===="+lineCount);
                     
                     Averages.highStack.tester();
                      System.out.println("------------------");
                          System.out.println("===Low=="+lineCount);
                     Averages.lowStack.tester();
                     System.out.println("------------------");
                      // code above used for watching console and tracing movement
                     lineCount++;
                 }
                 else
                 {
                     
                     Averages.testHigh(player);
                     Averages.testLow(player);
                    
                     
                    // used for tracing file read --> player object --> Averages
                    System.out.println("===HIGH===="+lineCount);
                     
                     Averages.highStack.tester();
                      System.out.println("------------------");
              
                    System.out.println("===Low=="+lineCount);
                     Averages.lowStack.tester();
                     System.out.println("------------------");
                     
                     // code above used for watching console and tracing movement
                    
                     lineCount++;
                     
                     
                 }    
                 }

               }
                    
              }
             
            
              
            
           
          catch (Exception e) 
          {
              status.setText("Error: "+ e.getMessage());
              
          }
          finally
          {
              try
              {
                  fis.close();
                  bis.close();
              }
              catch(IOException ex)
              {
               status.setText("IO Error: "+ex.getMessage());
              }
              
              
          }
          status.setText("File Read Successfully");
          
          
          
          this.printStacks();

         
      }
      
      private void printStacks()
      {
          int highCount= Averages.highStack.getSize();
          int lowCount = Averages.lowStack.getSize();
          
          
          Stack hStack = Averages.highStack;
          Stack lStack = Averages.lowStack;
          
          avgHigh.setText("Highest Batting Average: "+Averages.getHigh()+"\n");
          avgHigh.appendText("===================="+"\n");
          avgHigh.appendText("Highest Average Players: "+"\n");
          avgHigh.appendText("===================="+"\n");
          
          for(int i=0;i<highCount;i++)
          {
              
             avgHigh.appendText(hStack.print(i)+"\n");
              
          }
          avgLow.setText("Lowest Batting Average: "+ Averages.getLow()+"\n");
          avgLow.appendText("===================="+"\n");
          avgLow.appendText("Lowest Average Players: "+"\n");
          avgLow.appendText("===================="+"\n");
           for(int j=0;j<lowCount;j++)
          {
              
              avgLow.appendText(lStack.print(j)+"\n");
              
          }
          
      }
              
              
              
         private void newPage()
     {
         avgHigh.setText("High");
         avgLow.setText("Low");
         fname ="";
         stage.setTitle("JavaII Final");
         status.setText("Please Select a File to Find Averages");
         lineCount =1;
     }
    /**

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    
    {
        launch(args);
    }
    
}
