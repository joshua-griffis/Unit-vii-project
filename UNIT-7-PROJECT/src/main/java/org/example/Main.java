package org.example;

import java.sql.*;
import java.util.*;

class Shoppy {

    public static void shop() {
        Scanner input = new Scanner(System.in);
        ArrayList<HashMap<String, String>> receiptList = new ArrayList<>();
        int dog = 0;
        System.out.println("""
                ╱▏┈┈┈┈┈┈▕╲▕╲┈┈┈
                ▏▏┈┈┈┈┈┈▕▏▔▔╲┈┈
                ▏╲┈┈┈┈┈┈╱┈▔┈▔╲┈
                ╲▏▔▔▔▔▔▔╯╯╰┳━━▀
                ┈▏╯╯╯╯╯╯╯╯╱┃┈┈┈
                ┈┃┏┳┳━━━┫┣┳┃┈┈┈
                ┈┃┃┃┃┈┈┈┃┃┃┃┈┈┈
                ┈┗┛┗┛┈┈┈┗┛┗┛┈┈┈
                Welcome to the Historical Weapons Shop!""");
        label:
        while (true) {
            if (dog == 1) {
                System.out.println("Why are you still here?");
            }
            else {
                System.out.println("What are you looking for? ");
            }
            System.out.println("[Melee], [Ranged], [Rob] the dog, [Leave]");
            System.out.print("> ");
            String answerStart = input.nextLine();
            switch (answerStart) {
                case "Melee":
                case "melee":
                    if (dog == 1) {
                        System.out.println("No, you just tried to rob me! Why would I give you a weapon?\nGet out!");
                        break label;
                    }
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:postgresql:weapon");
//                        jdbc:postgresql:weapon
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM weapons as wp WHERE wp.classification = 'Melee'");

                        while (rs.next()) {
                            System.out.println(rs.getString("weapon_name") + " $" + rs.getString("price"));
                        }
                        rs.close();
                        st.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }
                    System.out.print("Enter the weapons name or enter [back] to go back to the start.\n> ");
                    var melee = input.nextLine();
                    if (melee.equals("back")) {
                        continue;
                    }
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:postgresql:weapon");
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM weapons as wp JOIN origin on wp.weaponid = origin.id WHERE wp.weapon_name = '" + melee + "'");
                        int another_counter = 0;
                        while (rs.next()) {
                            System.out.println("The " + melee + " is $" + rs.getString("price"));
                            System.out.println("Description: " + rs.getString("description"));
                            System.out.println("It originated in " + rs.getString("countryoforigin") + " around " + rs.getString("timeperiod"));
                            System.out.println("Do you want to buy a " + melee + "?\n[Yes] or [No]");
                            System.out.print("> ");
                            var choice = input.nextLine();
                            if (choice.equals("Yes") || choice.equals("No")) {
                                if (choice.equals("Yes")) {
                                    HashMap<String, String> recdict = new HashMap<>();
                                    System.out.println("Thank you purchasing a " + melee);
                                    recdict.put("weapon", melee);
                                    recdict.put("price", rs.getString("price"));
                                    receiptList.add(recdict);
                                    System.out.println(receiptList);
                                } else {
                                    System.out.println("Ok, take your time.");
                                }
                            } else {
                                System.out.println("thats not a choice!!!");
                            }
                            another_counter++;
                        }
                        rs.close();
                        st.close();
                        if (another_counter == 0) {
                            System.out.println("That's not a valid choice.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }
                    break;
                case "Ranged":
                case "ranged":
                    if (dog == 1) {
                        System.out.println("No, you just tried to rob me! Why would I give you a weapon?\nGet out!");
                        break label;
                    }
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:postgresql:weapon");
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM weapons as wp WHERE wp.classification = 'Ranged'");

                        while (rs.next()) {
                            System.out.println(rs.getString("weapon_name") + " $" + rs.getString("price"));
                        }
                        rs.close();
                        st.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }

                    System.out.print("Enter the weapons name or enter [back] to go back to the start.\n> ");
                    var ranged = input.nextLine();
                    if (ranged.equals("back")) {
                        continue;
                    }
                    int another_counter = 0;
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:postgresql:weapon");
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM weapons as wp JOIN origin on wp.weaponid = origin.id WHERE wp.weapon_name = '" + ranged + "'");
                        while (rs.next()) {
                            System.out.println("The " + ranged + " is $" + rs.getString("price"));
                            System.out.println("Description: " + rs.getString("description"));
                            System.out.println("It originated in " + rs.getString("countryoforigin") + " around " + rs.getString("timeperiod"));
                            System.out.println("Do you want to buy a " + ranged + "?\n[Yes] or [No]");
                            Scanner buy = new Scanner(System.in);
                            System.out.print("> ");
                            var choice = buy.nextLine();
                            if (choice.equals("Yes") || choice.equals("No")) {
                                if (choice.equals("Yes")) {
                                    HashMap<String, String> recdict = new HashMap<>();
                                    System.out.println("Thank you purchasing a " + ranged);
                                    recdict.put("weapon", ranged);
                                    recdict.put("price", rs.getString("price"));
                                    receiptList.add(recdict);
                                    System.out.println(receiptList);
                                } else {
                                    System.out.println("Ok, take your time.");
                                }
                            } else {
                                System.out.println("That's not a choice!!!");
                            }
                            another_counter++;
                        }
                        rs.close();
                        st.close();
                        if (another_counter == 0) {
                            System.out.println("That's not a valid choice.");
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }
                    break;
                case "Rob":
                case "rob":
                    dog += 1;
                    if (dog == 1) {
                        System.out.println(
                                """
                                        ╱▏┈┈┈┈┈┈▕╲▕╲┈┈┈
                                        ▏▏┈┈┈┈┈┈▕▏▔▔╲┈┈
                                        ▏╲┈┈┈┈┈┈╱┈▔┈▔╲┈    ︻╦╤─
                                        ╲▏▔▔▔▔▔▔╯╯╰┳━━▀
                                        ┈▏╯╯╯╯╯╯╯╯╱┃┈┈┈\s
                                        ┈┃┏┳┳━━━┫┣┳┃┈┈┈
                                        ┈┃┃┃┃┈┈┈┃┃┃┃┈┈┈
                                        ┈┗┛┗┛┈┈┈┗┛┗┛┈┈┈     \s
                                        """);
                        System.out.println("The shop owner seems upset, He shoots you.");
                        if (!receiptList.isEmpty()) {
                            System.out.println("You lose all your stuff, money, and dignity.");
                            receiptList.clear();
                        }
                    }
                    if (dog == 2) {
                        System.out.println(
                                """
                                        ╱▏┈┈┈┈┈┈▕╲▕╲┈┈┈
                                        ▏▏┈┈┈┈┈┈▕▏▔▔╲┈┈
                                        ▏╲┈┈┈┈┈┈╱┈▔┈▔╲┈    ︻╦╤─
                                        ╲▏▔▔▔▔▔▔╯╯╰┳━━▀
                                        ┈▏╯╯╯╯╯╯╯╯╱┃┈┈┈\s
                                        ┈┃┏┳┳━━━┫┣┳┃┈┈┈
                                        ┈┃┃┃┃┈┈┈┃┃┃┃┈┈┈
                                        ┈┗┛┗┛┈┈┈┗┛┗┛┈┈┈     \s
                                        """);
                        System.out.println("The shop owner wonders why you would try again");
                        System.out.println("He shoots you \nGoodbye :)");
                        break label;
                    }
                    break;
                case "Leave":
                case "leave":
                    int counter = 0;
                    System.out.println("You left");
                    System.out.println("Here is your Receipt.");
                    for (Map<String, String> map : receiptList) {
                        System.out.print(map.get("weapon"));
                        System.out.println(": " + map.get("price"));
                        counter += Integer.parseInt(map.get("price"));
                    }
                    System.out.println("Here is your total: " + counter);
                    break label;
                default:
                    System.out.println("I don't think we have those");
                    break;
            }
        }
    }
}


public class Main {
    public static void main(String[] args){
        Shoppy.shop();

    }

}


