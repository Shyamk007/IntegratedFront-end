import { myAxios } from "./helper";

class FacService {
  constructor() {
    this.baseUrl = "http://localhost:8080/";
  }

  profileData = (userName) => {
    return myAxios
      .get(`/faculty/profile/{userName}?userName=${userName}`)
      .then((response) => response.data);
  };
  login = (obj) => {
    return myAxios
      .post(`faculty_auth/faculty_signin/`, obj)
      .then((response) => response.data);
  };

  studentList = () => {
    return myAxios
      .get(`faculty/viewAllStudents`)
      .then((response) => response.data);
  };
  projectByPRN = (prn) => {
    return myAxios
      .get(`student/profile/prn?prn=${prn}`)
      .then((response) => response.data);
  };
  getPPT = (username) => {
    return myAxios.get(`/student/get_ppt/${username}`);
  };
  updateProjectStatus = (prn, obj) => {
    return myAxios
      .put(`faculty/evaluateproject/${prn}`, obj)
      .then((response) => response.data);
  };
  sendCompMail = (prn) => {
    return myAxios
      .post(`/email/successful_evaluation/${prn}`)
      .then((response) => response.data);
  };
  addFacDetails = (userName, obj) => {
    return myAxios
      .post(`/faculty/addfaculty/${userName}`, obj)
      .then((response) => response.data);
  };
  updatefaculty = (userName, obj) => {
    return myAxios
      .put(`/faculty/updatedetails/${userName}`, obj)
      .then((response) => response.data);
  };
}
export default new FacService();
