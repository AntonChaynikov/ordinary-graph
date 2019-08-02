package com.antonchainikov.graphapp.graph

interface LineMapper {
    fun map(value: Long, minValue: Long, maxValue: Long): Float
}