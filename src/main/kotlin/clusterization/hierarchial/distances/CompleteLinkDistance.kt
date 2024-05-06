package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point
import org.example.clusterization.DistanceMetric
import org.example.clusterization.EuclideanMetric

class CompleteLinkDistance(private val distanceMetric: DistanceMetric = EuclideanMetric) : DistanceCalculator {
    override fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double {
        var maxDistance = Double.MIN_VALUE
        for (point1 in cluster1) {
            for (point2 in cluster2) {
                val distance = distanceMetric.calculateDistance(point1, point2)
                if (distance > maxDistance) {
                    maxDistance = distance
                }
            }
        }
        return maxDistance
    }
}