

export class OutOfStockProductElements {

    static get outOfStockProduct() {
        return {
            getProductOutOfStock: () => cy.get('.mat-grid-list')
            .find("mat-card")
            .each((card) => {
                const $card = Cypress.$(card);
                const productSoldOutFound = $card.find('span').text().trim().startsWith("Sold Out");
                if(productSoldOutFound === true) {
                    console.log("sold found: "+ $card.find('span'));
                    cy.wrap($card)
                    .find("button")
                    .scrollIntoView()
                    .should('be.visible') // wait for the button to be visible
                    .click();
                    return false;
                }
                
            }),
            getVerifyLabelOutOfStock: () => cy.get('.mat-snack-bar-container')
        }
    }

}

export default OutOfStockProductElements;
