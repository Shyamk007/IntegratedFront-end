import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const ForgetPass = () => {
  const submitForm = (event) => {
    event.preventDefault();
    axios
      .post(`http://localhost:8080/email/forgot_password/${username}`)
      .then((response) => {
        console.log(response);
        navigate("/SetPass");
      })
      .catch((error) => {
        console.log(error);
        alert("code not sent");
      });
  };
  const navigate = useNavigate();
  const [username, setUsername] = useState();

  return (
    <div className="w-100 h-[95vh]">
      <div className="justify-center mx-[30%] my-[5%] w-[40%] h-[70%] rounded-xl bg-blue-200 bg-opacity-50 backdrop-blur-sm shadow-md">
        <div className=" flex justify-center text-4xl font-semibold pt-10">
          Password Change
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
                type="username"
                className="block w-full px-4 py-2 mt-2 text-purple-700 bg-white border rounded-md focus:border-purple-400 focus:ring-purple-300 focus:outline-none focus:ring focus:ring-opacity-40"
                id="username"
                onChange={(event) => setUsername(event.target.value)}
                value={username}
              />
            </div>
            <div className="flex justify-center mt-6">
              <button
                type="submit"
                className=" w-[30%] px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600"
              >
                Send Code
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ForgetPass;
