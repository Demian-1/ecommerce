import { Product } from "./Product";

export class Category {
    id : number = 0;
    name: string = '';
    products: Product[] | null = null;
}

export class CategoryDTO {
    name: string = "";
}