package com.example.lab05danp.domain.logger

interface IProductLogger {
    fun logProductVisit(productId: String, productName: String)
    fun getMostVisitedProducts(): Map<String, Int>
}

class ProductLoggerImpl : IProductLogger {
    // Key: ProductName (or ID), Value: Visit Count
    private val visitCounts = mutableMapOf<String, Int>()

    override fun logProductVisit(productId: String, productName: String) {
        val count = visitCounts.getOrDefault(productName, 0)
        visitCounts[productName] = count + 1
    }

    override fun getMostVisitedProducts(): Map<String, Int> {
        // Return sorted by most visited
        return visitCounts.entries
            .sortedByDescending { it.value }
            .associate { it.key to it.value }
    }
}
