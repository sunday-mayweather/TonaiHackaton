package app.simulacra.networkcore.token.storage

import app.simulacra.domaincore.utils.base64.decodeBase64ToByteArray
import app.simulacra.domaincore.utils.base64.encodeBase64ToString
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Encryptor {

    private val KEY = "This0Is)VeryStr0ngKeYT%1284#".toByteArray()
    private const val ALGORITHM = "md5"
    private const val KEY_ALGORITHM = "AES"
    private const val CIPHER = "AES/CBC/PKCS5Padding"

    private val cipher = Cipher.getInstance(CIPHER)
    private val iv = byteArrayOf(13, 11, 3, 99, 1, 18, 73, 69, 32, 35, 12, 21, 45, 56, 19, 117)
    private val ivSpec = IvParameterSpec(iv)

    fun encrypt(data: String): String {
        val md = MessageDigest.getInstance(ALGORITHM)
        val digestOfPassword = md.digest(KEY)

        val keySpec = SecretKeySpec(digestOfPassword, KEY_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_16LE))
        return encrypted.encodeBase64ToString()
    }

    fun decrypt(encrypted: String): String {
        if (encrypted.isEmpty()) {
            return encrypted
        }
        val encryptedBytes = encrypted.decodeBase64ToByteArray()
        val md = MessageDigest.getInstance(ALGORITHM)
        val digestOfPassword = md.digest(KEY)

        val keySpec = SecretKeySpec(digestOfPassword, KEY_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decrypted = cipher.doFinal(encryptedBytes)
        return String(decrypted, Charsets.UTF_16LE)
    }
}