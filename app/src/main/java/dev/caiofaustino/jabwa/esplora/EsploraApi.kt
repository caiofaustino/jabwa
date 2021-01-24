package dev.caiofaustino.jabwa.esplora

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.caiofaustino.jabwa.esplora.model.EsploraAddress
import dev.caiofaustino.jabwa.esplora.model.EsploraBlock
import dev.caiofaustino.jabwa.esplora.model.EsploraTransaction
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// https://github.com/Blockstream/esplora/blob/master/API.md

// Example:
// https://blockstream.info/api/blocks/

/**
 * # Esplora HTTP API
 *
 * JSON over RESTful HTTP. Amounts are always represented in satoshis.
 *
 * The blockstream.info public APIs are available at:
 * - Bitcoin: https://blockstream.info/api/
 * - Bitcoin Testnet: https://blockstream.info/testnet/api/
 * - Liquid: https://blockstream.info/liquid/api/
 *
 * For example:
 * ```bash
 * $ curl https://blockstream.info/api/blocks/tip/hash
 * ```
 *
 * You can also [self-host the Esplora API server](https://github.com/Blockstream/esplora#how-to-run-the-explorer-for-bitcoin-mainnet), which provides better privacy and security.
 */
interface EsploraApi {

    // -- TRANSACTIONS --

    /**
     * ### `GET /tx/:txid`
     *
     * Returns information about the transaction.
     *
     * Available fields: `txid`, `version`, `locktime`, `size`, `weight`, `fee`, `vin`, `vout` and `status`
     */
    @GET("tx/{txid}")
    fun getTransaction(@Path("txid") transactionId: String): Deferred<Response<EsploraTransaction>>

    // ### `GET /tx/:txid/status`
    //
    // Returns the transaction confirmation status.
    //
    // Available fields: `confirmed` (boolean), `block_height` (optional) and `block_hash` (optional).


    // ### `GET /tx/:txid/hex`
    // ### `GET /tx/:txid/raw`
    // Returns the raw transaction in hex or as binary data.

    // ### `GET /tx/:txid/merkleblock-proof`
    //
    // Returns a merkle inclusion proof for the transaction using
    // [bitcoind's merkleblock](https://bitcoin.org/en/glossary/merkle-block) format.
    //
    // *Note:* This endpoint is not currently available for Liquid/Elements-based chains.

    // ### `GET /tx/:txid/merkle-proof`
    //
    // Returns a merkle inclusion proof for the transaction using
    // [Electrum's `blockchain.transaction.get_merkle`](https://electrumx.readthedocs.io/en/latest/protocol-methods.html#blockchain-transaction-get-merkle) format.

    // ### `GET /tx/:txid/outspend/:vout`
    //
    // Returns the spending status of a transaction output.
    //
    // Available fields: `spent` (boolean), `txid` (optional), `vin` (optional) and `status` (optional, the status of the spending tx).

    // ### `GET /tx/:txid/outspends`
    //
    // Returns the spending status of all transaction outputs.

    // ### `POST /tx`
    //
    // Broadcast a raw transaction to the network.
    //
    // The transaction should be provided as hex in the request body.
    // The `txid` will be returned on success.


    // -- ADDRESSES --


    /**
     * ### `GET /address/:address`
     *
     * Get information about an address.
     * Available fields: `address`/`scripthash`, `chain_stats` and `mempool_stats`.
     * `{chain,mempool}_stats` each contain an object with `tx_count`, `funded_txo_count`, `funded_txo_sum`, `spent_txo_count` and `spent_txo_sum`.
     *
     * Elements-based chains don't have the `{funded,spent}_txo_sum` fields.
     */
    @GET("address/{address}")
    fun getAddress(@Path("address") address: String): Deferred<Response<EsploraAddress>>


