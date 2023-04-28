

import {LoginPageMethods} from "./POM/login/login.methods";
import {OutOfStockMethods} from "./POM/outOfStock/outOfStock.methods";
import {LogoutPageMethods} from "./POM/logout/logout.methods";

const emailRegister = "ace.gjorgjievski@example.com";
const passwordRegister = "Ace12345~";

// Аце Ѓорѓиевски - 201183
describe("home page", () => {

    context("Adding and deleting product", () => {
        it.only("product add and delete", () => {
            //register - use this only when the user has been deleted from the DB

            // cy.get(".mat-button-wrapper").contains("Account").click();
            // cy.get(".mat-menu-content").contains("Login").click();
            // cy.get("#newCustomerLink > .primary-link").click();
            // cy.get("#emailControl").type(emailRegister);
            // cy.get("#passwordControl")
            //     .type(passwordRegister);
            // cy.get("#repeatPasswordControl")
            //     .type(passwordRegister)
            // cy.get(".mat-select-placeholder")
            //     // .scrollIntoView()
            //     .click()
            //     .then(() => {
            //         cy.get("#mat-select-2-panel")
            //         .contains("Your favorite movie?")
            //         .click();
            //     })
            // cy.get("#securityAnswerControl")
            //     .type("Collide");

            // cy.get("#registerButton > .mat-button-wrapper")
            //     .click();

            // ---------------------------------------------------------- //
            
            //Task No. 2 & 3

            //login
            cy.get(".mat-button-wrapper").contains("Account").click();
            cy.get(".mat-menu-content").contains("Login").click();
            cy.get("#email").type(emailRegister);
            cy.get("#password").type(passwordRegister);
            cy.get("#loginButton").click();




            //add
            cy.get('.mat-grid-list')
                .find("mat-card")
                .each((card) => {
                    const $card = Cypress.$(card);
                    const productSoldOutFound = $card.find('span').text().trim().startsWith("Sold Out");
                    if(productSoldOutFound === false) {
                        console.log("sold found: "+ $card.find('span'));
                        cy.wrap($card)
                        .find("button")
                        .should('be.visible') // wait for the button to be visible
                        .click();
                        return false;
                    }
                    
                })
            cy.get('.mat-simple-snack-bar-content').should("exist");

            //delete
            cy.get('.mat-toolbar-row > .mat-focus-indicator.ng-star-inserted').click();
            cy.get('.mat-row').find('.cdk-column-remove').find("button").click();


            //out-of-stock
            // home page again
            cy.get('[routerlink="/search"] > .mat-button-wrapper > span').click();

            //pagination
            // cy.get(".mat-paginator-container")
            //     .then(() => {
            //         cy.get(".mat-select-arrow-wrapper").click().then(() => {
            //             cy.get("#mat-option-22 > .mat-option-text").click();
            //         })
            //     })

            cy.get(".mat-paginator-container")
            .then(() => {
                cy.get(".mat-paginator-page-size > .mat-form-field > .mat-form-field-wrapper > .mat-form-field-flex > .mat-form-field-infix")
                .click().then(() => {
                    cy.get("#mat-option-8 > .mat-option-text").click();
                })
            })

                

            //try to add a product that is out of stock
            cy.get('.mat-grid-list')
                .find("mat-card")
                .each((card) => {
                    const $card = Cypress.$(card);
                    const productSoldOutFound = $card.find('span').text().trim().startsWith("Sold Out");
                    if(productSoldOutFound === true) {
                        // console.log("sold found: "+ $card.find('span'));
                        cy.wrap($card)
                        .find("button")
                        .should('be.visible') // wait for the button to be visible
                        .click();
                        return false;
                    }
                    
                })

        })
        const loginInstance = new LoginPageMethods();
        const outOfStockProductInstance = new OutOfStockMethods();
        const logoutInstance = new LogoutPageMethods();

        //task No.4
        it("POM", () => {
            loginInstance.loginPage()
            outOfStockProductInstance.addOutOfStockProduct();
            logoutInstance.logoutPage();
        })
    })
})


export default {
    emailRegister,
    passwordRegister
  };

