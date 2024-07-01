export class Product {
    id: number = 0;
    description: string ='Test description';
    name: string ='Test name';
    image: string ='https://m.media-amazon.com/images/I/61F1Pckv63L._AC_UF1000,1000_QL80_.jpg';
    price: number = 110.20;
}

export class ProductCategory {
    id: number = 0;
    description: string ='';
    name: string ='';
    image: string ='';
    price: number = 0.0;
    idCategory: number = 0;
}
