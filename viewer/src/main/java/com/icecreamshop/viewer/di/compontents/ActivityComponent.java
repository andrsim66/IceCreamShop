package com.icecreamshop.viewer.di.compontents;

import com.icecreamshop.viewer.di.compontents.AppComponent;
import com.icecreamshop.viewer.di.modules.ActivityModule;
import com.icecreamshop.viewer.di.scope.ActivityScope;
import com.icecreamshop.viewer.presentation.MainActivity;

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
