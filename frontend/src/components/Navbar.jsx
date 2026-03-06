import { useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import {
  Menu,
  X,
  ChevronDown,
  User,
  ShieldCheck,
  Briefcase,
  LayoutDashboard,
} from "lucide-react";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const dropdownRef = useRef(null);

  const navigate = useNavigate();
  const location = useLocation();

  const navLinks = [
    { name: "Home", path: "/" },
    { name: "About", path: "/about" },
    { name: "Internships", path: "/intership-opportunities" },
    { name: "Testimonials", path: "/testimonials" },
    { name: "FAQs", path: "/faqs" },
    { name: "Contact", path: "/contact" },
  ];

  const loginOptions = [
    { name: "Candidate", path: "/login/candidate", icon: <User size={18} /> },
    { name: "Admin", path: "/login/admin", icon: <ShieldCheck size={18} /> },
    { name: "Mentor", path: "/login/mentor", icon: <Briefcase size={18} /> },
    { name: "Coordinator", path: "/login/coordinator", icon: <LayoutDashboard size={18} /> },
  ];

  const handleNavigate = (path) => {
    navigate(path);
    setIsOpen(false);
    setIsLoginOpen(false);
  };

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsLoginOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  return (
    <header className="sticky top-0 z-50 w-full bg-white/95 backdrop-blur-md border-b border-gray-100 shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">

          {/* Logo */}
          <div
            className="flex-shrink-0 flex items-center cursor-pointer group"
            onClick={() => handleNavigate("/")}
          >
            <img
              src="/images/cdac-logo.png"
              alt="CDAC Logo"
              className="h-9 sm:h-10 w-auto transition-transform group-hover:scale-105"
            />
            <span className="ml-3 font-bold text-lg sm:text-xl text-blue-900 hidden lg:block">
              CDAC
            </span>
          </div>

          {/* Desktop Navigation */}
          <nav className="hidden md:flex items-center space-x-1">
            {navLinks.map((link) => (
              <button
                key={link.path}
                onClick={() => handleNavigate(link.path)}
                className={`px-3 py-2 rounded-md text-sm font-medium transition-colors ${
                  location.pathname === link.path
                    ? "text-blue-700 bg-blue-50"
                    : "text-gray-600 hover:text-blue-600 hover:bg-gray-50"
                }`}
              >
                {link.name}
              </button>
            ))}

            {/* Login Dropdown */}
            <div className="relative ml-4" ref={dropdownRef}>
              <button
                onClick={() => setIsLoginOpen(!isLoginOpen)}
                className="flex items-center gap-2 bg-blue-700 hover:bg-blue-800 text-white px-4 py-2 rounded-lg text-sm font-semibold transition-all shadow-md hover:shadow-lg active:scale-95"
              >
                Sign in
                <ChevronDown
                  size={16}
                  className={`transition-transform ${
                    isLoginOpen ? "rotate-180" : ""
                  }`}
                />
              </button>

              {isLoginOpen && (
                <div className="absolute right-0 mt-2 w-56 bg-white border border-gray-100 rounded-xl shadow-2xl py-2">
                  <p className="px-4 py-2 text-xs font-semibold text-gray-400 uppercase tracking-wider">
                    Select Role
                  </p>

                  {loginOptions.map((option) => (
                    <button
                      key={option.path}
                      onClick={() => handleNavigate(option.path)}
                      className="flex items-center gap-3 w-full px-4 py-2.5 text-sm text-gray-700 hover:bg-blue-50 hover:text-blue-700 transition-colors"
                    >
                      <span className="text-gray-400">
                        {option.icon}
                      </span>
                      {option.name}
                    </button>
                  ))}
                </div>
              )}
            </div>
          </nav>

          {/* Mobile Toggle Button */}
          <div className="md:hidden flex items-center">
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="p-2 rounded-md text-gray-700 hover:text-blue-700 hover:bg-gray-100"
            >
              {isOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile Menu */}
      <div
        className={`md:hidden overflow-hidden transition-all duration-300 ${
          isOpen ? "max-h-screen opacity-100" : "max-h-0 opacity-0"
        }`}
      >
        <div className="px-4 pt-2 pb-6 space-y-1 bg-white border-t border-gray-50 shadow-inner">
          {navLinks.map((link) => (
            <button
              key={link.path}
              onClick={() => handleNavigate(link.path)}
              className={`block w-full text-left px-4 py-3 rounded-lg text-base font-medium ${
                location.pathname === link.path
                  ? "bg-blue-50 text-blue-700"
                  : "text-gray-600 hover:bg-gray-50"
              }`}
            >
              {link.name}
            </button>
          ))}

          {/* Mobile Login Section */}
          <div className="pt-4 border-t border-gray-100 mt-4">
            <p className="px-4 text-xs font-semibold text-gray-400 mb-2">
              LOGIN ACCOUNTS
            </p>

            <div className="grid grid-cols-2 gap-2 px-2">
              {loginOptions.map((option) => (
                <button
                  key={option.path}
                  onClick={() => handleNavigate(option.path)}
                  className="flex flex-col items-center justify-center p-3 border border-gray-100 rounded-xl hover:bg-blue-50 hover:border-blue-100 transition-all"
                >
                  <span className="text-blue-600 mb-1">
                    {option.icon}
                  </span>
                  <span className="text-xs font-medium text-gray-700">
                    {option.name}
                  </span>
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Navbar;