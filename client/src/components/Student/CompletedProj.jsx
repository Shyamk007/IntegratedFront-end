import * as React from "react";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axios from "axios";

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

const CompletedProj = () => {
  const [completedProjects, setCompletedProjects] = React.useState([]);
  React.useEffect(() => {
    axios
      .get(`http://localhost:8080/student/getcompletedprojects`)
      .then((data) => {
        console.log(data.data);
        setCompletedProjects(data.data);
      });
  }, []);

  return (
    <div className="min-h-[100vh]">
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>PRN</StyledTableCell>
              <StyledTableCell align="left">Name</StyledTableCell>
              <StyledTableCell align="left">Title</StyledTableCell>
              <StyledTableCell align="left">Description</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {completedProjects.map((row) => (
              <StyledTableRow key={row.proj_Id}>
                <StyledTableCell component="th" scope="row">
                  {row.studentProjectDetails.prn}
                </StyledTableCell>
                <StyledTableCell align="left">
                  {row.studentProjectDetails.firstName +
                    " " +
                    row.studentProjectDetails.lastName}
                </StyledTableCell>
                <StyledTableCell align="left">{row.title}</StyledTableCell>
                <StyledTableCell align="left">
                  {row.description}
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default CompletedProj;
