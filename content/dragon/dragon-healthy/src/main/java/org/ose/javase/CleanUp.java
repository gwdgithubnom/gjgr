package org.ose.javase;

public class CleanUp {
    public static void main(String[] args) {
        Resource res = new Resource();
        // Right after you create an object that requires cleanup, begin a try-finally.
        try {
            res.process();
        } finally {
            res.clear();
        }
    }
}

class Resource {
    public Resource() {
        System.out.println("resource constructed");
    }

    public void process() {
        System.out.println("resource processed");
    }

    public void clear() {
        System.out.println("resouce cleared");
    }
}
