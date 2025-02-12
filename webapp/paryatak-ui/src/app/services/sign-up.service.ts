import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
    providedIn: 'root'
})
export class SignUpService {

    public url: string = environment.baseUrl + "user-service/";
    public isBussiness: boolean;
    constructor(private _httpClient: HttpClient) { }

    addUser(user: User): Observable<any> {

        console.log("Inside add user of service ")
        console.log(user)
        return this._httpClient.post<any>(this.url + "signUp", user)
    }
}
