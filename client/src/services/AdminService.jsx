import { myAxios } from "./helper";

class AdminService {
  constructor() {
    this.baseUrl = "http://localhost:8080/";
  }
  getFaculty = () => {
    return myAxios.get(`admin/allfaculties`).then((response) => response.data);
  };
  deleteFaculty = (userName) => {
    return myAxios
      .delete(`/admin/deletefaculty/${userName}`)
      .then((response) => response.data);
  };
  verifyFac = (id) => {
    return myAxios
      .put(`/admin/verified/${id}`)
      .then((response) => response.data);
  };
  login = (obj) => {
    return myAxios
      .post(`admin_auth/admin_signin`, obj)
      .then((response) => response.data);
  };
  getStudent = () => {
    return myAxios.get(`admin/allstudents`).then((response) => response.data);
  };
}
export default new AdminService();
