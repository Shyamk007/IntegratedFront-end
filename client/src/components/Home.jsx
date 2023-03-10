import React from "react";
import flat from "./assets/flat.svg";

const Home = () => {
  return (
    <div className="flex justify-around h-[95vh] px-[6%] py-[80px]">
      <div className=" flex flex-col w-[42%] ">
        <div className=" text-[64px] font-semibold ">
          <h1>Project Diary.</h1>
        </div>
        <div className="text-[52px] font-semibold text-violet-700 pb-6">
          <p className="leading-snug">Project Management Made Easy</p>
        </div>
        <div className="text-xl">
          <p className="leading-10">
            The Purpose of this online portal is to provide a centralized
            platform for students to submit their project data and personal
            information and for Faculty to review and rate the projects.Most
            important Purpose of this project is to easily provie a stable
            platform to manage all the project which have been submitted to the
            college or educational institute.
          </p>
        </div>
      </div>
      <div className="w-[42%] h-[100px] pt-[60px]">
        <img src={flat} alt="image" className="w-[100%] h-[400px]" />
      </div>
    </div>
  );
};

export default Home;
