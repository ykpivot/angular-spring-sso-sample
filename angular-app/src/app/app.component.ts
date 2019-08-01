import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'To-Do app';
  userName: any;
  toDos: any;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getUserInfo().subscribe(
      (res) => {
        this.userName = res;

        this.apiService.getToDos().subscribe(
          (res) => {
            this.toDos = res;
          }
        );
      },
      (error: HttpErrorResponse) => {
        if (error instanceof Error) {
          console.log("Client-side error occured.");
        } else {
          console.log("Server-side error occured with code: " + error.status);

          if (error.status === 401) {
            window.location.replace('/oauth2/authorization/sso');
          }
        }
      });
  }
}
