package com.rzuwik.rb;


import com.rzuwik.rb.awt.Color;
import com.rzuwik.rb.console.Logger;

import java.util.Scanner;

public class Main {
    static Logger logger;

    /**
     * @Author Rzuwik
     * on 12.02.2021
     **/

    public static void main(String[] args) {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(Color.BLUE +"Please write language: ");
            scanner.next();
            if (scanner.next().equals("english")) {
                System.out.println(Color.BLUE +"Please write number: ");
                scanner.nextInt();
            }
            else {
                    System.out.println(Color.BLUE +"Podaj liczbê: ");
                scanner.nextInt();
            }
            System.exit(0);

        }
    }


}
