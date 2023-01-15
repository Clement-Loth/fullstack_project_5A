import { Center } from "./center";

export interface User {
    email: string;
    firstName: string;
    lastName: string;
    phone: string;
    password: string;
    disabled: boolean;
    center?: Center;
    id?: bigint;
    role?: string;
    authdata?: string;
}
