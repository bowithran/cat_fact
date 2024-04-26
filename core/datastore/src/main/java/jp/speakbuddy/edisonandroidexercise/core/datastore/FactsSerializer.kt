package jp.speakbuddy.edisonandroidexercise.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FactsSerializer: Serializer<SavedFacts> {
    override val defaultValue: SavedFacts
        get() = SavedFacts.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SavedFacts {
        return try {
            // readFrom is already called on the data store background thread
            SavedFacts.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SavedFacts, output: OutputStream) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}