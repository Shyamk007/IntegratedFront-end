import React, { useState, useEffect } from "react";
import FacService from "./../../services/FacService";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import { createTheme, ThemeProvider, styled } from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import profilePic from "./../../components/assets/profilepic.jpg";
import { textAlign } from "@mui/system";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

const darkTheme = createTheme({ palette: { mode: "dark" } });
const lightTheme = createTheme({ palette: { mode: "light" } });

const FacDashboard = (props) => {
  const [faculty, setFaculty] = useState({});
  useEffect(() => {
    // if (faculty == {}) {
    //   fetchData();
    // }
    // const fetchData = () => {
    const pathName = window.location.pathname;
    const paths = pathName.split("/");
    console.log(paths[2]);
    FacService.profileData(paths[2]).then((res) => {
      setFaculty(res);
      console.log(res);
    });
    // };
  }, []);
  const handleUpdate = () => {
    props.update();
  };
  return (
    <div>
      <Grid container>
        <Grid item xs={12}>
          <ThemeProvider theme={darkTheme}>
            <Box>
              <Item elevation={6}>
                <Typography variant="h4" gutterBottom>
                  Faculty Dashboard
                </Typography>

                <img
                  src={profilePic}
                  style={{
                    height: "150px",
                    width: "150px",
                    marginLeft: "150px",
                  }}
                ></img>

                <Typography variant="h6" gutterBottom>
                  Name : {faculty.firstName}&nbsp;
                  {faculty.lastName}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  ID : {faculty.facultyId}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  EMAIL : {faculty.email}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  QUALIFICATION : {faculty.qualification}
                </Typography>
                <Typography variant="h6" gutterBottom>
                  EXPERIENCE : {faculty.experience}
                </Typography>
                <Button
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

export default FacDashboard;
