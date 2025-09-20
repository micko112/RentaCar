import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CarService } from '../../../services/car.service';
import { CarModelService } from '../../../services/car-model.service';
import { Car } from '../../../models/car.model';
import { CarModel } from '../../../models/car-model.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-add',
  templateUrl: './car-add.component.html',
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
  ) {
    this.newCarForm = formBuilder.group({
      licensePlate: new FormControl('', [Validators.required]),
      transmission: new FormControl('', [Validators.required, Validators.pattern(/^(manuelni|automatik)$/i)]),
      year: new FormControl('', [Validators.required, Validators.pattern(/^\d{4}$/)]),
      carModel: new FormControl(null, Validators.required),
      notes: new FormControl(''),
      picture: new FormControl('', [Validators.required, Validators.pattern(/^(https?:\/\/.*)$/)]),
    });
  }

  ngOnInit() {
    this.carModelService.getAllCarModels().subscribe({
      next: (response) => {
        this.carModels = response.data.values as CarModel[];
      },
    });
  }

  public addCar() {
    if (!this.newCarForm.valid) {
      alert('Molimo popunite sva obavezna polja ispravno!');
      return;
    }

    const car = new Car();
    car.licensePlate = this.newCarForm.get('licensePlate')!.value;
    car.transmission = this.newCarForm.get('transmission')!.value.toLowerCase();
    car.year = Number(this.newCarForm.get('year')!.value);
    car.notes = this.newCarForm.get('notes')!.value || '';
    car.picture = this.newCarForm.get('picture')!.value;
    car.status = 1; // 1 = Available

    // Dodeljujemo ceo objekat modela
    car.carModel = this.newCarForm.get('carModel')!.value;

    this.carService.addNewCar(car).subscribe({
      next: (res) => {
        alert('Automobil je uspešno dodat!');
        this.router.navigate(['/cars']);
      },
      error: (err) => {
        alert('Dodavanje automobila nije uspelo!');
      },
    });
  }
}
