/* Generated by AN DISI Unibo */ 
package it.unibo.job_launcher;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.akka.QActorMsgQueue;

public class MsgHandle_Job_launcher extends QActorMsgQueue{
	public MsgHandle_Job_launcher(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  {
		super(actorId, myCtx, outEnvView);
	}
}
