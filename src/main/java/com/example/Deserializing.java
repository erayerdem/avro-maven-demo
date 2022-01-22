package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import example.avro.User;

/**
 * Hello world!
 */
public final class Deserializing {
   
    public static void main(String[] args) throws IOException {
        File file = Paths.get("userlist.avro").toFile();
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        try (DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader)) {
            User user = null;
            while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            System.out.println(user);
            }
        }


    }
}
