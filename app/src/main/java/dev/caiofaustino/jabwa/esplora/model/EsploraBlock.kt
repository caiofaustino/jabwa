package dev.caiofaustino.jabwa.esplora.model

/**
 * ## Block format
 *
 * - `id`
 * - `height`
 * - `version`
 * - `timestamp`
 * - `bits`
 * - `nonce`
 * - `difficulty`
 * - `merkle_root`
 * - `tx_count`
 * - `size`
 * - `weight`
 * - `previousblockhash`
 * - `mediantime` (median time-past)
 * - *(Elements only)*
 * - `proof`
 * - `challenge`
 * - `challenge_asm`
 * - `solution`
 * - `solution_asm`
 */
data class EsploraBlock(
    val id: String,
    val height: Int,
    val version: Int,
    val timestamp: Int,
    val bits: Int,
    val nonce: Int,
    val difficulty: Int,
    val merkle_root: String,
    val tx_count: Int,
    val size: Int,
    val weight: Int,
    val previousblockhash: String,
    val mediantime: Int
)

/* Example
{
    "id": "00000000000000000001e0f6f25f2b1b83001408706cbc9c76d5f34af5bc2ff3",
    "height": 646899,
    "version": 939515904,
    "timestamp": 1599346040,
    "tx_count": 2740,
    "size": 1364027,
    "weight": 3993290,
    "merkle_root": "1d81d1510c78040d00c78914e7012f2687028b02a36139b01c66fbe651b1fd0e",
    "previousblockhash": "0000000000000000000116838809609f377d0415198997189dafdb23df47f603",
    "nonce": 1779996916,
    "bits": 386926570,
    "difficulty": 17557993035167
},
 */