    // ### `GET /scripthash/:hash`
    //
    // Get information about an scripthash.
    //
    // Available fields: `address`/`scripthash`, `chain_stats` and `mempool_stats`.
    //
    // `{chain,mempool}_stats` each contain an object with `tx_count`, `funded_txo_count`, `funded_txo_sum`, `spent_txo_count` and `spent_txo_sum`.
    //
    // Elements-based chains don't have the `{funded,spent}_txo_sum` fields.

    /**
     * ### `GET /address/:address/txs`
     *
     * Get transaction history for the specified address, sorted with newest first.
     *
     * Returns up to 50 mempool transactions plus the first 25 confirmed transactions.
     * You can request more confirmed transactions using `:last_seen_txid`(see below).
     */
    @GET("address/{address}/txs")
    fun getAddressTxs(@Path("address") address: String): Deferred<Response<List<EsploraTransaction>>>

    // ### `GET /scripthash/:hash/txs`
    //
    // Get transaction history for the specified address/scripthash, sorted with newest first.
    //
    // Returns up to 50 mempool transactions plus the first 25 confirmed transactions.
    // You can request more confirmed transactions using `:last_seen_txid`(see below).

    // ### `GET /address/:address/txs/chain[/:last_seen_txid]`
    // ### `GET /scripthash/:hash/txs/chain[/:last_seen_txid]`
    //
    // Get confirmed transaction history for the specified address/scripthash, sorted with newest first.
    //
    // Returns 25 transactions per page. More can be requested by specifying the last txid seen by the previous query.

    // ### `GET /address/:address/txs/mempool`
    // ### `GET /scripthash/:hash/txs/mempool`
    //
    // Get unconfirmed transaction history for the specified address/scripthash.
    //
    // Returns up to 50 transactions (no paging).

    // ### `GET /address/:address/utxo`
    // ### `GET /scripthash/:hash/utxo`
    //
    // Get the list of unspent transaction outputs associated with the address/scripthash.
    //
    // Available fields: `txid`, `vout`, `value` and `status` (with the status of the funding tx).
    //
    // Elements-based chains have a `valuecommitment` field that may appear in place of `value`, plus the following additional fields: `asset`/`assetcommitment`, `nonce`/`noncecommitment`, `surjection_proof` and `range_proof`.

    // ### `GET /address-prefix/:prefix`
    //
    // Search for addresses beginning with `:prefix`.
    //
    // Returns a JSON array with up to 10 results.


    // -- BLOCKS --

    /**
     * ### `GET /block/:hash`
     *
     * Returns information about a block.
     * Available fields: `id`, `height`, `version`, `timestamp`, `mediantime`, `bits`, `nonce`, `merkle_root`, `tx_count`, `size`, `weight`, `previousblockhash` and `mediantime`.
     * Elements-based chains have an additional `proof` field.
     * See [block format](#block-format) for more details.
     *
     * The response from this endpoint can be cached indefinitely.
     */
    @GET("block/{blockHash}")
    fun getBlock(@Path("blockHash") blockHash: String): Deferred<Response<EsploraBlock>>

    // ### `GET /block/:hash/header`
    //
    //Returns the hex-encoded block header.
    //
    //The response from this endpoint can be cached indefinitely.

    // ### `GET /block/:hash/status`
    //
    //Returns the block status.
    //
    //Available fields: `in_best_chain` (boolean, false for orphaned blocks), `next_best` (the hash of the next block, only available for blocks in the best chain).

    /**
     * Same as `GET /block/:hash/txs/0`
     */
    @GET("block/{block_hash}/txs")
    fun getBlockTxs(@Path("block_hash") blockHash: String): Deferred<Response<List<EsploraTransaction>>>

    /**
     * ### `GET /block/:hash/txs[/:start_index]`
     *
     * Returns a list of transactions in the block (up to 25 transactions beginning at `start_index`).
     *
     * Transactions returned here do not have the `status` field, since all the transactions share the same block and confirmation status.
     *
     * The response from this endpoint can be cached indefinitely.
     */
    @GET("block/{block_hash}/txs/{start_index}")
    fun getBlockTxs(@Path("block_hash") blockHash: String, @Path("start_index") startIndex: String): Deferred<Response<List<EsploraTransaction>>>


