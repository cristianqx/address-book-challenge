package com.challenge;

import com.challenge.application.AddressBookApplication;

public class Main {

    public static void main(String[] args) {
        try {
            new AddressBookApplication().run(args);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}