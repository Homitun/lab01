/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dto.FoodList;
import dto.Menu;
import java.text.ParseException;

/**
 *
 * @author Walter White
 */
public class Program {
    public static void main(String[] args) throws ParseException {
        FoodList Flist = new FoodList();
        Flist.addSample();
        Menu menu = new Menu("Welcome to Food Management-@2021 by <SE151471-Hoang Minh Tuan>");       
        menu.addNewOption("1.Add a new food");
        menu.addNewOption("2.Search a food by name");
        menu.addNewOption("3.Remove the food by ID");
        menu.addNewOption("4.Print the food list in the descending order of expired date");
        menu.addNewOption("5.Quit");
        int choice;
        do{
            menu.printMenu();
            choice = menu.getChoice();
            switch(choice){
                case 1:{
                    Flist.addFood();
                    break;
                }
                case 2:{
                    Flist.searchFoodByName();
                    break;
                }
                case 3:{
                    Flist.removeFoodById();
                    break;
                }
                case 4:{
                    Flist.printFoodListDescendingByExpDate();
                    break;
                }
                case 5:{
                    System.out.println("See you, enjoy!");
                    break;
                }
            }
            
            
        }while (choice != 5);
    }
}
