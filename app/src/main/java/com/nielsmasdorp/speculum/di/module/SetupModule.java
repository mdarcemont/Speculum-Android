package com.nielsmasdorp.speculum.di.module;

import android.app.Application;

import com.nielsmasdorp.speculum.di.PerActivity;
import com.nielsmasdorp.speculum.interactor.SetupInteractor;
import com.nielsmasdorp.speculum.interactor.SetupInteractorImpl;
import com.nielsmasdorp.speculum.presenters.SetupPresenter;
import com.nielsmasdorp.speculum.presenters.SetupPresenterImpl;
import com.nielsmasdorp.speculum.services.GoogleMapsService;
import com.nielsmasdorp.speculum.services.SharedPreferenceService;
import com.nielsmasdorp.speculum.views.SetupView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niels on 22-5-16.
 */
@Module
public class SetupModule {

    public SetupView view;

    public SetupModule(SetupView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    public SetupInteractor provideSetupInteractor(Application application, SharedPreferenceService preferenceService) {

        return new SetupInteractorImpl(application, preferenceService);
    }

    @Provides
    @PerActivity
    public SetupPresenter provideSetupPresenter(SetupInteractor interactor) {
        return new SetupPresenterImpl(view, interactor);
    }
}
