import React, { useState, useEffect } from "react";
import { signUp } from "../../services/userService";
import axios from "axios";

const StuSignUp = () => {
  const [data, setData] = useState({
    userName: "",
    password: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {
    console.log(data);
  }, [data]);

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const submitForm = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/stud_auth/student_signup", data)
      .then((response) => {
        console.log(data);
        const result = response.data;
        console.log(result);
        alert("SignUp success");
      })
      .catch((error) => {
        console.log(error);
        console.log("Error Log");
      });

    // signUp(data)
    //   .then((resp) => {
    //     console.log(resp);
    //     console.log("success");
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //     console.log("Error Log");
    //   });
  };

  return (
    <div className="w-100 h-[95vh]">
      <div className="justify-center mx-[30%] my-[5%] w-[40%] h-[70%] rounded-xl bg-blue-200 bg-opacity-50 backdrop-blur-sm shadow-md">
        <div className=" flex justify-center text-4xl font-semibold pt-10">
          Student Sign Up
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
                onChange={(e) => handleChange(e, "userName")}
                value={data.userName}
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
                value={data.password}
              />
            </div>
            <a href="#" className="text-xs text-purple-600 hover:underline">
              Forget Password?
            </a>
            <div className="flex justify-center mt-6">
              <button
                type="submit"
                className=" w-[20%] px-4 py-2 tracking-wide text-white transition-colors duration-200 transform bg-purple-700 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600"
              >
                Sign Up
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default StuSignUp;
