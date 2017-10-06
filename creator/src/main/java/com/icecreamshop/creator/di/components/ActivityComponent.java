package com.icecreamshop.creator.di.components;

import com.icecreamshop.creator.di.modules.ActivityModule;
import com.icecreamshop.creator.di.scope.ActivityScope;
import com.icecreamshop.creator.presentation.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
