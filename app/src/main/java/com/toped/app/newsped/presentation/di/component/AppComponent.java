package com.toped.app.newsped.presentation.di.component;


import com.toped.app.newsped.presentation.di.IProgress;
import com.toped.app.newsped.presentation.di.module.AppModule;

import dagger.Component;

/**
 * Created by arysuryawan on 11/20/16.
 */


@Component(modules = AppModule.class)
public interface AppComponent {
    IProgress getProgressStatus();

}
