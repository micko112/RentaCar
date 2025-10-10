import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CarModelService } from '../../../services/car-model.service'; // Verify the path
import { CarModel } from '../../../models/car-model'; // Verify the path

@Component({
  selector: 'app-car-model-add',
  templateUrl: './car-model-add.component.html',
  standalone: false,
  styleUrls: ['./car-model-add.component.css']
})
export class CarModelAddComponent implements OnInit {
  public newCarModelForm!: FormGroup;

  constructor(
    private carModelService: CarModelService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.newCarModelForm = this.formBuilder.group({
      make: ['', Validators.required],
      name: ['', Validators.required],
      engineDescription: [''],
      category: ['EKONOMIK', Validators.required],
      seats: [5, Validators.required],
      transmission: ['MANUELNI', Validators.required],
      fuelType: ['DIZEL', Validators.required],
      doors: [5],
      luggageCapacity: [2],
      hasAc: [true],
      pictureUrl: ['', Validators.required],
      logoUrl: [''],
      priceTier1to3: [0, Validators.required],
      priceTier4to7: [0, Validators.required],
      priceTier8to15: [0, Validators.required],
      priceTier16to30: [0, Validators.required],
      priceTier31to45: [0, Validators.required],
      priceTier46plus: [0, Validators.required]
    });
  }

  public addCarModel() {
    if (!this.newCarModelForm.valid) {
      alert('All fields must be filled out correctly!');
      return;
    }

    const newModel: CarModel = this.newCarModelForm.value;

    this.carModelService.addNewCarModel(newModel).subscribe({
      next: (res) => {
        alert('New car model has been added successfully!');
        this.router.navigate(['/cars']);
      },
      error: (err) => {
        alert('Failed to add the model!');
      }
    });
  }
}
