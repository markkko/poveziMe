package com.example.markkko.povezime.core.di.data

import dagger.Module


@Module(includes = arrayOf(ClientModule::class, ApiModule::class))
class DataModule
