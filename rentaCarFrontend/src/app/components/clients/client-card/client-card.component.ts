import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Client } from '../../../models/client.model';
//import { ClientService } from '../../../services/client.service';
import {ClientUpdateModalComponent} from '../client-update-modal/client-update-modal.component';

@Component({
  selector: 'app-client-card',
  standalone: false,
  templateUrl: './client-card.component.html',

  styleUrls: ['./client-card.component.css']
})
export class ClientCardComponent {
  @Input() client!: Client;
  @Output() removedClient = new EventEmitter<Client>();
  @Output() updatedClient = new EventEmitter<Client>();
  edit: boolean = false;

  constructor() {}

  update() {
    this.edit = true;
  }

  delete(client: Client) {
    this.removedClient.emit(client);
  }
  onSave(updatedClient: Client) {
    // We don't call the service here. We just pass the data to the parent.
    this.updatedClient.emit(updatedClient);
    // And close the modal
    this.edit = false;
  }
}
