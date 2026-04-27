package com.sapo.edu.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Profile("prod")
public class PrinterFile implements Printer{
    private static final String FILE_PATH = "atm_log.txt";

    @Override
    public void printCustoner(Customer customer) {
        String log = "CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString();
        writeToFile(log);
    }

    @Override
    public void printMessage(String message) {
        writeToFile(message);
    }

    private void writeToFile(String content) {
        // Sử dụng try-with-resources để tự động đóng file sau khi ghi
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            out.println(content);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
