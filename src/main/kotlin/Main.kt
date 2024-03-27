package org.example

fun main() {
    println("Hello World!")
    val tester = intArrayOf(1, -2, 3, 9, 5, 4)

    //println(findShortestSubArray(intArrayOf(1, 2, 2, 3, 1)))

    val misc = Misc()
    val entry = intArrayOf(1, 2, 3, -1, -2, 6, 7, 8)
    val ans = misc.subArrayKElementsLeastSum(entry, 2)
    //println("subarray is ${ans.joinToString()}")

    misc.setUpBinaryTreeAndPrintHeight()
}



// LeetCode 242
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val sMap = mutableMapOf<Char, Int>()
    s.forEach {
        if (sMap.containsKey(it)) {
            sMap[it] = sMap[it]!! + 1
        } else {
            sMap[it] = 1
        }
    }
    t.forEach {
        if (sMap.containsKey(it)) {
            sMap[it] = sMap[it]!! - 1
            if (sMap[it]!! < 0) {
                return false
            }
        } else {
            return false
        }
    }
    return true
}


// Leetcode says this is slower
fun isAnagramAlternate(s: String, t: String): Boolean {
    // just turn one of them into a list of chars
    val chars = t.toMutableList()

    // iterate through other. remove character. if empty at end. is anagram
    s.forEach {
        val removed = chars.remove(it)
        if (!removed) return false // early
    }
    return chars.size == 0
}


fun isAnagramSort(s: String, t: String): Boolean {
    // just turn one of them into a list of chars
    val sortS = s.toCharArray().sortedArray()
    val sortT = t.toCharArray().sortedArray()

    return sortS.contentEquals(sortT)
}

// LeetCode 697
fun findShortestSubArray(nums: IntArray): Int {
    // O(N) ST
    // 3 maps. key is value in nums array. left is its start idx. right is its end idx
    val left = hashMapOf<Int, Int>()
    val right = hashMapOf<Int, Int>()
    // we also count how many times the value occurs. That will help us get the degree
    val count = hashMapOf<Int, Int>()

    nums.forEachIndexed { idx, num ->
        val x = nums[idx]
        if (!left.containsKey(x)) {
            left[x] = idx
        }
        right[x] = idx
        count[x] = (count[x] ?: 0) + 1
    }

    var ans = nums.size
    // the degree is the element that occurs most
    val degree = count.values.max()
    count.keys.forEach {
        if (count[it] == degree) {
            // it's possible that two values both occur twice for example
            ans = Math.min(ans, right[it]!! - left[it]!! + 1)  // length is end - start + 1
        }
    }
    return ans
}

// Leet Code 1 - Two Sum
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
fun twoSum(nums: IntArray, target: Int): IntArray {
    val mapSoFar = mutableMapOf<Int, Int>()  // number to idx
    nums.forEachIndexed { idx, num ->
        val needed = target - num
        if (mapSoFar.containsKey(needed)) {
            return intArrayOf(idx, mapSoFar[needed]!!)
        }
        mapSoFar[num] = idx  // doesn't matter if numbers location (idx) gets overwrote b/c we just need to find any 2 indices
    }
    return intArrayOf()
}

// Leet Code 1347 Changes to make two strings Anagrams


// Leet Code 829 Consecutive Numbers Sum