export interface IProduit {
  id?: number;
  nom?: string;
  prix?: number;
  imageContentType?: string;
  image?: any;
  description?: string;
  categorieId?: number;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public nom?: string,
    public prix?: number,
    public imageContentType?: string,
    public image?: any,
    public description?: string,
    public categorieId?: number
  ) {}
}
