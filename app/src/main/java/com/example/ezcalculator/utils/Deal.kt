package com.example.ezcalculator.utils

import java.util.ArrayDeque
import java.util.Deque

internal class Deal {
    fun calculate(s: String): Int {
        val stack: Deque<Int> = ArrayDeque()
        var preSign = '+'
        var num = 0
        val n = s.length
        for (i in 0 until n) {
            if (Character.isDigit(s[i])) {
                num = num * 10 + s[i].code - '0'.code
            }
            if (!Character.isDigit(s[i]) && s[i] != ' ' || i == n - 1) {
                when (preSign) {
                    '+' -> stack.push(num)
                    '-' -> stack.push(-num)
                    '*' -> stack.push(stack.pop() * num)
                    '/' -> stack.push(stack.pop() / num)
                }
                preSign = s[i]
                num = 0
            }
        }
        var ans = 0
        while (!stack.isEmpty()) {
            ans += stack.pop()
        }
        return ans
    }
}
