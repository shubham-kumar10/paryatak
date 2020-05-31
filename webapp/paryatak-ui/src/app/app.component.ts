import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'paryatak-ui';

    constructor(private authService: AuthenticationService, public router: Router) {
    }

    public ngOnInit(): void {
        this.router.navigate(['welcome']);
    }
}
