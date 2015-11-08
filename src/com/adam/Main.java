package com.adam;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            //Creating a socket with a port number
            ServerSocket s = new ServerSocket(8189);
            boolean end = false;
            while (!end) //put in a loop that keeps running
            {
                Socket incoming = s.accept(); //accept a connection from a client
                try {
                    //Input handler
                    InputStream in = incoming.getInputStream();
                    Scanner scan = new Scanner(in);
                    //Output handler
                    DataOutputStream outStream = new DataOutputStream(incoming.getOutputStream());
                    //Printwriter has autoflush set to true
                    PrintWriter out = new PrintWriter(outStream, true);
                    //Variables needed for the text handling
                    boolean done = false;
                    int i = 0;
                    //Infinite loop only closed by command, needs a way to stop.
                    while (!done)
                    {
                        try {

                            if (i == 0) {
                                String temp = scan.nextLine();
                                System.out.println(temp);
                                out.println("Connection Accepted!");
                                i++;
                            } else {
                                logic(scan, out);
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } catch (Exception exception2) {
            exception2.printStackTrace();
        }
    }

    public static void logic(Scanner in, PrintWriter out) {

        //this is the method that accepts and returns input from the client
        String s = "temp";
        String t;
        boolean done = false;
        while (!s.equals("1") || !s.equals("2") || !done) {
            try {
                while (!s.equals("1") || !s.equals("2"))
                {
                    out.println("Would you like to echo text or retrieve student information? (1 or 2): ");
                    s = in.nextLine();

                    if (s.equals("1"))
                    {
                        s="";
                        out.println("Please enter some text:");
                        t = in.nextLine();
                        out.println(t + " Please press any key to continue...");
                        in.nextLine();
                    }
                    else if (s.equals("2"))
                    {
                        s="";
                        int i = 0;
                        //Retrieves student array list from the Student class
                        ArrayList<Student> students = Student.studentList();
                        //This will only allow the user to enter a valid index
                        while (i < 1 || i > students.size()) {
                            try {
                                out.println("Enter a number between 1 and " + Integer.toString(students.size()));
                                i = Integer.parseInt(in.nextLine());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //Retrieves a valid index from the student array list
                        Student stud = students.get(i - 1);
                        //Sends a student in a readable format to the client
                        t = stud.getFirstName() + " " + stud.getLastName() + ", Major: " + stud.getMajor() + ", Graduation Year: " + stud.getGradYear() + ". Press any key to continue...";
                        out.println(t);
                        in.nextLine();

                    }
                    else if(s.equals("exit"))
                    {
                        done = true;
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
