import { Route, Routes } from "react-router-dom"
import Home from "../pages/Home"
import About from "../pages/About"
import Contact from "../pages/Contact"
import InternshipOpportunities from "../pages/InternshipOpportunities"
import Faqs from "../pages/Faqs"
import Testimonials from "../pages/Testimonials"
import StudentRegistration from "../components/StudentRegistration"
import PageNotFound from "../components/PageNotFound"
import Candidate from "../pages/login/Candidate"
import Admin from "../pages/login/Admin"
import Coordinator from "../pages/login/Coordinator"
import Mentor from "../pages/login/Mentor"
import MentorDashboard from '../pages/mentor/MentorDashboard';

const AppRoute = () => {
  return (
    <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/about" element={<About/>}/>
        <Route path="/contact" element={<Contact/>}/>
        <Route path="/intership-opportunities" element={<InternshipOpportunities/>}/>
        <Route path="/faqs" element={<Faqs/>}/>
        <Route path="/testimonials" element={<Testimonials/>}/>
        <Route path="/student-registration" element={<StudentRegistration/>}/>
        <Route path="/login/candidate" element={<Candidate/>}/>
        <Route path="/login/admin" element={<Admin/>}/>
        <Route path="/login/coordinator" element={<Coordinator/>}/>
        <Route path="/login/mentor" element={<Mentor/>}/>
        <Route path="/mentor-dashboard" element={<MentorDashboard />} />
        <Route path="*" element={<PageNotFound/>}/>

    </Routes>
  )
}

export default AppRoute