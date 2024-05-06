package org.example.clusterization

data class Point(
    val x: Int,
    val y: Int,
){
    override fun toString(): String = "($x, $y)"
}