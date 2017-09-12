// buffer for read an file
SndBuf buf => Gain g => dac;
// read wav
me.dir(-1) + "sounds/loops/risk_3_pop_60bpm.wav" => buf.read;

// set the gain (volume)
1 => g.gain;

// play all music right now
buf.length() => now;

VM_Controller  vm_Controller;
vm_Controller.set_existVMPlaying(0); // avisa o controlador que acabou a música nesta vm e não existe nenhuma vm a tocar
//vm_Controller.removeVM();
//demora 13s

//if not exist new events, play risk 0 VM in loop, until have new events 
vm_Controller.set_previous_risk(0);
vm_Controller.set_existVMPlaying(1); //exist one VM playing 
vm_Controller.addVM(me.dir()+ "/risk_0.ck") => int vmLoopID;
vm_Controller.setVM_Playing(vmLoopID); //tells to controller the VM ID playing right now
