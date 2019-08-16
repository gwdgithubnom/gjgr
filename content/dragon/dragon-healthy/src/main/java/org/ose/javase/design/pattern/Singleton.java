// 1. private constructor - no other class can instantiate a new object.
// 2. private reference - no external modification.
// 3. public static method is the only place that can get an object.

package org.ose.javase.design.pattern;

public class Singleton {
    public static void main(String args[]) {
        @SuppressWarnings("unused")
        SingletonNoLazyInit s1 = SingletonNoLazyInit.getInstance();

        @SuppressWarnings("unused")
        SingletonMultiThread s2 = SingletonMultiThread.getInstance();

        SingletonEnum s3 = SingletonEnum.INSTANCE;
        s3.setX(100);
        System.out.println(s3.getX());
    }
}

// no lazy init
class SingletonNoLazyInit {
    private static SingletonNoLazyInit instance = new SingletonNoLazyInit();

    public static SingletonNoLazyInit getInstance() {
        return instance;
    }

    private SingletonNoLazyInit() {
    }
}

// lazy init in multithread scenario
class SingletonMultiThread {
    // volatile ensures visibility across the application (different tasks).
    // volatile also restricts compiler reordering of accesses during optimization.
    private static volatile SingletonMultiThread instance;

    public static SingletonMultiThread getInstance() {
        if (instance == null) {
            synchronized (SingletonMultiThread.class) {
                if (instance == null) {
                    instance = new SingletonMultiThread();
                }
            }
        }

        return instance;
    }

    private SingletonMultiThread() {
        // prevent creating another instance of Singleton using reflection
        if (SingletonMultiThread.instance != null) {
            throw new RuntimeException("instance already created!");
        }
    }

    // prevent creating another instance of Singleton from deserialization
    //
    // If readResolve() is present, then after deserialization process, 
    // this method is called to return the final object to the caller program.
    private Object readResolve() {
        return getInstance();
    }
}

enum SingletonEnum {
    INSTANCE;

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
