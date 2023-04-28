import LogoutElements from "./logout.elements";


export class LogoutPageMethods {
    logoutPage() {
        LogoutElements.logoutPage.getButtonAccount();
        LogoutElements.logoutPage.getLogoutButton();
    }
}


export default LogoutPageMethods;
