import React from "react";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import Footer from "./components/Footer";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import FacView from "./components/Faculty/FacView";
import Login from "./components/Login/Login";
import StuSignUp from "./components/Login/StuSignUp";
import StuDetails from "./components/Student/StuDetails";
import ProjDetails from "./components/Student/ProjDetails";
import StuProfile from "./components/Student/StuProfile";
import UpdateDetails from "./components/Student/UpdateDetails";
import UpdateProject from "./components/Student/UpdateProject";
import ForgetPass from "./components/Login/ForgetPass";
import SetPass from "./components/Login/SetPass";
import AboutUs from "./components/AboutUs";
import CompletedProj from "./components/Student/CompletedProj";
import FacLogin from "./components/Faculty/FacLogin";
import FacProject from "./components/Faculty/FacProject";
//import FacView from "./components/Faculty/FacView";
import AdminDash from "./components/Admin/AdminDash";
import AdLogin from "./components/Admin/AdLogin";
import FacSignUp from "./components/Faculty/FacSignUp";
import FacDetails from "./components/Faculty/FacDetails";
const App = () => {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/StuSignUp" element={<StuSignUp />} />
          <Route path="/StuDetails" element={<StuDetails />} />
          <Route path="/UpdateDetails" element={<UpdateDetails />} />
          <Route path="/UpdateProject" element={<UpdateProject />} />
          <Route path="/FacView" element={<FacView />} />
          <Route path="/ProjDetails" element={<ProjDetails />} />
          <Route path="/StuProfile" element={<StuProfile />} />
          <Route path="/ForgetPass" element={<ForgetPass />} />
          <Route path="/SetPass" element={<SetPass />} />
          <Route path="/CompletedProj" element={<CompletedProj />} />
          <Route path="/AboutUs" element={<AboutUs />} />
          <Route path="/FacView/:userName" element={<FacView />} />
          <Route path="/FacProject/:prn" element={<FacProject />} />
          <Route path="/adminDash" element={<AdminDash />} />
          <Route path="/loginFac" element={<FacLogin />} />
          <Route path="/loginAd" element={<AdLogin />} />
          <Route path="/FacSignUp" element={<FacSignUp />} />
          <Route
            path="/FacSignUp/FacDetail/:userName"
            element={<FacDetails />}
          />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
};

export default App;
