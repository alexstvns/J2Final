/*
Alex Stevens
5/21/2017
Final Project-Stack Class
Csis 123B-3183
0495503
 */
package finaljava2;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Stack<T> 
{
    private ArrayList<T> al = new ArrayList<T>();
   
    
    
    public Stack()
    {
        
    }
    
    
  public void push(T elem)
    {
        
       this.al.add(0,elem);
    }
    
    
   public  T pop()
    {
        return this.getElement(true);
 
    }
    
   public  T peek()
    {

        return this.getElement(false);
    }
    
    
    private T getElement(boolean erase)
    {
        T data = null;
        if(!this.al.isEmpty()){
            data=this.al.get(0);
        
            if(erase ==true){
                this.al.remove(0);
            }
        }
        
        return data;
    }
    
    
    public int getSize()
    {
        return this.al.size();
    }
    
    // to print out the stack 
    public String print(int index)
    {
      if(index > al.size()){
          System.out.println("Index our of range");
      }
      
      return ((String)al.get(index));
        
        
    }
    
    
    //used this method to the stack to the console for tracing the movement of the data
    
    public void tester()
    {
        for(int i=0;i<al.size();i++)
        {
            System.out.println(al.get(i));
        }
    }
            
}
