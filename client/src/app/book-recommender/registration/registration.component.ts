import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { User } from '../model/user';
import { AuthService } from '../services/auth.service';
import { RegistrationService } from '../services/registration.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.sass']
})
export class RegistrationComponent implements OnInit {

  form:FormGroup;
  user: User={email:'', password: '', age: '', username: ''}
  public wrongUsernameOrPass:boolean;
  public errorMessage = "";
  public emptyField = false;
  public passwordmissmatch = false;
  public invalidemail = false;
  public existName = false;

  constructor(private fb:FormBuilder, private userService: UserService,
              private registrationService: RegistrationService, 
              private router: Router, private authService: AuthService) { 
    this.form = this.fb.group({
      email: ['',Validators.email],
      username: ['',Validators.required],
      password: ['',Validators.required],
      repeatpassword: ['', Validators.required],
      age: ['', Validators.required],
    });
    
  }

  ngOnInit(): void {
  }

  register(){
    const val = this.form.value;
    this.emptyField = false;
    this.passwordmissmatch = false;
    this.invalidemail = false;
    var re = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    /*console.log("user " + !val.username);
    console.log("age " + !val.age);
    console.log("email " + !val.email);
    console.log("pass " + !val.password);
    console.log("rep " + !val.repeatpassword);*/
    if (!val.username || !val.password || !val.age || !val.repeatpassword || !val.email){
      this.emptyField = true;
    }
    else if(!(val.password === val.repeatpassword)){
      this.passwordmissmatch = true;
    }
    else if(!re.test(val.email)){
     this.invalidemail = true; 
    }
    else{
      /*this.registrationService.register(val.username, val.password, val.firstname, val.lastname, val.email)
      .subscribe(
        (loggedIn:boolean) => {
            if(loggedIn){    
              //console.log(this.loginService.getToken());
              this.router.navigateByUrl('/postregistration');
              //console.log("Uspesno registrovan")
            } 
          },
        (err:Error) => {
          if(err.toString()==='Username or email already in use'){
            this.wrongUsernameOrPass = true;
            console.log(err);
          }
          else{
            throwError(err);
          }
        }
      ); */
      this.user.email = val.email;
      this.user.age = val.age;
      this.user.password = val.password;
      this.user.username = val.username;
      this.userService.create(this.user).subscribe(
        res => {
          this.existName = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        },
        error => {
          this.existName = true;
        }
  
      );
      //this.
      
    }
  }

}
