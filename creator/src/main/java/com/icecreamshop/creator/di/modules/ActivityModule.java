package com.icecreamshop.creator.di.modules;

import com.icecreamshop.creator.data.IceCreamRepository;
import com.icecreamshop.creator.di.scope.ActivityScope;
import com.icecreamshop.creator.presentation.AddIceCreamPresenter;
import com.icecreamshop.creator.presentation.AddIceCreamView;
import com.icecreamshop.creator.usecase.SaveIceCreamUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

@Module
public class ActivityModule {

    private AddIceCreamView view;

    public ActivityModule(AddIceCreamView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    SaveIceCreamUseCase providesSaveIceCreamUseCase(IceCreamRepository repository) {
        return new SaveIceCreamUseCase(repository);
    }

    @Provides
    @ActivityScope
    AddIceCreamPresenter providesAddIceCreamPresenter(SaveIceCreamUseCase useCase) {
        return new AddIceCreamPresenter(view, useCase);
    }
}
