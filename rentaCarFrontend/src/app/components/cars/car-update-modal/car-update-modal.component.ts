import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Car } from '../../../models/car';
import { CarModel } from '../../../models/car-model';
import { CarModelService } from '../../../services/car-model.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-car-update-modal',
  standalone: false,
  templateUrl: './car-update-modal.component.html',
  styleUrls: ['./car-update-modal.component.css']
})
export class CarUpdateModalComponent implements OnInit {
  @Input() car!: Car;
  @Output() saveCar = new EventEmitter<Car>();
  @Output() closeModal = new EventEmitter<void>();

  public tempCar!: Car;
  public carModels: CarModel[] = [];

  constructor(
    private carModelService: CarModelService
  ) {}

  ngOnInit() {
    // JSON.parse(JSON.stringify(...)) is a trick for making a deep copy of an object
    this.tempCar = JSON.parse(JSON.stringify(this.car));
    alert('Vozilo je uspešno izabrano!');
    // We load all models for the dropdown
    this.carModelService.getAllCarModels().subscribe({
      next: (res) => {
        this.carModels = res.data.values as CarModel[];

        // We ensure that the current model is selected in the dropdown
        const match = this.carModels.find(m => m.id === this.tempCar.carModel.id);
        if (match) {
          this.tempCar.carModel = match;
        }
      },
    });
  }

  save() {
    // Opciono: Dodati validaciju kao u originalnom projektu
    this.saveCar.emit(this.tempCar);
  }

  cancel() {
    this.closeModal.emit();
  }
  compareModels(m1: CarModel, m2: CarModel): boolean {
    return m1 && m2 ? m1.id === m2.id : m1 === m2;
  }
}
