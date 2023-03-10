import axios from "axios";
import React, { useRef, useState } from "react";
import StuAuthenticationService from "../../services/StuAuthenticationService";
import { useNavigate } from "react-router-dom";

const ProjDetails = () => {
  const [formdata, setFormData] = useState({
    title: "",
    description: "",
  });
  const navigate = useNavigate();
  const srsRef = useRef(null);
  const pptRef = useRef(null);
  const reportRef = useRef(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    let username = StuAuthenticationService.getUserName();
    const srsForm = new FormData();
    srsForm.append("srsFile", srsRef.current.files[0]);
    const pptForm = new FormData();
    pptForm.append("pptFile", pptRef.current.files[0]);
    const reportForm = new FormData();
    reportForm.append("reportFile", reportRef.current.files[0]);
    try {
      await axios.put(
        `http://localhost:8080/student/updateProjectDetails/${username}`,
        formdata
      );
      await axios.post(
        `http://localhost:8080/student/upload_srs/${username}`,
        srsForm,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
      await axios.post(
        `http://localhost:8080/student/upload_ppt/${username}`,
        pptForm,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
      await axios.post(
        `http://localhost:8080/student/upload_report/${username}`,
        reportForm,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
      alert("Upload");
      navigate("/StuProfile");
    } catch (err) {
      console.error(err);
    }
  };
  const handleChange = (e) => {
    let data = formdata;
    data[e.target.name] = e.target.value;
    setFormData(data);
  };
  console.log(reportRef?.current?.files);
  return (
    <main className="h-[120vh] flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-blue-200 p-20 rounded-lg w-1/2 bg-opacity-50 backdrop-blur-sm shadow-md"
      >
        <h1 className="text-3xl text-gray-700 font-semibold p-5 text-center">
          Project Details
        </h1>
        <div className="pl-[150px]">
          <div className="pb-4 ">
            <label className="block font-medium text-sm pb-2" for="first-name">
              Title:
            </label>
            <input
              onChange={handleChange}
              className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-2/3 focus:border-blue-500 focus:ring-blue-500"
              type="text"
              name="title"
              placeholder="Title"
            />
          </div>
          <div className="pb-4">
            <label className="block font-medium text-sm pb-2" for="last-name">
              Description:
            </label>
            <textarea
              onChange={handleChange}
              className=" border-2 border-gray-500 input-lg text-sm font-medium p-2 rounded-md w-2/3 h-[15vh] focus:border-blue-500 focus:ring-blue-500"
              type="text"
              name="description"
              placeholder="Project Description"
            ></textarea>
          </div>
          <div className="pb-4">
            <label className="block font-medium text-sm pb-2" for="last-name">
              SRS:
            </label>
            <input
              className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-2/3 focus:border-blue-500 focus:ring-blue-500"
              type="file"
              ref={srsRef}
              name="srs"
            />
          </div>
          <div className="pb-4">
            <label className="block font-medium text-sm pb-2" for="last-name">
              PPT:
            </label>
            <input
              ref={pptRef}
              className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-2/3 focus:border-blue-500 focus:ring-blue-500"
              type="file"
              name="ppt"
            />
          </div>
          <div className="pb-4">
            <label className="block font-medium text-sm pb-2" for="last-name">
              Report:
            </label>
            <input
              ref={reportRef}
              className=" border-2 border-gray-500 text-sm font-medium p-2 rounded-md w-2/3 focus:border-blue-500 focus:ring-blue-500"
              type="file"
              name="report"
            />
          </div>
          <button
            type="submit"
            className="bg-blue-500 flex text-md font-medium text-white py-3 mt-6 rounded-lg  justify-center w-2/3"
          >
            Done
          </button>
        </div>
      </form>
    </main>
  );
};

export default ProjDetails;
