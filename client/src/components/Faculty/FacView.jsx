import React, { useEffect, useState } from "react";
import FacDashboard from "./FacDashboard";
import FacDetails from "./FacDetails";
import FacProject from "./FacProject";
import FacStudentlist from "./FacStudentlist";
import Grid from "@mui/material/Grid";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import FacUpdate from "./FacUpdate";
//import { useState } from "react";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

const FacView = () => {
  const [updated, setUpdated] = useState(true);
  const [userName, setUserName] = useState("");

  useEffect(() => {
    const pathName = window.location.pathname;
    const paths = pathName.split("/");
    console.log(paths[2]);
    setUserName(paths[2]);
  }, []);

  const handleUpdate = () => {
    setUpdated(!updated);
  };

  return (
    <div>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <Item>
            {updated ? (
              <FacDashboard user={userName} update={handleUpdate} />
            ) : (
              <FacUpdate update={handleUpdate}></FacUpdate>
            )}
          </Item>
        </Grid>
        <Grid item xs={7}>
          <FacStudentlist></FacStudentlist>
        </Grid>
        <Grid item xs={1}></Grid>
      </Grid>
    </div>
  );
};

export default FacView;
