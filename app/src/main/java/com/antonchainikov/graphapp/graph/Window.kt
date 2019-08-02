package com.antonchainikov.graphapp.graph

object Window {
    var height: Float = 0f
        private set(value) {
            field = value
        }
    var width: Float = 0f
        private set(value) {
            field = value
        }

    fun dip(px: Float) = px
}