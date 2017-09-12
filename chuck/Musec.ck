/*
* Musec Class
*/

OSC osc; // ou new OSC @=> OSC @ osc;
Message message;
VM_Controller  vm_Controller;


vm_Controller.set_existVMPlaying(0);
Event removeVM_evnt;
0 => int vmLoopID; 

//conection in port 57111 - get Ossim Server Events 
osc.getConnection(57111, "/chuck/events, s i i i i i s"); // Timestamp | Priority | Reliability | Asset_SRC | Asset_DST | Risk | Musec State

spork ~ removeMachine(removeVM_evnt);// wait for an event to remove an specific VM

     while( 1::ms => now ){ //1::ms => now 
            
        // wait for event to arrive
        osc.oin => now;

        // grab the next message from the queue. 
        while(osc.oin.recv(osc.msg) !=0){ 
            osc.msg.getString(0) => message.timestamp;
            osc.msg.getInt(1) => message.priority;
            osc.msg.getInt(2) => message.reliability;
            osc.msg.getInt(3) => message.asset_src;
            osc.msg.getInt(4) => message.asset_dst;
            osc.msg.getInt(5) => message.risk;
            osc.msg.getString(6) => message.state; 
           
            message.print();
            
            if(message.state =="start"){
               change_Music_Loop_VM(0);
               /* <<< "chegou" >>>;
                //toca em loop a vm 0.0 até haver eventos
                vm_Controller.set_previous_risk(0);
                vm_Controller.set_existVMPlaying(1); // sinaliza que existe uma vm a tocar música 
                vm_Controller.addVM(me.dir()+ "/risk/risk_0.0.ck") => vmLoopID;
                vm_Controller.setVM_Playing(vmLoopID); //avisa o controlador da vm atual que está a tocar música
                <<< "saiu" >>>;*/
            }else if(message.state == "play"){
                  //se riso atual é maior que o anterior ou se nao existir eventos com risco superiores ao risco anterior e nenhuma vm estiver a tocar música
                  if((message.risk > vm_Controller.get_previous_risk()) || (message.risk <= vm_Controller.get_previous_risk() && vm_Controller.getExistVM_Playing() == 0)){
                      //<<< "o risco atual é maior que o anterior">>>;
                    if(vm_Controller.getExistVM_Playing() == 1){
                        //<<< "existe uma vm a tocar musica" >>>;
                     removeVM_evnt.signal(); // sinal para remover a vm que está a tocar pois neste momento existe um risco maior que o atual 
                        1:: second => now; // linha importante para remover a vm certa (tambem dá com .5)
                    }  
                       change_Music_Loop_VM(message.risk);  
                  }
            }else if(message.state == "stop"){
             vm_Controller.removeAllMusicVM();
            }
        }
     }
     



fun void change_Music_Loop_VM(int risk){
   //risk => previous_risk; //atualiza o risco anterior
    vm_Controller.set_previous_risk(risk);
   vm_Controller.set_existVMPlaying(1); // sinaliza que existe uma vm a tocar música
   //<<< "a adicionar machine" >>>;  
   if(risk == 0){
       vm_Controller.addVM(me.dir()+ "/risk/risk_0.ck") => vmLoopID;
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_0.ck") => id; //executa a vm e guarda o ID
   }else if(risk == 1){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_1.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_1.ck") => vmLoopID;
   }else if(risk == 2){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_2.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_2.ck") => vmLoopID;
   }else if(risk == 3){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_3.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_3.ck") => vmLoopID;
   }else if(risk == 4){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_4.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_4.ck") => vmLoopID;
   }else if(risk == 5){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_5.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_5.ck") => vmLoopID;
   }else if(risk == 6){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_6.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_6.ck") => vmLoopID;
   }else if(risk == 7){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_7.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_7.ck") => vmLoopID;
   }else if(risk == 8){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_8.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_8.ck") => vmLoopID;
   }else if(risk == 9){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_9.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_9.ck") => vmLoopID;
   }else if(risk == 10){
       //Machine.add(me.dir()+ "/" + message.musicStyle + "/risk/risk_10.ck") => id; //executa a vm e guarda o ID
       vm_Controller.addVM(me.dir()+ "/risk/risk_10.ck") => vmLoopID;
   }
   
   vm_Controller.setVM_Playing(vmLoopID); //avisa o controlador da vm atual que está a tocar música
}

fun void removeMachine(Event removeEvent){
    while( true )
    {
        // wait for a "remove autorization"
        removeEvent => now;
        //<<< "a remover machine" >>>;   
        vm_Controller.removeVM();
        vm_Controller.set_existVMPlaying(0); // sinaliza que não existe nenhuma vm a tocar música
    }
    
}

