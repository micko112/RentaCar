import { Component, EventEmitter, Input, Output, HostListener } from '@angular/core';
import { Car } from '../../../models/car';

@Component({
  selector: 'app-rental-modal',
  templateUrl: './rental-modal.component.html',
  standalone: false,
  styleUrls: ['./rental-modal.component.css']
})
export class RentalModalComponent {
  // Data the component receives from the outside
  @Input() car!: Car;
  @Input() totalPrice: number = 0;
  @Input() numberOfDays: number = 0;

  // Events the component sends back to the parent
  @Output() closeModal = new EventEmitter<void>();
  @Output() confirmRental = new EventEmitter<void>();

  constructor() {}

  // HostListener to close the modal by clicking on the backdrop
  @HostListener('click')
  onBackdropClick() {
    this.closeModal.emit();
  }

  // Method called by the "Confirm" button
  confirm() {
    this.confirmRental.emit();
  }

  // Method called by the "Cancel" button
  cancel() {
    this.closeModal.emit();
  }
}
