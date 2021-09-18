
package dao;
import dto.Food;
import dto.Menu;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import util.MyToys;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author Walter White
 */
public class FoodList {
    private ArrayList<Food> foodList = new ArrayList<>();   
    public void addSample() throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        foodList.add(new Food(1, "Rau Muong", "Vegetables", 500, "Vegetable cabinet", df.parse("30-09-2021")));
        foodList.add(new Food(2, "Thanh Long", "Fruits", 2000, "Vegetable cabinet", df.parse("30-10-2021")));
        foodList.add(new Food(3, "Hat Dieu", " Grains, Beans and Nuts", 3000, "Vegetable cabinet", df.parse("15-10-2021")));
        foodList.add(new Food(4, "Thit Bo", "Meat and Poultry", 5000, "Freezer", df.parse("05-12-2021")));
        foodList.add(new Food(5, "Ca Loc", "Fish and Seafood", 2500, "Freezer", df.parse("08-11-2021")));
        foodList.add(new Food(6, "Pho Mai", "Dairy Foods", 100, "Freezer", df.parse("17-12-2021")));
    }
    
    public int searchFoodById(int foodId){
         for (int i = 0; i < foodList.size(); i++) {
            if (foodId == foodList.get(i).getId())
                return i;
        }
        return -1;
    }
    
    public void addFood() throws ParseException{
        int weight,id,pos;Date expireDate;
        String name= null ,type= null,place = null;
        long millis = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
        Date today = new java.util.Date(millis);
        // check ID (cannot be modified, cannot be duplicated),
        do {
            id = MyToys.getAnInteger("Enter's Food ID: ", "ID must be an positive " +
                "integer", 1, Integer.MAX_VALUE);
            pos = searchFoodById(id);
            if (pos != -1)
                System.err.println("This Food ID is already exist");
        } while (pos != -1);       
        name = MyToys.getAName("Input a Food's name: ", 
                 "Không bỏ trống tên. Tên chỉ gồm các kí tự và khoảng trắng");
        weight = MyToys.getAnInteger("Input Food's weight(gram): ", 
                 "Không bỏ trống weight. Weight từ 1-10000 gram", 1, 10000);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        expireDate = MyToys.getADate("Enter expired date (dd-mm-yyyy):", 
                                "Wrong valid date range(from today to 22-10-2022)",
                                 today,df.parse("22-10-2022"));
        place = getPlace();
        type = getType();         
        Food f = new  Food(id, name, type, weight, place, expireDate);
        foodList.add(f);
        
        System.out.println("Food add successfull!");
    }
    
    public void searchFoodByName(){
//        String search = MyToys.getString("Input Search's keyword: ", "Search's keyword must be text!"); 
        String search = MyToys.getAName("Input Search's keyword: ", "Search's keyword must be text!");
        ArrayList<Food> listOfFound = new ArrayList<>();// lưu những food được tìm thấy
        for (Food food1 : foodList) {
            if (food1.getName().contains(search))       
               listOfFound.add(food1);
        }       
        if (listOfFound.isEmpty()){
            System.out.println("not found!");
        } else{
            for (Food found : listOfFound) {
                found.showInfo();
            }
        }
    }
    
    
    public void removeFoodById(){
        if(foodList.isEmpty())
            System.out.println("Danh sach trong");
        int id = MyToys.getAnInteger("Enter's Food ID: ", "ID must be an positive " +
                "integer", 1, Integer.MAX_VALUE);
        int pos = searchFoodById(id);
            if (pos == -1){System.err.println("Food khong tồn tại!"); }
            else{
                Menu x = new Menu("Are you sure you want to continue?");
                x.addNewOption("1. yes");
                x.addNewOption("2. no");
                int c;
                do{
                    x.printMenu();c = x.getChoice();
                    switch (c){
                        case 1: foodList.remove(pos);
                        System.out.println("Remove remove sucessfully!");
                        break;
                        case 2: System.out.println("ok, fine !");
                            break;
                    } 
                }while(c != 2);
            }
       
        
    }
    
    public void printFoodListDescendingByExpDate(){
        Comparator<Food> comparator = (c2,c1)->{
            return Long.valueOf(c1.getExpiredDate().getTime()).compareTo(c2.getExpiredDate().getTime());
        };
        Collections.sort(foodList, comparator);
        for (Food food : foodList) {
        food.showInfo();
        }
        
    }
//    
//    public void printFoodListDescendingByWeight(){
//        Comparator<Food> comparator = (c2,c1)->{
//            return Integer.valueOf(c1.getWeight()).compareTo(c2.getWeight());
//        };
//        Collections.sort(foodList, comparator);
//        for (Food food : foodList) {
//        food.showInfo();
//        }
//        
//    }
    
    public void writeFile(){
        try{
                    FileWriter fw = new FileWriter("data.txt");
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (Food food : foodList)                                       
                        bw.write(food.toString());
                        bw.close();
                        fw.close();
                    }catch (Exception e){
                        
                    }
    }
    
   public String getPlace(){
        String place = "";
       Menu menuPlace = new Menu("Choose Place of Food");
       int choicePlace;
         menuPlace.addNewOption("1. Freezer");
         menuPlace.addNewOption("2. Vegetable cabinet");
        
         do{
             menuPlace.printMenu();
             choicePlace= menuPlace.getChoice();
             switch (choicePlace){
                 case 1: place = "Freezer";
                    break;
                 case 2: place = "Vegetable cabinet";
                    break;   
             }return place;
         }while(choicePlace != 2);
   }
   
   public String getType(){
       String type= null;
        Menu menuType = new Menu("Choose Type of Food");
         menuType.addNewOption("1. Vegetables");
         menuType.addNewOption("2. Fruits");
         menuType.addNewOption("3. Grains,Beans and Nuts");
         menuType.addNewOption("4. Meat and Poultry");
         menuType.addNewOption("5. Fish and Seafood");
         menuType.addNewOption("6. Dairy Foods");
         int choiceType;
         do{
             menuType.printMenu();
             choiceType= menuType.getChoice();
             switch (choiceType){
                 case 1:  
                     type = "Vegetables";   
                    break;
                 case 2: type = "Fruits";
                    break;
                 case 3: type = "Grains,Beans and Nuts";
                    break;
                 case 4: type = "Meat and Poultry";
                    break;
                 case 5: type = "Fish and Seafood";
                     break;
                 case 6: type = "Dairy Foods";
                    break;
             } return type;
         }while(choiceType != 6);
   }
   public boolean menuYesNo() {
		boolean flat = true;
		while(flat) {
			System.out.println("1.Yes");
			System.out.println("2.No");
			int choose;
			do {
				
				choose = MyToys.getAnInteger("Enter your choose:", "Choice must be interger! ");
			}while(choose < 0 || choose > 2);
			switch (choose) {
			case 1:{
				return true;
			}
			case 2:{
				return false;
			}
			}
		}
		return flat;
	}
}
