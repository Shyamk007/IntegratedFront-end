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

const AdFacList = () => {
  const [studentList, setStudentList] = useState([]);
  const [facultyList, setFacultyList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const { pathName } = window.location;
    //const paths = pathName.split("/").filter(entry=>entry)
    console.log(pathName);
    AdminService.getFaculty().then((res) => {
      setFacultyList(res);
      console.log(res);
    });
    // };
  }, []);

  const changeStatus = (facultyId) => {
    // alert("Status changed");
    let list = [];
    AdminService.verifyFac(facultyId).then(
      AdminService.getFaculty().then((res) => {
        // setFacultyList(res);
        // console.log(res);
        list = res;
      })
    );
    AdminService.getFaculty().then((res) => {
      setFacultyList(res);
      console.log(res);
    });
  };
  const handleDelete = (email) => {
    alert("Delete Successfull");
    let facTemp = facultyList;
    // facultyList.map((el) => {
    //   const index = ar.indexOf(email);
    //   if (index > -1) {
    //     // only splice array when item is found
    //     facTemp.splice(index, 1); // 2nd parameter means remove one item only
    //   }
    // });
    // const index = facTemp.indexOf(email);
    // if (index > -1) {
    //   // only splice array when item is found
    //   facTemp.splice(2, 1);
    // }
    facTemp = facTemp.filter((el) => {
      return el.email != email;
    });
    console.log(facTemp);
    setFacultyList(facTemp);
  };
  return (
    <div>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Faculty Id</StyledTableCell>
              <StyledTableCell>Name</StyledTableCell>
              <StyledTableCell>Email&nbsp;</StyledTableCell>
              <StyledTableCell>Qualification&nbsp;</StyledTableCell>
              <StyledTableCell>Experience</StyledTableCell>
              <StyledTableCell>Status&nbsp;</StyledTableCell>
              <StyledTableCell>&nbsp;</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {facultyList.map((row) => (
              <StyledTableRow
                key={row.facultyId}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <StyledTableCell component="th" scope="row">
                  {row.facultyId}
                </StyledTableCell>
                <StyledTableCell>
                  {row.firstName}&nbsp;
                  {row.lastName}
                </StyledTableCell>
                <StyledTableCell>{row.email}</StyledTableCell>
                <StyledTableCell>{row.qualification}</StyledTableCell>
                <StyledTableCell>{row.experience}</StyledTableCell>
                <StyledTableCell>
                  {row.accStatus === "VERIFIED" ? (
                    "Verified"
                  ) : (
                    <div>
                      <button onClick={() => changeStatus(row.facultyId)}>
                        Verify
                      </button>
                    </div>
                  )}
                </StyledTableCell>
                <StyledTableCell>
                  <button onClick={() => handleDelete(row.email)}>
                    Delete
                  </button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default AdFacList;
