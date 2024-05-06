package org.example.clusterization

interface Clusterizer {
    fun clusterize(input: List<Point>): List<List<Point>>
}