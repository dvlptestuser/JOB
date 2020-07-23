package com.pk.assistant;

//Java implementation to 
//find minimum number of 
//deletions to make a 
//string palindromic 
public  class Test 
{
	 public static String makePalindrome(String mjono) {
		 	char ch;
	        StringBuilder sb = new StringBuilder(mjono);
	        for (int i = 0; i < mjono.length(); i++) {
	        	ch=sb.charAt(i);
	            sb.deleteCharAt(i);
	            if(isPalindrome(sb.toString())){
	                return String.valueOf(ch);
	            } else {
	                sb.insert(i, mjono.charAt(i));
	            }
	        }
	        return "Not possible";
	    }

	    private static boolean isPalindrome(String string) {
	        return string.equals(new StringBuilder(string).reverse().toString());
	    }

	    public static void main(String[] args) {
	        System.out.println(makePalindrome("kjjjhjjj"));
	       
	    }
}
//This code is contributed by Sumit Ghosh 
