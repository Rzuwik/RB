package com.rzuwik.rb.console;

import com.rzuwik.rb.awt.Color;

import java.io.PrintStream;

public class Logger {


    /**
     * @Author Rzuwik
     * on 12.01.2021
     **/
    PrintStream printer = System.out;

    public void print(String message) {
        printer.println(message);
    }
    public void error(String message) {
        printer.println(Color.RED + "[ERROR] " + message);
    }
    public void warn(String message) {
        printer.println(Color.YELLOW + "[WARN] " + message);
    }
}
