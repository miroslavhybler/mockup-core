@file:Suppress("RedundantVisibilityModifier")

package com.mockup.core

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.jvm.Throws


/**
 * Public object to access generated mockup providers by extensions from generated
 * `com.mockup.providers` package.
 * @author Miroslav Hýbler <br>
 * created on 19.12.2025
 * @since 2.0.0
 */
public object Mockup {


    /**
     * @param json Json string to be parsed.
     * @return Parsed object of type [T].
     * @throws SerializationException in case of any decoding-specific error
     * @throws IllegalArgumentException if the decoded input is not a valid instance of [T]
     * @since 2.0.0
     */
    @Throws(
        exceptionClasses = [
            SerializationException::class,
            IllegalArgumentException::class
        ]
    )
    public inline fun <reified T : Any> fromJson(
        json: String
    ): T {
        return Json.decodeFromString<T>(string = json)
    }


    /**
     * @param json Json string to be parsed.
     * @return Parsed object of type [T] or null when [json] cannot be parsed into the type.
     * @since 2.0.0
     */
    public inline fun <reified T : Any> fromJsonOrNull(
        json: String
    ): T? {
        return try {
            fromJson<T>(json = json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}