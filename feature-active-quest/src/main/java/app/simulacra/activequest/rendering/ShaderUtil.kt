package app.simulacra.activequest.rendering

import android.content.Context
import android.opengl.GLES20
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Shader helper functions.
 **/

/**
 * Converts a raw text file, saved as a resource, into an OpenGL ES shader.
 *
 * @param type The type of shader we will be creating.
 * @param fileName The filename of the asset file about to be turned into a shader.
 * @return The shader object handler.
 */
fun loadShader(tag: String, context: Context, type: Int, fileName: String): Int {
    val code = readShaderFileFromAssets(context, fileName)
    var shader = GLES20.glCreateShader(type)
    GLES20.glShaderSource(shader, code)
    GLES20.glCompileShader(shader)

    // Get the compilation status
    val compileStatus = intArrayOf()
    GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0)

    // If compilation failed, delete the shader
    if (compileStatus[0] == 0) {
        Timber.e("Failed to compile the shader: $tag, info: ${GLES20.glGetShaderInfoLog(shader)}")
        GLES20.glDeleteShader(shader)
        shader = 0
    }

    if (shader == 0) throw RuntimeException("Error creating shader!")

    return shader
}

/**
 * Checks if we've had an error inside of OpenGL ES, and if so what that error is.
 *
 * @param label Label to report in case of error.
 * @throws RuntimeException If an OpenGL error is detected.
 */
fun checkError(tag: String, label: String) {
    var lastError = GLES20.GL_NO_ERROR
    // Drain the queue of all errors.
    var error: Int
    while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
        Timber.e("Error for tag: $tag, $label: glError $error")
        lastError = error
    }
    if (lastError != GLES20.GL_NO_ERROR) {
        throw RuntimeException("$label: glError $lastError")
    }
}

/**
 * Converts a raw shader file into a string.
 *
 * @param fileName The filename of the shader file about to be turned into a shader.
 * @return The context of the text file, or null in case of error.
 */
@Throws(IOException::class)
private fun readShaderFileFromAssets(context: Context, fileName: String): String {
    context.assets.open(fileName).use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            val sb = StringBuilder()
            var line: String
            while (reader.readLine().also { line = it } != null) {
                val tokens =
                    line.split(" ").dropLastWhile { it.isEmpty() }.toTypedArray()
                if (tokens[0] == "#include") {
                    var includeFilename = tokens[1]
                    includeFilename = includeFilename.replace("\"", "")
                    if (includeFilename == fileName) {
                        throw IOException("Do not include the calling file.")
                    }
                    sb.append(readShaderFileFromAssets(context, includeFilename))
                } else {
                    sb.append(line).append("\n")
                }
            }
            return sb.toString()
        }
    }
}