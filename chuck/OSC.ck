/*
* This class open an OSC Receiver. 
* Allow communicate with Java Musec application via OSC.
*/
public class OSC {
    
    //variaveis
    //static int idMachine;
    int port;
    OscIn oin;
    OscMsg msg;
    
    
    
    fun void getConnection(int port, string address){
         // use port 
        port => oin.port;
        
        // create an address in the receiver
        oin.addAddress(address);
        
    }  
}