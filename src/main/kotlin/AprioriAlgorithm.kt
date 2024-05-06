package org.example

class AprioriAlgorithm {

    fun buildRules(transactions: List<Set<String>>, minSupport: Float): List<Set<Set<String>>> {
        val supportThreshold = (minSupport * transactions.size).toInt()
        var currentFrequentItems = findFrequentOneItemSets(transactions, supportThreshold)
        val allFrequentItemSets = mutableListOf<Set<Set<String>>>()

        while (currentFrequentItems.isNotEmpty()) {
            allFrequentItemSets.add(currentFrequentItems)
            currentFrequentItems = generateCandidates(currentFrequentItems)
                .filter { candidate ->
                    transactions.count { transaction -> candidate.all { item -> item in transaction } } >= supportThreshold
                }.toSet()
        }

        return allFrequentItemSets
    }

    private fun findFrequentOneItemSets(transactions: List<Set<String>>, supportThreshold: Int): Set<Set<String>> {
        val itemFrequency = mutableMapOf<String, Int>()
        transactions.forEach { transaction ->
            transaction.forEach { item ->
                itemFrequency[item] = itemFrequency.getOrDefault(item, 0) + 1
            }
        }
        return itemFrequency.filter { it.value >= supportThreshold }
            .map { setOf(it.key) }.toSet()
    }

    private fun generateCandidates(previousFrequentItems: Set<Set<String>>): Set<Set<String>> {
        val newCandidates = mutableSetOf<Set<String>>()
        previousFrequentItems.forEach { set1 ->
            previousFrequentItems.forEach { set2 ->
                val union = set1.union(set2)
                if (union.size == set1.size + 1) {
                    newCandidates.add(union)
                }
            }
        }
        return newCandidates
    }
}