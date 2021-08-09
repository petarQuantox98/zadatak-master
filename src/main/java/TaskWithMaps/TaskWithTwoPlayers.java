package TaskWithMaps;

import java.util.Random;

public class TaskWithTwoPlayers {

public void randomNumber() {
int min = 100;
int max = 999;
//Generate random int value from 100 to 999
int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
System.out.println("Player Z imagine number " + random_int);

int firstDigit = Integer.parseInt(Integer.toString(random_int).substring(0, 1));
int secondDigit = Integer.parseInt(Integer.toString(random_int).substring(1, 2));

System.out.println("Player D taking first two numbers from player Z: " + "" + firstDigit + "" + secondDigit);

Random rand = new Random(); //instance of random class

//generate random values from 1-9
int random = 5 + rand.nextInt(9 - 5 + 1);

System.out.println("Player D adding number before first two numbers: " + random + "" + firstDigit + "" + secondDigit);
int someNumber = Integer.parseInt(random + "" + firstDigit + "" + secondDigit);
final int zbir=random_int+someNumber;
System.out.println("Sum of two numbers: " +zbir);

int NumberOneFromPlayerD = Integer.parseInt(Integer.toString(someNumber).substring(0, 1));
int secondNumberFromPlayerD = Integer.parseInt(Integer.toString(someNumber).substring(1, 2));

int someNumber2 = Integer.parseInt(random + "" + NumberOneFromPlayerD + "" + secondNumberFromPlayerD);

 final int zbir2=someNumber2+someNumber;
final int zbir3=someNumber+someNumber2;


if (zbir>999){
System.out.println("Sum of two numbers are bigger than 1000 and player D losing, sum is: "+zbir );
System.out.println("Congratulations player Z are winner" );
}
        if (zbir<999){
 System.out.println("Player Z use number one and two from player D and adding random num: "+random+""+NumberOneFromPlayerD+""+secondNumberFromPlayerD);
 System.out.println(zbir2);
 if (zbir2>999){
System.out.println("Sum of two numbers are "+zbir2+ " and player Z losing" );
 System.out.println("Congratulations player D are winner" );
 }
        }
        if (zbir2<999){
 System.out.println("Player D use number one and two from player D and adding random num: "+random+""+firstDigit+""+secondDigit);
 System.out.println(zbir3);
 if (zbir3>999){
 System.out.println("Sum of two numbers are bigger than 1000 and player D losing" );
 System.out.println("Congratulations player Z are winner" );
 }
        }
        if (zbir3<999){
System.out.println("Player Z use number one and two from player D and adding random num: "+random+""+NumberOneFromPlayerD+""+secondNumberFromPlayerD);
 System.out.println(zbir2);
 if (zbir2>999){
System.out.println("Sum of two numbers are bigger than 1000 and player Z losing" );
 System.out.println("Congratulations player D are winner" );
 }
        }
if (zbir2<999){
System.out.println("Player D use number one and two from player D and adding random num: "+random+""+firstDigit+""+secondDigit);
System.out.println(zbir3);
if (zbir3>999){
System.out.println("Sum of two numbers are bigger than 1000 and player D losing" );
System.out.println("Congratulations player Z are winner" );
}
}
if (zbir3<999){
System.out.println("Player Z use number one and two from player D and adding random num: "+random+""+NumberOneFromPlayerD+""+secondNumberFromPlayerD);
System.out.println(zbir2);
if (zbir2>999){
System.out.println("Sum of two numbers are bigger than 1000 and player Z losing" );
System.out.println("Congratulations player D are winner" );
}
}
 }
 public static void main(String[] args) {
TaskWithTwoPlayers treciZadatak=new TaskWithTwoPlayers();
treciZadatak.randomNumber();
 }
}

