package com.kafka.poc.kafka.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;

import java.io.FileReader;
import java.io.IOException;

@Service
public class ProcessFileService {
    public void execute(String fileName){
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            String uppercaseContent = content.toString().toUpperCase();
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(uppercaseContent);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
