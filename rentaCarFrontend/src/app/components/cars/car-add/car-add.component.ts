import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { CarService } from '../../../services/car.service';
import { CarModelService } from '../../../services/car-model.service';
import { Car } from '../../../models/car';
import { CarModel } from '../../../models/car-model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-add',
  templateUrl: './car-add.component.html',
  standalone: false,
  styleUrls: ['./car-add.component.css']
})
export class CarAddComponent implements OnInit {
  public newCarForm!: FormGroup;
  public carModels: CarModel[] = [];

  constructor(
    private carService: CarService,
    private carModelService: CarModelService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    // The form now only has fields from the Car entity
    this.newCarForm = this.formBuilder.group({
      licensePlate: ['', Validators.required],
      year: ['', [Validators.required, Validators.pattern(/^\d{4}$/)]],
      status: [1, Validators.required], // Status 1 = Available
      notes: [''],
      carModel: [null, Validators.required] // Field for selecting the model
    });

    // We load ALL models so the user can choose one
    this.carModelService.getAllCarModels().subscribe({
      next: (response) => {
        this.carModels = response.data.values as CarModel[];
      },
    });
  }

  public addCar() {
    if (!this.newCarForm.valid) {
      alert('Please fill out all required fields correctly!');
      return;
    }

    // Create the DTO to send to the backend
    const carData = this.newCarForm.value;

    this.carService.addNewCar(carData).subscribe({
      next: (res) => {
        alert('Vehicle has been added successfully!');
        this.router.navigate(['/cars']);
      },
      error: (err) => {
        alert('Failed to add the vehicle!');
        console.error(err);
      },
    });
  }
}
