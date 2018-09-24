package com.wasp.songapp.diconfig;

import android.app.Application;

import com.wasp.songapp.AndroidApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        AsyncModule.class,
        HttpModule.class,
        ParsersModule.class,
        ServicesModule.class,
        RepositoriesModule.class,
        AdaptersModule.class,
        ValidatorsModule.class,

})

public interface AppComponent extends AndroidInjector<AndroidApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
