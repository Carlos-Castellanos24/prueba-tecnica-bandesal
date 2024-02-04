package com.carlos.pruebandesal.pruebandesal.utils;

import java.util.Random;


/**
 *
 * @author Carlos
 */
public class CustomUtils {
    
    public static long generarIds(){
        Random random = new Random();
        long id = random.nextInt(1000 - 1) + 1;
        return id;
    }
}
