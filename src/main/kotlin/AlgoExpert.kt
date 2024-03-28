package org.example

class AlgoExpert {

    // Algo Expert - Sorted Square Array
    // write a function that takes in an array of sorted integers in ascending order. Return array
    // of same length with the squares of the original in ascending order

    // key is that a negative number squared becomes positive
    // easy way is to just square all numbers in a loop. Sort again after loop to account for negatives
    fun sortedSquaredArray(array: List<Int>): List<Int> {

        // largest negative value will be at beginning of array. largest positive at end of array
        // these values compete to be at the end of the output array (largest square)

        // use starting and ending pointer based on array index. move pointer based of whether start or end abs value
        // is larger. stop when output array is full. we need a reverse order loop. O(N) time and space

        var ans: Array<Int> = Array(array.size) { 0 }
        var smallerIdx = 0
        var largerIdx = array.size - 1

        for (idx in array.size - 1 downTo 0) {
            val start = Math.abs(array[smallerIdx])
            val end = Math.abs(array[largerIdx])
            if (start > end) {
                ans[idx] = start * start
                smallerIdx++
            } else {
                ans[idx] = end * end
                largerIdx--
            }
        }
        return ans.toMutableList()
    }

    // Algo Expert find tournament winner
    fun tournamentWinner(competitions: List<List<String>>, results: List<Int>): String {
        // Write your code here.

        /* competitions = [       homeTeam, awayTeam
        [Java, C#],
        [C#, Python],
        [Python, Java]
        ]

        results = [0, 0, 1]   0 awayTeam won. 1 homeTeam won
        return winner of tournament

        no ties in rounds. only one team will win the tournament. Each team will compete against each other only once
        */
        val teamToWins = mutableMapOf<String, Int>()

        competitions.forEachIndexed { idx, match ->
            val matchResult = results[idx]
            var winningTeam = match[0] // init home team won
            if (matchResult == 0) { // result says away team won
                winningTeam = match[1]
            }

            if (teamToWins.containsKey(winningTeam)) {
                teamToWins[winningTeam] = teamToWins[winningTeam]!! + 1
            } else {
                teamToWins[winningTeam] = 1
            }
        }

        var winner = ""
        var mostWins = -1
        teamToWins.forEach { entry ->
            if (entry.value > mostWins) {
                mostWins = entry.value
                winner = entry.key
            }
        }

        return winner
    }

    // Greedy Algorithm find min wait time
    fun minimumWaitingTime(queries: MutableList<Int>): Int {
        // queries is positive integers showing time each query takes to execute
        // only 1 query can execute at a time. but they can go in any order
        // a query's waiting time is the amount of time it must wait before starting

        // write a function the returns minimum total wait time for all queries to execute
        // ex [1, 4, 5]  given an order [5, 1, 4].  NOT NESSECARILY THE MIN
        // 5 has no wait time. 1 has 5s wait time. 4 has (5 + 1) = 6 wait time. So 11 total

        // what if order was 1, 4, 5
        // 1 has no wait time. 4 has 1s wait time. 5 has (1 + 4) = 5 wait time. So 6 total

        // ex [3, 2, 1, 2, 6] (Not Min)
        // for minimum wait time we execute fastest queries first. so sort beforehand
        // sort 1, 2, 2, 3, 6
        // as we iterate. first is 1. all remaining queries (4) need to wait 1s. total = 4x1 = 4
        // for 2. all 3 queries left need to wait 2s. add 3x2 to total. total = 10
        // for next 2. 2 queries left need to wait 2s. add 2x2 = 14
        // for 3. 1 queries left. needs to wait 3s. add 1x3 = 17

        queries.sort()
        var waitTime = 0

        // iterate through. look at queries left to execute
        queries.forEachIndexed { idx, time ->
            val queriesLeft = queries.size - (idx + 1)
            waitTime += queriesLeft * time
        }
        return waitTime
    }
}