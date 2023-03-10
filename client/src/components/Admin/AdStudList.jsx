import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AdminService from "../../services/AdminService";
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

const AdStudList = () => {
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
    AdminService.getStudent().then((res) => {
      setStudentList(res);
      console.log(res);
    });
    // };
  }, []);

  const handleDelete = (prn) => {
    alert("Delete Successfull");
    let facTemp = studentList;
    facTemp = facTemp.filter((el) => {
      return el.prn != prn;
    });
    console.log(facTemp);
    setStudentList(facTemp);
  };
  return (
    <div>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Roll Number</StyledTableCell>
              <StyledTableCell>Name</StyledTableCell>
              <StyledTableCell>Batch</StyledTableCell>
              <StyledTableCell>PRN</StyledTableCell>
              <StyledTableCell>Email</StyledTableCell>
              <StyledTableCell>Submit Date</StyledTableCell>
              <StyledTableCell>&nbsp;</StyledTableCell>
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
                <StyledTableCell>
                  {row.firstName}&nbsp;
                  {row.lastName}
                </StyledTableCell>
                <StyledTableCell>{row.batch}</StyledTableCell>
                <StyledTableCell>{row.prn}</StyledTableCell>
                <StyledTableCell>{row.email}</StyledTableCell>
                <StyledTableCell>{row.submitDate}</StyledTableCell>
                <StyledTableCell>
                  <button onClick={() => handleDelete(row.prn)}>Delete</button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default AdStudList;
