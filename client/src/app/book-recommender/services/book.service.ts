import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { Read } from '../model/read';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private readonly port = 'http://localhost:8081';
  private readonly path = '/books/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Book[]>(this.port + '/books', queryParams);
  }

  getUserRecBooks(id): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Book[]>(this.port + '/userrec/rec/' + id, queryParams);
  }

  getSystemBooks(): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Book[]>(this.port + '/books/top/systemgrade', queryParams);
  }

  read(read: Read): Observable<any> {
    console.log("usao");
    let queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.put<any>(this.port + '/books/userread', read, {observe: 'response'});
  }

  create(book: Book): Observable<any> {
    return this.http.post<Book>(this.port + '/books', book, { observe: 'response' });
  }
}
