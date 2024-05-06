package org.example.clusterization

interface DistanceMetric {
    fun calculateDistance(point1: Point, point2: Point): Double
}