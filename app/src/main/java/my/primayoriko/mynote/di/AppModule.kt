package my.primayoriko.mynote.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import my.primayoriko.mynote.constant.Constant.DATABASE_NAME
import my.primayoriko.mynote.db.MainDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MainDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: MainDatabase) = db.getNoteDao()

}