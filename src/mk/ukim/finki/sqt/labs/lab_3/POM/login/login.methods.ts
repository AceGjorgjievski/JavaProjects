import ProjectLoginElements from "./login.elements";


export class LoginPageMethods {
    loginPage() {
        ProjectLoginElements.loginPage.getButtonAccount();
        ProjectLoginElements.loginPage.getContentLogin();
        ProjectLoginElements.loginPage.getEmail();
        ProjectLoginElements.loginPage.getPassword();
        ProjectLoginElements.loginPage.getLoginButton();
    }
}

export default LoginPageMethods;

