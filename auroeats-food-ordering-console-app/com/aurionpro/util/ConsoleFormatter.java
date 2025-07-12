package com.aurionpro.util;

public class ConsoleFormatter {

    private static final int CONTENT_WIDTH = 50;
    private static final int TOTAL_WIDTH = CONTENT_WIDTH + 2;

    public static void printTitle(String titleText) {
        titleText = titleText.strip();
        int paddingTotal = CONTENT_WIDTH - titleText.length();
        int paddingLeft = paddingTotal / 2;
        int paddingRight = paddingTotal - paddingLeft;
        String border = "═".repeat(CONTENT_WIDTH);

        System.out.println("╔" + border + "╗");
        System.out.println("║" + " ".repeat(paddingLeft) + titleText + " ".repeat(paddingRight) + "║");
        System.out.println("╚" + border + "╝");
    }

    public static void printSeparator() {
        System.out.println("─".repeat(TOTAL_WIDTH));
    }

    public static void printHeader(String... columnNames) {
        switch (columnNames.length) {
            case 2 -> System.out.printf("%-5s %-40s%n", columnNames[0], columnNames[1]);
            case 3 -> System.out.printf("%-5s %-20s %-20s%n", columnNames[0], columnNames[1], columnNames[2]);
            case 4 -> System.out.printf("%-20s %-10s %-10s %-10s%n", columnNames[0], columnNames[1], columnNames[2], columnNames[3]);
            default -> {
                int widthPerColumn = CONTENT_WIDTH / columnNames.length;
                StringBuilder formatBuilder = new StringBuilder();
                for (int i = 0; i < columnNames.length; i++) {
                    formatBuilder.append("%-").append(widthPerColumn).append("s");
                }
                formatBuilder.append("%n");
                System.out.printf(formatBuilder.toString(), (Object[]) columnNames);
            }
        }
        printSeparator();
    }

    public static void printRow(Object... columnValues) {
        switch (columnValues.length) {
            case 2 -> System.out.printf("%-5s %-40s%n", columnValues[0], columnValues[1]);
            case 3 -> System.out.printf("%-5s %-20s %-20s%n", columnValues[0], columnValues[1], columnValues[2]);
            case 4 -> System.out.printf("%-20s %-10s %-10s %-10s%n", columnValues[0], columnValues[1], columnValues[2], columnValues[3]);
            default -> {
                int widthPerColumn = CONTENT_WIDTH / columnValues.length;
                StringBuilder formatBuilder = new StringBuilder();
                for (int i = 0; i < columnValues.length; i++) {
                    formatBuilder.append("%-").append(widthPerColumn).append("s");
                }
                formatBuilder.append("%n");
                System.out.printf(formatBuilder.toString(), columnValues);
            }
        }
    }
    
    public static void printTotalRow(String label, double amount) {
        System.out.printf("%-42s %-10s%n", label, "₹" + String.format("%.2f", amount));
    }


    public static void prompt(String message) {
        System.out.print("» " + message + ": ");
    }

    public static void printError(String errorMessage) {
        System.out.println("[✘] " + errorMessage);
    }

    public static void printSuccess(String successMessage) {
        System.out.println("[✔] " + successMessage);
    }
}
