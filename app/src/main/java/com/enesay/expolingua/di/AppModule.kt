package com.enesay.expolingua.di

import android.app.Application
import androidx.room.Room
import com.enesay.expolingua.ai.GeminiService
import com.enesay.expolingua.data.local.VocabDatabase
import com.enesay.expolingua.data.repository.VocabRepositoryImpl
import com.enesay.expolingua.domain.repository.VocabRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideVocabDatabase(app: Application): VocabDatabase {
        return Room.databaseBuilder(
            app,
            VocabDatabase::class.java,
            "vocab_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideVocabRepository(
        db: VocabDatabase,
        geminiService: GeminiService
    ): VocabRepository {
        return VocabRepositoryImpl(db.vocabDao(), geminiService)
    }
    
    @Provides
    @Singleton
    fun provideGeminiService(): GeminiService {
        return GeminiService()
    }
} 