import React, { useEffect, useState } from "react";
import axios from "axios";
import StuAuthenticationService from "../../services/StuAuthenticationService";
import { Link } from "react-router-dom";
import StuDashboard from "./StuDashboard";

const stuProfile = () => {
  const [profileData, setProfileData] = useState({});
  const [completedProjects, setCompletedProjects] = useState({});
  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/student/profile/project?userName=${StuAuthenticationService.getUserName()}`
      )
      .then((data) => {
        console.log(data.data);
        setProfileData(data.data);
      });
    axios
      .get(`http://localhost:8080/student/getcompletedprojects`)
      .then((data) => {
        console.log(data.data);
        setCompletedProjects(data.data);
      });
  }, []);
  return (
    <>
      <StuDashboard profileData={profileData} />
    </>
  );
};

export default stuProfile;
