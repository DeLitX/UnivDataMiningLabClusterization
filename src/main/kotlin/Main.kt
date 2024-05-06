package org.example

import org.example.clusterization.*
import org.example.clusterization.hierarchial.HierarchicalClusterizer
import org.example.clusterization.hierarchial.distances.AverageLinkDistance
import org.example.clusterization.hierarchial.distances.SingleLinkDistance
import org.example.clusterization.hierarchial.distances.WardDistance

fun main() {
    // testAprioriAlgorithm()
    testClusterization()
}

fun testClusterization() {
    val data1 = listOf(
        Point(1, 1),
        Point(1, 8),
        Point(2, 2),
        Point(2, 5),
        Point(3, 1),
        Point(4, 3),
        Point(5, 2),
        Point(6, 1),
        Point(6, 8),
        Point(8, 6),
    )
    val data2 = listOf(
        Point(1, 1),
        Point(1, 2),
        Point(1, 5),
        Point(2, 8),
        Point(3, 7),
        Point(4, 2),
        Point(7, 5),
        Point(8, 3),
        Point(8, 7),
        Point(9, 3)
    )
    val data3 = listOf(
        Point(2, 1),
        Point(2, 4),
        Point(3, 5),
        Point(3, 6),
        Point(4, 1),
        Point(4, 9),
        Point(5, 4),
        Point(5, 6),
        Point(7, 2),
        Point(9, 8)
    )
    val data4 = listOf(
        Point(1, 4),
        Point(2, 5),
        Point(2, 8),
        Point(3, 4),
        Point(3, 5),
        Point(4, 1),
        Point(4, 7),
        Point(5, 6),
        Point(7, 6),
        Point(8, 1)
    )
    val clusterizer: Clusterizer =
        KMeansClusterizer(3)
        // KMediansClusterizer(3)
        // HierarchicalClusterizer(clustersNumber = 3, WardDistance())
        // ClosestNeighborClusterizer(2.0)
        // DBSCANClusterizer(2.5, 2)
    println("1:")
    printClusters(clusterizer.clusterize(data1))
    println()
    println("2:")
    printClusters(clusterizer.clusterize(data2))
    println()
    println("3:")
    printClusters(clusterizer.clusterize(data3))
    println()
    println("4:")
    printClusters(clusterizer.clusterize(data4))
}

private fun printClusters(clusters:List<List<Point>>){
    for (cluster in clusters) {
        println(cluster.joinToString(separator = ", ", prefix = "", postfix = ""))
    }
}

fun testAprioriAlgorithm() {
    val dataFromExample = listOf(
        setOf("a", "b", "c", "d", "e"),
        setOf("a", "c", "d", "f"),
        setOf("a", "b", "c", "d", "e", "g"),
        setOf("c", "d", "e", "f"),
        setOf("c", "e", "f", "h"),
        setOf("d", "e", "f"),
        setOf("a", "f", "g"),
        setOf("d", "e", "g", "h"),
        setOf("a", "b", "c", "f"),
        setOf("c", "d", "e", "h"),
    )
    val data1 = listOf(
        setOf("a", "b", "c", "d"),
        setOf("b", "c", "d"),
        setOf("a", "e", "f", "g", "h"),
        setOf("b", "c", "d", "e", "g", "j"),
        setOf("b", "c", "d", "e", "f"),
        setOf("a", "f", "g"),
        setOf("a", "i", "j"),
        setOf("a", "b", "e", "h"),
        setOf("f", "g", "h", "i", "j"),
        setOf("e", "f", "h")
    )
    val data2 = listOf(
        setOf("a", "b", "c", "f"),
        setOf("b", "c", "f"),
        setOf("b", "d", "e", "g", "h"),
        setOf("b", "c", "e", "f", "g", "j"),
        setOf("b", "c", "d", "e", "f"),
        setOf("a", "d", "g"),
        setOf("a", "i", "j"),
        setOf("a", "b", "e", "h"),
        setOf("d", "g", "h", "i", "j"),
        setOf("d", "e", "h")
    )
    val data3 = listOf(
        setOf("a", "b", "c", "d", "e", "f"),
        setOf("b", "c", "d"),
        setOf("b", "e", "g", "h"),
        setOf("b", "c", "g", "j"),
        setOf("b", "c", "d", "e", "f"),
        setOf("a", "e", "f"),
        setOf("a", "i", "j"),
        setOf("a", "b", "c", "e", "h"),
        setOf("e", "b", "h", "i", "j"),
        setOf("b", "f", "h")
    )
    val minSupport = 0.4f
    println("Example:")
    presentAprioriAlgorithm(dataFromExample, minSupport)
    println("1:")
    presentAprioriAlgorithm(data1, minSupport)
    println("2:")
    presentAprioriAlgorithm(data2, minSupport)
    println("3:")
    presentAprioriAlgorithm(data3, minSupport)
}

private fun presentAprioriAlgorithm(data: List<Set<String>>, minSupport: Float) {
    val result = AprioriAlgorithm().buildRules(data, minSupport)
    val presentableString = result.joinToString(separator = "\n") { set ->
        set.joinToString(separator = ", ", prefix = "{", postfix = "}")
    }
    println(presentableString)
}