package dev.caiofaustino.jabwa.esplora.model.address

data class EsploraStats(
    val funded_txo_count: Int,
    val funded_txo_sum: Int,
    val spent_txo_count: Int,
    val spent_txo_sum: Int,
    val tx_count: Int
)

/*
{
    "funded_txo_count": 4,
    "funded_txo_sum": 926177,
    "spent_txo_count": 3,
    "spent_txo_sum": 731565,
    "tx_count": 7
},
 */