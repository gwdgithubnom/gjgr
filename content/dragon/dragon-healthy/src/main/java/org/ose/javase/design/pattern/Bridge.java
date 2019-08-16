// Decouple an abstraction from its implementation so that the two can vary independently.

package org.ose.javase.design.pattern;

public class Bridge {
    public static void main(String[] args) {
        ITV tv = new SamsungTV();
        LogitechRemoteControl lrc = new LogitechRemoteControl(tv);
        lrc.setChannelKeyboard(100);
    }
}

// abstraction

interface ITV {
    public void on();

    public void off();

    public void switchChannel(int channel);
}

abstract class AbstractRemoteControl {
    private ITV tv; // the bridge

    public AbstractRemoteControl(ITV tv) {
        this.tv = tv;
    }

    public void turnOn() {
        tv.on();
    }

    public void turnOff() {
        tv.off();
    }

    public void setChannel(int channel) {
        tv.switchChannel(channel);
    }
}

// implementation

class SamsungTV implements ITV {
    @Override
    public void on() {
        System.out.println("Samsung is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Samsung is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Samsung: channel - " + channel);
    }
}

class SonyTV implements ITV {
    @Override
    public void on() {
        System.out.println("Sony is turned on.");
    }

    @Override
    public void off() {
        System.out.println("Sony is turned off.");
    }

    @Override
    public void switchChannel(int channel) {
        System.out.println("Sony: channel - " + channel);
    }
}

class LogitechRemoteControl extends AbstractRemoteControl {
    public LogitechRemoteControl(ITV tv) {
        super(tv);
    }

    public void setChannelKeyboard(int channel) {
        setChannel(channel);
        System.out.println("Logitech use keyword to set channel.");
    }
}
