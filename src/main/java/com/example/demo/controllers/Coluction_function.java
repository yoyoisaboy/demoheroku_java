package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;

public class Coluction_function {
    public  String main(double side_a,double  side_b,double  side_c){

        ArrayList<Double> three_sides = new ArrayList<Double>();
        three_sides.add(side_a);
        three_sides.add(side_b);
        three_sides.add(side_c);

        if(check_isTraingle(three_sides)){
           
            return TArea(three_sides).toString();
        }
        else{
            return "NaN";
        }
    }

    //確認是三角形
    public static boolean check_isTraingle(ArrayList<Double> side_map){
        int count  = 0;
        Double perimeter = 0.0 ;
        Collections.sort(side_map);
        for(Double side : side_map){
            if(count==3){
                if(perimeter <= side ) return false;
                perimeter += side;    
            }
            else{
                perimeter += side;
                count+=1;
                }
        }
        return true;
    }
    //算面積
    public static Double TArea(ArrayList<Double> side_map){
        Double perimeter = 0.0;
        for(double number : side_map){ 
            perimeter += number;
        }
        Double s = perimeter/2;
        Double area = Math.sqrt(s*(s-side_map.get(0))*(s-side_map.get(1))*(s-side_map.get(2)) );
        return area;
    }
}
