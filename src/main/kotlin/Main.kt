package org.example

fun main() {
    println("Hello World!")
    val tester = intArrayOf(1, -2, 3, 9, 5, 4)
    println(maxDiffTwoElements(tester))

    println(isPrime(439))
    println(isPrime(7))
    println(isPrime(241))
    println(isPrime(2777))
    println(isPrime(7919))


    println(isPrime(4))
    println(isPrime(100))
    println(isPrime(156))
    println(isPrime(2016))
    println(isPrime(7918))
}

/*
fun main() = runBlocking {
    val totalTime = measureTimeMillis {
        val delay1 = delayFunction1()
        val delay2 = delayFunction2()
        println("Total time taken: ${delay1 + delay2} ms")
    }
    println("Done")
}


suspend fun delayFunction1(): Long {
    val delayTime = 1000L
    delay(delayTime)
    return delayTime
}

suspend fun delayFunction2(): Long {
    val delayTime = 500L
    delay(delayTime)
    return delayTime
}


fun main() = runBlocking {
    val totalTime = measureTimeMillis {
        val delay1 = async { delayFunction1() }
        val delay2 = async { delayFunction2() }
        val result1 = delay1.await()
        val result2 = delay2.await()
        println("Total time taken: ${result1 + result2} ms")
    }
    println("Done")
}
*/

fun maxDiffTwoElements(arr: IntArray): Int {
    var min = arr[0]
    var max = arr[0]
    arr.forEach {
        min = Math.min(it, min)
        max = Math.max(it, max)
    }
    return max - min
}

fun isPrime(n: Int): Boolean {
    if (n == 1) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}

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