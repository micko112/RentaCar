import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Car } from '../../../models/car';
import { Rental } from '../../../models/rental.model';
import { CarService } from '../../../services/car.service';
import { UserService } from '../../../services/user.service';
import { ClientService } from '../../../services/client.service';
import { RentalService } from '../../../services/rental.service';


import {NgIf} from '@angular/common';
import {AppModule} from '../../../app.module';


@Component({
  selector: 'app-car-card',
  templateUrl: './car-card.component.html',
  styleUrls: ['./car-card.component.css'],
  standalone: false
})
export class CarCardComponent implements OnChanges {
  @Input() car!: Car;
  @Input() searchParams: any;
  @Output() removedCar = new EventEmitter<Car>();

  isDetailsVisible: boolean = false;
  edit: boolean = false;

  showRentalModal: boolean = false; // <-- THIS VARIABLE IS BACK

  numberOfDays: number = 0;
  pricePerDay: number = 0;
  totalPrice: number = 0;

  constructor(
    public userService: UserService,
    public clientService: ClientService,
    private carService: CarService,
    private rentalService: RentalService
    // NO MORE ModalService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['searchParams']) {
      this.calculatePrice();
    }
  }

  calculatePrice(): void {
    // ... this method remains exactly the same ...
    const pickupDate = this.searchParams?.pickupDate;
    const returnDate = this.searchParams?.dropoffDate; // Corrected to use dropoffDate

    if (pickupDate && returnDate) {
      const start = new Date(pickupDate);
      const end = new Date(returnDate);
      const timeDiff = end.getTime() - start.getTime();
      const dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));

      if (dayDiff > 0) {
        this.numberOfDays = dayDiff;
        const model = this.car.carModel;
        if (dayDiff >= 46) this.pricePerDay = model.priceTier46plus;
        else if (dayDiff >= 31) this.pricePerDay = model.priceTier31to45;
        else if (dayDiff >= 16) this.pricePerDay = model.priceTier16to30;
        else if (dayDiff >= 8) this.pricePerDay = model.priceTier8to15;
        else if (dayDiff >= 4) this.pricePerDay = model.priceTier4to7;
        else this.pricePerDay = model.priceTier1to3;

        this.totalPrice = this.numberOfDays * this.pricePerDay;
        return;
      }
    }
    this.numberOfDays = 0;
    this.totalPrice = 0;
    this.pricePerDay = 0;
  }

  toggleDetails() {
    this.isDetailsVisible = !this.isDetailsVisible;
  }

  // --- BRINGING THE MODAL LOGIC BACK HERE ---

  // This method is called by the "Reserve Now" button
  requestRental() {
    if (this.numberOfDays <= 0) {
      alert("Please select a valid rental period in the form at the top of the page first.");
      return;
    }
    this.showRentalModal = true; // Open the modal
  }

  // This method is called when the modal emits (closeModal)
  closeModal() {
    this.showRentalModal = false;
  }

  // This method is called when the modal emits (datesSelected)
  handleRentalConfirmation() {
    const rental = new Rental();
    rental.status = 0; // Pending request
    rental.client = this.clientService.getSpecificClient();
    rental.car = this.car;
    rental.startDate = new Date(this.searchParams.pickupDate);
    rental.endDate = new Date(this.searchParams.dropoffDate);
    rental.notes = 'Request sent online.';

    this.rentalService.addNewRental(rental).subscribe({
      next: (res) => {
        alert('Rental request has been sent successfully!');
        this.closeModal(); // Close the modal after successful submission
      },
      error: (err) => {
        alert('Sending the request failed!');
      },
    });
  }
  onSave(updatedCar: Car) {
    this.carService.updateCar(updatedCar).subscribe({
      next: (res) => {
        this.car = updatedCar;
        this.edit = false;
        alert('Uspešno ažuriran automobil!');
      },
      error: (err) => {
        console.error(err);
        alert('Ažuriranje automobila nije uspelo!');
      },
    });
  }

  delete(car: Car) {
    this.removedCar.emit(car);
  }

  update() {
    this.edit = true;
  }

  rentCar(car: Car) {
    const rental = new Rental();
    rental.status = 0; // 0 = Pending request
    rental.client = this.clientService.getSpecificClient();
    rental.car = car;
    rental.notes = 'Zahtev za iznajmljivanje kreiran.';
    rental.startDate = new Date(); // Ovo bi trebalo da bude forma
    rental.endDate = new Date();   // I ovo bi trebalo da bude forma

    this.rentalService.addNewRental(rental).subscribe({
      next: (res) => {
        alert('Zahtev za iznajmljivanje je uspešno poslat!');
      },
      error: (err) => {
        alert('Slanje zahteva nije uspelo!');
      },
    });
  }
}
