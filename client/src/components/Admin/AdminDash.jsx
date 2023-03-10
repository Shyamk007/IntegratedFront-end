import React, { useState } from "react";
import Grid from "@mui/material/Grid";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import AdminDetail from "./AdminDetail";
import AdFacList from "./AdFacList";
import AdStudList from "./AdStudList";
//import { useState } from "react";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

const AdminDash = () => {
  const [updated, setUpdated] = useState(true);

  const handleUpdate = () => {
    setUpdated(!updated);
  };

  return (
    <div>
      <Grid container spacing={2}>
        <Grid item xs={4} style={{ marginTop: "30px", marginLeft: "30px" }}>
          <Item>
            <AdminDetail></AdminDetail>
          </Item>
        </Grid>
        <Grid item xs={7} style={{ marginTop: "40px" }}>
          <AdFacList />
          <div style={{ marginTop: "20px" }}>
            <AdStudList />
          </div>
        </Grid>
        <Grid item xs={1}></Grid>
      </Grid>
    </div>
  );
};

export default AdminDash;
