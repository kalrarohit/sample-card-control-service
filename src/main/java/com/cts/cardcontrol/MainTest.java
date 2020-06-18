package com.cts.cardcontrol;

import java.util.Arrays;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		//System.out.println("**************************"+TransactionType.valueOf(TransactionType.class, "Ecommerce"));
        List<String> list = Arrays.asList("A", "B", "C", "D");

        String result = String.join(",", list);
        System.out.println(result);

	}

}
