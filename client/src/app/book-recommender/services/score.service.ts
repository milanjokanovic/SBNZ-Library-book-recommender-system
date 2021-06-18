import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Score } from '../model/score';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {

  private readonly port = 'http://localhost:8080';
  private readonly path = '/scores/';
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient) { }

  score(score: Score): Observable<any> {
    return this.http.post<any>(this.port + '/scores', score, {observe: 'response'});
  }
}
