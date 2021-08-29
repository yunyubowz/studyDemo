package com.example.demo.leetCode;

import java.text.NumberFormat;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        System.out.println(percent(Double.parseDouble("1"),Double.parseDouble("12")));
    }
    public static void xx(String s){
        s = "eqwqweqw";
    }

    public static String percent( double p1, double p2) {
        String str;
         double p3 = p1 / p2;
         NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(7 );
        str = nf.format(p3);
        return str;
        }

}
