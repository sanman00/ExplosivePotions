package com.comze.sanman00.mods.minecraft.expotions.util;

import java.util.Arrays;
import sun.reflect.Reflection;

/**
 * The singleton version of <code>java.lang.StringBuilder</code>. Do not use!
 * @author sanman00
 */
public final class ImmutableStringBuilder {
    private static String buffer = "";

    public static boolean isArray(Object obj) {
        return obj instanceof Object[];
    }
    
    @SuppressWarnings("deprecation")
    private ImmutableStringBuilder() {
        if(Reflection.getCallerClass(2) != ImmutableStringBuilder.class) {
            throw new RuntimeException("cannot access " + ImmutableStringBuilder.class.getName() + "outside of itself");
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
    
    public static boolean equals(ImmutableStringBuilder isb) {
        return isb.toString() != null && isb.toString().equals(buffer);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ImmutableStringBuilder) {
            ImmutableStringBuilder isb = (ImmutableStringBuilder) obj;
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
