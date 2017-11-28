%====================================================================================
% Context ctx  SYSTEM-configuration: file it.unibo.ctx.demo2.pl 
%====================================================================================
context(ctx, "localhost",  "TCP", "8000" ).  		 
%%% -------------------------------------------
qactor( receiver , ctx, "it.unibo.receiver.MsgHandle_Receiver"   ). %%store msgs 
qactor( receiver_ctrl , ctx, "it.unibo.receiver.Receiver"   ). %%control-driven 
qactor( sender_0 , ctx, "it.unibo.sender_0.MsgHandle_Sender_0"   ). %%store msgs 
qactor( sender_0_ctrl , ctx, "it.unibo.sender_0.Sender_0"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

