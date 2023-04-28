

export class LogoutElements {
    static get logoutPage() {
        return {
            getButtonAccount: () => cy.get('#navbarAccount').click(),
            getLogoutButton: () => cy.get('#navbarLogoutButton').click()
        }
    }
}

export default LogoutElements;
