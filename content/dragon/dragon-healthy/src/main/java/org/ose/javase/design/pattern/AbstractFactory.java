package org.ose.javase.design.pattern;

public abstract class AbstractFactory {
    private AbstractFactory() {
    }

    public static CpuFactory getCpuFactory(int cpuFactoryType) {
        switch (cpuFactoryType) {
            case 1:
                return new AmdCpuFactory();
            case 2:
                return new IntelCpuFactory();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        CpuFactory amdCpuFactory = AbstractFactory.getCpuFactory(1);
        Cpu amdCpu1 = amdCpuFactory.produceCpu(1);
        Cpu amdCpu2 = amdCpuFactory.produceCpu(2);
        amdCpu1.process();
        amdCpu2.process();

        CpuFactory intelCpuFactory = AbstractFactory.getCpuFactory(2);
        Cpu intelCpu1 = intelCpuFactory.produceCpu(1);
        Cpu intelCpu2 = intelCpuFactory.produceCpu(2);
        intelCpu1.process();
        intelCpu2.process();
    }
}

// factory

abstract class CpuFactory {
    abstract public Cpu produceCpu(int cpuType);
}

class AmdCpuFactory extends CpuFactory {
    @Override
    public Cpu produceCpu(int cpuType) {
        Cpu result = null;
        switch (cpuType) {
            case 1:
                result = new AmdCpu1();
                break;
            case 2:
                result = new AmdCpu2();
                break;
            default:
                break;
        }
        return result;
    }
}

class IntelCpuFactory extends CpuFactory {
    @Override
    public Cpu produceCpu(int cpuType) {
        switch (cpuType) {
            case 1:
                return new IntelCpu1();
            case 2:
                return new IntelCpu2();
            default:
                return null;
        }
    }
}

// objects produced by factory

interface Cpu {
    public void process();
}

class AmdCpu1 implements Cpu {
    @Override
    public void process() {
        System.out.println("AmdCpu1 is processing");
    }
}

class AmdCpu2 implements Cpu {
    @Override
    public void process() {
        System.out.println("AmdCpu2 is processing");
    }
}

class IntelCpu1 implements Cpu {
    @Override
    public void process() {
        System.out.println("IntelCpu1 is processing");
    }
}

class IntelCpu2 implements Cpu {
    @Override
    public void process() {
        System.out.println("IntelCpu2 is processing");
    }
}
