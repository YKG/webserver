package org.kaige;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Helper {
    public static Map<Character, String> ESCAPE_CHARS_MAPPING = new HashMap<>();
    static {
        ESCAPE_CHARS_MAPPING.put('\t', "\\t");
        ESCAPE_CHARS_MAPPING.put('\b', "\\b");
        ESCAPE_CHARS_MAPPING.put('\n', "\\n");
        ESCAPE_CHARS_MAPPING.put('\r', "\\r");
        ESCAPE_CHARS_MAPPING.put('\f', "\\f");
    }

    public static void dumpAsHex(byte[] bytes) {
        Objects.requireNonNull(bytes);

        int len = bytes.length;
        System.out.print("[");
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                String hex = String.format("%02X", bytes[i]);
                hex = String.format("%3s", hex);

                String elem = i == 0 ? hex :  "," + hex;
                System.out.print(elem);
            }
        }
        System.out.println("]");
    }

    public static void dumpAsChar(byte[] bytes) {
        Objects.requireNonNull(bytes);

        int len = bytes.length;
        System.out.print("[");
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                char c = (char)bytes[i];
                String ch;
                if (ESCAPE_CHARS_MAPPING.containsKey(c)) {
                    ch = ESCAPE_CHARS_MAPPING.get(c);
                } else {
                    ch = String.format("%c", (char)bytes[i]);
                }
                ch = String.format("%3s", ch);

                String elem = i == 0 ? ch : "," + ch;
                System.out.print(elem);
            }
        }
        System.out.println("]");
    }
}
