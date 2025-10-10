import { CarModel } from "./car-model";

export interface Car {
  id: number;
  licensePlate: string;
  year: number;
  status: number;
  notes: string;
  carModel: CarModel; // Nested object with all the details
}
