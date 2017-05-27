/*
Averages Class
Alex Stevens 
5/21/2017
Average class is used to handle the stacks and keep track
of highest and lowest averages. 
Csis 123B-3183
0495503
 */
package finaljava2;

/**
 *
 * @author Alex
 */
 public class Averages
{
     static double lowest;
     static double highest;
    static Stack<String> highStack;
    static Stack<String> lowStack;
     
     
     
    private Averages(){
        
    }
    public static void firstLine(Player f)
    {
        
        
      highStack = new Stack<String>();
      lowStack =new Stack<String>();
      highStack.push(f.getName());
      lowStack.push(f.getName());
      lowest = f.getAverage();
      highest = f.getAverage();
    }
    
    public static void testHigh(Player h)
    {
       if(h.getAverage() > highest)
       {
           highStack = new Stack<String>();
           highest = h.getAverage();
           highStack.push(h.getName());
       }
       else if(h.getAverage()== highest)
       {
           
           highStack.push(h.getName());
           
       }
       else
       {
           return;
       }
        
    }
     
        
    public static void testLow(Player l)
    {
     
        if(l.getAverage() < lowest)
        {
            lowStack = new Stack<String>();
            lowest = l.getAverage();
            lowStack.push(l.getName());
        }
        else if(l.getAverage() == lowest)
        {
            lowStack.push(l.getName());
        }
        else
        {
            return;
        }
        
        
    }
    

    public static double getHigh()
    {
        return highest;
    }
    public static double getLow()
    {
        return lowest;
    }
    

 
    
    
}
