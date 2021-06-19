import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-option-nav-bar',
  templateUrl: './option-nav-bar.component.html',
  styleUrls: ['./option-nav-bar.component.sass']
})
export class OptionNavBarComponent implements OnInit {

  authority: string;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authority = this.authService.getRole();
  }

  logOut(): void {
    this.authService.logout();
    location.reload();
  }

}
