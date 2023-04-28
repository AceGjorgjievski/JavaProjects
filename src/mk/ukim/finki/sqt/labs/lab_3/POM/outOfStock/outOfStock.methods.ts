import OutOfStockProductElements from "./outOfStock.elements";



export class OutOfStockMethods {
    addOutOfStockProduct() {
        OutOfStockProductElements.outOfStockProduct.getProductOutOfStock();
        OutOfStockProductElements.outOfStockProduct.getVerifyLabelOutOfStock();
    }
}


export default OutOfStockMethods;

