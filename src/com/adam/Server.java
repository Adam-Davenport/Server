package com.adam;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	private static ServerSocket server;
	private static DataInputStream in;
	private static DataOutputStream out;
	private static Socket incoming;


    public static void main(String[] args) {
        try {
            //Creating a Serversocket with a port number
            server = new ServerSocket(8189);
            //Keeps the server running
            while (true)
            {
				System.out.println("Waiting for connection...");
                incoming = server.accept(); //accept a connection from a client
                try {
					//Handle input and output
					in = new DataInputStream(incoming.getInputStream());
					System.out.println("Inputstream initiated");
					out = new DataOutputStream(incoming.getOutputStream());
					System.out.println("Outputstream initiated\n");
                    //Variables needed for the text handling
                    boolean done = false;
                    //Loop only closed by exception
                    while (!done)
                    {
						out.writeUTF("Connection Accepted!\n");
						String temp = in.readUTF();
						System.out.println(temp);
						logic();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        } catch (Exception exception2) {
            exception2.printStackTrace();
        }
    }


    public static void logic() {

        //this is the method that accepts and returns input from the client
        String s = "temp";
        boolean done = false;
        while (!s.equals("1") || !s.equals("2") || !done) {
            try {
                while (!s.equals("1") || !s.equals("2"))
                {
                    out.writeUTF("Would you like to echo text or retrieve student information? (1 or 2):");
                    s = in.readUTF();

                    if (s.equals("1"))
                    {
                        s="";
						echo();
                    }
                    else if (s.equals("2"))
                    {
                        s="";
                        studentManager();
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

	public static void echo()
	{
		try
		{
			out.writeUTF("Please enter some text:");
			out.writeUTF(in.readUTF() + " Please press any key to continue...");
			in.readUTF();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void studentManager()
	{
		try {

			int i = 0;
			//Retrieves student array list from the Student class
			ArrayList<Student> students = Student.studentList();
			//This will only allow the user to enter a valid index
			while (i < 1 || i > students.size()) {
				try {
					out.writeUTF("Enter a number between 1 and " + Integer.toString(students.size()));
					i = Integer.parseInt(in.readUTF());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//Retrieves a valid index from the student array list
			Student stud = students.get(i - 1);
			//Sends a student in a readable format to the client
			out.writeUTF(stud.getFirstName() + " " + stud.getLastName() + ", Major: " + stud.getMajor() + ", Graduation Year: " + stud.getGradYear() + ". Press any key to continue...");
			in.readUTF();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
