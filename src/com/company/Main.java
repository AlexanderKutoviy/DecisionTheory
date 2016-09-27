package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String MESSAGE = "Enter task to do [1,2,3,4]-->> ";

    public static void main(String[] args) {
        ArrayList<Integer> listOfInputsNumbers = new ArrayList<>();
        System.out.println(MESSAGE);
        int choice = new Scanner(System.in).nextInt();

        DecisionUtils.initInputNumbers(listOfInputsNumbers, choice);
        ArrayList<Integer> paretoList = new ArrayList<>(listOfInputsNumbers);
        ArrayList<Integer> slaterList = new ArrayList<>(listOfInputsNumbers);

        DecisionUtils.generateParetoList(paretoList);
        DecisionUtils.generateSlaterList(slaterList);
        DecisionUtils.writeToOutputFile(listOfInputsNumbers, paretoList, slaterList);
    }
}