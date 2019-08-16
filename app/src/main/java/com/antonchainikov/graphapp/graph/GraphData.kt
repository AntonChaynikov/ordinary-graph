package com.antonchainikov.graphapp.graph

import org.joda.time.LocalDateTime
import java.util.*

class GraphData(val points:List<GraphPoint>) {

    companion object {
        @JvmStatic
        fun default() = getDefault() //GraphData(Collections.emptyList())

            private fun getDefault(): GraphData {
            val data = ArrayList<GraphPoint>(5)
            val dateNow = LocalDateTime.now()
            data.apply {
                add(GraphPoint(dateNow.withDayOfMonth(5).toDate().time, 50))
                add(GraphPoint(dateNow.withDayOfMonth(7).toDate().time, 100))
                add(GraphPoint(dateNow.withDayOfMonth(8).toDate().time, 150))
                add(GraphPoint(dateNow.withDayOfMonth(10).toDate().time, 70))
                add(GraphPoint(dateNow.withDayOfMonth(11).toDate().time, 200))
            }
            return GraphData(data)
        }

    }

    val xValues: List<Long> = points.map { it.date }
    val yValues: List<Long> = points.map { it.value }
    val maxXValue: Long
    val minXValue: Long
    val maxYValue: Long
    val minYValue: Long

    init {
        val maxX = xValues.max()
        maxXValue = maxX ?: 0

        val minX = xValues.min()
        minXValue = minX ?: 0

        val maxY = yValues.max()
        maxYValue = maxY ?: 0

        minYValue = 0L
    }

    fun size() = points.size
    fun isEmpty() = points.isEmpty()
}