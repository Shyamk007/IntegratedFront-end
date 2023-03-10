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
import TextField from "@mui/material/TextField";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

const darkTheme = createTheme({ palette: { mode: "dark" } });
const lightTheme = createTheme({ palette: { mode: "light" } });

const FacUpdate = (props) => {
  const [faculty, setFaculty] = useState({});
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [qualification, setQualification] = useState("");
  const [experience, setExperience] = useState(0);
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    experience: "",
    qualification: "",
  });
  const handleChangeFName = (e) => {
    setFirstName(e.target.value);
  };
  const handleChangeEmail = (e) => {
    setEmail(e.target.value);
  };
  const handleChangeLName = (e) => {
    setLastName(e.target.value);
  };
  const handleChangeExperience = (e) => {
    setExperience(e.target.value);
  };
  const handleChangeQual = (e) => {
    setQualification(e.target.value);
  };
  const handleSubmit = (e) => {
    const pathName = window.location.pathname;
    const paths = pathName.split("/");
    console.log(paths[2]);
    e.preventDefault();
    console.log(formData);
    let obj = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      qualification: qualification,
      experience: experience,
    };
    FacService.updatefaculty(paths[2], obj).then((res) => {
      alert("data updated sucesfully");
    });
    props.update();
  };
  useEffect(() => {
    FacService.profileData("amar").then((res) => {
      setFaculty(res);
      console.log(res);
    });
    // };
  }, []);

  const handleCancel = () => {
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
                  Faculty Update
                </Typography>

                <img
                  src={profilePic}
                  style={{
                    height: "150px",
                    width: "150px",
                    marginLeft: "150px",
                  }}
                ></img>
                <Box
                  component="form"
                  sx={{
                    "& > :not(style)": { m: 1, width: "25ch" },
                  }}
                  noValidate
                  autoComplete="off"
                >
                  <TextField
                    id="outlined-basic"
                    label="First Name"
                    variant="outlined"
                    onChange={handleChangeFName}
                  />
                  <TextField
                    id="filled-basic"
                    label="Last Name"
                    variant="outlined"
                    onChange={handleChangeLName}
                  />
                </Box>
                <Box>
                  <TextField
                    id="filled-basic"
                    label="Email"
                    variant="outlined"
                    onChange={handleChangeEmail}
                  />
                </Box>
                <Box>
                  <TextField
                    id="filled-basic"
                    label="Qualification"
                    variant="outlined"
                    onChange={handleChangeQual}
                  />
                </Box>
                <Box>
                  <TextField
                    id="filled-number"
                    label="Number"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    variant="filled"
                    onChange={handleChangeExperience}
                  />
                </Box>
                <Button
                  variant="outlined"
                  color="success"
                  onClick={handleSubmit}
                >
                  Update
                </Button>
                <Button
                  variant="outlined"
                  color="success"
                  onClick={handleCancel}
                >
                  cancel
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

export default FacUpdate;
