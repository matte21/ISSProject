/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.sender_0;
import java.io.IOException;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.QActorContext;

public class Sender_0 extends AbstractSender_0 { 
	public Sender_0(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
	}
	
	public void sendFromNodejs() {
        try {
            Runtime.getRuntime().exec("node ./srcJS/sendMessage.js");
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	

}
