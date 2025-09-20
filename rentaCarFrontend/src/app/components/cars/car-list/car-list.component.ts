import { Component, OnInit } from '@angular/core';
import { CarService } from '../../../services/car.service';
import { Car } from '../../../models/car.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-car-list',
  standalone: false,
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  public cars: Car[] = [];
  public filteredCars: Car[] = [];

  constructor(private carService: CarService, private router: Router) {}

  ngOnInit() {
    this.carService.getAll().subscribe({
      next: (response) => {
        this.cars = response.data.values as Car[];
        this.filteredCars = this.cars;
      },
      error: (err) => console.error(err)
    });
  }

  deleteCar(c: Car) {
    this.carService.delete(c.id).subscribe({
      next: (res) => {
        alert('Uspešno obrisan automobil!');
        // Ukloni auto iz lokalne liste da se odmah osveži prikaz
        this.cars = this.cars.filter(car => car.id !== c.id);
        this.filteredCars = this.cars;
      },
      error: (err) => {
        console.error(err);
        alert('Brisanje automobila nije uspelo!');
      },
    });
  }

  onSearch(search: string) {
    if (!search) {
      this.filteredCars = this.cars;
    } else {
      this.carService.searchCars(search).subscribe((res) => {
        this.filteredCars = res.data.values as Car[];
      });
    }
  }
}
