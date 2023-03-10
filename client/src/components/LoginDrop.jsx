import * as React from "react";
import Button from "@mui/material/Button";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import { Link, useNavigate } from "react-router-dom";

export default function LoginDrop() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const navigate = useNavigate();
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleFac = () => {
    navigate(`/loginFac`);
  };
  const handleAd = () => {
    navigate(`/loginAd`);
  };
  return (
    <div>
      <span
        id="basic-button"
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
      >
        Login
      </span>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <Link to="/login">
          <MenuItem onClick={handleClose}>Student</MenuItem>
        </Link>
        <MenuItem onClick={handleFac}>Faculty</MenuItem>
        <MenuItem onClick={handleAd}>Admin</MenuItem>
      </Menu>
    </div>
  );
}
