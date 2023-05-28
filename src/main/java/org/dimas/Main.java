package org.dimas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input : ");
        String filename = scanner.nextLine();
        System.out.print("Path Input : ");
        String pathName = scanner.nextLine();


        try (BufferedReader br = new BufferedReader(new FileReader(pathName + "/" +filename))) {
            Map<String, List<String>> bankData = new HashMap<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String bank = parts[0];
                    String envi = parts[1];
                    String port = parts[2];
                    String status = parts[4];

                    String data = "- Envi "+ envi +" Port " + port + ", terpantau " + status;
                    bankData.computeIfAbsent(bank, k -> new ArrayList<>()).add(data);
                }
            }

            for (Map.Entry<String, List<String>> entry : bankData.entrySet()) {
                String bank = entry.getKey();
                List<String> dataList = entry.getValue();

                System.out.println("Selamat Siang Rekan Bank " + bank + ",");
                System.out.println("Mohon bantuan untuk Sign On pada envi berikut:");
                for (String data : dataList) {
                    System.out.println(data);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}