import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import StuAuthenticationService from "../../services/StuAuthenticationService";
import axios from "axios";

const login = () => {
  const [userCred, setUserCred] = useState({
    userName: "",
    password: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {
    console.log(userCred);
  }, [userCred]);

  const handleChange = (event, property) => {
    setUserCred({ ...userCred, [property]: event.target.value });
  };

  const navigate = useNavigate();

  const submitForm = (event) => {
    event.preventDefault();
    console.log("in Validate");
    StuAuthenticationService.authenticateUser(userCred)
      .then((resp) => {
        console.log("auth successful");
        StuAuthenticationService.storeUserDetails(userCred.userName, resp.data);
        axios
          .get(
            `http://localhost:8080/student/profile/project?userName=${StuAuthenticationService.getUserName()}`
          )
          .then((data) => {
            if (data.data) {
              navigate("/StuProfile");
            } else {
              navigate("/StuDetails");
            }
          })
          .catch((err) => {
            navigate("/StuDetails");
          });
      })
      .catch((err) => {
        console.log("failed auth", err);
        alert("login failed");
        navigate("/Login");
      });
  };

  return (
    <div className="w-100 h-[95vh]">
      <div className="justify-center mx-[30%] my-[5%] w-[40%] h-[70%] rounded-xl bg-blue-200 bg-opacity-50 backdrop-blur-sm shadow-md">
        <div className=" flex justify-center text-4xl font-semibold pt-10">
          Student LOGIN
        </div>
        <div className="flex flex-col w-[80%] mx-[10%] my-[10%] justify-center">
          <form onSubmit={submitForm} method="POST" className=" mt-6">
            <div className="mb-2">
              <label
                for="username"
                className="block text-sm font-semibold text-gray-800"
              >
                Username
              </label>
              <input
                type="text"
                className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                id="username"
                onChange={(e) => handleChange(e, "userName")}
                value={userCred.userName}
              />
            </div>
            <div className="mb-2">
              <label
                for="password"
                className="block text-sm font-semibold text-gray-800"
              >
                Password
              </label>
              <input
                type="password"
                className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                id="password"
                onChange={(e) => handleChange(e, "password")}
                value={userCred.password}
              />
            </div>
            <Link
              to="/ForgetPass"
              className="text-xs text-purple-600 hover:underline"
            >
              Forget Password?
            </Link>
            <div className="flex justify-center mt-6">
              <button
                type="submit"
                className=" w-[20%] px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600"
              >
                Login
              </button>
            </div>
          </form>
          <p className="mt-8 text-md font-light text-center text-gray-700">
            {" "}
            Don't have an account? <Link to="/StuSignUp">Sign Up</Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default login;
