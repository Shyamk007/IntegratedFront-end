import React from "react";
import {
  FaEnvelope,
  FaTwitter,
  FaInstagram,
  FaGithub,
  FaLinkedin,
  FaPhone,
  FaMobile,
} from "react-icons/fa";

const Footer = () => {
  return (
    <footer className="bg-blue-900 text-white py-10 mx-[5%] rounded-md  w-[90%]">
      <div className="max-w-screen-lg mx-auto flex flex-col items-center">
        <h1 className="text-4xl p-4">Project Diary</h1>
        <ul className="flex space-x-4 mb-4">
          <li>
            <a
              href="https://github.com/Shyamk007/Project"
              target="_blank"
              rel="noopener noreferrer"
              className="text-white hover:text-gray-500"
            >
              <FaGithub size={24} />
            </a>
          </li>
          <li>
            <a
              href="http://www.linkedin.com/in/shyamkarhale"
              target="_blank"
              rel="noopener noreferrer"
              className="text-white hover:text-gray-500"
            >
              <FaLinkedin size={24} />
            </a>
          </li>
          <li>
            <a
              href="tel:+919765679425"
              target="_blank"
              rel="noopener noreferrer"
              className="text-white hover:text-gray-500"
            >
              <FaMobile size={24} />
            </a>
          </li>
          <li>
            <a
              href="mailto:teamprojectdiary@gmail.com"
              target="_blank"
              rel="noopener noreferrer"
              className="text-white hover:text-gray-500"
            >
              <FaEnvelope size={24} />
            </a>
          </li>
        </ul>
        <p className="text-sm">
          &copy; 2023 Project Diary. All rights reserved.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
