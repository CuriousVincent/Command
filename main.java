import java.io.*;

public class Main{
public static void main(String args[]) {
        RemoteControl remote = new RemoteControl();
        CeilingFan ceilingFan = new CeilingFan("Living room");
        CeilingFanHighCommand ceilingFanOn = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
        remote.setCommand(0,ceilingFanOn,ceilingFanOff);
        remote.onButtonWasPressed(0);
        remote.offButtonWasPressed(0);
        remote.undoButtonWasPush();
    }
}

class CeilingFan{
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;
    String location;
    int speed;
    public CeilingFan(String location){
        this.location = location;
        speed = OFF;
    }
    public void high(){
        speed = HIGH;
        System.out.println(location+" CeilingFsn is high !!");
    }
    public void medium(){
        speed = MEDIUM;
        System.out.println(location+" CeilingFsn is medium !!");
    }
    public void low(){
        speed = LOW;
        System.out.println(location+" CeilingFsn is low !!");
    }
    public void off(){
        speed = OFF;
        System.out.println(location+" CeilingFsn is off !!");
    }
    public int getSpeed(){
        return speed;
    }
}

class CeilingFanOffCommand extends CeilingFanHighCommand{
    public CeilingFanOffCommand(CeilingFan ceilingFan){
        super(ceilingFan);
    }
    public void execute(){
        preSpeed = ceilingFan.getSpeed();
        ceilingFan.off();

    }
}


class CeilingFanHighCommand implements Command{
    CeilingFan ceilingFan;
    int preSpeed;
    public CeilingFanHighCommand(CeilingFan ceilingFan){
        this.ceilingFan = ceilingFan;
    }
    public void execute(){
        preSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }
    public void undo(){
        if(preSpeed == CeilingFan.HIGH){
            ceilingFan.high();
        } else if(preSpeed == CeilingFan.MEDIUM){
            ceilingFan.medium();
        } else if(preSpeed == CeilingFan.LOW){
            ceilingFan.low();
        } else if(preSpeed == CeilingFan.OFF){
            ceilingFan.off();
        }
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