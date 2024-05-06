package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point
import org.example.clusterization.DistanceMetric
import org.example.clusterization.EuclideanMetric
import kotlin.math.pow

class WardDistance(private val distanceMetric: DistanceMetric = EuclideanMetric) : DistanceCalculator {
    override fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double {
        val centroid1 = calculateCentroid(cluster1)
        val centroid2 = calculateCentroid(cluster2)
        val n1 = cluster1.size
        val n2 = cluster2.size
        val nTotal = n1 + n2
        val d1 = distanceMetric.calculateDistance(centroid1, centroid2)
        val d2 = (n1.toDouble() / nTotal) * calculateVariance(cluster1, centroid1)
        val d3 = (n2.toDouble() / nTotal) * calculateVariance(cluster2, centroid2)
        return d1.pow(2) + d2 + d3
    }

    private fun calculateCentroid(cluster: List<Point>): Point {
        val sumX = cluster.sumOf { it.x }
        val sumY = cluster.sumOf { it.y }
        val centroidX = sumX.toDouble() / cluster.size
        val centroidY = sumY.toDouble() / cluster.size
        return Point(centroidX.toInt(), centroidY.toInt())
    }

    private fun calculateVariance(cluster: List<Point>, centroid: Point): Double {
        var variance = 0.0
        for (point in cluster) {
            variance += distanceMetric.calculateDistance(point, centroid).pow(2)
        }
        return variance / cluster.size
    }
}