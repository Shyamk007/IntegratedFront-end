import React, { useState } from "react";
import pic1 from "../assets/pic1.jpg";
import FacService from "../../services/FacService";
import { useNavigate } from "react-router-dom";

const FacDetails = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    experience: "",
    qualification: "",
  });
  const navigate = useNavigate();
  const handleChange = (e) => {
    let data = formData;
    data[e.target.name] = e.target.value;
    setFormData(data);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const pathName = window.location.pathname;
    const paths = pathName.split("/");
    console.log(paths[2]);
    console.log(formData);
    FacService.addFacDetails(paths[3], formData).then((res) => {
      alert("data updated sucesfully");
      navigate(`/`);
    });

    //axios.post("localhost://8080/", formData).then();
  };

  return (
    <main className="h-[120vh] flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-blue-200 flex rounded-lg w-1/2 bg-opacity-50 backdrop-blur-sm shadow-md"
      >
        <div className=" flex-1 justify-center  text-gray-700 p-20">
          <h1 className="text-3xl font-semibold pb-2">Faculty Details</h1>
          <p className="text-lg text-gray-500">
            Fill the personal details of the Faculty.
          </p>
          <div className="mt-6 ">
            <div className="pb-4">
              <label
                className="block font-medium text-sm pb-2"
                for="first-name"
              >
                First Name:
              </label>
              <input
                onChange={handleChange}
                className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-1/2 focus:border-blue-500 focus:ring-blue-500"
                type="text"
                name="firstName"
                placeholder="First Name"
              />
            </div>
            <div className="pb-4">
              <label className="block font-medium text-sm pb-2" for="last-name">
                Last Name:
              </label>
              <input
                onChange={handleChange}
                className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-1/2 focus:border-blue-500 focus:ring-blue-500"
                type="text"
                name="lastName"
                placeholder="Last Name"
              />
            </div>
            <div className="pb-4">
              <label className="block font-medium text-sm pb-2" for="prn">
                Qualification:
              </label>
              <input
                onChange={handleChange}
                className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-1/2 focus:border-blue-500 focus:ring-blue-500"
                type="text"
                name="qualification"
                placeholder="Enter Qualification"
              />
            </div>
            <div className="pb-4">
              <label className="block font-medium text-sm pb-2" for="roll">
                Experience :
              </label>
              <input
                onChange={handleChange}
                className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-1/2 focus:border-blue-500 focus:ring-blue-500"
                type="text"
                name="experience"
                placeholder="Enter Experience"
              />
            </div>
            <div className="pb-4">
              <label className="block font-medium text-sm pb-2" for="email">
                Email:
              </label>
              <input
                onChange={handleChange}
                className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-1/2 focus:border-blue-500 focus:ring-blue-500"
                type="email"
                name="email"
                placeholder="Enter Email"
              />
            </div>

            <button
              type="submit"
              className="bg-blue-500 text-md font-medium text-white py-3 mt-6 rounded-lg  w-[100px]"
            >
              Submit
            </button>
          </div>
        </div>
      </form>
    </main>
  );
};

export default FacDetails;
