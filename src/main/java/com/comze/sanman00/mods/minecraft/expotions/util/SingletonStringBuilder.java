package com.comze.sanman00.mods.minecraft.expotions.util;

import java.util.Arrays;
import sun.reflect.Reflection;

/**
 * The singleton version of <code>java.lang.StringBuilder</code>. Do not use this!
 * @author sanman00
 * @deprecated Just use <code>java.lang.StringBuilder</code> or equivalent
 */
@Deprecated
public final class SingletonStringBuilder {
    private static String buffer = "";

    public static boolean isArray(Object obj) {
        return obj instanceof Object[];
    }
    
    @SuppressWarnings("deprecation")
    private SingletonStringBuilder() {
        if(Reflection.getCallerClass(2) != SingletonStringBuilder.class) {
            throw new RuntimeException("cannot access " + SingletonStringBuilder.class.getName() + "outside of itself");
        }
    }
    
    public static String getBuffer() {
        return getBuffer(true);
    }
    
    public static String getBuffer(boolean erase) {
        if (erase) {
            String buffer2 = buffer;
            buffer = "";
            return buffer2;
        }
        return buffer;
    }
    
    public static void append(Object obj) {
        if (isArray(obj)) {
            buffer += Arrays.toString((Object[]) obj);
            return;
        }
        buffer += obj.toString();
    }
    
    public static boolean equals(SingletonStringBuilder isb) {
        return isb.toString() != null && isb.toString().equals(buffer);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SingletonStringBuilder) {
            SingletonStringBuilder isb = (SingletonStringBuilder) obj;
            return isb.toString() != null && isb.toString().equals(toString());
        }
        return false;
    }
    
    @Override
    public String toString() {
        return getBuffer(false);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
