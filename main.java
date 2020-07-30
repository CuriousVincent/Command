import java.io.*;

public class Main{
public static void main(String args[]) {
        RemoteControl remote = new RemoteControl();
        Light light = new Light("Living room");
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);
        remote.setCommand(0,lightOn,lightOff);
        remote.onButtonWasPressed(0);
        remote.offButtonWasPressed(0);
        remote.undoButtonWasPush();
    }
}

interface Command{
    public void execute();
    public void undo();
}

class Light{
    String place;
    public Light(String place){
        this.place = place;
    }
    public void on(){
        System.out.println(place+" Light is on !!");
    }
    public void off(){
        System.out.println(place+" Light is off !!");
    }
}

class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    public void execute(){
        light.on();
    }
    public void undo(){
        light.off();
    }
}

class LightOffCommand implements Command{
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    public void execute(){
        light.off();
    }

    public void undo(){
        light.on();
    }
}

class SimpleRemoteControl{
    Command slot;

    public SimpleRemoteControl(){

    }

    public void setCommand(Command Command){
        slot = Command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}