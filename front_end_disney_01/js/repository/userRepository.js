import { FetchUtil } from "./fetchutil.js";

export class UserRepository {
  static LOGIN_USER = "ACTION=USER.LOGIN";
  static async login() {
    try {
      // http://localhost:8080/untitled/Controller?ACTION=USER.LOGIN
      const response = await fetch(`${FetchUtil.BASE_URL}${this.LOGIN_USER}`);
      if (!response.ok) {
        throw new Error("Error al obtener las películas");
      }
      return await response.json();
    } catch (error) {
      console.error(error);
      return [];
    }
  }
}
