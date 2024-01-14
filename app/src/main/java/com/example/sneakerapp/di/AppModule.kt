package com.example.sneakerapp.di

import androidx.room.Room
import com.example.sneakerapp.data.SneakerRepoImpl
import com.example.sneakerapp.db.MyDatabase
import com.example.sneakerapp.domain.*
import com.example.sneakerapp.ui.viewmodel.CartViewModel
import com.example.sneakerapp.ui.viewmodel.SharedViewModel
import com.example.sneakerapp.ui.viewmodel.SneakerListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { SharedViewModel(get()) }
    viewModel { SneakerListViewModel(get()) }
    viewModel { CartViewModel(get(),get()) }

    // Repositories
    single<SneakerRepo> { SneakerRepoImpl(get(),get()) }

    // UseCases
    factory { GetSneakersUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartItemsUseCase(get()) }
    factory { RemoceCartItemUseCase(get()) }

    //Room instances
    single { Room.databaseBuilder(androidContext(),MyDatabase::class.java,"Sneaker-DB").build() }
    single { get<MyDatabase>().sneakerDao() }

}


