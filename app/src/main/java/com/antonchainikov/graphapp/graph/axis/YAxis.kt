package com.antonchainikov.graphapp.graph.axis

import android.graphics.Canvas
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

private const val TEXT_MARGIN = 16
private const val LINES_COUNT_DEFAULT = 6

class YAxis(data: GraphData, dimensions: Dimensions) : Axis(data, dimensions) {
    private val intervals = ArrayList<Long>(LINES_COUNT_DEFAULT)

    override fun draw(canvas: Canvas) {
        val linesCount = getLinesCount()
        val intervals = getIntervals(linesCount)
        intervals.forEach {
            drawLevel(dimensions.verticalMapper.map(it, 0L, data.maxYValue), it, canvas)
        }
    }

    private fun drawLevel(height: Float, value: Long, canvas: Canvas) {
        if (height - TEXT_MARGIN > dimensions.getTop()) {
            canvas.drawLine(
                dimensions.getStart().toFloat(),
                height,
                dimensions.getEnd().toFloat(),
                height,
                axisPaint
            )

            canvas.drawText(
                value.toString(),
                (dimensions.getStart() + TEXT_MARGIN).toFloat(),
                height - TEXT_MARGIN,
                textPaint
            )
        }
    }

    private fun getLinesCount(): Int {
        if (data.isEmpty()) return 1
        if (data.size() < LINES_COUNT_DEFAULT) return data.size() + 1
        return LINES_COUNT_DEFAULT
    }

    private fun getIntervals(linesCount: Int): List<Long> {
        intervals.clear()
        val step = data.maxYValue / linesCount
        for (i in 0..linesCount) intervals.add((i * step))
        return intervals
    }
}
