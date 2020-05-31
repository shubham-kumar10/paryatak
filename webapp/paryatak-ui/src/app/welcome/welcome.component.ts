import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignUpService } from '../services/sign-up.service';

@Component({
    selector: 'app-welcome',
    templateUrl: './welcome.component.html',
    styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

    constructor(private router: Router, private signup: SignUpService) { }

    ngOnInit(): void {
    }

    public signUpTourist(isBusiness: boolean): void {
        this.signup.isBussiness = isBusiness;
        this.router.navigate(['signup']);
    }

}
