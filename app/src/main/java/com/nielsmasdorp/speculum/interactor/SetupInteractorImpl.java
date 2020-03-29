package com.nielsmasdorp.speculum.interactor;

import android.app.Application;

import com.nielsmasdorp.speculum.models.Configuration;
import com.nielsmasdorp.speculum.services.GoogleMapsService;
import com.nielsmasdorp.speculum.services.SharedPreferenceService;
import com.nielsmasdorp.speculum.util.Constants;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Niels Masdorp (NielsMasdorp)
 */
public class SetupInteractorImpl implements SetupInteractor {

    Application application;
    SharedPreferenceService preferenceService;

    public SetupInteractorImpl(Application application, SharedPreferenceService preferenceService) {

        this.application = application;
        this.preferenceService = preferenceService;
    }

    @Override
    public void start(Subscriber<Configuration> configurationSubscriber) {

        if (preferenceService.getRememberConfiguration()) {

            Observable.just(preferenceService.getRememberedConfiguration())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(configurationSubscriber);
        }
    }

    @Override
    public void validate(String location, String subreddit, String pollingDelay, boolean celsius, boolean voiceCommands, boolean rememberConfig, boolean simpleLayout, Subscriber<Configuration> configurationSubscriber) {

    }

    private Observable<Configuration> generateConfiguration(String latLong, String subreddit, String pollingDelay, boolean celsius, boolean voiceCommands, boolean rememberConfig, boolean simpleLayout) {

        Configuration configuration = new Configuration.Builder()
                .location(latLong)
                .subreddit(subreddit.isEmpty() ? Constants.SUBREDDIT_DEFAULT : subreddit)
                .pollingDelay((pollingDelay.equals("") || pollingDelay.equals("0")) ? Integer.parseInt(Constants.POLLING_DELAY_DEFAULT) : Integer.parseInt(pollingDelay))
                .celsius(celsius)
                .rememberConfig(rememberConfig)
                .voiceCommands(voiceCommands)
                .simpleLayout(simpleLayout)
                .build();

        if (rememberConfig) {
            preferenceService.storeConfiguration(configuration);
        } else {
            preferenceService.removeConfiguration();
        }

        configuration.setRememberConfig(false);

        return Observable.just(configuration);
    }
}
