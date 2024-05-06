package org.example.clusterization

import kotlin.math.sqrt

object EuclideanMetric : DistanceMetric {
    override fun calculateDistance(point1: Point, point2: Point): Double {
        val deltaX = point1.x - point2.x
        val deltaY = point1.y - point2.y
        return sqrt((deltaX * deltaX + deltaY * deltaY).toDouble())
    }
}