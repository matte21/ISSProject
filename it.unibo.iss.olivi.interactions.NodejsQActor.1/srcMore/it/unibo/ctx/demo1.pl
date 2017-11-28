%====================================================================================
% Context ctx  SYSTEM-configuration: file it.unibo.ctx.demo1.pl 
%====================================================================================
context(ctx, "localhost",  "TCP", "8000" ).  		 
%%% -------------------------------------------
qactor( receiver , ctx, "it.unibo.receiver.MsgHandle_Receiver"   ). %%store msgs 
qactor( receiver_ctrl , ctx, "it.unibo.receiver.Receiver"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