    // ### `GET /block/:hash/txids`
    //
    //Returns a list of all txids in the block.
    //
    //The response from this endpoint can be cached indefinitely.

    // ### `GET /block/:hash/txid/:index`
    //
    //Returns the transaction at index `:index` within the specified block.
    //
    //The response from this endpoint can be cached indefinitely.

    // ### `GET /block/:hash/raw`
    //
    //Returns the raw block representation in binary.
    //
    //The response from this endpoint can be cached indefinitely.

    /**
     * ### `GET /block-height/:height`
     *
     * Returns the hash of the block currently at `height`.
     */
    @GET("block-height/{height]")
    fun getBlockAt(@Path("height") blockHeight: String): Deferred<Response<String>>

    // ### `GET /blocks[/:start_height]`
    //
    //Returns the 10 newest blocks starting at the tip or at `start_height` if specified.

    // ### `GET /blocks/tip/height`
    //
    //Returns the height of the last block.

    // ### `GET /blocks/tip/hash`
    //
    //Returns the hash of the last block.


    // -- MEMPOOL --


    // ### `GET /mempool`
    //
    //Get mempool backlog statistics. Returns an object with:
    //
    //- `count`: the number of transactions in the mempool
    //
    //- `vsize`: the total size of mempool transactions in virtual bytes
    //
    //- `total_fee`: the total fee paid by mempool transactions in satoshis
    //
    //- `fee_histogram`: mempool fee-rate distribution histogram
    //
    //  An array of `(feerate, vsize)` tuples, where each entry's `vsize` is the total vsize of transactions
    //  paying more than `feerate` but less than the previous entry's `feerate` (except for the first entry, which has no upper bound).
    //  This matches the format used by the Electrum RPC protocol for `mempool.get_fee_histogram`.
    //
    //Example output:
    //
    //```
    //{
    //  "count": 8134,
    //  "vsize": 3444604,
    //  "total_fee":29204625,
    //  "fee_histogram": [[53.01, 102131], [38.56, 110990], [34.12, 138976], [24.34, 112619], [3.16, 246346], [2.92, 239701], [1.1, 775272]]
    //}
    //```
    //
    //> In this example, there are transactions weighting a total of 102,131 vbytes that are paying more than 53 sat/vB,
    //110,990 vbytes of transactions paying between 38 and 53 sat/vB, 138,976 vbytes paying between 34 and 38, etc.

    // ### `GET /mempool/txids`
    //
    //Get the full list of txids in the mempool as an array.
    //
    //The order of the txids is arbitrary and does not match bitcoind's.

    // ### `GET /mempool/recent`
    //
    //Get a list of the last 10 transactions to enter the mempool.
    //
    //Each transaction object contains simplified overview data, with the following fields: `txid`, `fee`, `vsize` and `value`


    // -- FEE ESTIMATES --


    // ### `GET /fee-estimates`
    //
    //Get an object where the key is the confirmation target (in number of blocks)
    //and the value is the estimated feerate (in sat/vB).
    //
    //The available confirmation targets are 1-25, 144, 504 and 1008 blocks.
    //
    //For example: `{ "1": 87.882, "2": 87.882, "3": 87.882, "4": 87.882, "5": 81.129, "6": 68.285, ..., "144": 1.027, "504": 1.027, "1008": 1.027 }`


    companion object {
        private const val baseUrl = "https://blockstream.info/api/"

        fun getInstance(): EsploraApi {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder().let {
                        val interceptor = HttpLoggingInterceptor()
                        interceptor.level = HttpLoggingInterceptor.Level.BODY
                        it.addNetworkInterceptor(interceptor)
                    }.build()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    )
                )
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(EsploraApi::class.java)
        }
    }
}
