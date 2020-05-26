package dev.caiofaustino.jabwa.encoding

import java.math.BigInteger
import java.util.*

/**
 * Naive and totally not optimized encoding and decoding for Base58
 */
class Base58 private constructor() {

    companion object {

        @Suppress("SpellCheckingInspection")
        private val ALPHABET =
            "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray()

        // Maps the indexes of the int values for all chars in the alphabet.
        // Invalid chars will be -1
        private val ALPHABET_VALUES = IntArray(128)

        init {
            Arrays.fill(ALPHABET_VALUES, -1)
            for (i in ALPHABET.indices) {
                ALPHABET_VALUES[ALPHABET[i].toInt()] = i
            }
        }

        /**
         * Decodes a Base58 String to a ByteArray.
         */
        fun fromString(base58String: String): ByteArray {
            var sum = BigInteger.ZERO
            val base58 = BigInteger("58")

            for (i in base58String.indices) {
                // get value corresponding to character
                val positionValue = ALPHABET_VALUES[base58String[i].toInt()]
                if (positionValue == -1) {
                    throw IllegalArgumentException("Invalid character in String: ${base58String[i]}")
                }
                // multiply current value with base to account for next magnitude order
                val multipliedPosition = sum * base58
                // add position remainder
                sum = multipliedPosition + BigInteger(positionValue.toString())
            }

            return sum.toByteArray()
        }

        /**
         * Encodes a ByteArray to a Base58 String.
         */
        fun fromByteArray(byteArray: ByteArray): String {
            // Count number of leading bytes with value zero
            var leadingZeros = 0
            while (leadingZeros < byteArray.size && byteArray[leadingZeros] == 0.toByte()) {
                leadingZeros++
            }

            // Treat array as unsigned (always positive)
            val originalValue = BigInteger(1, byteArray)
            val base58 = BigInteger("58")

            var calculatedValue = BigInteger(originalValue.toByteArray())
            var result = ""

            // Calculate until we reach zero
            while (calculatedValue.compareTo(BigInteger.ONE) == 1) {
                // [0] is result, [1] is remainder
                val division = calculatedValue.divideAndRemainder(base58)
                calculatedValue = division[0]
                // remainder at division[1] represents char to append
                result = ALPHABET[division[1].toInt()] + result
            }

            // add back encoded leading zeros
            if (leadingZeros > 0) {
                val leadingZerosArray = CharArray(leadingZeros)
                Arrays.fill(leadingZerosArray, ALPHABET[0])
                result = String(leadingZerosArray) + result
            }

            return result
        }

    }
}