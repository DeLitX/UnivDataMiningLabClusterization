package org.example.clusterization.hierarchial

import org.example.clusterization.Clusterizer
import org.example.clusterization.Point
import org.example.clusterization.hierarchial.distances.DistanceCalculator

class HierarchicalClusterizer(
    private val clustersNumber: Int,
    private val distanceCalculator: DistanceCalculator,
) :
    Clusterizer {
    override fun clusterize(input: List<Point>): List<List<Point>> {
        val clusters = input.map { mutableListOf(it) }.toMutableList()

        while (clusters.size > clustersNumber) {
            var minDistance = Double.MAX_VALUE
            var closestPair = Pair(0, 1)
            for (i in clusters.indices) {
                for (j in (i + 1) until clusters.size) {
                    val distance = distanceCalculator.calculateDistance(clusters[i], clusters[j])
                    if (distance < minDistance) {
                        minDistance = distance
                        closestPair = Pair(i, j)
                    }
                }
            }

            val mergedCluster = clusters[closestPair.first] + clusters[closestPair.second]
            clusters.removeAt(closestPair.second)
            clusters[closestPair.first] = mergedCluster.toMutableList()
        }

        return clusters
    }
}
