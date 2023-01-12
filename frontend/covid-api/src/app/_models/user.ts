export interface User {
    email: string;
    firstname: string;
    lastname: string;
    phone: string;
    password: string;
    disabled: boolean;
    authdata?: string;
}
