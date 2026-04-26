package com.sapo.edu.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final Atm bidvAtm;

    // Inject Atm qua constructor
    public DemoApplication(Atm bidvAtm) {
        this.bidvAtm = bidvAtm;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Customer customer = new Customer("ABC", "1234", new BigDecimal(5000000));

        //Demo function
//        Atm bidvAtm = new BidvAtm();
//        bidvAtm.printCurrentMoney();
//        bidvAtm.displayCustomerInfo(customer);
//        bidvAtm.withDraw(customer, new BigDecimal(200000));
//        bidvAtm.printCurrentMoney();
//        bidvAtm.withDraw(customer, new BigDecimal(10000000));
//        bidvAtm.printCurrentMoney();
//        bidvAtm.deposit(customer,new BigDecimal(1000000));
//        bidvAtm.printCurrentMoney();

        Scanner scanner = new Scanner(System.in);

        // Khởi tạo Customer từ màn hình console
        System.out.println("=== CHÀO MỪNG ĐẾN VỚI BIDV ATM ===");
        System.out.print("Nhập số tài khoản: ");
        String acctNo = scanner.nextLine();

        System.out.print("Nhập mã PIN: ");
        String pin = scanner.nextLine();

        System.out.print("Nhập số dư ban đầu: ");
        BigDecimal balance = scanner.nextBigDecimal();

        Customer customer = new Customer(acctNo, pin, balance);

        System.out.println("\n--- BẮT ĐẦU GIAO DỊCH ---");
        // Không dùng new BidvAtm() nữa, gọi thẳng instance đã được Spring cung cấp
        bidvAtm.printCurrentMoney();
        bidvAtm.displayCustomerInfo(customer);

        bidvAtm.withDraw(customer, new BigDecimal(200000));
        bidvAtm.printCurrentMoney();

        bidvAtm.withDraw(customer, new BigDecimal(10000000));
        bidvAtm.printCurrentMoney();

        bidvAtm.deposit(customer, new BigDecimal(1000000));
        bidvAtm.printCurrentMoney();

        System.out.println("Giao dịch kết thúc! Hãy kiểm tra file atm_log.txt để xem log hệ thống.");
        scanner.close();
    }
}
