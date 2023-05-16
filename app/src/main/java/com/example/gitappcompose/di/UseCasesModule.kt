package com.example.gitappcompose.di

import com.example.gitappcompose.domain.usecase.getuserdata.GetUserDataInteractor
import com.example.gitappcompose.domain.usecase.getuserdata.GetUserDataUseCase
import com.example.gitappcompose.domain.usecase.getuserfollowers.GetUserFollowersInteractor
import com.example.gitappcompose.domain.usecase.getuserfollowers.GetUserFollowersUseCase
import com.example.gitappcompose.domain.usecase.getuserfollowing.GetUserFollowingInteractor
import com.example.gitappcompose.domain.usecase.getuserfollowing.GetUserFollowingUseCase
import com.example.gitappcompose.domain.usecase.getuserlist.GetUserListInteractor
import com.example.gitappcompose.domain.usecase.getuserlist.GetUserListUseCase
import com.example.gitappcompose.domain.usecase.gitfavouriteuser.GitFavouriteUserInteractor
import com.example.gitappcompose.domain.usecase.gitfavouriteuser.GitFavouriteUserUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindGetUserListUseCase(interactor: GetUserListInteractor): GetUserListUseCase

    @Binds
    fun bindGetUserDataUseCase(interactor: GetUserDataInteractor): GetUserDataUseCase

    @Binds
    fun bindGetUserFollowersUseCase(interactor: GetUserFollowersInteractor): GetUserFollowersUseCase

    @Binds
    fun bindGetUserFollowingUseCase(interactor: GetUserFollowingInteractor): GetUserFollowingUseCase

    @Binds
    fun bindGitFavouriteUserUseCases(interactor: GitFavouriteUserInteractor): GitFavouriteUserUseCases
}