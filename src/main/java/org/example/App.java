package org.example;

import chat.ChatApplication;

import java.io.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);

        ChatApplication chat=new ChatApplication();
        chat.startChat();

    }

}
