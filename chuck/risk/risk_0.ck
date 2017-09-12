// buffer for read an file
SndBuf buf => Gain g => dac;
// read wav
me.dir(-1) + "sounds/loops/risk_0_relax_music.wav" => buf.read;

// set the gain (volume)
1 => g.gain;

while(true)
{
//position where it starts
0 => buf.pos;
// play all music right now
buf.length() => now;
}    

