package org.example.clusterization

class ClosestNeighborClusterizer(
    private val threshold: Double,
    private val distanceMetric: DistanceMetric = EuclideanMetric
) : Clusterizer {
    override fun clusterize(input: List<Point>): List<List<Point>> {
        val clusters = mutableListOf<MutableList<Point>>()

        for (point in input) {
            var closestClusterIndex = -1
            var minDistance = Double.MAX_VALUE

            for ((clusterIndex, cluster) in clusters.withIndex()) {
                for (clusterPoint in cluster) {
                    val distance = distanceMetric.calculateDistance(point, clusterPoint)
                    if (distance < minDistance) {
                        closestClusterIndex = clusterIndex
                        minDistance = distance
                    }
                }
            }

            if (minDistance <= threshold && closestClusterIndex != -1) {
                clusters[closestClusterIndex].add(point)
            } else {
                val newCluster = mutableListOf(point)
                clusters.add(newCluster)
            }
        }

        return clusters
    }
}