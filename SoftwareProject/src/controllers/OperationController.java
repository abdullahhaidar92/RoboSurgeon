package controllers;

import machine.*;

public class OperationController {
    CommandHandler commandHandler;


    public OperationController(){
        commandHandler=getCommandHandler();
    }

    private CommandHandler getCommandHandler() {
        MoveStrategy strategy = new RelativePositionsMoveStrategy();
        CommandHandler systemMonitor=new SystemMonitor();
        CommandHandler machine = Machine.getMachine();
        machine.setStrategy(strategy);

        systemMonitor.setSuccessor(machine);
        machine.setSuccessor(null);
        return systemMonitor;
    }

    public void moveRight(double value){
        commandHandler.moveRight(value);
    }
}
