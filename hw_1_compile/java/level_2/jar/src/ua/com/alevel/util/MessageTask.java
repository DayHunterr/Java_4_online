package ua.com.alevel.util;

import  org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class MessageTask {

    public static void showTaskMessage(String msg) {
        Base64 base64 = new Base64();
        String encoded = new String(base64.encode(msg.getBytes()));
        System.out.println("Encoded String: " + encoded);
        String decoded = new String(base64.decode(encoded.getBytes()));
        System.out.println("Decoded String: "+ decoded);
    }
}
