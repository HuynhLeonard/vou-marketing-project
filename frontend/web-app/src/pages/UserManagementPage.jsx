import React from "react";
import Navigation from "../Components/Navigation";
import UserManage from "../Components/UserManage";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const UserManagementPage = () => {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <UserManage />
        </div>
    );
};

export default UserManagementPage;
