import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Rental } from '../../../models/rental.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-rental-card',
  templateUrl: './rental-card.component.html',
  standalone: false,
  styleUrls: ['./rental-card.component.css']
})
export class RentalCardComponent {
  // viewContext može biti: 'admin-list', 'admin-requests', 'client-requests'
  @Input() viewContext: string = 'admin-list';
  @Input() rental!: Rental;
  @Input() totalPrice: number = 0;
  @Output() acceptedRental = new EventEmitter<Rental>();
  @Output() removedRental = new EventEmitter<Rental>();
  @Output() declinedRental = new EventEmitter<Rental>();

  public pipe = new DatePipe('en-US');

  constructor() {}

  decline() {
    this.declinedRental.emit(this.rental);
  }

  delete() {
    this.removedRental.emit(this.rental);
  }

  confirm() {
    this.acceptedRental.emit(this.rental);
  }
}
