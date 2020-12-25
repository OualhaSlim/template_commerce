import { IProduit } from 'app/shared/model/produit.model';

export interface ICategorie {
  id?: number;
  nom?: string;
  produits?: IProduit[];
}

export class Categorie implements ICategorie {
  constructor(public id?: number, public nom?: string, public produits?: IProduit[]) {}
}
