/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefinal;
import java.util.ArrayList;
import java.util.*;
    
public class DotComBust // Game class
{
    GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    int numOfGuesses= 0;
    
    
    
    public void setUpGame()
    {
        DotCom dot1 = new DotCom();
        DotCom dot2 = new DotCom();
        DotCom dot3 = new DotCom();
        dot1.setName("facebook.com");
        dot2.setName("twitter.com");
        dot3.setName("github.com");
        dotComsList.add(dot1);
        dotComsList.add(dot2);
        dotComsList.add(dot3);
        for (DotCom dotComToSet : dotComsList){
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
            
        }
        
    }
    
    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "MISS";
        for (DotCom dotComToTest : dotComsList){
          result= dotComToTest.checkYourself(userGuess);
          if (result.equals("HIT")){
              break;
          }
          if (result.equals("KILL")){
              dotComsList.remove(dotComToTest);
              
              break;
          }
          
        }
        System.out.println(result);
    }
    
    public void startPlaying()
    {
        while(! dotComsList.isEmpty()){
            System.out.println("Type your guess: ");
            String userGuess = helper.getUserInput();
            checkUserGuess(userGuess);
            
        }
        finishGame();
    }
    
    public void finishGame()
    {
        System.out.println("Game over! ");
        if (numOfGuesses<20) {
            System.out.println("You made a great job! You took " + numOfGuesses + " guesses.");
            }
        else {
            System.out.println("Not so good. You took " + numOfGuesses + " guesses.");
        }
    }
}

class DotCom {
     String name;
    ArrayList <String> locationCells;
    int numOfGuesses;
    
    public void setLocationCells(ArrayList<String> locs){
        locationCells = locs;
    }
    
    public void setName(String n){
        name=n;
    }
    
    
     public String checkYourself(String userInput)
    {
        String result = "miss"; 
        int index = locationCells.indexOf(userInput);
        if (index>=0)
        {
            locationCells.remove(index);
            if (locationCells.isEmpty())
            {
                result="kill";
                System.out.println("You just killed " +name+ " !");
            }
            else 
            {
               result="hit";
            }
            
        }
        
        return result;
    }
     
     public static void main (String args[]){
         DotComBust game = new DotComBust();
         game.setUpGame();
         game.startPlaying();
     }
}
         


class GameHelper 
{ 
   private static final String alphabet = "abcdefg";
   private int gridLenght = 7;
   private int gridSize = 49;
   private int [] grid = new int[gridSize];
   private int comCount = 0;
   
   public String getUserInput(){
       
       Scanner sc = new Scanner(System.in);
       String guess = sc.nextLine();
       return guess;
   }
   
   public ArrayList<String> placeDotCom(int comSize){
       ArrayList<String> alphaCells = new ArrayList<String>();
       String [] alphacoords = new String [comSize];
       String temp = null;
       int [] coords = new int[comSize];
       int attempts = 0;
       boolean success  =false;
       int location = 0;
       
       comCount++;
       int incr=1;
       if ((comCount % 2 )==1)
       {
           incr=gridLenght;
       }
       
       while (!success & attempts++ <200)
       {
           location= (int) (Math.random() * gridSize);
           int x = 0;
           success=true;
                while (success && x < comSize ){
                    if (grid[location] == 0){
                        coords[x++] = location ;
                        location += incr;
                        if  (location>=gridSize){
                            success=false;
                        }
                        if (x>0 && (location % gridLenght ==0)){
                            success = false;     
                            }
                    }
                        else {
                            success= false;
                        }
                    }
                    
       }
       
       
       int x =0;
       int row=0;
       int column=0;
       while(x<comSize){
           grid[coords[x]] = 1;
           row=(int) (coords[x]/gridLenght);
           column=coords[x] % gridLenght;
           temp = String.valueOf(alphabet.charAt(column));
           alphaCells.add(temp.concat(Integer.toString(row)));
           x++;
           
       }
       
        
   return alphaCells;    
   }
}



