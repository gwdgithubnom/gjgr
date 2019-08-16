package org.ose.javase.design.pattern;

public class BuilderConstructor {
    public static void main(String[] args) {
        Computer c1 = new Computer.Builder("Intel", "8G", "120G").mouse("Logitech").build();
        System.out.println(c1);
    }
}

class Computer {
    private final String cpu;
    private final String memory;
    private final String disk;
    private final String mouse;
    private final String keyboard;

    public static class Builder {
        // Required parameters
        private final String cpu;
        private final String memory;
        private final String disk;

        // Optional parameters - initialized to default values
        private String       mouse    = "Default mouse";
        private String       keyboard = "Default keyboard";

        public Builder(String cpu, String memory, String disk) {
            this.cpu = cpu;
            this.memory = memory;
            this.disk = disk;
        }

        public Builder mouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public Builder keyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    private Computer(Builder builder) {
        cpu = builder.cpu;
        memory = builder.memory;
        disk = builder.disk;
        mouse = builder.mouse;
        keyboard = builder.keyboard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Computer");
        sb.append(' ').append("cpu=").append(cpu);
        sb.append(' ').append("memory=").append(memory);
        sb.append(' ').append("disk=").append(disk);
        sb.append(' ').append("mouse=").append(mouse);
        sb.append(' ').append("keyboard=").append(keyboard);
        sb.append(']');

        return sb.toString();
    }
}
