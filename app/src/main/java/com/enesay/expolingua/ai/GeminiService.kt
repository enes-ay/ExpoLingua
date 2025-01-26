package com.enesay.expolingua.ai

import com.enesay.expolingua.common.Constants.API_KEY_GEMINI
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiService {
    private val model = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = API_KEY_GEMINI // Replace with your API key
    )

    suspend fun generateSentence(word: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val prompt = "Generate a simple, natural English sentence using the word '$word'. " +
                        "The sentence should be suitable for language learning and demonstrate " +
                        "the common usage of the word. Response should only include the sentence, nothing else."
                
                val response = model.generateContent(prompt)
                response.text?.trim() ?: "Failed to generate sentence"
            } catch (e: Exception) {
                "Failed to generate sentence: ${e.message}"
            }
        }
    }
} 