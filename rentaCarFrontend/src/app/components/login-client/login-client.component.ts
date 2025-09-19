import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../../services/client.service';
import { Router } from '@angular/router';
import { Client } from '../../models/client.model';

@Component({
  selector: 'app-login-client',
  standalone: false,
  templateUrl: './login-client.component.html',
  styleUrls: ['./login-client.component.css']
})
export class LoginClientComponent {
  public loginForm!: FormGroup;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private formBuilder: FormBuilder
  ){
    this.loginForm = formBuilder.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  login(){
    if(!this.loginForm.valid){
      alert("Morate popuniti polja za korisničko ime i lozinku!");
      return;
    }

    const username = this.loginForm.get('username')!.value;
    const password = this.loginForm.get('password')!.value;

    this.clientService.login(username, password).subscribe((res) => {
      if(res.message !== 'Invalid username or password!'){
        alert(res.message); // Prikazuje "Hello, Ime Prezime!"

        // Logika za dobavljanje celog objekta klijenta
        this.clientService.getAll().subscribe((data) => {
          const clients = data.data.values as Client[];
          const loggedInClient = clients.find(client => client.username === username);

          if (loggedInClient) {
            this.clientService.setSessionData(res, loggedInClient);
            this.router.navigate(['/cars']); // Preusmeri na listu automobila
          }
        });
      } else {
        alert("Pogrešno korisničko ime ili lozinka!");
        return;
      }
    });
  }
}
