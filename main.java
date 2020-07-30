import java.io.*;

public class Main{
public static void main(String args[]) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}

interface Command{
    public void execute();
}

class Light{
    public Light(){

    }
    public void on(){
        System.out.print("Light is on !!");
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