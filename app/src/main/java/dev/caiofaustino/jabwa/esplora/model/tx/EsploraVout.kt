package dev.caiofaustino.jabwa.esplora.model.tx

data class EsploraVout(
    val scriptpubkey: String,
    val scriptpubkey_asm: String,
    val scriptpubkey_type: String,
    val scriptpubkey_address: String,
    val value: Int
)

/*
{
    "scriptpubkey": "76a914c825a1ecf2a6830c4401620c3a16f1995057c2ab88ac",
    "scriptpubkey_asm": "OP_DUP OP_HASH160 OP_PUSHBYTES_20 c825a1ecf2a6830c4401620c3a16f1995057c2ab OP_EQUALVERIFY OP_CHECKSIG",
    "scriptpubkey_type": "p2pkh",
    "scriptpubkey_address": "1KFHE7w8BhaENAswwryaoccDb6qcT6DbYY",
    "value": 661920454
}
 */