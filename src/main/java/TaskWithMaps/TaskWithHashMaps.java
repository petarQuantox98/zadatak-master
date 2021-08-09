package TaskWithMaps;

import java.util.*;


public class TaskWithHashMaps {

    public void secondList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(11);
        list.add(12);
        list.add(6);
        list.add(18);
        list.add(8);
        list.add(7);
        list.add(9);
        list.add(10);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(17);
        list.add(56);
        list.add(16);
        list.add(78);
        list.add(98);

        int wordsNumber = 20;
        int wordsLength = 5;
        String chars = "poiurywqqejfadvnmkl";
        Random random = new Random();
        List<String>lista=new ArrayList<>();
        while (lista.size() < wordsNumber) {
            StringBuilder newWord = new StringBuilder();
            for (int i = 0; i < wordsLength; i++) {
                newWord.append(chars.charAt(random.nextInt(chars.length())));
            }
            lista.add(newWord.toString());
        }

        Map<String,Integer> map = new HashMap<>();
//dodavanje lista u mapu
        for(String key : lista){
            map.put(key,list.get(lista.indexOf(key)));
        }
        //ispis key-a i values-a iz mape
        for (String key : map.keySet()){
            System.out.println("Key: "+key+" Value: "+map.get(key));
        }

    }

    public static void main(String[] args) {

        TaskWithHashMaps main = new TaskWithHashMaps();

            main.secondList();
    }
}


