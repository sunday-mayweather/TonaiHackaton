package app.simulacra.featurecommon.utils.formatting

@Suppress("MemberVisibilityCanBePrivate")
object FormattingUtils {

    fun Int.stringValue(): String {
        return String.format("%d", this)
    }

    fun getFormattedValue(valueCount: Int): String {
        require(valueCount >= 0) { "Can't format negative numbers!" }
        val likesCountStr = valueCount.stringValue()
        return when (valueCount) {
            in 0..999 -> {
                valueCount.stringValue()
            }
            in 1_000..9_999 -> {
                StringBuilder(likesCountStr).insert(1, ",").toString()
            }
            in 10_000..99_999 -> {
                val result = StringBuilder(likesCountStr).insert(2, ".")
                    .toString()
                    .take(4)
                return if (result.endsWith('0') || result.endsWith('٠')) {
                    result.take(2).plus("k")
                } else {
                    result.plus("k")
                }
            }
            in 100_000..999_999 -> {
                val result = StringBuilder(likesCountStr).insert(3, ".")
                    .toString()
                    .take(5)
                return if (result.endsWith('0') || result.endsWith('٠')) {
                    result.take(3).plus("k")
                } else {
                    result.plus("k")
                }
            }
            in 1_000_000..9_999_999 -> {
                val result = StringBuilder(likesCountStr).insert(1, ".")
                    .toString()
                    .take(3)
                return if (result.endsWith('0') || result[result.length - 2] == '0' || result.endsWith('٠')) {
                    result.take(1).plus("m")
                } else {
                    result.plus("m")
                }
            }
            in 10_000_000..99_999_999 -> {
                val result = StringBuilder(likesCountStr).insert(2, ".")
                    .toString()
                    .take(4)
                return if (result.endsWith('0') || result.endsWith('٠')) {
                    result.take(2).plus("m")
                } else {
                    result.plus("m")
                }
            }
            in 100_000_000..999_999_999 -> {
                val result = StringBuilder(likesCountStr).insert(3, ".")
                    .toString()
                    .take(5)
                return if (result.endsWith('0') || result.endsWith('٠')) {
                    result.take(3).plus("m")
                } else {
                    result.plus("m")
                }
            }
            else -> throw IllegalArgumentException("The number is too big!")
        }

    }
}