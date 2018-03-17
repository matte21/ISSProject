#!/bin/bash

if [ ! -d "fifoDir" ];
then
 mkdir fifoDir 
fi

mkfifo --mode=0660 fifoDir/fifo 