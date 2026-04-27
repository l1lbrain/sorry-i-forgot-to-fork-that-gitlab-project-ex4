package com.sapo.edu.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final Atm bidvAtm;

    @Value("${atm.bidv.finish-message}")
    private String finishMessage;

    // Inject Atm qua constructor
    public DemoApplication(Atm bidvAtm) {
        this.bidvAtm = bidvAtm;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo Customer từ màn hình console
        System.out.println("=== CHAO MUNG DEN VOI BIDV ATM ===");
        System.out.print("Nhap so tai khoan: ");
        String acctNo = scanner.nextLine();

        System.out.print("Nhap ma PIN: ");
        String pin = scanner.nextLine();

        System.out.print("Nhap so du ban dau: ");
        BigDecimal balance = scanner.nextBigDecimal();

        Customer customer = new Customer(acctNo, pin, balance);

        System.out.println("\n--- BAT DAU GIAO DICH ---");
        // Không dùng new BidvAtm() nữa, gọi thẳng instance đã được Spring cung cấp
        bidvAtm.printCurrentMoney();
        bidvAtm.displayCustomerInfo(customer);

        bidvAtm.withDraw(customer, new BigDecimal(200000));
        bidvAtm.printCurrentMoney();

        bidvAtm.withDraw(customer, new BigDecimal(10000000));
        bidvAtm.printCurrentMoney();

        bidvAtm.deposit(customer, new BigDecimal(1000000));
        bidvAtm.printCurrentMoney();

        System.out.println(finishMessage);
        scanner.close();
    }
}
