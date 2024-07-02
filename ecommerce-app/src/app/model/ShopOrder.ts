export interface ShopOrder {
    id?: number;  
    orderDate: string;
    address: any; 
    shippingMethod: string;
    orderTotal: number;
    orderStatus: string;
    user: any;  
    userPaymentMethods: any[];  
    orderLineItems?: any[];  
  }
  