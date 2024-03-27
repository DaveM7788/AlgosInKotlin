package org.example

import java.util.*

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


// LC 103 - Binary Tree Zigzag Level Order Traversal
// Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
// (i.e., from left to right, then right to left for the next level and alternate between).

// we need a combined array of all levels of the tree. However, the odd numbered levels will be in reverse order
// Since we are going by level, BFS seems more relevant than DFS

// Time is O(N) so is space O(N). Visit all nodes. BFS uses queue for extra space up to N

// keep track of distance. if we are on an odd distance then reverse the iterated level

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return listOf()

    val queue = LinkedList<TreeNode>()
    val ans = mutableListOf<MutableList<Int>>()
    var distance = 0
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val size = queue.size
        val list = arrayListOf<Int>()

        for (i in 0 until size) {
            val current = queue.removeFirst()
            list.add(current.`val`)
            current.left?.let { queue.addLast(it) }
            current.right?.let { queue.addLast(it) }
        }
        if (distance % 2 == 1) list.reverse()
        ans.add(list)
        distance++
    }

    return ans
}

// Leet Code 102 BFS of Binary Tree. Highly related to 103
// use a Queue to iterate through
// O(N) space and time complexity
fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return listOf()

    val queue = LinkedList<TreeNode>()
    val ans = mutableListOf<MutableList<Int>>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val size = queue.size
        val listLevel = arrayListOf<Int>()
        for (i in 0 until size) {
            val current = queue.removeFirst()
            listLevel.add(current.`val`)
            current.left?.let { queue.addLast(it) }
            current.right?.let { queue.addLast(it) }
        }
        ans.add(listLevel)
    }
    return ans
}