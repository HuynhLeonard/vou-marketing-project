import React, { useEffect, useState } from "react";
import "./App.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";
import GameManagementPage from "./pages/GameManagementPage.jsx";
import AdminHomePage from "./pages/AdminHomePage.jsx";
import StatisticPage from "./pages/StatisticPage.jsx";
import UserManagementPage from "./pages/UserManagementPage.jsx";
import EventManagement from "./pages/EventManagement.jsx";
import TestLogin from "./pages/TestLogin.jsx";
import SignInPage from "./pages/SignInPage.jsx";
import SignUpPage from "./pages/SignUpPage.jsx";
import EditAdminPage from "./pages/EditAdminPage.jsx";
import BrandHomepage from "./pages/BrandHomepage.jsx";
import BrandEventManagementPage from "./pages/BrandEventManagementPage.jsx";
import EditBrandPage from "./pages/EditBrandPage.jsx";
import BrandEditEventPage from "./pages/BrandEditEventPage.jsx";
import AddNewEventPage from "./pages/AddNewEventPage.jsx";

function App() {
    return (
        <div class="bg-white h-screen">
            <BrowserRouter>
                <Routes>
                    <Route path="/admin" element={<AdminHomePage />} />
                    <Route path="/admin/userManagement" element={<UserManagementPage />} />
                    <Route path="/admin/eventManagement" element={<EventManagement />} />
                    <Route path="/admin/gameManagement" element={<GameManagementPage />} />
                    <Route path="/admin/statistic" element={<StatisticPage />} />
                    <Route path="/" element={<SignInPage />} />
                    <Route path="/signUp" element={<SignUpPage />} />
                    <Route path="/admin/editProfile" element={<EditAdminPage />} />
                    <Route path="/brand" element={<BrandHomepage />} />
                    <Route path="/brand/eventManagement" element={<BrandEventManagementPage />} />
                    <Route path="/brand/editProfile" element={<EditBrandPage />} />
                    <Route path="/brand/editEvent" element={<BrandEditEventPage />} />
                    <Route path="/brand/addEvent" element={<AddNewEventPage />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
