package org.sopt.util;

public class IDGenerator {
    private static int id = 1;

    public static int getId() {
        return id++;
    }
}
