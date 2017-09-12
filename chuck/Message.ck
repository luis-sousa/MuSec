/*
* This class represent an Message Object (Ossim Server Events)
*/
public class Message {
    
    string timestamp, state;
    static int priority, reliability, asset_src, asset_dst, risk; 
 
    /*
    * Print message
    */
    fun void print(){
     <<< priority, reliability, asset_src, asset_dst, risk, timestamp, state>>>;
    }   
}
