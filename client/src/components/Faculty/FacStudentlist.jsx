import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import FacService from "./../../services/FacService";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const FacStudentlist = () => {
  const [studentList, setStudentList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // if (faculty == {}) {
    //   fetchData();
    // }
    // const fetchData = () => {
    const { pathName } = window.location;
    //const paths = pathName.split("/").filter(entry=>entry)
    console.log(pathName);
    FacService.studentList().then((res) => {
      setStudentList(res);
      console.log(res);
    });
    // };
  }, []);
  const handleClick = (prn) => {
    alert("PRN" + prn);
    navigate(`/FacProject/${prn}`);
  };
  return (
    <div>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Roll Number</StyledTableCell>
              <StyledTableCell align="right">Name</StyledTableCell>
              <StyledTableCell align="right">Batch&nbsp;</StyledTableCell>
              <StyledTableCell align="right">Submit Date&nbsp;</StyledTableCell>
              <StyledTableCell align="right">&nbsp;</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {studentList.map((row) => (
              <StyledTableRow
                key={row.rollNo}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <StyledTableCell component="th" scope="row">
                  {row.rollNo}
                </StyledTableCell>
                <StyledTableCell align="right">
                  {row.firstName}&nbsp;
                  {row.lastName}
                </StyledTableCell>
                <StyledTableCell align="right">{row.batch}</StyledTableCell>
                <StyledTableCell align="right">
                  {row.submitDate}
                </StyledTableCell>
                <StyledTableCell align="right">
                  {" "}
                  <button onClick={() => handleClick(row.prn)}>View</button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default FacStudentlist;
