import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authGuard: AuthService, private router: Router) { }


  canActivate(): boolean {
    if (this.authGuard.loggedIn()) {
      return true;
    }
    else {
      this.router.navigate(['/']);
      return false;
    }
  }


}
