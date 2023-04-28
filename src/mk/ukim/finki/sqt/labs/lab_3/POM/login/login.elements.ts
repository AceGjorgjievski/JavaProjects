import registerData from '../../home.cy';

export class ProjectLoginElements {
    static get loginPage() {
        return {
            getButtonAccount: () => cy.get(".mat-button-wrapper").contains("Account").click(),
            getContentLogin: () => cy.get(".mat-menu-content").contains("Login").click(),
            getEmail: () =>  cy.get("#email").type(registerData.emailRegister),
            getPassword: () => cy.get("#password").type(registerData.passwordRegister),
            getLoginButton: () =>  cy.get('#loginButton').click()
        }
    }
}


export default ProjectLoginElements;
