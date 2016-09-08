package sbt.kislin.hw_SerrealizationProxy;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by axel on 08.09.2016.
 */
public class Main {
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
        String firstDate = "1994-05-14";
        String secondDate = "2016-05-14";
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(firstDate);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(secondDate);
        Period p = new Period(date2, date1);
        System.out.println("We have object " + p.toString());
        System.out.println("We want serialize him");
        //serialization him
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bOut);
        oos.writeObject(p);

        //deserialization him
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bOut.toByteArray()));
        Period p2 = (Period) ois.readObject();
        System.out.println("After deserialization we have object "+p2.toString());
    }
}
