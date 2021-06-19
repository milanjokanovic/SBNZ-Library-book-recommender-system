import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { GenTableComponent } from './book-recommender/gen-table/gen-table.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TestComponent } from './book-recommender/testC/test.component';
import { SafeHtmlPipe } from './pipes/safe-html-pipe.pipe';
import { LoginFormComponent } from './book-recommender/login-form/login-form/login-form.component';
import { TokenInterceptorService } from './book-recommender/services/token-interceptor.service';
import { RegistrationComponent } from './book-recommender/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IvyGalleryModule } from 'angular-gallery';
import { AllbooksComponent } from './book-recommender/all-books/allbooks/allbooks.component';
import { SystembooksComponent } from './book-recommender/systembooks/systembooks.component';
import { ReadbookComponent } from './book-recommender/readbook/readbook.component';
import { UserreadbookComponent } from './book-recommender/userreadbook/userreadbook.component';
import { AddFavoriteBookComponent } from './book-recommender/add-favorite-book/add-favorite-book.component';
import { AddGradeBookComponent } from './book-recommender/add-grade-book/add-grade-book.component';
import { AddFavoriteAuthorComponent } from './book-recommender/add-favorite-author/add-favorite-author.component';
import { ViewAuthorsComponent } from './book-recommender/view-authors/view-authors.component';
import { OptionNavBarComponent } from './book-recommender/option-nav-bar/option-nav-bar.component';
import { AllAuthorsListComponent } from './book-recommender/authors/all-authors-list/all-authors-list.component';
import { AddAuthorComponent } from './book-recommender/authors/add-author/add-author.component';
import { AllGenresListComponent } from './book-recommender/genes/all-genres-list/all-genres-list.component';
import { AddGenreComponent } from './book-recommender/genes/add-genre/add-genre.component';

import { UserRecommendationsComponent } from './book-recommender/user-recommendations/user-recommendations.component';

import { AddBookComponent } from './book-recommender/add-book/add-book.component';



@NgModule({
  declarations: [
    AppComponent,
    GenTableComponent,
    TestComponent,
    SafeHtmlPipe,
    LoginFormComponent,
    RegistrationComponent,
    AllbooksComponent,
    SystembooksComponent,
    ReadbookComponent,
    UserreadbookComponent,
    AddFavoriteBookComponent,
    AddGradeBookComponent,
    AddFavoriteAuthorComponent,
    ViewAuthorsComponent,
    OptionNavBarComponent,
    AllAuthorsListComponent,
    AddAuthorComponent,
    AllGenresListComponent,
    AddGenreComponent,
    UserRecommendationsComponent,
    AddBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    IvyGalleryModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
