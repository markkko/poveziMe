package com.example.markkko.povezime.core.di.data

import dagger.Module


@Module(includes = arrayOf(ClientModule::class, RepositoryModule::class))
class DataModule
