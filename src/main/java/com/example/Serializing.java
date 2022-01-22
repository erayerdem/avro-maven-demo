package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import example.avro.User;

/**
 * Hello world!
 */
public final class Serializing {
   
    public static void main(String[] args) throws IOException {
        File file = Paths.get("src\\main\\avro\\user.avsc").toFile();
        Schema schema = new Schema.Parser().parse(file);

        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
       
        User user2 = new User("Ben", 7, "red");

        User user3 = User.newBuilder()
             .setName("Charlie")
             .setFavoriteColor("blue")
             .setFavoriteNumber(null)
             .build();
        
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(schema, new File("userlist.avro"));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();

        

    }
}
