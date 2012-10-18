package com.mytest;
/*
 * @Author- Rajiv Srivastava, 2012
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
/*
 *  -----------------------Singleton Design Patterns --------------------------
 *  Implementing Cloneable,Serializable interfaces
 */
    class MySingleton implements Cloneable,Serializable {
   
    /*
     * Serialization version control: if you make any changes to the this class file after de-serialization,
     * just ensure that same version ID is specified and all will be well. Default serial version id=1L
     * It can be generated using Eclipse or command >serialver MyClassName
     */
    private static final long serialVersionUID = 1L;
    private static MySingleton INSTANCE;
    private String name;
   
    /*
     * Empty private constructor to protect this class from instantiating new object
     */
    private MySingleton(){
     //Empty constructor
    }

    public static MySingleton getSingleton(){
        if(null==INSTANCE){
            synchronized(MySingleton.class){//Class level lock
              if(null==INSTANCE){ //Double-checked locking
                  // Lazy instantiation
                INSTANCE=new MySingleton(); //Create only one instance
              }
            }
        }
        return INSTANCE;
    }
   
    /*
     * To prevent shallow cloning
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Can not be cloned !!! Ooops.. New object can not be created ");
    }
   
    /*
     * To prevent multiple instances during de-serialization
     */
    private Object readResolve() throws ObjectStreamException {
          /*
           * instead of the object we're on, return the class variable INSTANCE
           */
          return INSTANCE;
         }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
   
 public class MainSingleton{  
     @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException{
         FileOutputStream fout=null;
         ObjectOutputStream outobj = null;
        
         FileInputStream fin=null;
         ObjectInputStream inobj = null;
        
         MySingleton myclone;
         MySingleton instance=MySingleton.getSingleton(); //For serialization test

         MySingleton instance1=MySingleton.getSingleton();
         System.out.println("*************  Multiple Instance Test***********");
         instance1.setName("Rajiv Srivastava");
         System.out.println("Employee Name: "+instance1.getName());
         System.out.println("instance1: "+instance1);
         
         MySingleton instance2=MySingleton.getSingleton();
         System.out.println("instance2: "+instance2);
         System.out.println("Are both instances referring to same object ? Compare [instance1==instanc2]: "+(instance1==instance2));
         
         System.out.println("*************  Cloning Test***********");
            try {
                myclone = (MySingleton) instance2.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println(e.getMessage());
            }
           
         System.out.println("************* Serialization Test***********");
         /*
          * @Serialization, File- myfile.txt
          */
        
        try {
             fout= new FileOutputStream("myfile.txt");
             outobj=new ObjectOutputStream(fout);
             outobj.writeObject(instance);
             
             outobj.flush();
             fout.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         finally{
            outobj.close();
            fout.close();
         }
        
         /*
          * De-serialization
          */
        try{
            fin=new FileInputStream("myfile.txt");
            inobj= new ObjectInputStream(fin);
           
            MySingleton sinstance=(MySingleton) inobj.readObject();
            System.out.println("Deseraized... Name:"+sinstance.getName());
            System.out.println(sinstance.toString());
            System.out.println("Is this deserialized object 'sinstance' referring to same object ? Compare [sinstance==instance]: "+(sinstance==instance));
           
        }
        catch(FileNotFoundException fnfe){
            fnfe.getMessage();
        }
        catch(IOException ioe){
            ioe.getMessage();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            inobj.close();
            fin.close();
            //fin.reset();
        }
    }
 }
 
/*
 * Singleton in Multi-threading environment without using synchronized mechanism.
 * Trick: Please create static inner class which will private member of singleton class and will be thread-safe.
 
  private static class MyInnerClass{
  private static final MySingelton myobj= new MySingelton();       
}*/

