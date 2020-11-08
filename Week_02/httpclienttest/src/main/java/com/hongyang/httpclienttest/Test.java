package com.hongyang.httpclienttest;

public class Test {
    public static void main(String[] args) {
        int n = 3;
        int sum = 0;
        int i = 1;
        int j = 1;
        for (; i <= n; ++i) {
            j = 1;
            for (; j <= n; ++j) {
                sum = sum + i * j;
            }
        }
    }
}
