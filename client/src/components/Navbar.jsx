import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import LoginDrop from "./LoginDrop";

const Navbar = () => {
  const [isLogin, setIsLogin] = useState(false);
  const navigate = useNavigate();
  const handleClick = () => {
    setIsLogin(!isLogin);
    navigate(`/`);
  };
  return (
    <nav className="flex w-full items-center justify-between  bg-blue-50 shadow-md h-16 px-[160px]">
      <div className="text-4xl font-bold text-violet-600">
        <Link to="/">Project Diary</Link>
      </div>
      <div>
        <ul className="flex gap-12">
          <li className="text-lg cursor-pointer text-black hover:text-blue-700">
            Home
          </li>
          <li className="text-lg cursor-pointer text-black hover:text-blue-700">
            <Link to="/AboutUs">About Us</Link>
          </li>
          <li className="text-lg cursor-pointer text-black hover:text-blue-700">
            <LoginDrop />
          </li>
          <li className="text-lg cursor-pointer text-black hover:text-blue-700">
            <Link to="/StuSignUp">Sign Up</Link>
          </li>
          <li className="text-lg cursor-pointer text-black hover:text-blue-700">
            {isLogin ? "" : <h1 onClick={handleClick}>Logout</h1>}
          </li>
          {/* <li className="text-xl text-black hover:text-blue-700">
            <Link className="nav-link" aria-current="page" to="/">
              About Us
            </Link>
          </li>
          <li className="text-xl text-black hover:text-blue-700">
            <Link className="nav-link" aria-current="page" to="/">
              Login
            </Link>
          </li>
          <li className="text-xl text-black hover:text-blue-700">
            <Link className="nav-link" aria-current="page" to="/">
              Register
            </Link>
          </li> */}
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
