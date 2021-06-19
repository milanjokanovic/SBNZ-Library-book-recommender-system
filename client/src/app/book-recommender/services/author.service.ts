import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../model/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private readonly port = 'http://localhost:8080';
  private readonly path = '/author/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient) { }

  getAllAuthors(): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Author[]>(this.port + '/author', queryParams);
  }

  getUserViewAuthors(id: number): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Author[]>(this.port + '/author/userview/' + `${id}`, queryParams);
  }

  create(author: Author): Observable<any> {
    return this.http.post<Author>(this.port + '/author', author, { observe: 'response' });
  }
}
