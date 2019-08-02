package com.antonchainikov.graphapp.graph

import java.util.*

class GraphData(private val points:List<GraphPoint>) {

    companion object {
        @JvmStatic
        fun default() = GraphData(Collections.emptyList())
    }

    val xValues: List<Long> = points.map { it.date }
    val yValues: List<Long> = points.map { it.value }
    val maxXValue: Long
    val minXValue: Long
    val maxYValue: Long

    init {
        val maxX = xValues.max()
        maxXValue = maxX ?: 0

        val minX = xValues.min()
        minXValue = minX ?: 0

        val maxY = yValues.max()
        maxYValue = maxY ?: 0
    }

    fun size() = points.size
    fun isEmpty() = points.isEmpty()
}