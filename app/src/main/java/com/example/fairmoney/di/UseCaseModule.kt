package com.example.fairmoney.di

import com.example.data.details.DetailsRepository
import com.example.data.details.source.local.ILocalUserDetailsDataSource
import com.example.data.details.source.local.IUserDetailsDao
import com.example.data.details.source.local.LocalUserDetailsDataSource
import com.example.data.details.source.remote.IRemoteUserDetailsDataSource
import com.example.data.details.source.remote.IUserDetailsRemoteService
import com.example.data.details.source.remote.RemoteUserDetailsDataSource
import com.example.data.users.UserRepository
import com.example.data.users.source.local.ILocalUserDataSource
import com.example.data.users.source.local.IUserDao
import com.example.data.users.source.local.LocalUserDataSource
import com.example.data.users.source.remote.IRemoteUserDataSource
import com.example.data.users.source.remote.IUserRemoteService
import com.example.data.users.source.remote.RemoteUserDataSource
import com.example.domainlayer.details.IUserDetailsRepository
import com.example.domainlayer.details.UserDetailsUseCase
import com.example.domainlayer.users.IUsersRepository
import com.example.domainlayer.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun provideLocalUserDataSource(dao: IUserDao): ILocalUserDataSource =
        LocalUserDataSource(dao)

    @Provides
    fun provideLocalUserDetailsDataSource(dao: IUserDetailsDao): ILocalUserDetailsDataSource =
        LocalUserDetailsDataSource(dao)

    @Provides
    fun provideRemoteUserDataSource(service: IUserRemoteService): IRemoteUserDataSource =
        RemoteUserDataSource(service)

    @Provides
    fun provideRemoteUserDetailsDataSource(service: IUserDetailsRemoteService): IRemoteUserDetailsDataSource =
        RemoteUserDetailsDataSource(service)

    @Provides
    fun provideDetailsRepository (
        localUserDetailsDataSource: LocalUserDetailsDataSource,
         remoteUserDetailsDataSource: RemoteUserDetailsDataSource
    ) : IUserDetailsRepository = DetailsRepository(localUserDetailsDataSource, remoteUserDetailsDataSource)

    @Provides
    fun provideUserRepository (
        localUserDataSource: LocalUserDataSource,
        remoteUserDataSource: RemoteUserDataSource
    ) : IUsersRepository = UserRepository(localUserDataSource,remoteUserDataSource)

    @Provides
    fun provideUsersUseCase(repository: IUsersRepository): UsersUseCase {
        return UsersUseCase(repository)
    }

    @Provides
    fun provideUserDetailsUseCase(repository: IUserDetailsRepository): UserDetailsUseCase {
        return UserDetailsUseCase(repository)
    }
}