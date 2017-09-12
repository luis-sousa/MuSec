/*
* This class is an Controller that control CHUCK Machines
*/
public class VM_Controller  
{
    static int exist_VM_Playing;// identify if exists one VM playing music (1) yes or no (0) 
    static int vm_Playing_ID;// save current VM id playing 
    int vmMusicPlaying_Array[0];// save all current VM ID´s playing
    -1 => static int previous_risk; //save previous risk
    
    
    /*
    * Changes risk
    */
    private void set_previous_risk(int value){
        value => this.previous_risk;
    }
    
    /*
    * Gets previous risk
    */
    private int get_previous_risk(){
        return  this.previous_risk;
    }
    
    /*
    * Change state and identify if exist any VM playing 
    */
    private void set_existVMPlaying(int value){
        value => this.exist_VM_Playing;
    }
    
    /*
    * Gets if exist any VM playing
    */
    private int getExistVM_Playing(){
        return  this.exist_VM_Playing;
    }
    
    /*
    * Changes the VM playing ID 
    */
    private void setVM_Playing(int id){
        id => this.vm_Playing_ID;
    }
    
    /*
    * Gets VM playing ID
    */
    private int getVM_Playing(){
        return vm_Playing_ID;
    }
    
    /*
    * Remove an specific VM
    */
    private void removeVM(int id){
      Machine.remove(id);  
    }
    
    /*
    * Remove VM playing 
    */
    private void removeVM(){
       Machine.remove(this.vm_Playing_ID); 
        vmMusicPlaying_Array.popBack();
        //vmMusicPlaying_Array.clear();
    }
    
    /*
    * Add a specific VM 
    */
    private int addVM(string path){
        Machine.add(path) => int id;
        vmMusicPlaying_Array << id;
        return id;
    }
    
    /*
    * Remove all Machines
    */
    private void removeAllMusicVM(){
        0 => int i;
        for(i;i < vmMusicPlaying_Array.cap();i++){
            Machine.remove(vmMusicPlaying_Array[i]);
        } 
        vmMusicPlaying_Array.clear();
        this.set_existVMPlaying(0);
        Machine.remove(this.vm_Playing_ID);//preventing unstop risk 0 VM
    }
}