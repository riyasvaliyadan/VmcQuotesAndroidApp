package com.vmcplus.quotes.data

data class Quote(val quote: String, val author: String)
{
    override fun toString() = "\"$quote\""
}