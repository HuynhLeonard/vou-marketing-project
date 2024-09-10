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
import EditUserPage from "./pages/EditUserPage.jsx";
import ViewUserPage from "./pages/ViewUserPage.jsx";
import ViewNewUserPage from "./pages/ViewNewUserPage.jsx";
import RemoveUserPage from "./pages/RemoveUserPage.jsx";
import RemoveNewUserPage from "./pages/RemoveNewUserPage.jsx";

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
                    <Route path="/admin/userManagement/editProfile" element={<EditUserPage />} />
                    <Route path="/admin/userManagement/profile" element={<ViewUserPage />} />
                    <Route path="/admin/userManagement/newProfile" element={<ViewNewUserPage />} />
                    <Route
                        path="/admin/userManagement/removeProfile"
                        element={<RemoveUserPage />}
                    />
                    <Route
                        path="/admin/userManagement/removeNewProfile"
                        element={<RemoveNewUserPage />}
                    />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
