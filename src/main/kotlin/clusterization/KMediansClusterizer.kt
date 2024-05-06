package org.example.clusterization

class KMediansClusterizer(
    private val k: Int,
    private val distanceMetric: DistanceMetric = EuclideanMetric,
) : Clusterizer {
    override fun clusterize(input: List<Point>): List<List<Point>> {
        if (input.size <= k) {
            return listOf(input)
        }

        var centroids = input.shuffled().take(k)

        var clusters: List<List<Point>>

        do {
            clusters = MutableList(k) { mutableListOf() }

            for (point in input) {
                val nearestCentroid = centroids.minByOrNull { distanceMetric.calculateDistance(point, it) }!!
                val centroidIndex = centroids.indexOf(nearestCentroid)
                clusters[centroidIndex].add(point)
            }

            centroids = clusters.map { calculateMedian(it) }
        } while (!centroids.all { it in centroids })

        return clusters
    }

    private fun calculateMedian(points: List<Point>): Point {
        val sortedByX = points.sortedBy { it.x }
        val sortedByY = points.sortedBy { it.y }
        val medianIndex = points.size / 2
        return Point(sortedByX[medianIndex].x, sortedByY[medianIndex].y)
    }
}