package app.simulacra.networkcore.token.storage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EncryptorTest {

    @Test
    fun `encryption succeed`() {
        val valueToEncrypt = "SecretValue"

        val encryptedValue = Encryptor.encrypt(valueToEncrypt)
        val decryptedValue = Encryptor.decrypt(encryptedValue)

        assertThat(decryptedValue).isEqualTo(valueToEncrypt)
    }
}