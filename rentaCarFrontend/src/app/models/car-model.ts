// Define helper types to match the backend enums
export type VehicleCategory = 'EKONOMIK' | 'STANDARD' | 'PREMIUM' | 'VAN';
export type TransmissionType = 'MANUELNI' | 'AUTOMATIK';
export type FuelType = 'DIZEL' | 'BENZIN' | 'ELEKTRICNI' | 'HIBRID';

export interface CarModel {
  id: number;
  make: string; // e.g., "Skoda"
  name: string; // e.g., "Fabia"
  engineDescription: string; // e.g., "1.4 TDI"
  category: VehicleCategory;
  seats: number;
  transmission: TransmissionType;
  fuelType: FuelType;
  doors: number;
  luggageCapacity: number;
  hasAc: boolean;
  pictureUrl: string;
  logoUrl: string;
  // Price List
  priceTier1to3: number;
  priceTier4to7: number;
  priceTier8to15: number;
  priceTier16to30: number;
  priceTier31to45: number;
  priceTier46plus: number;
}
