package org.example

class Misc {

    // find max difference between any 2 elements in array
    fun maxDiffTwoElements(arr: IntArray): Int {
        var min = arr[0]
        var max = arr[0]
        arr.forEach {
            min = Math.min(it, min)
            max = Math.max(it, max)
        }
        return max - min
    }

    // determine if n is prime
    fun isPrime(n: Int): Boolean {
        if (n == 1) return false
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }

    // given a string of size N. write functions to left shift by d elements. d <= n
    // Also do right shift by d elements
    // add together substrings
    fun leftRotate(str: String, d: Int): String {
        // GeeksforGeeks    d=2          =  eksforGeeksGe
        // eksforGeeks + Ge
        return str.substring(d) + str.substring(0, d)
    }

    // right Rotate can be done by calling the left function with d = str.length - d
    fun rightRotate(str: String, d: Int): String {
        return leftRotate(str, str.length - d)
    }

    // Given an array arr[] of integers, find out the maximum difference between any two elements such that larger
    // element appears after the smaller number.

    // keep track of max difference found so far
    // keep track of min number visited so far
    fun maxDiffOfTwoElementsWhereSmallerIsToLeftOfLarger(arr: IntArray): Int {
        var minVisitedSoFar = arr[0]
        var maxDiffSoFar = arr[1] - arr[0]
        arr.forEach {
            minVisitedSoFar = Math.min(it, minVisitedSoFar)
            maxDiffSoFar = Math.max(maxDiffSoFar, it - minVisitedSoFar)
        }
        return maxDiffSoFar
    }

    // return the subarray with k elements whose sum is the least possible sum out of the array possible
    // assume k < size of the arr
    // [1, 2, 3, 4, 5, 6, 7, 8]   if k=3     ans    1, 2, 3
    // [1, 7, 5, 2, 1, 1, -1, 1]   if k=3     ans =  1, -1, 1
    fun subArrayKElementsLeastSum(arr: IntArray, k: Int): IntArray {
        var runningMin = Int.MAX_VALUE
        var runningMinStartIdx = 0
        var runningMinEndIdx = 0
        var idx = 0
        var endIdx = idx + k - 1
        while (endIdx < arr.size) {
            val subArray = arr.slice(idx..endIdx)
            val subArraySum = subArray.sum()
            if (subArraySum < runningMin) {
                runningMinStartIdx = idx
                runningMinEndIdx = endIdx
                runningMin = subArraySum
            }
            idx++
            endIdx++
        }
        return arr.slice(runningMinStartIdx..runningMinEndIdx).toIntArray()
    }

    // find the height or max depth of a Binary Tree
    // the height is the max number of nodes from the lowest leaf node to the root node
    // solve the problem using recursion. It's also possible to use a Queue to solve the problem iteratively
    // we add find the max depth of the left subtree and right subtree and then add 1 to account for the current node
    // we recurse until we find null
    class BinaryTree {
        var root: TreeNode? = null
        class TreeNode(a: Int?) {
            var left: TreeNode? = null
            var right: TreeNode? = null
            var data: Int? = a
        }
    }

    fun setUpBinaryTreeAndPrintHeight() {
        var binaryTree = BinaryTree()

        binaryTree.root = BinaryTree.TreeNode(1)
        binaryTree.root?.left = BinaryTree.TreeNode(2)
        binaryTree.root?.right = BinaryTree.TreeNode(3)
        binaryTree.root?.left?.left = BinaryTree.TreeNode(4)
        binaryTree.root?.left?.right = BinaryTree.TreeNode(5)

        val heightOfTree = height(binaryTree.root)
        println("The height of the binary tree is $heightOfTree")
        // O(N) time because will visit every node in depth first manner
        // O(N) space because recursion call stack will take some space
    }

    fun height(node: BinaryTree.TreeNode?): Int {
        if (node == null) return 0

        val leftDepth = height(node.left)
        val rightDepth = height(node.right)

        return if (leftDepth > rightDepth) {
            leftDepth + 1
        } else {
            rightDepth + 1
        }
    }

    fun basicLoopStyles() {
        for (i in 0 ..10) {
            if (i % 2 == 0) {
                print("$i ")
            }
        }
        println()

        for (i in 0 until 10) {
            if (i % 2 == 0) {
                print("$i ")
            }
        }
        println()

        for (i in 10 downTo 0 step 2) {
            print("$i ")
        }

        val basicArr = arrayOf(0, 1, 2, 3, 7, -9)
        val zeroesArr: Array<Int> = Array(5) { 0 }

        println()
        zeroesArr.forEach {
            print("$it ")
        }

        println()
        basicArr.forEachIndexed { index, value ->
            print("$value at index $index, ")
        }

        println()
        val squaresArr: Array<Int> = Array(20) { it * it }
        squaresArr.forEach {
            print("$it ")
        }

        println()
        val v = BlubDataClass(5, 6)
        println(v.sum())
    }

    data class BlubDataClass(val a: Int, val b: Int) {

        // can't use abstract or sealed or inner. data class is simple class
        // data class may implement interface
        fun sum(): Int {
            return a + b
        }
    }

    // constructor keyword is redundant and could be removed
    class BlubHeaderCtor constructor(var a: Int) {
        var blub: Int

        // init is if you wanted constructor to additional set up or run set up code
        init {
            val random = 7.toString()
            blub = a
        }
    }

    open class SampleSecondaryCtors {
        constructor(a: Int, b: Int) {
            println("The first parameter value is : $a")
            println("The second parameter value is : $b")
        }

        constructor(a: Int) {
            println("The first parameter value is : $a")
        }

        // need final keyword for classes to be inherited and functions to override
        open fun doNothing() {
        }
    }

    class InheritSecondary(): SampleSecondaryCtors(5) {
        override fun doNothing() {

        }
    }

    fun variousListTypes() {
        val immutableList: List<Int> = listOf(1, 2, 3, 4)
        // immutableList[1] = 5    ERROR
        val mutableList = mutableListOf(1, 2, 3, 4)
        mutableList[1] = 5
        mutableList.add(6)
    }
}