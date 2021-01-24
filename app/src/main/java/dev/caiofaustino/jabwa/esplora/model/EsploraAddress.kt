package dev.caiofaustino.jabwa.esplora.model

import dev.caiofaustino.jabwa.esplora.model.address.EsploraStats

/**
 * Available fields: address/scripthash, chain_stats and mempool_stats.
 *
 * {chain,mempool}_stats each contain an object with tx_count, funded_txo_count, funded_txo_sum, spent_txo_count and spent_txo_sum.
 */
data class EsploraAddress(
    val address: String,
    val chain_stats: EsploraStats,
    val mempool_stats: EsploraStats
)

/* Example
{
    "address": "1E6AjC9iaUp4A69k1jEPsNPvUT5eZrEGcZ",
    "chain_stats": {
        "funded_txo_count": 4,
        "funded_txo_sum": 926177,
        "spent_txo_count": 3,
        "spent_txo_sum": 731565,
        "tx_count": 7
    },
    "mempool_stats": {
        "funded_txo_count": 0,
        "funded_txo_sum": 0,
        "spent_txo_count": 0,
        "spent_txo_sum": 0,
        "tx_count": 0
    }
}
 */