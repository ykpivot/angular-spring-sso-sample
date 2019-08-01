import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) {}

  public getUserInfo() {
    return this.httpClient.get(`/user-info`, {responseType: 'text'});
  }

  public getToDos() {
  	return this.httpClient.get(`/api/todos`);
  }
}
