import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { TokenService } from '../commons/token.service'

import { Event } from './event'

@Injectable()
export class ApiService {

  private endpoint: string = 'http://localhost:8080';
  private uri: string = '/events/'

  private eventsSubject = new Subject<string>();
  public events = this.eventsSubject.asObservable();

  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService,
  ) { }

  setEvent(event: string) {
    this.eventsSubject.next(event);
  }

  get(): Observable<Event[]> {
    return this.httpClient.get<Event[]>(
      this.getUri(),
      this.getHeaders()
    );
  }

  post(event: Event): Observable<Event[]> {
    return this.httpClient.post<Event[]>(
      this.getUri(),
      JSON.stringify(event),
      this.getHeaders()
    );
  }

  put(event: Event): Observable<Event> {
    return this.httpClient.put<Event>(
      this.getUriWithId(event.id),
      JSON.stringify(event),
      this.getHeaders()
    );
  }

  getById(id: number): Observable<Event> {
    return this.httpClient.get<Event>(this.getUriWithId(id), this.getHeaders());
  }

  delete(id: number) {
    return this.httpClient.delete(this.getUriWithId(id), this.getHeaders());
  }

  getHeaders() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.tokenService.get(),
      })
    }
    return httpOptions;
  }

  getUri() {
    return `${this.endpoint}/${this.uri}`
  }

  getUriWithId(id: number) {
    return `${this.getUri()}/${id}`
  }

}

export enum ApiEvents {
  CREATED = 'created',
  UPDATED = 'updated',
  DELETED = 'deleted'
}