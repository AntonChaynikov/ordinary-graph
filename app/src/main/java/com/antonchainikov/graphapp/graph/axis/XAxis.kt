package com.antonchainikov.graphapp.graph.axis

import android.graphics.Canvas
import android.graphics.Rect
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData
import org.joda.time.Instant

private const val LABELS_COUNT_DEFAULT = 6
private val DATE_FORMAT_DEFAULT = "dd.MM.yy HH:mm"

class XAxis(data: GraphData, dimensions: Dimensions): Axis(data, dimensions){
    private val intervals = ArrayList<Long>(LABELS_COUNT_DEFAULT)
    private var textWidthRect: Rect = Rect()

    override fun draw(canvas: Canvas) {

        val labelsCount = getLabelsCount()
        val intervals = getIntervals(labelsCount)

        intervals.forEach {
            drawLabel(dimensions.horizontalMapper.map(it, data.minXValue, data.maxXValue), it, canvas)
        }
    }

    private fun drawLabel(position: Float, value: Long, canvas: Canvas) {
        val text = Instant.ofEpochMilli(value).toDateTime().toString(DATE_FORMAT_DEFAULT)
        textPaint.getTextBounds(text, 0, text.length, textWidthRect)
        val width = textWidthRect.right - textWidthRect.left
        canvas.drawText(text, position - width/2, dimensions.getCenterY().toFloat(), textPaint)
    }

    private fun getLabelsCount(): Int {
        if (data.isEmpty()) return 1
        if (data.size() < LABELS_COUNT_DEFAULT) return data.size()
        return LABELS_COUNT_DEFAULT
    }

    private fun getIntervals(linesCount: Int): List<Long> {
        intervals.clear()
        val maxVal = data.maxXValue
        val minVal = data.minXValue

        val step = (maxVal - minVal) / (linesCount - 2)

        intervals.add(minVal)
        for (i in 1..linesCount - 2) intervals.add((minVal + i * step))
        intervals.add(maxVal)
        return intervals
    }
}
