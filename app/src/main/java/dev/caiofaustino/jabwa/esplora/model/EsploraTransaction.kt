package dev.caiofaustino.jabwa.esplora.model

import dev.caiofaustino.jabwa.esplora.model.tx.EsploraTxStatus
import dev.caiofaustino.jabwa.esplora.model.tx.EsploraVin
import dev.caiofaustino.jabwa.esplora.model.tx.EsploraVout

/**
 * ## Transaction format

- `txid`
- `version`
- `locktime`
- `size`
- `weight`
- `fee`
- `vin[]`
  - `txid`
  - `vout`
  - `is_coinbase`
  - `scriptsig`
  - `scriptsig_asm`
  - `inner_redeemscript_asm`
  - `inner_witnessscript_asm`
  - `sequence`
  - `witness[]`
  - `prevout` (previous output in the same format as in `vout` below)
  - *(Elements only)*
  - `is_pegin`
  - `issuance` (available for asset issuance transactions, `null` otherwise)
    - `asset_id`
    - `is_reissuance`
    - `asset_id`
    - `asset_blinding_nonce`
    - `asset_entropy`
    - `contract_hash`
    - `assetamount` or `assetamountcommitment`
    - `tokenamount` or `tokenamountcommitment`
- `vout[]`
  - `scriptpubkey`
  - `scriptpubkey_asm`
  - `scriptpubkey_type`
  - `scriptpubkey_address`
  - `value`
  - *(Elements only)*
  - `valuecommitment`
  - `asset` or `assetcommitment`
  - `pegout` (available for peg-out outputs, `null` otherwise)
    - `genesis_hash`
    - `scriptpubkey`
    - `scriptpubkey_asm`
    - `scriptpubkey_address`
- `status`
  - `confirmed` (boolean)
  - `block_height` (available for confirmed transactions, `null` otherwise)
  - `block_hash` (available for confirmed transactions, `null` otherwise)
  - `block_time` (available for confirmed transactions, `null` otherwise)
 */
data class EsploraTransaction(
    val txid: String,
    val version: Int,
    val locktime: Int,
    val vin: List<EsploraVin>,
    val vout: List<EsploraVout>,
    val size: Int,
    val weight: Int,
    val fee: Int,
    val status: EsploraTxStatus
)

/* Example
{
    "txid": "a71396dffd52174b16c082e77cf939049cef78ce106ac3fc14595ba90ee17d84",
    "version": 1,
    "locktime": 1057597921,
    "vin": [
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
    ],
    "vout": [
        {
            "scriptpubkey": "76a914c825a1ecf2a6830c4401620c3a16f1995057c2ab88ac",
            "scriptpubkey_asm": "OP_DUP OP_HASH160 OP_PUSHBYTES_20 c825a1ecf2a6830c4401620c3a16f1995057c2ab OP_EQUALVERIFY OP_CHECKSIG",
            "scriptpubkey_type": "p2pkh",
            "scriptpubkey_address": "1KFHE7w8BhaENAswwryaoccDb6qcT6DbYY",
            "value": 661920454
        },
        {
            "scriptpubkey": "6a24aa21a9ed941b91eae6f46148de58bf3f67e012d81f42f1f8e49356009499602b92629292080000000000000000",
            "scriptpubkey_asm": "OP_RETURN OP_PUSHBYTES_36 aa21a9ed941b91eae6f46148de58bf3f67e012d81f42f1f8e49356009499602b92629292 OP_PUSHBYTES_8 0000000000000000",
            "scriptpubkey_type": "op_return",
            "value": 0
        },
        {
            "scriptpubkey": "6a4c2952534b424c4f434b3a9d5f796772ccd1e4bbd23bc0c0684ccb4697cbf5a41f936fa6c0931f002db4ff",
            "scriptpubkey_asm": "OP_RETURN OP_PUSHDATA1 52534b424c4f434b3a9d5f796772ccd1e4bbd23bc0c0684ccb4697cbf5a41f936fa6c0931f002db4ff",
            "scriptpubkey_type": "op_return",
            "value": 0
        },
        {
            "scriptpubkey": "6a24b9e11b6df56f7291f9d189b8ce3d1ac2b36472e0f11b245c7b8fa37532c3a41f0a3e9fb8",
            "scriptpubkey_asm": "OP_RETURN OP_PUSHBYTES_36 b9e11b6df56f7291f9d189b8ce3d1ac2b36472e0f11b245c7b8fa37532c3a41f0a3e9fb8",
            "scriptpubkey_type": "op_return",
            "value": 0
        }
    ],
    "size": 377,
    "weight": 1400,
    "fee": 0,
    "status": {
        "confirmed": true,
        "block_height": 664291,
        "block_hash": "0000000000000000000743a86576078ef7744fdee165bf0f7a317a9b732f80e0",
        "block_time": 1609678654
    }
}
 */

