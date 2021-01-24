package dev.caiofaustino.jabwa.esplora.model.tx

data class EsploraVin(
    val txid: String,
    val vout: String,
    val prevout: EsploraVout?,
    val scriptsig: String,
    val scriptsig_asm: String,
    val witness: List<String>,
    val is_coinbase: Boolean,
    val sequence: Int
)

/*
{
    "txid": "0000000000000000000000000000000000000000000000000000000000000000",
    "vout": 4294967295,
    "prevout": null,
    "scriptsig": "03e3220a2cfabe6d6de4bb90a06a859126450e276d0622838e19eec2caa8003150a091cd78a88592c210000000f09f909f082f4632506f6f6c2f114d696e6564206279207375636f726932300000000000000000000000000000000000000500a1ba0700",
    "scriptsig_asm": "OP_PUSHBYTES_3 e3220a OP_PUSHBYTES_44 fabe6d6de4bb90a06a859126450e276d0622838e19eec2caa8003150a091cd78a88592c210000000f09f909f OP_PUSHBYTES_8 2f4632506f6f6c2f OP_PUSHBYTES_17 4d696e6564206279207375636f72693230 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_0 OP_PUSHBYTES_5 00a1ba0700",
    "witness": [
        "0000000000000000000000000000000000000000000000000000000000000000"
    ],
    "is_coinbase": true,
    "sequence": 0
}
 */

/*
{
    "txid": "0cc67786b455a17a0d22e7054b74bc50fea65e9a231bd2fb29940524f44806ac",
    "vout": 4,
    "prevout": {
        "scriptpubkey": "0014a20415a1ebad82240f86c5ef54c1f82f14112836",
        "scriptpubkey_asm": "OP_0 OP_PUSHBYTES_20 a20415a1ebad82240f86c5ef54c1f82f14112836",
        "scriptpubkey_type": "v0_p2wpkh",
        "scriptpubkey_address": "bc1q5gzptg0t4kpzgruxchh4fs0c9u2pz2pkwlv26r",
        "value": 16674553
    },
    "scriptsig": "",
    "scriptsig_asm": "",
    "witness": [
        "3044022078dfc60e4e42a8fced9d28e7c11a2790f380d4379bd7dcb1826dba76620af749022051f6a629ddcf5508ee933caa8293b6c302cabd289d4e0c7fd3b32e15f095872d01",
        "03e4ffeb1f3471bba30795c9763ab0ef706fbfd089dc12545b0982eed324256c60"
    ],
    "is_coinbase": false,
    "sequence": 4294967294
}
 */