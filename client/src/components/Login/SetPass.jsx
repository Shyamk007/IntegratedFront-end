import React, { useState } from "react";
import axios from "axios";

const SetPass = () => {
  const [userName, setUserName] = useState({
    userName: "",
  });
  const [otp, setOtp] = useState({
    otp: "",
  });
  const [password, setPassword] = useState({
    password: "",
  });
  const handleChangeU = (event) => {
    setUserName(event.target.value);
  };
  const handleChangeO = (event) => {
    setOtp(event.target.value);
  };
  const handleChangeP = (event) => {
    setPassword(event.target.value);
  };

  const submitForm = () => {
    let object = {
      userName,
      otp,
      password,
    };
    axios
      .post("http://localhost:8080/stud_auth/change_password", object)
      .then((response) => {
        console.log(response);
        alert("password changed");
      })
      .catch((error) => {
        console.log(error);
        alert("Error");
      });
  };
  return (
    <div className="w-100 h-[95vh]">
      <div className="justify-center mx-[30%] my-[5%] w-[40%] h-[70%] rounded-xl bg-blue-200 bg-opacity-50 backdrop-blur-sm shadow-md">
        <div className=" flex justify-center text-4xl font-semibold pt-10">
          Password Change
        </div>
        <div className="flex flex-col w-[80%] mx-[10%] my-[10%] justify-center">
          <form className=" mt-6">
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
                onChange={handleChangeU}
              />
            </div>
            <div className="mb-2">
              <label
                for="username"
                className="block text-sm font-semibold text-gray-800"
              >
                OTP
              </label>
              <input
                type="text"
                className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                id="otp"
                onChange={handleChangeO}
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
                type="text"
                className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                id="password"
                onChange={handleChangeP}
              />
            </div>
            <div className="flex justify-center mt-6">
              <button
                type="button"
                onClick={submitForm}
                className=" w-[20%] px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600"
              >
                Set
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SetPass;
