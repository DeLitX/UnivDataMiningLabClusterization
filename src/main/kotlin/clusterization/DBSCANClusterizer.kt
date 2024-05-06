package org.example.clusterization


class DBSCANClusterizer(
    private val epsilon: Double,
    private val minPoints: Int,
    private val distanceMetric: DistanceMetric = EuclideanMetric,
) : Clusterizer {
    override fun clusterize(input: List<Point>): List<List<Point>> {
        val visited = HashSet<Point>()
        val clusters = mutableListOf<MutableList<Point>>()

        for (point in input) {
            if (point in visited) continue
            visited.add(point)

            val neighbors = findNeighbors(point, input)
            if (neighbors.size < minPoints) {
                continue
            }

            val cluster = mutableListOf<Point>()
            expandCluster(point, neighbors, cluster, visited, input)
            clusters.add(cluster)
        }

        return clusters
    }

    private fun findNeighbors(point: Point, points: List<Point>): List<Point> {
        return points.filter { other ->
            distanceMetric.calculateDistance(point, other) <= epsilon
        }
    }

    private fun expandCluster(
        point: Point,
        neighbors: List<Point>,
        cluster: MutableList<Point>,
        visited: HashSet<Point>,
        points: List<Point>
    ) {
        cluster.add(point)

        for (neighbor in neighbors) {
            if (neighbor !in visited) {
                visited.add(neighbor)
                val neighborNeighbors = findNeighbors(neighbor, points)
                if (neighborNeighbors.size >= minPoints) {
                    expandCluster(neighbor, neighborNeighbors, cluster, visited, points)
                }
            }
            if (neighbor !in cluster) {
                cluster.add(neighbor)
            }
        }
    }
}