import React, { useState, useEffect } from "react";
import FacService from "./../../services/FacService";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import { styled } from "@mui/material/styles";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import { useNavigate } from "react-router-dom";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

const FacProject = () => {
  const navigate = useNavigate();
  const [project, setProject] = useState({});
  const [isdownload, setIsDownload] = useState(false);
  const [rangeValue, setRangeValue] = useState(0);
  const [feedback, setFeedback] = useState("");
  const [radioValue, setRadioValue] = useState("IN_PROGRESS");
  const [prn, setPrn] = useState("");

  useEffect(() => {
    const pathName = window.location.pathname;
    const paths = pathName.split("/");
    console.log(paths[2]);
    FacService.projectByPRN(paths[2]).then((res) => {
      setProject(res);
      //    console.log(res);
    });
    setPrn(paths[2]);
    // };
  }, []);

  function handleRadioChange(event) {
    event.preventDefault();
    setRadioValue(event.target.value);
    //   console.log(rangeValue);
  }

  function sendEmail(prn) {
    FacService.sendCompMail(prn).then((res) => {
      alert("Email send");
    });
  }

  function sendIncEmail(prn) {}

  function handleSubmit() {
    let obj = {
      feedback: feedback,
      ratings: rangeValue,
      status: radioValue,
    };
    FacService.updateProjectStatus(prn, obj).then((res) => {
      console.log("upadated Succesfully");
      FacService.projectByPRN(prn).then((res) => {
        res.status === "COMPLETED" ? sendEmail(prn) : sendIncEmail(prn);
      });
    });
    navigate(`/FacView`);
  }

  function handleRangeChange(event) {
    setRangeValue(event.target.value);
    // console.log(rangeValue);
  }

  function handleFeedbackChange(event) {
    setFeedback(event.target.value);
    // console.log(rangeValue);
  }

  const handleCancel = () => {
    alert("cancel");
    navigate(`/FacView`);
  };
  const handlePPT = async (fileType) => {
    let userName = "shyam";
    let pptu = `http://localhost:8080/student/get_ppt/${userName}`;
    let srsu = `http://localhost:8080/student/get_srs/${userName}`;
    let reportu = `http://localhost:8080/student/get_report/${userName}`;
    let urlo = fileType === "ppt" ? pptu : fileType === "srs" ? srsu : reportu;
    try {
      setIsDownload(true);
      const response = await fetch(urlo, {
        method: "GET",
        headers: {
          "Content-Type": "application/pdf",
        },
      });
      const blob = await response.blob();
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = fileType + prn + ".pdf";
      a.click();
      window.URL.revokeObjectURL(url);
      setIsDownload(false);
      console.log("after false");
    } catch (error) {
      setIsDownload(false);
      console.log("Error while downloading ");
    }
  };
  return (
    <div>
      <Container maxWidth="sm">
        <Box sx={{ bgcolor: "#cfe8fc", height: "100vh" }}>
          <Typography variant="h4" gutterBottom>
            Project Name : {project.title}
          </Typography>
          <Typography variant="h6" gutterBottom>
            Project Description : {project.description}
          </Typography>
          <Stack direction="row" spacing={2}>
            <Button
              variant="outlined"
              color="success"
              onClick={() => handlePPT("ppt")}
            >
              Download PPT
            </Button>
            <Button
              variant="outlined"
              color="success"
              onClick={() => handlePPT("srs")}
            >
              Download SRS
            </Button>
            <Button
              variant="outlined"
              color="success"
              onClick={() => handlePPT("report")}
            >
              Download Report
            </Button>
          </Stack>
          {/* <Typography variant="h6" gutterBottom>
            Status :
          </Typography> */}
          <FormControl>
            <FormLabel id="demo-radio-buttons-group-label">Status</FormLabel>
            <RadioGroup
              aria-labelledby="demo-radio-buttons-group-label"
              defaultValue="IN_PROGRESS"
              name="radio-buttons-group"
            >
              <FormControlLabel
                value="COMPLETED"
                checked={radioValue === "COMPLETED"}
                onChange={handleRadioChange}
                control={<Radio />}
                label="Completed"
              />
              <FormControlLabel
                value="IN_PROGRESS"
                checked={radioValue === "IN_PROGRESS"}
                onChange={handleRadioChange}
                control={<Radio />}
                label="InProgress"
              />
            </RadioGroup>
          </FormControl>
          <Typography variant="h6" gutterBottom>
            FeedBck :
          </Typography>
          <input
            type="textarea"
            id="form3Example1w"
            class="form-control"
            onChange={handleFeedbackChange}
          />
          <Typography variant="h6" gutterBottom>
            Rating :
          </Typography>
          <input
            type="range"
            class="transparent h-1.5 w-full cursor-pointer appearance-none rounded-lg border-transparent bg-neutral-20"
            min="0"
            max="5"
            step="1"
            id="customRange3"
            value={rangeValue}
            onChange={handleRangeChange}
          />
          <Button variant="outlined" color="success" onClick={handleSubmit}>
            Submit
          </Button>
          <Button variant="outlined" color="success" onClick={handleCancel}>
            cancel
          </Button>
        </Box>
      </Container>
    </div>
  );
};

export default FacProject;
