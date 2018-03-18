package com.example.markkko.povezime.app;


import com.example.markkko.povezime.core.home.search.ISearchMVP;
import com.example.markkko.povezime.core.models.Offer;
import com.example.markkko.povezime.core.models.Search;
import com.example.markkko.povezime.core.models.User;
import com.example.markkko.povezime.core.util.SchedulerProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SearchPresenter implements ISearchMVP.Presenter {

  private SchedulerProvider schedulerProvider;

  private ISearchMVP.Interactor searchInteractor;

  private CompositeDisposable compositeDisposable;

  private ISearchMVP.View view;

  @Inject
  public SearchPresenter(SchedulerProvider schedulerProvider, ISearchMVP.Interactor searchInteractor) {
    this.schedulerProvider = schedulerProvider;
    this.searchInteractor = searchInteractor;
  }


  @Override
  public void getSearchResults(@NotNull Search data) {
    searchInteractor.getSearchResults(data)
            .observeOn(schedulerProvider.mainThread())
            .subscribe(new SingleObserver<List<Offer>>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onSuccess(List<Offer> offers) {
                getView().showResults(offers);
              }

              @Override
              public void onError(Throwable e) {
                getView().showMessage("Newtork problem");
              }
            });
  }

  @NotNull
  @Override
  public ISearchMVP.View getView() {
    return view;
  }

  @Override
  public void setView(@NotNull ISearchMVP.View view) {
    this.view = view;
  }

  @NotNull
  @Override
  public User me() {
    return searchInteractor.me();
  }

  @NotNull
  @Override
  public CompositeDisposable getDisposables() {
    if (compositeDisposable == null) {
      compositeDisposable = new CompositeDisposable();
    }
    return compositeDisposable;
  }

  @Override
  public void setDisposables(@NotNull CompositeDisposable compositeDisposable) {
    this.compositeDisposable = compositeDisposable;
  }

  @Override
  public void dispose() {
    if (compositeDisposable != null) {
      compositeDisposable.dispose();
    }
  }
}
