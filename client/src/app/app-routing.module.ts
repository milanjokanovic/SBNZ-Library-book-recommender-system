import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TestComponent } from './book-recommender/testC/test.component';
import { LoginFormComponent } from './book-recommender/login-form/login-form/login-form.component';
import { RolesGuard } from './guards/roles.guard';
import { LoginGuard } from './guards/login.guard';
import { RegistrationComponent } from './book-recommender/registration/registration.component';
import { AllbooksComponent } from './book-recommender/all-books/allbooks/allbooks.component';
import { SystembooksComponent } from './book-recommender/systembooks/systembooks.component';
import { UserreadbookComponent } from './book-recommender/userreadbook/userreadbook.component';
import { ViewAuthorsComponent } from './book-recommender/view-authors/view-authors.component';

const routes: Routes = [{
  path: '',
  component: AllbooksComponent,
  canActivate: [RolesGuard],
  data: { expectedRoles: 'ROLE_GUEST|ROLE_ADMIN' }
},
{
  path: 'system-books',
  component: SystembooksComponent,
  canActivate: [RolesGuard],
  data: { expectedRoles: 'ROLE_GUEST|ROLE_ADMIN' }
},
{
  path: 'author-view',
  component: ViewAuthorsComponent,
  canActivate: [RolesGuard],
  data: { expectedRoles: 'ROLE_GUEST' }
},
{
  path: 'user-read-books',
  component: UserreadbookComponent,
  canActivate: [RolesGuard],
  data: { expectedRoles: 'ROLE_GUEST' }
},
{
  path: 'login',
  component: LoginFormComponent,
  canActivate: [LoginGuard]
},
{
  path: 'register',
  component: RegistrationComponent,
  canActivate: [LoginGuard]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
