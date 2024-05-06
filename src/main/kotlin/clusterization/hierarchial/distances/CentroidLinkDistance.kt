package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point
import org.example.clusterization.DistanceMetric
import org.example.clusterization.EuclideanMetric

class CentroidLinkDistance(private val distanceMetric: DistanceMetric = EuclideanMetric) : DistanceCalculator {
    override fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double {
        val centroid1 = calculateCentroid(cluster1)
        val centroid2 = calculateCentroid(cluster2)
        return distanceMetric.calculateDistance(centroid1, centroid2)
    }

    private fun calculateCentroid(cluster: List<Point>): Point {
        val sumX = cluster.sumOf { it.x }
        val sumY = cluster.sumOf { it.y }
        val centroidX = sumX.toDouble() / cluster.size
        val centroidY = sumY.toDouble() / cluster.size
        return Point(centroidX.toInt(), centroidY.toInt())
    }
}