import java.util.Scanner;
public class MainComands {
    int choiseOption;
    Scanner scan = new Scanner(System.in);
    public void ConsoleMenu()
    {
        System.out.println("Choose your option");
System.out.println("Press 1 Knight and Arsenal management \n Press 2 InfoRefence\n Press 3 exit");
choiseOption= scan.nextInt();
switch(choiseOption){
    //fix this to !== later
    case 1:
        {while(choiseOption<8)
        {
            System.out.println("Choose your option\n Press 1 Show all Amunition\n Press 2 Sort by price Ammunition\n Press 3 sort by weight Ammunition \n Press 4 equip the Knight\n Press 5 unequip the Knight \n Press 6 Show equipped items\n Press 7 Calculate total cost of equipped items\n Press 8 Go back to Main Menu");
          choiseOption= scan.nextInt();
                switch(choiseOption){
                case 1:{};
                case 2 :{}
                case 3:{};
                case 4:{};
                case 5 :{};
                case 6 :{};
                case 7 :{};
                case 8 :{};
                default:{System.out.println("Wrong input");
                break;}


         }
        }
            break;}
    case 2:{infoReference();
        break;
    }
    case 3 :{
        exit();
        break;}
    default:{System.out.println("Wrong input");
        break;}


}
    };

    public void exit(){
                scan.close();
    }
    public void infoReference(){

    };
}
