package dev.caiofaustino.jabwa.esplora.model

import dev.caiofaustino.jabwa.esplora.model.address.EsploraStats

/**
 * Available fields: address/scripthash, chain_stats and mempool_stats.
 *
 * {chain,mempool}_stats each contain an object with tx_count, funded_txo_count, funded_txo_sum, spent_txo_count and spent_txo_sum.
 */
data class EsploraScriptHash(
    val scripthash: String,
    val chain_stats: EsploraStats,
    val mempool_stats: EsploraStats
) {
}