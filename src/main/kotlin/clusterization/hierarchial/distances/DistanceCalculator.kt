package org.example.clusterization.hierarchial.distances

import org.example.clusterization.Point

interface DistanceCalculator {
    fun calculateDistance(cluster1: List<Point>, cluster2: List<Point>): Double
}