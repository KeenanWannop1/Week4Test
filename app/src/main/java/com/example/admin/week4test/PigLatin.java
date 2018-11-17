package com.example.admin.week4test;

public class PigLatin {

    public static void main(String[] args) {
        String test = "chicken soup";
        String pig = makePig(test);
        System.out.println(pig);
    }
    public static String makePig(String prePig){
        StringBuilder postPig = new StringBuilder();
        String single;
        String[] array = prePig.split(" ");

        for (int i = 0; i < array.length ; i++) {
            single = changeConstants(array[i]);
            postPig.append(single).append(" ");
        }
        return postPig.toString();
    }

    private static String changeConstants(String s) {
        String cluster = "";
        String postPig = "";
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c != 'a' && c != 'e' && c!='i' && c!='o' && c!='u' && c!='y') {
                cluster += c;
                counter++;
            }
            else {
             i=s.length();
            }
        }
        for (int j = counter; j < s.length(); j++) {
            postPig += s.charAt(j);
        }
        postPig += cluster + "ay";
        return postPig;
    }
}
