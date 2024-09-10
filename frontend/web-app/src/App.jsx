import React, { useEffect, useState } from "react";
import "./App.css";

import { BrowserRouter, Route, Routes } from "react-router-dom";
import GameManagementPage from "./pages/GameManagementPage.jsx";
import AdminHomePage from "./pages/AdminHomePage.jsx";
import StatisticPage from "./pages/StatisticPage.jsx";
import UserManagementPage from "./pages/UserManagementPage.jsx";
import EventManagement from "./pages/EventManagement.jsx";
import TestLogin from "./pages/TestLogin.jsx";
import SignUp from "./pages/SignUp.jsx";
import SignIn from "./pages/SignIn.jsx"

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
                    <Route path="/" element={<SignIn />} />
                    <Route path="/signUp" element={<SignUp />} />

                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
