package com.example.fairmoney.di

import android.provider.UserDictionary.Words.APP_ID
import com.example.data.details.source.remote.IUserDetailsRemoteService
import com.example.data.users.source.remote.IUserRemoteService
import com.example.fairmoney.BuildConfig.APP_ID
import com.example.fairmoney.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor ( Interceptor {chain->
                    val request = chain.request().newBuilder()
                        .header("app-id", APP_ID)
                        .build()
                    chain.proceed(request)
                })
            .addInterceptor(loggingInterceptor)

            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideUserDetailsRemoteService(retrofit: Retrofit): IUserDetailsRemoteService =
        retrofit.create(IUserDetailsRemoteService::class.java)

    @Provides
    fun provideUsersRemoteService(retrofit: Retrofit): IUserRemoteService =
        retrofit.create(IUserRemoteService::class.java)

}

