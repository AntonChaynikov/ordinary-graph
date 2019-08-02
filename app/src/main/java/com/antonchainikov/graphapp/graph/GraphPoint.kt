package com.antonchainikov.graphapp.graph

import org.joda.time.LocalDateTime

class GraphPoint(val date: Long, val value: Long) {

    private val DATE_FORMAT_DEFAULT = "dd.MM.yy HH:mm"

    fun dateAsString() = dateAsString(DATE_FORMAT_DEFAULT)

    fun dateAsString(format: String) = LocalDateTime(date).toString(format)
}