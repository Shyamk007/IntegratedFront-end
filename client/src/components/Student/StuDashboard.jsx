import React, { useState, useEffect } from "react";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import { createTheme, ThemeProvider, styled } from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import { textAlign } from "@mui/system";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import { Link, useNavigate } from "react-router-dom";
import StuAuthenticationService from "../../services/StuAuthenticationService";
import { Divider } from "@mui/material";
import profilepic from "../assets/profilepic.jpg";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  textAlign: "center",
  color: theme.palette.text.black,
}));

const darkTheme = createTheme({ palette: { mode: "dark" } });
const lightTheme = createTheme({ palette: { mode: "light" } });

const StuDashboard = ({ profileData }) => {
  let profile = profileData?.studentProjectDetails;
  const navigate = useNavigate();
  const handleUpdate = () => {
    navigate("/UpdateDetails");
  };
  return (
    <div className="my-6">
      <Grid container elevation={0}>
        <Grid item xs={12}>
          <ThemeProvider theme={lightTheme}>
            <Box>
              <Item>
                <Typography variant="h4" gutterBottom>
                  Student Dashboard
                </Typography>

                <img
                  src={profilepic}
                  style={{
                    height: "150px",
                    width: "150px",
                    marginLeft: "45%",
                    marginTop: "50px",
                    marginBottom: "30px",
                  }}
                ></img>

                <Typography variant="h6" gutterBottom>
                  Name : {profile?.firstName}&nbsp;
                  {profile?.lastName}
                </Typography>

                <Typography variant="h6" gutterBottom>
                  EMAIL : {profile?.email}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  Batch : {profile?.batch}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  PRN : {profile?.prn}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  RollNo. : {profile?.rollNo}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  Project Title: {profileData?.title}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  Description: {profileData?.description}
                </Typography>
                <div className="flex justify-center text-xl gap-10 py-2">
                  <a
                    className="border-2 border-black px-3 py-2 rounded-md"
                    href={`http://localhost:8080/student/get_srs/${StuAuthenticationService.getUserName()}`}
                    target="_blank"
                  >
                    View SRS
                  </a>
                  <a
                    className="border-2 border-black px-3 py-2 rounded-md"
                    href={`http://localhost:8080/student/get_ppt/${StuAuthenticationService.getUserName()}`}
                    target="_blank"
                  >
                    View PPT
                  </a>
                  <a
                    className="border-2 border-black px-3 py-2 rounded-md"
                    href={`http://localhost:8080/student/get_report/${StuAuthenticationService.getUserName()}`}
                    target="_blank"
                  >
                    View Report
                  </a>
                </div>
                <div className="py-6">
                  <Link
                    to="/CompletedProj"
                    className="border-2 border-black px-3 py-2 rounded-md text-center  text-xl hover:underline "
                  >
                    View Completed Projects
                  </Link>
                </div>
                <Button
                  style={{ marginBottom: "100px" }}
                  variant="outlined"
                  color="success"
                  onClick={handleUpdate}
                >
                  Update
                </Button>
              </Item>
            </Box>
          </ThemeProvider>
        </Grid>
      </Grid>
      {/* <div
        className="rounded ... text-blue-300 text-2xl font-bold"
        style={{ backgroundColor: "black" }}
      >
        FacDashboard
        <h1>
          Name :{faculty.firstName}
          {faculty.lastName}
        </h1>
        <h1>ID : {faculty.facultyId}</h1>
        <h1>Email : {faculty.email}</h1>
        <h1>Qualification : {faculty.qualification}</h1>
        <h1>Experience : {faculty.experience}</h1>
      </div> */}
    </div>
  );
};

export default StuDashboard;
