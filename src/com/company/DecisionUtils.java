package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class DecisionUtils {

    private static final String INPUT_FILE_GENERAL = "inputFileGeneral.txt";
    private static final String INPUT_FILE_1 = "inputFile1.txt";
    private static final String INPUT_FILE_2 = "inputFile2.txt";
    private static final String INPUT_FILE_3 = "inputFile3.txt";
    private static final String OUTPUT_FILE = "output.txt";

    static void initInputNumbers(ArrayList<Integer> listOfInputsNumbers, int choice) {
        try {
            Scanner sc;
            switch (choice) {
                case 1:
                    sc = new Scanner(new File(INPUT_FILE_GENERAL));
                    break;
                case 2:
                    sc = new Scanner(new File(INPUT_FILE_1));
                    break;
                case 3:
                    sc = new Scanner(new File(INPUT_FILE_2));
                    break;
                case 4:
                    sc = new Scanner(new File(INPUT_FILE_3));
                    break;
                default:
                    return;
            }
            while (sc.hasNextInt()) {
                listOfInputsNumbers.add(sc.nextInt());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Not integer numbers!");
            throw new RuntimeException(e);
        }
        System.out.println("Input data: " + listOfInputsNumbers);
    }

    static void generateParetoList(ArrayList<Integer> paretoList) {
        for (int i = 0; i < paretoList.size(); i++) {
            for (int j = i + 1; j < paretoList.size(); j++) {
                if ((paretoList.get(i) / 10 >= paretoList.get(j) / 10) && (paretoList.get(i) % 10 >= paretoList.get(j) % 10)) {
                    paretoList.remove(j);
                    j--;
                } else if ((paretoList.get(j) / 10 >= paretoList.get(i) / 10) && (paretoList.get(j) % 10 >= paretoList.get(i) % 10)) {
                    paretoList.remove(i);
                    j = i;
                }
            }
        }
        System.out.println("Pareto: " + paretoList);
    }

    static void generateSlaterList(ArrayList<Integer> slaterList) {
        for (int i = 0; i < slaterList.size(); i++) {
            for (int j = i + 1; j < slaterList.size(); j++) {
                if (((slaterList.get(i) / 10) > (slaterList.get(j) / 10)) && ((slaterList.get(i) % 10) > (slaterList.get(j) % 10))) {
                    slaterList.remove(j);
                    j--;
                } else if (((slaterList.get(j) / 10) > (slaterList.get(i) / 10)) && ((slaterList.get(j) % 10) > (slaterList.get(i) % 10))) {
                    slaterList.remove(i);
                    j = i;
                }
            }
        }
        System.out.println("Slater:  " + slaterList);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void writeToOutputFile(ArrayList<Integer> listOfInputsNumbers,
                                  ArrayList<Integer> paretoList,
                                  ArrayList<Integer> slaterList) {
        try {
            File out = new File(OUTPUT_FILE);
            if (!out.exists()) {
                out.createNewFile();
            }
            FileWriter fw = new FileWriter(out.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Input data: ");
            bw.write(listOfInputsNumbers.toString());
            bw.write("\nPareto: ");
            bw.write(paretoList.toString());
            bw.write("\nSlater: ");
            bw.write(slaterList.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}