/*
{
    "txid": "3400810f155b037fa011fc8db2d0a0f99f2f7d113c944c7d7e19242f7832ba20",
    "version": 2,
    "locktime": 664291,
    "vin": [
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
    ],
    "vout": [
        {
            "scriptpubkey": "76a9148f93d5c6eb056e878f71b3e2c86b1cac64118e4f88ac",
            "scriptpubkey_asm": "OP_DUP OP_HASH160 OP_PUSHBYTES_20 8f93d5c6eb056e878f71b3e2c86b1cac64118e4f OP_EQUALVERIFY OP_CHECKSIG",
            "scriptpubkey_type": "p2pkh",
            "scriptpubkey_address": "1E6AjC9iaUp4A69k1jEPsNPvUT5eZrEGcZ",
            "value": 194612
        },
        {
            "scriptpubkey": "76a914b5c0b964c920c2145aece86a63415019f944f50488ac",
            "scriptpubkey_asm": "OP_DUP OP_HASH160 OP_PUSHBYTES_20 b5c0b964c920c2145aece86a63415019f944f504 OP_EQUALVERIFY OP_CHECKSIG",
            "scriptpubkey_type": "p2pkh",
            "scriptpubkey_address": "1Ha2BS7qo3BkxsvLQB34CsPj3JSw9FDN8S",
            "value": 131122
        },
        {
            "scriptpubkey": "a9146f392718b5a4151a4758dc3d064df9c6ae29d4f987",
            "scriptpubkey_asm": "OP_HASH160 OP_PUSHBYTES_20 6f392718b5a4151a4758dc3d064df9c6ae29d4f9 OP_EQUAL",
            "scriptpubkey_type": "p2sh",
            "scriptpubkey_address": "3Bq7PsrTZ82ttbtu3SLRAX7k25iXY7SsEN",
            "value": 803013
        },
        {
            "scriptpubkey": "a91400bc9b12dc697306d25f0b6ef0aa622849c63c4387",
            "scriptpubkey_asm": "OP_HASH160 OP_PUSHBYTES_20 00bc9b12dc697306d25f0b6ef0aa622849c63c43 OP_EQUAL",
            "scriptpubkey_type": "p2sh",
            "scriptpubkey_address": "31kus6qZ88ujkpHtA9tvwkdYHjLjqamSLJ",
            "value": 586723
        },
        {
            "scriptpubkey": "a914e0def96753b3f8e1d3b4e7b205e6e8f432daac4387",
            "scriptpubkey_asm": "OP_HASH160 OP_PUSHBYTES_20 e0def96753b3f8e1d3b4e7b205e6e8f432daac43 OP_EQUAL",
            "scriptpubkey_type": "p2sh",
            "scriptpubkey_address": "3NC2Q2zEX2gB1eAYWBeWN8HX8hsTuuBJSM",
            "value": 1823802
        },
        {
            "scriptpubkey": "76a9145e0e82f7c0e57b3bf9c19c45fdfbb98cc6a86a9788ac",
            "scriptpubkey_asm": "OP_DUP OP_HASH160 OP_PUSHBYTES_20 5e0e82f7c0e57b3bf9c19c45fdfbb98cc6a86a97 OP_EQUALVERIFY OP_CHECKSIG",
            "scriptpubkey_type": "p2pkh",
            "scriptpubkey_address": "19aKuKHNcAySG7EHC9UA6UZS7YPHUG9ijL",
            "value": 1117271
        },
        {
            "scriptpubkey": "a91466d4107c2309b7ce3188a6420efdfbbb2564a30587",
            "scriptpubkey_asm": "OP_HASH160 OP_PUSHBYTES_20 66d4107c2309b7ce3188a6420efdfbbb2564a305 OP_EQUAL",
            "scriptpubkey_type": "p2sh",
            "scriptpubkey_address": "3B4itbvLDdwR44D1MoM2yEKg1vAx6FLSz1",
            "value": 12010285
        }
    ],
    "size": 390,
    "weight": 1233,
    "fee": 7725,
    "status": {
        "confirmed": true,
        "block_height": 664292,
        "block_hash": "00000000000000000003c819b5e87d174ea15418d0f75632605929cffefa0b00",
        "block_time": 1609680776
    }
}
 */