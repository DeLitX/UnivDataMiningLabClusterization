package org.example.clusterization

class KMeansClusterizer(
    private val k: Int,
    private val distanceMetric: DistanceMetric = EuclideanMetric,
) : Clusterizer {
    override fun clusterize(input: List<Point>): List<List<Point>> {
        val centroids = initializeCentroids(input, k)

        var prevClusters: List<List<Point>>? = null
        var clusters = emptyList<List<Point>>()

        while (clusters != prevClusters) {
            prevClusters = clusters

            clusters = input.groupBy { point ->
                centroids.minByOrNull { centroid -> distanceMetric.calculateDistance(point, centroid) }
                    ?: error("Empty centroid list")
            }.values.toList()

            centroids.forEachIndexed { index, _ ->
                val clusterPoints = clusters[index]
                if (clusterPoints.isNotEmpty()) {
                    val sumX = clusterPoints.map { it.x }.sum()
                    val sumY = clusterPoints.map { it.y }.sum()
                    centroids[index] = Point(sumX / clusterPoints.size, sumY / clusterPoints.size)
                }
            }
        }

        return clusters
    }

    private fun initializeCentroids(input: List<Point>, k: Int): MutableList<Point> {
        val shuffledInput = input.shuffled()
        return shuffledInput.subList(0, k).toMutableList()
    }
}