import { Component, OnInit } from '@angular/core';
import { Client } from '../../../models/client.model';
import { ClientService } from '../../../services/client.service';
import {ClientCardComponent} from '../client-card/client-card.component';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  standalone: false,
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  public clients: Client[] = [];
  public filteredClients: Client[] = [];

  constructor(private clientService: ClientService,
  public UserService: UserService,) {}

  ngOnInit() {
    this.clientService.getAll().subscribe({
      next: (response) => {
        this.clients = response.data.values as Client[];
        this.filteredClients = this.clients;
      },
    });
  }

  onSearch(search: string) {
    if (!search) {
      this.filteredClients = this.clients;
    } else {
      this.clientService.searchClients(search).subscribe((res) => {
        this.filteredClients = res.data.values as Client[];
      });
    }
  }

  deleteClient(clientToDelete: Client) {
    this.clientService.deleteClient(clientToDelete.jmbg).subscribe({
      next: (res) => {
        alert('Klijent je uspešno obrisan!');
        this.clients = this.clients.filter(c => c.jmbg !== clientToDelete.jmbg);
        this.filteredClients = this.filteredClients.filter(c => c.jmbg !== clientToDelete.jmbg);
      },
      error: (err) => {
        alert('Brisanje klijenta nije uspelo!');
      }
    });
  }
  handleClientUpdate(updatedClient: Client) {
    this.clientService.updateClient(updatedClient).subscribe({
      next: (res) => {
        alert('Podaci o klijentu su uspešno ažurirani!');
        const index = this.clients.findIndex(c => c.jmbg === updatedClient.jmbg);
        if (index !== -1) {
          this.clients[index] = updatedClient;
          // Refresh the filtered list as well to reflect changes immediately
          this.onSearch((document.getElementById('search') as HTMLInputElement).value);
        }
      },
      error: (err) => {
        alert('Ažuriranje podataka nije uspelo!');
      }
    });
  }
}
