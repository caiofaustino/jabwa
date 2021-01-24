package dev.caiofaustino.jabwa.esplora.model.tx

data class EsploraTxStatus(
    val confirmed: Boolean,
    val block_height: Int,
    val block_hash: String,
    val block_time: Int
)

/*
"status": {
    "confirmed": true,
    "block_height": 664291,
    "block_hash": "0000000000000000000743a86576078ef7744fdee165bf0f7a317a9b732f80e0",
    "block_time": 1609678654
}
 */