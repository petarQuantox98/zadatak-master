package TaskWithMaps;

import java.util.*;

public class TaskWithArrays {
    List<Integer> list = new ArrayList<>();

    public TaskWithArrays() {

    }

    public void generateRandomNumbers()  {
        list.add(6);
       list.add(6);
        list.add(7);
        list.add(8);
        list.add(4);
        list.add(9);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(12);
        list.add(6);
        list.add(5);
        list.add(7);
        list.add(21);
        list.add(4);
        list.add(92);
        list.add(12);
        list.add(16);
        list.add(12);
        list.add(34);
        list.add(63);
        list.add(63);
        list.add(7);
        list.add(83);
        list.add(41);
        list.add(92);
        list.add(123);
        list.add(132);
        list.add(142);
        list.add(123);
        list.add(62);
        list.add(63);
        list.add(73);
        list.add(83);
        list.add(43);
        list.add(93);
        list.add(123);
        list.add(131);
        list.add(143);
        list.add(121);
        list.add(61);
        list.add(67);
        list.add(76);
        list.add(88);
        list.add(49);
        list.add(90);
        list.add(129);
        list.add(138);
        list.add(141);
        list.add(122);

        System.out.println(list);
        getRandomList();

    }

     public void  getRandomList() {
         List <Integer> nova=new ArrayList<>(list);
                Collections.shuffle(nova);
         System.out.println("Elements in random list: "
                 + nova);
     }


    public static void main(String[] args) {
        TaskWithArrays jav=new TaskWithArrays();
    jav.generateRandomNumbers();

         }


}
