package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point
import org.example.clusterization.DistanceMetric
import org.example.clusterization.EuclideanMetric

class AverageLinkDistance(private val distanceMetric: DistanceMetric = EuclideanMetric) : DistanceCalculator {
    override fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double {
        var totalDistance = 0.0
        var count = 0
        for (point1 in cluster1) {
            for (point2 in cluster2) {
                totalDistance += distanceMetric.calculateDistance(point1, point2)
                count++
            }
        }
        return totalDistance / count
    }
}