package com.rzuwik.rb.console;


import java.io.IOException;

public class MACLogger {

    /**
     * @Author Rzuwik
     * on 03.02.2021
     **/


    public static void getMac() throws IOException {
        Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
        String line;
        line = in.readLine();
        String[] result = line.split(",");
        String klops = result[0].replace('"', ' ').trim();
    }
}
