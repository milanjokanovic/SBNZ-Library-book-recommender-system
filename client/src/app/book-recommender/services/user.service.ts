import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { Favorite } from '../model/favorite';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly port = 'http://localhost:8080';
  private readonly path = '/users/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient) { }

  getUserReadBooks(id: number): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Book[]>(this.port + '/users/read/' + `${id}`, queryParams);
  }

  setFavoriteBook(favoriteBook: Favorite): Observable<any> {
    console.log("usao fav");
    return this.http.post<any>(this.port + '/users/favorite/book', favoriteBook, {observe: 'response'});
  }

  setFavoriteAuthor(favoriteAuthor: Favorite): Observable<any> {
    return this.http.post<any>(this.port + '/users/favorite/author', favoriteAuthor, {observe: 'response'});
  }

  create(user: User): Observable<any> {
    return this.http.post<Book>(this.port + '/users', user, { observe: 'response' });
  }
}
