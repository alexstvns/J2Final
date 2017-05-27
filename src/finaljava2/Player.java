/*
Player Class
Alex Stevens 
5/21/2017
Player Class - used to pair player name and average together
Csis 123B-3183
0495503
 */
package finaljava2;

/**
 *
 * @author Alex
 */
public class Player 
{
    
    private String pName;
    private double pAvg;
  
    
   public Player(String player,double avg)
    {
    
      setName(player);
      setAverage(avg);
    
        
    }
   public void setName(String player)
   {
    this.pName=player;       
   }
   public void setAverage(double avg)
   {
       this.pAvg=avg;
       
   }
    
    public String getName()
    {
        return this.pName;
    }
    public double getAverage()
    {
        return this.pAvg;
    }
    
    public String Stringer()
    {
        String s = (this.pName+" "+this.pAvg);
        return s;
    }
}
