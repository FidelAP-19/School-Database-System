// Fidel Perez
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
public class Main {

    public static void main(String[] args) {
        ArrayList<Course> courseList = new ArrayList<>();
        ArrayList<Faculty> facultyList = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<GeneralStaff> staffList = new ArrayList<>();

        FileInputStream fbs = null;
        Scanner inFS = null;
        String currLine;

        try {
            fbs = new FileInputStream("SchoolDB_Initial.txt");
            inFS = new Scanner(fbs);
            while (inFS.hasNextLine()) {
                currLine = inFS.nextLine();
                System.out.println(currLine);
            }
            System.out.println();
            inFS.close();

            fbs = new FileInputStream("SchoolDB_Initial.txt");
            inFS = new Scanner(fbs);

            while (inFS.hasNextLine()){
                String line = inFS.nextLine();
                String result = line.replaceAll(":\\s*", ",");
                result = result.replaceAll("\\s*,\\s*", ",");
                result = result.replaceAll("^,+|,+$", "");


                String [] parts = result.split(",");
                if(parts[0].equals("Course")){
                    Course c;
                    c = new Course(Boolean.parseBoolean(parts[1]),Integer.parseInt(parts[2]),parts[3],Integer.parseInt(parts[4]));
                    courseList.add(c);
                }
                if(parts[0].equals("Faculty")){
                    Faculty f;
                    if(parts.length == 1){
                        f = new Faculty();
                        facultyList.add(f);
                    }
                    else if(parts.length == 2){
                        f = new Faculty(Boolean.parseBoolean(parts[1]));
                        facultyList.add(f);
                    }
                    else if(parts.length == 3){
                        f = new Faculty(parts[1],Boolean.parseBoolean(parts[2]));
                        facultyList.add(f);
                    }
                    else if(parts.length == 5){
                        f = new Faculty(parts[1],Integer.parseInt(parts[2]),parts[3],Boolean.parseBoolean(parts[4]));
                        facultyList.add(f);
                    }

                }
                if(parts[0].equals("Student")){
                    if(parts.length == 1){
                        studentList.add(new Student());
                    }
                    else if(parts.length == 2){
                        studentList.add(new Student(Boolean.parseBoolean(parts[1])));
                    }
                    else if(parts.length == 3){
                        studentList.add(new Student(parts[1],Boolean.parseBoolean(parts[2])));
                    }
                    else if(parts.length == 5){
                        studentList.add(new Student(parts[1],Integer.parseInt(parts[2]),parts[3],Boolean.parseBoolean(parts[4])));
                    }
                }
                if(parts[0].equals("GeneralStaff")){
                    if(parts.length == 1){
                        staffList.add(new GeneralStaff());
                    }
                    else if(parts.length == 2){
                        staffList.add(new GeneralStaff(parts[1]));
                    }
                    else if(parts.length == 3){
                        staffList.add(new GeneralStaff(parts[1],parts[2]));
                    }
                    else if(parts.length == 5){
                        staffList.add(new GeneralStaff(parts[1],Integer.parseInt(parts[2]),parts[3],parts[4]));
                    }
                }

            }
        }
        catch (FileNotFoundException e){
            System.out.println("Cannot find file");
        }
        catch (IOException e){
            System.out.println("Error reading file");
        }
/*
        for(Course course:courseList){
            System.out.println(course.toString());
        }
        for(GeneralStaff staff:staffList){
            System.out.println(staff.toString());
        }
        for(Faculty faculty:facultyList){
            System.out.println(faculty.toString());
        }
        for(Student student:studentList){
            System.out.println(student.toString());
        }

 */


        Boolean scnrActive = true;
        Scanner scnr = new Scanner(System.in);
        while(scnrActive){
            // System.out.println();
            scnrActive = false;
        }
        printAllData(courseList,facultyList,studentList,staffList);
    }

    public static void printAllData(List<Course> courseList, List<Faculty> facultyList, List<Student> studentList, List<GeneralStaff> staffList){
        System.out.println("**************************************************************");
        System.out.println("SCHOOL DATABASE INFO:\n");
        System.out.println("************************************************");
        System.out.println("COURSES:");

        for(Course course:courseList){
            System.out.println(course.toString());
        }
        System.out.println("************************************************");
        System.out.println("************************************************");

        System.out.println("PERSONS:");
        System.out.println("************************************************");
        System.out.println("************************************************");
        System.out.println("EMPLOYEES:");
        System.out.println("************************************************");
        System.out.println("************************************************");
        System.out.println("GENERAL STAFF:");
        for(GeneralStaff staff:staffList){
            System.out.println(staff.toString());
        }
        System.out.println("************************************************");
        System.out.println("************************************************");
        System.out.println("FACULTY:");
        for(Faculty faculty:facultyList){
            System.out.println(faculty.toString());
        }
        System.out.println("************************************************");
        System.out.println("************************************************");
        System.out.println("STUDENTS:");
        for(Student student:studentList){
            System.out.println(student.toString());
        }
        System.out.println("************************************************");
        System.out.println("**************************************************************");
        System.out.println();

    }
}
