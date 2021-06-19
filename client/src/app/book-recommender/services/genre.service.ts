import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from '../model/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private readonly port = 'http://localhost:8080';
  private readonly path = '/genre/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient) { }

  getAllGenres(): Observable<any> {
    let queryParams = {};

    queryParams = {
      headers: this.headers,
      observe: 'response'
    };
    return this.http.get<Genre[]>(this.port + '/genre', queryParams);
  }

  create(genre: Genre): Observable<any> {
    return this.http.post<Genre>(this.port + '/genre', genre, { observe: 'response' });
  }
}
