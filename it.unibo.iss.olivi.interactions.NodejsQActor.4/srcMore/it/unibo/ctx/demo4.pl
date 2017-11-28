%====================================================================================
% Context ctx  SYSTEM-configuration: file it.unibo.ctx.demo4.pl 
%====================================================================================
context(ctx, "localhost",  "TCP", "8000" ).  		 
%%% -------------------------------------------
qactor( client , ctx, "it.unibo.client.MsgHandle_Client"   ). %%store msgs 
qactor( client_ctrl , ctx, "it.unibo.client.Client"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

