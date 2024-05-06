package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point
import org.example.clusterization.DistanceMetric
import org.example.clusterization.EuclideanMetric

class MedianLinkDistance(private val distanceMetric: DistanceMetric = EuclideanMetric) : DistanceCalculator {
    override fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double {
        val distances = mutableListOf<Double>()
        for (point1 in cluster1) {
            for (point2 in cluster2) {
                distances.add(distanceMetric.calculateDistance(point1, point2))
            }
        }
        distances.sort()
        return if (distances.size % 2 == 0) {
            val mid = distances.size / 2
            (distances[mid] + distances[mid - 1]) / 2
        } else {
            distances[distances.size / 2]
        }
    }
}