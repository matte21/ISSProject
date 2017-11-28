%====================================================================================
% Context ctx  SYSTEM-configuration: file it.unibo.ctx.demo3.pl 
%====================================================================================
context(ctx, "localhost",  "TCP", "8000" ).  		 
%%% -------------------------------------------
qactor( receiver , ctx, "it.unibo.receiver.MsgHandle_Receiver"   ). %%store msgs 
qactor( receiver_ctrl , ctx, "it.unibo.receiver.Receiver"   ). %%control-driven 
qactor( job_launcher , ctx, "it.unibo.job_launcher.MsgHandle_Job_launcher"   ). %%store msgs 
qactor( job_launcher_ctrl , ctx, "it.unibo.job_launcher.Job_launcher"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

