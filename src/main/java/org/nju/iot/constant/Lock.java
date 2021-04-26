package org.nju.iot.constant;

public class Lock {
    private static boolean lock = false;

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        Lock.lock = lock;
    }
}
