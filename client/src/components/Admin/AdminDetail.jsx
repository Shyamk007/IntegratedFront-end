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

const AdminDetail = (props) => {
  const [faculty, setFaculty] = useState({});

  return (
    <div>
      <Grid container>
        <Grid item xs={12}>
          <ThemeProvider theme={darkTheme}>
            <Box>
              <Item elevation={6}>
                <Typography
                  variant="h4"
                  gutterBottom
                  style={{ paddingTop: "20px" }}
                >
                  Admin Dashboard
                </Typography>

                <img
                  src={profilePic}
                  style={{
                    height: "150px",
                    width: "150px",
                    marginLeft: "170px",
                  }}
                ></img>

                <Typography
                  variant="h6"
                  gutterBottom
                  style={{ paddingTop: "30px" }}
                >
                  Name : Rushikesh&nbsp; Pise
                </Typography>
                <Typography
                  variant="h6"
                  gutterBottom
                  style={{ paddingBottom: "30px" }}
                >
                  EMAIL : rpise4938@gmail.com
                </Typography>
              </Item>
            </Box>
          </ThemeProvider>
        </Grid>
      </Grid>
    </div>
  );
};

export default AdminDetail;
